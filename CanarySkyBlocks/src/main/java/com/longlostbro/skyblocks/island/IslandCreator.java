package com.longlostbro.skyblocks.island;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import net.canarymod.Canary;
import net.canarymod.ToolBox;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.World;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.blocks.Chest;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Position;
import com.longlostbro.skyblocks.SB_Config;
import com.longlostbro.skyblocks.SkyBlocks;
import com.longlostbro.skyblocks.database.IslandData;

/** Contains methods for creating an island
 * 
 * @author longlostbro
 *
 */
public class IslandCreator {
	private boolean UseWorldEdit = false;
	public IslandCreator() {
		// TODO Auto-generated constructor stub
	}
	
	public Position createPhysicalIsland(World world, Player player) throws FileNotFoundException, IOException{
		Position center = nextIslandLocation();
		player.teleportTo(new Location(world, center.getBlockX(),4, center.getBlockZ(), 0, 0));
		if(UseWorldEdit)
			pasteIslandBlocks(center,Canary.getServer().getWorld("SkyWorld_NORMAL"));
		else{
			generateIslandBlocks(center.getBlockX(), center.getBlockZ(),Canary.getServer().getWorld("SkyWorld_NORMAL"));
		}
		Canary.getServer().getPlayer(player.getName()).teleportTo(new Location(world, center.getX()-1, center.getY(), center.getZ(), 0, 0));
		Chest chest = getChest(center,player);
		Item[] items = SkyBlocks.getInstance().config.getChest_Items();
		for(Item item : items){
			chest.addItem(item);
		}
		
		
		return center;
	}
	private void pasteIslandBlocks(final Position center, World world) {
		//new WorldEditHandler().pasteSchematic(new CanaryWorld(world), center, "schematics/island.schematic");
	}

	public Chest getChest(Position center, Player player){
		SB_Config Settings = SkyBlocks.getInstance().config;
		for (int x = center.getBlockX() - (Settings.getIsland_protectionRange() / 2); x <= center.getBlockX() + (Settings.getIsland_protectionRange() / 2); x++) {
			for (int z = center.getBlockZ() - (Settings.getIsland_protectionRange() / 2); z <= center.getBlockZ() + (Settings.getIsland_protectionRange() / 2); z++) {
				for (int y = 50; y <= 255; y++) {
					Block block = Canary.getServer().getPlayer(player.getName()).getWorld().getBlockAt(x, y, z);
					if(block.getType() == BlockType.Chest) {
						return (Chest)block.getTileEntity();
					}
				}
			}
		}
		return null;
	}
	/**
	 * 
	 * @return Returns a unique position at the config specified distance from the previously created island
	 */
	private Position nextIslandLocation() {
		IslandData lastIsland = IslandData.getIslandData("LASTISLAND");
		Position v;
		if(lastIsland != null){
			v = new Position(lastIsland.x,lastIsland.y,lastIsland.z);
        	//v = new Position(v.getX(),v.getY(),v.getY());
        	//lastIsland.setPosition(v);
		}
        else{
        	v = new Position(0,SkyBlocks.getInstance().getConfig().getInt("island_height"),0);
        	IslandData.insertIslandData("LASTISLAND", new ArrayList<String>(), new ArrayList<String>(), v, ToolBox.getUnixTimestamp(), ToolBox.getUnixTimestamp(),0);
        	return v;
        }
		final int x = (int) v.getX();
		final int z = (int) v.getZ();
		final Position nextPos = v;
		if (x < z) {
			if (-1 * x < z) {
				nextPos.setX(nextPos.getX() + SkyBlocks.getInstance().getConfig().getInt("island_distance"));
				return nextPos;
			}
			nextPos.setZ(nextPos.getZ() + SkyBlocks.getInstance().getConfig().getInt("island_distance"));
			return nextPos;
		}
		if (x > z) {
			if (-1 * x >= z) {
				nextPos.setX(nextPos.getX() - SkyBlocks.getInstance().getConfig().getInt("island_distance"));
				return nextPos;
			}
			nextPos.setZ(nextPos.getZ() - SkyBlocks.getInstance().getConfig().getInt("island_distance"));
			return nextPos;
		}
		if (x <= 0) {
			nextPos.setZ(nextPos.getZ() + SkyBlocks.getInstance().getConfig().getInt("island_distance"));
			return nextPos;
		}
		nextPos.setZ(nextPos.getZ() - SkyBlocks.getInstance().getConfig().getInt("island_distance"));
		return nextPos;
	}
	public void generateIslandBlocks(final int x, final int z, final World world) {
		final int y = SkyBlocks.getInstance().getConfig().getInt("island_height")-5;
		final Block blockToChange = world.getBlockAt(x, y, z);
		blockToChange.setType(BlockType.Bedrock);
		blockToChange.update();
		islandLayer1(x, z, world);
		islandLayer2(x, z, world);
		islandLayer3(x, z, world);
		islandLayer4(x, z, world);
		islandExtras(x, z, world);
	}
	private void islandExtras(final int x, final int z, final World world) {
		int y = SkyBlocks.getInstance().getConfig().getInt("island_height")-5;

		Block blockToChange = world.getBlockAt(x, y + 5, z);
		blockToChange.setType(BlockType.OakLog);
		blockToChange.update();
		blockToChange = world.getBlockAt(x, y + 6, z);
		blockToChange.setType(BlockType.OakLog);
		blockToChange.update();
		blockToChange = world.getBlockAt(x, y + 7, z);
		blockToChange.setType(BlockType.OakLog);
		blockToChange.update();
		y = SkyBlocks.getInstance().getConfig().getInt("island_height")-5 + 8;
		for (int x_operate = x - 2; x_operate <= x + 2; x_operate++) {
			for (int z_operate = z - 2; z_operate <= z + 2; z_operate++) {
				blockToChange = world.getBlockAt(x_operate, y, z_operate);
				blockToChange.setType(BlockType.OakLeaves);
			}
		}
		blockToChange = world.getBlockAt(x + 2, y, z + 2);
		blockToChange.setType(BlockType.Air);
		blockToChange.update();
		blockToChange = world.getBlockAt(x + 2, y, z - 2);
		blockToChange.setType(BlockType.Air);
		blockToChange.update();
		blockToChange = world.getBlockAt(x - 2, y, z + 2);
		blockToChange.setType(BlockType.Air);
		blockToChange.update();
		blockToChange = world.getBlockAt(x - 2, y, z - 2);
		blockToChange.setType(BlockType.Air);
		blockToChange.update();
		blockToChange = world.getBlockAt(x, y, z);
		blockToChange.setType(BlockType.OakLog);
		blockToChange.update();
		y = SkyBlocks.getInstance().getConfig().getInt("island_height")-5 + 9;
		for (int x_operate = x - 1; x_operate <= x + 1; x_operate++) {
			for (int z_operate = z - 1; z_operate <= z + 1; z_operate++) {
				blockToChange = world.getBlockAt(x_operate, y, z_operate);
				blockToChange.setType(BlockType.OakLeaves);
				blockToChange.update();
			}
		}
		blockToChange = world.getBlockAt(x - 2, y, z);
		blockToChange.setType(BlockType.OakLeaves);
		blockToChange.update();
		blockToChange = world.getBlockAt(x + 2, y, z);
		blockToChange.setType(BlockType.OakLeaves);
		blockToChange.update();
		blockToChange = world.getBlockAt(x, y, z - 2);
		blockToChange.setType(BlockType.OakLeaves);
		blockToChange.update();
		blockToChange = world.getBlockAt(x, y, z + 2);
		blockToChange.setType(BlockType.OakLeaves);
		blockToChange.update();
		blockToChange = world.getBlockAt(x, y, z);
		blockToChange.setType(BlockType.OakLog);
		blockToChange.update();
		y = SkyBlocks.getInstance().getConfig().getInt("island_height")-5 + 10;
		blockToChange = world.getBlockAt(x - 1, y, z);
		blockToChange.setType(BlockType.OakLeaves);
		blockToChange.update();
		blockToChange = world.getBlockAt(x + 1, y, z);
		blockToChange.setType(BlockType.OakLeaves);
		blockToChange.update();
		blockToChange = world.getBlockAt(x, y, z - 1);
		blockToChange.setType(BlockType.OakLeaves);
		blockToChange.update();
		blockToChange = world.getBlockAt(x, y, z + 1);
		blockToChange.setType(BlockType.OakLeaves);
		blockToChange.update();
		blockToChange = world.getBlockAt(x, y, z);
		blockToChange.setType(BlockType.OakLog);
		blockToChange.update();
		blockToChange = world.getBlockAt(x, y + 1, z);
		blockToChange.setType(BlockType.OakLeaves);
		blockToChange.update();

		blockToChange = world.getBlockAt(x, SkyBlocks.getInstance().getConfig().getInt("island_height")-5 + 5, z - 1);
		blockToChange.setType(BlockType.Chest);
		blockToChange.update();
	}

	private void islandLayer1(final int x, final int z, final World world) {
		int y = SkyBlocks.getInstance().getConfig().getInt("island_height")-5;
		y = y + 4;
		for (int x_operate = x - 3; x_operate <= x + 3; x_operate++) {
			for (int z_operate = z - 3; z_operate <= z + 3; z_operate++) {
				final Block blockToChange = world.getBlockAt(x_operate, y, z_operate);
				blockToChange.setType(BlockType.Grass);
				blockToChange.update();
			}
		}
		Block blockToChange = world.getBlockAt(x - 3, y, z + 3);
		blockToChange.setType(BlockType.Air);
		blockToChange.update();
		blockToChange = world.getBlockAt(x - 3, y, z - 3);
		blockToChange.setType(BlockType.Air);
		blockToChange.update();
		blockToChange = world.getBlockAt(x + 3, y, z - 3);
		blockToChange.setType(BlockType.Air);
		blockToChange.update();
		blockToChange = world.getBlockAt(x + 3, y, z + 3);
		blockToChange.setType(BlockType.Air);
		blockToChange.update();
	}

	private void islandLayer2(final int x, final int z, final World world) {
		int y = SkyBlocks.getInstance().getConfig().getInt("island_height")-5;
		y = y + 3;
		for (int x_operate = x - 2; x_operate <= x + 2; x_operate++) {
			for (int z_operate = z - 2; z_operate <= z + 2; z_operate++) {
				final Block blockToChange = world.getBlockAt(x_operate, y, z_operate);
				blockToChange.setType(BlockType.Dirt);
				blockToChange.update();
			}
		}
		Block blockToChange = world.getBlockAt(x - 3, y, z);
		blockToChange.setType(BlockType.Dirt);
		blockToChange.update();
		blockToChange = world.getBlockAt(x + 3, y, z);
		blockToChange.setType(BlockType.Dirt);
		blockToChange.update();
		blockToChange = world.getBlockAt(x, y, z - 3);
		blockToChange.setType(BlockType.Dirt);
		blockToChange.update();
		blockToChange = world.getBlockAt(x, y, z + 3);
		blockToChange.setType(BlockType.Dirt);
		blockToChange.update();
		blockToChange = world.getBlockAt(x, y, z);
		blockToChange.setType(BlockType.Sand);
		blockToChange.update();
	}

	private void islandLayer3(final int x, final int z, final World world) {
		int y = SkyBlocks.getInstance().getConfig().getInt("island_height")-5;
		y = y + 2;
		for (int x_operate = x - 1; x_operate <= x + 1; x_operate++) {
			for (int z_operate = z - 1; z_operate <= z + 1; z_operate++) {
				final Block blockToChange = world.getBlockAt(x_operate, y, z_operate);
				blockToChange.setType(BlockType.Dirt);
				blockToChange.update();
			}
		}
		Block blockToChange = world.getBlockAt(x - 2, y, z);
		blockToChange.setType(BlockType.Dirt);
		blockToChange.update();
		blockToChange = world.getBlockAt(x + 2, y, z);
		blockToChange.setType(BlockType.Dirt);
		blockToChange.update();
		blockToChange = world.getBlockAt(x, y, z - 2);
		blockToChange.setType(BlockType.Dirt);
		blockToChange.update();
		blockToChange = world.getBlockAt(x, y, z + 2);
		blockToChange.setType(BlockType.Dirt);
		blockToChange.update();
		blockToChange = world.getBlockAt(x, y, z);
		blockToChange.setType(BlockType.Sand);
		blockToChange.update();
	}

	private void islandLayer4(final int x, final int z, final World world) {
		int y = SkyBlocks.getInstance().getConfig().getInt("island_height")-5;
		y = y + 1;
		Block blockToChange = world.getBlockAt(x - 1, y, z);
		blockToChange.setType(BlockType.Dirt);
		blockToChange.update();
		blockToChange = world.getBlockAt(x + 1, y, z);
		blockToChange.setType(BlockType.Dirt);
		blockToChange.update();
		blockToChange = world.getBlockAt(x, y, z - 1);
		blockToChange.setType(BlockType.Dirt);
		blockToChange.update();
		blockToChange = world.getBlockAt(x, y, z + 1);
		blockToChange.setType(BlockType.Dirt);
		blockToChange.update();
		blockToChange = world.getBlockAt(x, y, z);
		blockToChange.setType(BlockType.Sand);
		blockToChange.update();
	}
}
