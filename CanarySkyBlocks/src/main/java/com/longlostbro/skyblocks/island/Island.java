package com.longlostbro.skyblocks.island;

import java.util.ArrayList;
import java.util.List;

import net.canarymod.Canary;
import net.canarymod.ToolBox;
import net.canarymod.api.entity.EntityType;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.World;
import net.canarymod.api.world.position.Position;
import net.playblack.cuboids.impl.canarymod.CanaryWorld;
import net.playblack.cuboids.regions.Region;
import net.playblack.cuboids.regions.RegionManager;

import com.longlostbro.skyblocks.SkyBlocks;
import com.longlostbro.skyblocks.database.IslandData;
/** Represents an Island
 * 
 * @author longlostbro
 *
 */
public class Island {
	// Position of the Island
	private Position position;
	// Owner of the Island
	private Player owner;
	// Owner of the Island
	private int level;
	private long last_used;
	private long time_created;
	private IslandGuard guard;
	private int xp;
	private ArrayList<String> players = new ArrayList<String>();
	private ArrayList<String> Challenges_Complete = new ArrayList<String>();
	private World world;
	
	public Island(Player player){
		try {
			this.owner = player;
			this.last_used = (ToolBox.getUnixTimestamp());
			this.level = 1;
			this.xp = 0;
			this.world = Canary.getServer().getWorld("SkyWorld_NORMAL");
			this.position = (new IslandCreator().createPhysicalIsland(world, this.owner));
			IslandData.insertIslandData(player.getName(), new ArrayList<String>(), Challenges_Complete, this.position,this.level,this.last_used,this.xp);
			if(SkyBlocks.getInstance().config.getProtect_with_cuboids())
				this.guard = new IslandGuard(this);
			else
				this.guard = null;
		} catch (Exception e) {
			Canary.log.error("failed to create island-"+e.getMessage());
			e.printStackTrace();
		}
	}

	public Island(Player player, long x, long y, long z, int level, long last_used, long time_created, int xp) {
		this.owner = player;
		this.position = new Position(x,y,z);
		this.last_used = last_used;
		this.time_created = time_created;
		this.level = level;
		this.xp = xp;
	}
	public IslandGuard getGuard(){
		return guard;
	}
	public int getLevel(){
		return this.level;
	}
	
	public void addXP(int xp){
		this.xp += xp;
		IslandData.setXP(this.xp, this.owner.getName());
		int required = SkyBlocks.getInstance().config.getXP_per_level();
		this.level = (this.xp/required)+1;
	}

	public World getWorld(){
		return this.world;
	}
	public static Island getIsland(String playerName){
		IslandData id = IslandData.getIslandData(playerName);
		if(id != null){
			int level = 1+(id.xp/SkyBlocks.getInstance().config.getXP_per_level());
			Player player = Canary.getServer().getPlayer(playerName);
			Island island = null;
			if(player != null){
				island = new Island(player, id.x, id.y, id.z, level, id.last_used, id.time_created, id.xp);
			}
			else if(playerName == "LASTISLAND"){
				island = new Island(player, id.x, id.y, id.z, level, id.last_used, id.time_created, id.xp);
			}
				
			return island;
		}
		else
			return null;
	}
	public void setPlayers(ArrayList<String> players){
		this.players = players;
	}
	public void addPlayer(Player player){
		players.add(player.getName());
		if(this.guard == null){
			Region region = RegionManager.get().getRegionByName("island_"+player.getName(), new CanaryWorld(player.getWorld()).getName(), new CanaryWorld(player.getWorld()).getDimension());
			region.addPlayer(player.getName());
			RegionManager.get().updateRegion(region);
		}
		else
			this.guard.addPlayer(player);
		IslandData.setPlayers(players, owner.getName());
	}
	public Player[] getPlayers(){
		return players.toArray(new Player[players.size()]);
	}
	public Position getPosition() {
		return this.position;
	}
	public Player getOwner(){
		return this.owner;
	}

	public long getTime_created() {
		return time_created;
	}

	public void setPosition(Position v) {
		IslandData id = null;
		if(this.owner != null){
			id = IslandData.getIslandData(this.owner.getName());
			id.setPosition(v);
		}
		else
			Canary.log.error("no such player to set island position");
		
	}
	

}
