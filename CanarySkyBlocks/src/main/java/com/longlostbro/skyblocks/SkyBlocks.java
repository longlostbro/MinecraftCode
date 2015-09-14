package com.longlostbro.skyblocks;


import java.io.File;
import com.longlostbro.skyblocks.challenge.ChallengeFile;
import com.longlostbro.skyblocks.challenge.Challenges;

import net.canarymod.Canary;
import net.canarymod.commandsys.CommandDependencyException;
import net.canarymod.config.Configuration;
import net.canarymod.logger.Logman;
import net.canarymod.plugin.Plugin;
/** Main Plugin Class
 * 
 * @author longlostbro
 *
 */
public class SkyBlocks extends Plugin {
	public static Logman logger;
	public SB_Config config;
	private static SkyBlocks instance;
	private static Challenges challenges;
	public static SB_World skyBlockWorld = null;
	/**
	 * 
	 * @return a SkyBlock instance
	 */
	public static SkyBlocks getInstance() {
		return instance;
	}
	public static Challenges getChallenges(){
		return challenges;
	}
	/** Initializes the logger and sets instance
	 * 
	 */
	public SkyBlocks(){
		instance = this;
		logger = getLogman();
	}
	/** Loads config, registers listeners and initializes SkyWorld
	 * 
	 */
	@Override
	public boolean enable() {

		File WD = Canary.getWorkingDirectory();
		File CD = new File(WD.getAbsolutePath()+File.separator+"config");
		File SBCD = new File(CD.getAbsolutePath()+File.separator+"SkyBlocks");
		File CF = new File(SBCD.getAbsolutePath()+File.separator+"SkyBlocks.cfg");
		if(!CF.exists()){
			config = new SB_Config(CF, true);
		}
		else{
			if(Configuration.getPluginConfig(this).getBoolean("reset")){
				config = new SB_Config(CF, true);
			}
			else
				config = new SB_Config(CF,false);
		}
		File CL = new File(SBCD.getAbsolutePath() + File.separator+"Challenges.yml");
		if(!CL.exists()){
			new ChallengeFile(CL,true);
		}
		challenges = new Challenges();
		try {
			Canary.hooks().registerListener(new SB_PluginListener(), this);
			Canary.commands().registerCommands(new SB_CommandListener(), this, false);
			logger.info("Enabling "+getName() + " Version " + getVersion());
			logger.info("Authored by "+getAuthor());
			new SB_World();
			logger.info("Enabled");
		} catch (CommandDependencyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return true;
	}
	/** Disables the plugin
	 * 
	 */
	@Override
	public void disable() {
        logger.info("Disabled");
	}
}
