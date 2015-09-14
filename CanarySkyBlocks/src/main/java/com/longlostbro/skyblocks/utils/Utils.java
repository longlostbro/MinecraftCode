package com.longlostbro.skyblocks.utils;

import net.canarymod.Canary;
import net.canarymod.api.inventory.Item;

public class Utils {

	public Utils(){
		
	}

	public static Item[] stringToItem(String IDAMNT, String delimiter){
		String[] ItemStringArray = IDAMNT.split(delimiter);
		Item[] Items = new Item[ItemStringArray.length];
		int i = 0;
		for(String itemString : ItemStringArray){
			String[] splitItem = itemString.split(":");
			int id = Integer.parseInt(splitItem[0]);
			int amount = Integer.parseInt(splitItem[1]);
			Items[i++] = Canary.factory().getItemFactory().newItem(id,0,amount);
		}
		return Items;
	}
}
