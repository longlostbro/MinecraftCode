package com.longlostbro.skyblocks;

import com.longlostbro.skyblocks.command.ChallengeCommand;
import com.longlostbro.skyblocks.command.GMCommand;
import com.longlostbro.skyblocks.command.IslandCommand;
import com.longlostbro.skyblocks.command.ListWorldsCommand;
import com.longlostbro.skyblocks.command.ToCommand;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.Command;
import net.canarymod.commandsys.CommandListener;

/** Contains all commands listened for
 * 
 * @author longlostbro
 *
 */
public class SB_CommandListener implements CommandListener {
/** Listens for "/skyblock" command
 * 
 * @param caller
 * @param parameters
 */
	@Command(aliases = { "island" },
            description = "create or teleport to your Island",
            permissions = { "skyblocks.island", "skyblocks.*" },
            toolTip = "/island [remove]",// <player> [reason] [#number hour|day|week|month]",
            min = 1)
    public void islandCommand(MessageReceiver caller, String[] parameters) {
	    new IslandCommand().Execute(caller, parameters);
    }
	@Command(aliases = { "challenge" },
            description = "challenge challengename",
            permissions = { "skyblocks.challenge", "skyblocks.*" },
            toolTip = "/challenge [complete <challenge_name>]",// <player> [reason] [#number hour|day|week|month]",
            min = 1)
    public void challengeCommand(MessageReceiver caller, String[] parameters) {
	    new ChallengeCommand().Execute(caller, parameters);
    }
	/** Listens for "/worlds" command
	 * 
	 * @param caller
	 * @param parameters
	 */
	@Command(aliases = { "worlds" },
            description = "list worlds",
            permissions = { "skyblocks.worlds", "skyblocks.*" },
            toolTip = "/worlds",// <player> [reason] [#number hour|day|week|month]",
            min = 0)
    public void worldsCommand(MessageReceiver caller, String[] parameters) {
		new ListWorldsCommand().Execute(caller);
    }

	/** Listens for "/to" command
	 * 
	 * @param caller
	 * @param parameters
	 */
	@Command(aliases = { "to" },
            description = "teleport to world",
            permissions = { "skyblocks.to", "skyblocks.*" },
            toolTip = "/to <world>",// <player> [reason] [#number hour|day|week|month]",
            min = 0)
    public void toCommand(MessageReceiver caller, String[] parameters) {
		new ToCommand().Execute(caller,parameters);
    }
	/** Listens for "/gm" command
	 * 
	 * @param caller
	 * @param parameters
	 */
	@Command(aliases = { "gm" },
            description = "change gamemode",
            permissions = { "skyblocks.gamemode", "skyblocks.*" },
            toolTip = "/gm <0|1>",// <player> [reason] [#number hour|day|week|month]",
            min = 1)
    public void GMCommand(MessageReceiver caller, String[] parameters) {
		new GMCommand().Execute(caller, parameters);
    }

}
