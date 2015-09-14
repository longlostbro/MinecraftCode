package com.longlostbro.skyblocks.command;

import net.canarymod.chat.MessageReceiver;
/** Basic interface for commands
 * 
 * @author longlostbro
 *
 */
public interface Command {

	public void Execute(MessageReceiver caller, String[] parameters);
}
