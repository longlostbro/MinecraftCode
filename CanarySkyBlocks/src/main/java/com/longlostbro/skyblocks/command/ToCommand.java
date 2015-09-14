package com.longlostbro.skyblocks.command;

import net.canarymod.Canary;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.commands.warp.SpawnCommand;
import net.canarymod.commandsys.commands.warp.WarpUse;
/** Warp to a warp or Spawn to a world
 * 
 * @author longlostbro
 *
 */
public class ToCommand implements Command {

	public ToCommand() {
		// TODO Auto-generated constructor stub
	}

	public void Execute(MessageReceiver caller, String[] parameters) {
		for(String world : Canary.getServer().getWorldManager().getLoadedWorldsNames()){
			if(world.contains(parameters[0])){
		        new SpawnCommand().execute(caller, parameters);
				return;
			}
		}
		new WarpUse().execute(caller,parameters);

	}

}
