package com.longlostbro.skyblocks.challenge;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.Level;
import org.yaml.snakeyaml.Yaml;

import com.longlostbro.skyblocks.SB_Config;

import net.canarymod.Canary;
import net.canarymod.api.inventory.Item;
import net.canarymod.chat.MessageReceiver;


public class ChallengeParser {
	MessageReceiver caller;
	public ChallengeParser(MessageReceiver caller) {
		this.caller = caller;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<Challenge> getChallenges(){
		File configFile = new File(SB_Config.cfg.getFilePath());
		File ConfigDirectory = configFile.getParentFile();
		File ChallengesList = new File(ConfigDirectory.getAbsolutePath() + File.separator+"Challenges.yml");
		Yaml yaml = new Yaml();
		ArrayList<Challenge> challenges = new ArrayList<Challenge>();
		Map<String, Map<String, Map<String, Object>>> challengelist;
		try {
			challengelist = (Map<String, Map<String, Map<String, Object>>>) yaml.load(new FileInputStream(ChallengesList));
			Map<String, Map<String,Object>> Challenges = challengelist.get("challengeList");
			for(Entry<String, Map<String, Object>> ChallengeEntry : Challenges.entrySet()){
				Map<String,Object> cm = ChallengeEntry.getValue();
				Challenge challenge = new Challenge();
				challenge.setName(ChallengeEntry.getKey());
				challenge.setDescription((String)cm.get("description"));
				challenge.setRankLevel((String)cm.get("rankLevel"));
				challenge.setType((String)cm.get("type"));
				if(challenge.getType().equals("islandLevel")){
					challenge.setRequiredLevel((Integer)cm.get("requiredItems"));
				}
				else
					challenge.setRequiredItems(stringToItems((String)cm.get("requiredItems")));
				
				challenge.setTakeItems((Boolean)cm.get("takeItems"));
				
				challenge.setItemReward(stringToItems((String)cm.get("itemReward")));
				
				challenge.setPermissionReward((String)cm.get("permissionReward"));
				challenge.setRewardText((String)cm.get("rewardText"));
				challenge.setCurrencyReward((Integer)cm.get("currencyReward"));
				challenge.setXpReward((Integer)cm.get("xpReward"));
				challenge.setRepeatable((Boolean)cm.get("repeatable"));
				if(challenge.isRepeatable()){
					challenge.setRepeatItemReward(stringToItems((String)cm.get("repeatItemReward")));
					
					challenge.setRepeatItemRewardText((String)cm.get("repeatRewardText"));
					challenge.setRepeatCurrencyReward((Integer)cm.get("repeatCurrencyReward"));
					challenge.setRepeatXpReward((Integer)cm.get("repeatXpReward"));
				}
				challenges.add(challenge);
			}
		} catch (Exception e) {
			Canary.log.log(Level.ERROR, "Error reading challenges list");
			Canary.log.log(Level.ERROR, e.getMessage());
		}
		return challenges;
	}
	public Item[] stringToItems(String IDAMNT){
		String[] ItemStringArray = IDAMNT.split(" ");
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
