package com.longlostbro.skyblocks;

import net.canarymod.Canary;
import net.canarymod.api.world.DimensionType;
import net.canarymod.api.world.WorldManager;
import net.canarymod.api.world.WorldType;
import net.canarymod.config.WorldConfiguration;
import net.visualillusionsent.utils.PropertiesFile;
/** Contains methods for a world
 * 
 * @author longlostbro
 *
 */
public class SB_World {
	private WorldManager worlds;
	/** Checks if SkyWorld exists
	 * 
	 */
	public SB_World() {
		worlds = Canary.getServer().getWorldManager();
		if(worlds.worldExists("SkyWorld_NORMAL")){
			SkyBlocks.logger.info("World exists");			
		}
		else{
			SkyBlocks.logger.info("World being created");
			createWorld();
			SkyBlocks.logger.info("World created");
		}
	}
	/** Creates a flat world named "SkyWorld"
	 * 
	 */
	private void createWorld(){
		WorldConfiguration config = WorldConfiguration.create("SkyWorld", DimensionType.NORMAL);
		PropertiesFile worldconfig = config.getFile();
		worldconfig.setString("world-type", WorldType.SUPERFLAT.toString());
		worldconfig.setBoolean("generate-structures", false);
		worldconfig.setBoolean("startup-autoload", true);
		worldconfig.setBoolean("generate-structures", false);
		worldconfig.setBoolean("startup-autoload", true);
		worldconfig.save();
		worlds.createWorld(config);
		//SkyWorld = worlds.getWorld("SkyWorld_NORMAL", true);
	}

}
