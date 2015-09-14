package com.longlostbro.skyblocks.utils;

import java.util.HashMap;
import java.util.Map.Entry;

import com.longlostbro.skyblocks.SkyBlocks;
import com.longlostbro.skyblocks.island.Island;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.position.Position;

public class IslandParser {

	public HashMap<Integer,Integer> blocks = new HashMap<Integer,Integer>();
	public Island island;
	public Player player;
	public int range;
	
	public IslandParser(Player player){
		this.island = Island.getIsland(player.getName());
		this.player = player;
		this.range = SkyBlocks.getInstance().config.getIsland_protectionRange();
		parseIsland();
	}

	private void parseIsland() {
		Position pos = island.getPosition();
		int posx = (int) Math.round(pos.getX());
		int posy = (int) Math.round(pos.getY());
		int posz = (int) Math.round(pos.getZ());
		for (int x = -range; x <= range; x++) {
			for (int y = -range; y <= range; y++) {
				for (int z = -range; z <= range; z++) {
					final Block b = island.getOwner().getWorld().getBlockAt(posx+x,posy+y,posz+z);
					try{
						int count = blocks.get(b.getTypeId());
						blocks.put((int) b.getTypeId(), ++count);
					}
					catch(Exception e){
						blocks.put((int) b.getTypeId(), 1);
					}
				}
			}
		}
		for(Entry<Integer, Integer> block : blocks.entrySet()){
			player.notice(block.getKey()+":"+block.getValue());
		}
	}
	
	public boolean hasBlock(Item block){
		try{
			int blockCount = blocks.get(block.getId());
			if(blockCount >= block.getAmount()){
				return true;
			}
		}
		catch(Exception e){
			
		}
		return false;
	}
	public boolean hasBlocks(Item[] blocks, boolean message){
		boolean hasBlocks = true;
		for(Item block : blocks){
			if(!hasBlock(block)){
				if(message)
					player.notice("You do not have "+block.getAmount()+" "+BlockType.fromId(block.getId()).getMachineName().substring(10).replace('_', ' '));
				hasBlocks = false;
			}
		}
		return hasBlocks;
	}
	
	
}
