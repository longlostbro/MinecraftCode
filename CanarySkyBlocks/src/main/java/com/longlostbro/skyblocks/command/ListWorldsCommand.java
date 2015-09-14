package com.longlostbro.skyblocks.command;

import net.canarymod.Canary;
import net.canarymod.chat.MessageReceiver;
/** Lists existing worlds
 * 
 * @author longlostbro
 *
 */
public class ListWorldsCommand {
	
	public void Execute(MessageReceiver caller){
		//new BanCommand().execute(caller, parameters);
				StringBuilder worldList = new StringBuilder();
				for(net.canarymod.api.world.World world: Canary.getServer().getWorldManager().getAllWorlds()){
					worldList.append(world.getName()+" Loaded: "+Canary.getServer().getWorldManager().worldIsLoaded(world.getFqName())+"\n");
				}
				caller.message(worldList.toString());
	}
}
