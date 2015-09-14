package com.longlostbro.skyblocks.command;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
/** alias for gamemode
 * 
 * @author longlostbro
 *
 */
public class GMCommand implements Command {

	public GMCommand() {
		// TODO Auto-generated constructor stub
	}

	public void Execute(MessageReceiver caller, String[] parameters) {
		((Player)caller).setModeId(Integer.parseInt(parameters[1]));
	}

	/** alias for gamemode command, switches the gamemode of the player to the specified game mode
	 * 
	 * @param caller Person by which the call was executed
	 * @param parameters <0|1> does not accept gamemode names
	 */

}
