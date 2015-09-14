package com.longlostbro.skyblocks.island;

import net.canarymod.Canary;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.position.Location;

import com.longlostbro.skyblocks.SkyBlocks;
import com.longlostbro.skyblocks.SB_Config;

/** Contains methods for destroying an island
 * 
 * @author longlostbro
 *
 */
public class IslandDestroyer {

	public IslandDestroyer() {
		// TODO Auto-generated constructor stub
	}
	/** Uses the center of the island and the config to iterate through protected area and set blocks to air
	 * 
	 * @param center Center of island
	 */
	public void destroy(Location center){
		try{
			SB_Config Settings = SkyBlocks.getInstance().config;
			//BlockIterator blocks = new BlockIterator(center,Settings.getIsland_protectionRange(),1);
			//blocks.
			for (int x = center.getBlockX() - (Settings.getIsland_protectionRange() / 2); x <= center.getBlockX() + (Settings.getIsland_protectionRange() / 2); x++) {
				for (int z = center.getBlockZ() - (Settings.getIsland_protectionRange() / 2); z <= center.getBlockZ() + (Settings.getIsland_protectionRange() / 2); z++) {
					for (int y = 50; y <= 255; y++) {
						Block block = center.getWorld().getBlockAt(x, y, z);
						if(block.isAir()) {
							//
						}
						else //if(block.getType() == BlockType.Chest || block.getType() == BlockType.TrappedChest || 
							//block.getType() == BlockType.Dispenser || block.getType() == BlockType.Dropper || 
							//block.getType() == BlockType.Furnace || block.getType() == BlockType.BrewingStand)
						{
							block.setType(BlockType.Air);
							block.update();
							//BlockState state = (Chest)block.getStatus();
							//((InventoryHolder)state).getInventory().clear();
						}
					}
				}
			}
		}
		catch(Exception ex){
			Canary.getServer().broadcastMessage(ex.getMessage());
		}
	}

}
