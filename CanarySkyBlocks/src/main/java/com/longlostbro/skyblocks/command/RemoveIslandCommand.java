package com.longlostbro.skyblocks.command;

import com.longlostbro.skyblocks.database.IslandData;
import com.longlostbro.skyblocks.island.Island;
import com.longlostbro.skyblocks.island.IslandGuard;
import com.longlostbro.skyblocks.island.IslandDestroyer;
import net.canarymod.Canary;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Position;
import net.canarymod.chat.MessageReceiver;

/** Removes an island
 * 
 * @author longlostbro
 *
 */
public class RemoveIslandCommand implements Command {

	public RemoveIslandCommand() {
		// TODO Auto-generated constructor stub
	}

	/** Sets the blocks within their protected range to air and removes player data
	 * 
	 * @param caller Person by which the call was executed
	 */
	public void Execute(MessageReceiver caller) {
		Island island = Island.getIsland(caller.getName());
		if(island != null){
			Position position = island.getPosition();
			Location islandCenter = new Location(Canary.getServer().getWorld("SkyWorld_NORMAL"),position.getX(),position.getY(),position.getZ(), 0, 0);
	        new IslandDestroyer().destroy(islandCenter);
	        IslandData.removeIslandData(caller.getName());
	        IslandGuard IG = island.getGuard();
	        if(IG != null)
	        	IG.removeGuard();
	        caller.notice("Hope to see you back soon!");
		}
		else
			caller.notice("There is no island owned by "+caller.getName());

	}
	public void Execute(MessageReceiver caller, String[] parameters) {
		// TODO Auto-generated method stub
		
	}

}
