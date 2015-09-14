package com.longlostbro.skyblocks.island;

import com.longlostbro.skyblocks.SkyBlocks;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.position.Position;
import net.playblack.cuboids.InvalidPlayerException;
import net.playblack.cuboids.gameinterface.CPlayer;
import net.playblack.cuboids.gameinterface.CServer;
import net.playblack.cuboids.impl.canarymod.CanaryPlayer;
import net.playblack.cuboids.regions.Region;
import net.playblack.cuboids.regions.RegionManager;
import net.playblack.cuboids.selections.CuboidSelection;
import net.playblack.mcutils.Vector;

public class IslandGuard {

	private Region region;
	private Player player;
	
	public IslandGuard(Island island) {
		this.player = island.getOwner();
		CanaryPlayer player = new CanaryPlayer(this.player);
		Position pos = island.getPosition();
		int prange = SkyBlocks.getInstance().config.getIsland_protectionRange();
		Vector origin = new Vector(pos.getX()-prange,pos.getY()-prange,pos.getZ()-prange);
		Vector offset = new Vector(pos.getX()+prange,pos.getY()+prange,pos.getZ()+prange);
		CuboidSelection selection = new CuboidSelection(origin, offset);

        Region r;
        try {
        	CPlayer cplayer = CServer.getServer().getPlayer(player.getName());
            r = selection.toRegion(cplayer, new String[] {"o:"+player.getName(), "island_"+player.getName()});
            r.setProperty("creeper-explosion", Region.Status.DENY);
            r.setProperty("mob-damage", Region.Status.DENY);
            r.setProperty("mob-spawn", Region.Status.DENY);
            r.setProperty("pvp-damage", Region.Status.DENY);
            r.setProperty("enter-cuboid", Region.Status.DENY);
            r.setWorld(cplayer.getWorld().getName());
            r.setDimension(cplayer.getWorld().getDimension());
            r.setWelcome("Welcome to "+cplayer.getName()+"'s island!");
            r.setFarewell("You have left"+cplayer.getName()+"'s island!");
    		r.addPlayer(player.getName());
            
            RegionManager.get().addRegion(r);
            this.region = r;
        }
        catch (InvalidPlayerException e) {
            Canary.log.error("error protecting island");
        }
	}
	public Region getRegion(){
		return region;
	}
	public void removeGuard(){
		if(this.region != null){
			RegionManager.get().removeRegion(this.region);
			RegionManager.get().load();
		}
		else{
			Canary.log.error("The region doesn't exist.");
		}
		
	}
	public void addPlayer(Player player) {
		removeGuard();
		region.addPlayer(player.getName());
		RegionManager.get().addRegion(region);
		RegionManager.get().load();
	}
	public void removePlayer(Player player){
		removeGuard();
		region.removePlayer(player.getName());
		RegionManager.get().addRegion(region);
		RegionManager.get().updateRegion(region);
		RegionManager.get().load();		
	}

}
