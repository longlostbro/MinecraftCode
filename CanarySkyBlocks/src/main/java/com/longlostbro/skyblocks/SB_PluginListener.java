package com.longlostbro.skyblocks;

import java.util.Arrays;
import net.canarymod.hook.HookHandler;
import net.canarymod.hook.player.ConnectionHook;
import net.canarymod.hook.world.ChunkCreationHook;
import net.canarymod.plugin.PluginListener;

/** Contains all hooks listened for
 * 
 * @author longlostbro
 *
 */
public class SB_PluginListener implements PluginListener {
	/** Prevents chunk creation (aka cleanroom generator)
	 * 
	 * @param hook
	 */
	@HookHandler
    public void onChunkCreation(ChunkCreationHook hook) {
		if(hook.getWorld().getName().equals("SkyWorld")){
			int[] blockData = new int[65536]; 
			Arrays.fill(blockData, 0);
			hook.setBlockData(blockData);
		}
    }
	
	@HookHandler
	public void onLogin(ConnectionHook hook) {
	}
}

