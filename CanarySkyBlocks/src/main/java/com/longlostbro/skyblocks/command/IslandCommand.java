package com.longlostbro.skyblocks.command;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.position.Position;
import net.canarymod.chat.MessageReceiver;

import com.longlostbro.skyblocks.island.Island;
/** Main command for Creating or Teleporting to an island
 * 
 * @author longlostbro
 *
 */
public class IslandCommand {

	public IslandCommand() {
		// TODO Auto-generated constructor stub
	}

	/** Teleports user to their island and creates island if it doesn't exist
	 * 
	 * @param caller Person by which the call was executed
	 * @param parameters none
	 */
	public void Execute(MessageReceiver caller, String[] parameters){
		if(parameters.length == 1){
			Island island = Island.getIsland(caller.getName());
			Position pos;
			if(island != null){
				 pos = island.getPosition();
				((Player)caller).teleportTo(pos.getX()-1,pos.getY(),pos.getZ(), Canary.getServer().getWorld("SkyWorld_NORMAL"));
			}
			else {
				island = new Island((Player)caller);
				pos = island.getPosition();
			}
					}
		else if(parameters.length == 2){
			if(parameters[1].equals("remove"))
				new RemoveIslandCommand().Execute(caller);
		}
		else if(parameters.length == 3){
			if(parameters[1].equals("join")){
				caller.notice("Not yet implemented");
				/*Player owner = Canary.getServer().getPlayer(parameters[2]);
				if(owner != null){
					Island owners = Island.getIsland(owner.getName());
					Player joiner = (Player)caller;
					owners.addPlayer(joiner);
					joiner.teleportTo(owners.getPosition());
					new RemoveIslandCommand().Execute(caller);
				}*/
			}
		}
	}

}
