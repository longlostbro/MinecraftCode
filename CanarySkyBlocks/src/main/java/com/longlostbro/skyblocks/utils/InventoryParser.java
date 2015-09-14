package com.longlostbro.skyblocks.utils;

import java.util.ArrayList;
import java.util.HashMap;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;

public class InventoryParser {
	
	public HashMap<ItemType,ArrayList<Item>> items = new HashMap<ItemType,ArrayList<Item>>();
	public Inventory inventory;
	public Player player;
	
	public InventoryParser(Player player){
		this.inventory = player.getInventory();
		this.player = player;
		parseInventory();
	}
	
	private void parseInventory(){
		for(Item item : inventory.getContents()){
			if(item != null){
				try{
					items.get(item.getType()).add(item);
				}
				catch(Exception e){
					items.put(item.getType(), new ArrayList<Item>());
					items.get(item.getType()).add(item);
				}
			}
		}
	}
	
	public boolean hasItem(Item item){
		ItemType type = item.getType();
		int amount = item.getAmount();
		boolean found = false;
		try{
			for(Item currentItem : items.get(type)){
				amount -=currentItem.getAmount();
				if(amount <= 0){
					found = true;
					break;
				}
			}
		}
		catch(Exception e){
			
		}
		return found;
	}
	public boolean hasItems(Item[] items, boolean message){
		boolean hasItems = true;
		for(Item item : items){
			if(!hasItem(item)){
				if(message)
					player.notice("You do not have "+item.getAmount()+" "+item.getDisplayName());
				hasItems = false;
			}
		}
		return hasItems;
	}
	
	public boolean removeItem(Item item){
		ItemType type = item.getType();
		int RemoveAmount = item.getAmount();
		boolean removed = false;
		for(Item currentItem : items.get(type)){			
			if(currentItem.getAmount() <= RemoveAmount){
				RemoveAmount -=currentItem.getAmount();
				inventory.removeItem(currentItem);
			}
			else{
				currentItem.setAmount(currentItem.getAmount()-RemoveAmount);
				RemoveAmount = 0;
			}
				
			if(RemoveAmount == 0){
				removed = true;
				break;
			}
		}
		return removed;
	}
	
	public boolean removeItems(Item[] items){
		boolean removedAll = true;
		for(Item item : items){
			if(!removeItem(item)){
				Canary.log.error("Tried to remove an item that didn't exist from player "+player.getName());
				removedAll = false;
			}
		}
		return removedAll;
	}
}
