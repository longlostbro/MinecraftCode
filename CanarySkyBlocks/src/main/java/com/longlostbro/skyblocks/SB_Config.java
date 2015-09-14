package com.longlostbro.skyblocks;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import com.longlostbro.skyblocks.utils.Utils;

import net.canarymod.Canary;
import net.canarymod.api.inventory.Item;
import net.canarymod.config.Configuration;
import net.visualillusionsent.utils.PropertiesFile;
/** Contains methods for writing to and reading from config file
 * 
 * @author longlostbro
 *
 */
public class SB_Config {
    public static  PropertiesFile cfg;
	private static SB_Config instance;
    
	public SB_Config(File config, boolean create) {
		if(create){
	    	Canary.log.debug("creating config file");
			try {
				URL file = SkyBlocks.getInstance().getClass().getClassLoader().getResource("resources/SkyBlocks.cfg");
				FileUtils.copyURLToFile(file, config);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		cfg = Configuration.getPluginConfig(SkyBlocks.getInstance());
		instance = this;
	}
	public int getXP_per_level(){
		return cfg.getInt("xp_per_level");
	}
	public static SB_Config getInstance(){
		return instance;
	}
    
    public int getIsland_distance() {
        return cfg.getInt("island_distance");
    }

    public void setIsland_distance(int distance) {
        cfg.setInt("island_distance",distance);
        cfg.save();
    }
    
    public int getIsland_height() {
        return cfg.getInt("island_height");
    }
    
    public void setIsland_height(int height) {
        cfg.setInt("island_height",height);
        cfg.save();
    }
    
    public int getIsland_protectionRange() {
        return cfg.getInt("island_protectionRange");
    }
    
    public Item[] getChest_Items() {
    	String Items = cfg.getString("chest_items");
    	return Utils.stringToItem(Items, " ");
    }
    
    public void setIsland_protectionRange(int height) {
        cfg.setInt("island_protectionRange",height);
        cfg.save();
    }
    
    public boolean getProtect_with_cuboids() {
        return cfg.getBoolean("protect_with_cuboids");
    }
    public void setDefault(){
    	cfg.setBoolean("reset",false);
    	cfg.addComment("reset", "If you would like to reset the config to defaults");
    	cfg.setString("world_name","SkyWorld");
    	cfg.addComment("world_name", "The name of the world you would like the islands to be created in, default 'SkyWorld'(If world doesn't exists it is created)");
    	cfg.setBoolean("use_cleanroom_generator",true);
    	cfg.addComment("use_cleanroom_generator", "Whether the specified world should be empty(only works when creating a new world)");
    	cfg.setString("schematic_file","island.schematic");
    	cfg.addComment("schematic_file", "Path to custom island schematic file from which islands should be created, default island.schematic(path relative to [CanaryServer]/config/SkyBlocks)");
    	cfg.setInt("island_height",120);
    	cfg.addComment("island_height", "The height at which islands should be created [10-255] default '120'");
        cfg.setInt("island_distance",110);
    	cfg.addComment("island_distance", "The distance at which each island will be created from the previous island, default '100'");
        cfg.setInt("island_protectionRange", 30);
    	cfg.addComment("island_protectionRange", "The width at which the island should be protected with cuboids, default '30'.(should be at least the block width of the island)");
        cfg.setString("chest_items", "79:2 360:1 81:1 327:1 40:1 39:1 361:1 338:1 323:1");
    	cfg.addComment("chest_items", "The default items to be included in the chest on a new island, default '2 ice, 1 watermelon, 1 cactus, 1 lava bucket, 1 red & brown mushroom, 1 pumpkin seed, 1 sugar cane, 1 sign: '79:2 360:1 81:1 327:1 40:1 39:1 361:1 338:1 323:1'");
        cfg.setBoolean("protect_with_cuboids", true);
    	cfg.addComment("protect_with_cuboids", "Whether or not the islands should be protected with cuboids, default 'true'('false' if you don't have cuboids)");
        cfg.save();
    	
    }

}
