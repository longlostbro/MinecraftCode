package com.longlostbro.skyblocks.command;

import com.longlostbro.skyblocks.challenge.Challenge;
import com.longlostbro.skyblocks.challenge.Challenges;
import com.longlostbro.skyblocks.challenge.Status;
import com.longlostbro.skyblocks.island.Island;
import com.longlostbro.skyblocks.utils.InventoryParser;
import com.longlostbro.skyblocks.utils.IslandParser;
import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.chat.ChatFormat;

public class ChallengeCommand implements Command {

	private Challenge challenge;
	private Player owner;
	private boolean currentStatus;
	public ChallengeCommand() {
		// TODO Auto-generated constructor stub
	}

	public void Execute(MessageReceiver caller, String[] parameters) {
		if(parameters.length == 1){
			// do nothing
		}
		else if(parameters.length == 2){
			if(parameters[1].equalsIgnoreCase("info")){
				Challenges.ListChallenges(caller);
			}
		}
		else if(parameters.length == 3){
			if(parameters[1].equalsIgnoreCase("complete")){
				this.challenge = Challenges.getChallenge(parameters[2]);
				this.owner = (Player)caller;
				this.currentStatus = challenge.isComplete(caller.getName());
				completeChallenge();
			}
			else if(parameters[1].equals("info")){
				Challenge challenge = Challenges.getChallenge(parameters[2]);
				if(challenge != null){
					Challenges.GetChallengeInfo(caller, challenge.getName());
				}
				else{
					caller.notice("No such challenge exists try '/challenges info' for a list of challenges.");
				}
			}
			
		}
	}

	private void completeChallenge() {
		if(challenge != null){
			try {
				if(currentStatus && !challenge.isRepeatable()){
					owner.notice("Challenge already complete, not repeatable");
				}
				else{
					if(challenge.getType().equals("islandLevel")){
						processIslandLevelChallenge();
					}
					else if(challenge.getType().equals("onIsland")){
						processOnIslandChallenge();
					}
					else if(challenge.getType().equals("onPlayer")){
						processOnPlayerChallenge();	
					}
				}
			} catch (Exception e) {
				Canary.log.error(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	private void processOnPlayerChallenge() {
		InventoryParser IP = new InventoryParser(owner);
		if(currentStatus == Status.Incomplete){
			if(IP.hasItems(challenge.getRequiredItems(), true)){
				if(challenge.isTakeItems() == true){
					IP.removeItems(challenge.getRequiredItems());
				}
				for(Item item : challenge.getItemReward()){
					IP.inventory.addItem(item);
				}
				Canary.log.error("got reward");
				Island.getIsland(owner.getName()).addXP(challenge.getXpReward());
				finalizeComplete();
			}
		}
		else if(currentStatus == Status.Complete && challenge.isRepeatable()){
			if(IP.hasItems(challenge.getRequiredItems(), true)){
				if(challenge.isTakeItems() == true){
					IP.removeItems(challenge.getRequiredItems());
				}
				for(Item item : challenge.getRepeatItemReward()){
					IP.inventory.addItem(item);
				}
				Island.getIsland(owner.getName()).addXP(challenge.getRepeatXpReward());
				owner.notice(ChatFormat.GREEN+"Challenge Complete! Here is your reward!");
				
			}
		}									
	}

	private void processOnIslandChallenge() {
		IslandParser IP = new IslandParser(owner);
		if(currentStatus == Status.Incomplete){
			if(IP.hasBlocks(challenge.getRequiredItems(), true)){
				Inventory inventory = owner.getInventory();
				for(Item item : challenge.getItemReward()){
					inventory.addItem(item);
				}
				Island.getIsland(owner.getName()).addXP(challenge.getXpReward());
				finalizeComplete();
			}
		}
		else if(currentStatus == Status.Complete && challenge.isRepeatable()){
			if(IP.hasBlocks(challenge.getRequiredItems(), true)){
				Inventory inventory = owner.getInventory();
				for(Item item : challenge.getRepeatItemReward()){
					inventory.addItem(item);
				}
				Island.getIsland(owner.getName()).addXP(challenge.getRepeatXpReward());
				owner.notice(ChatFormat.GREEN+"Challenge Complete! Here is your reward!");
				
			}
		}	
	}

	private void processIslandLevelChallenge() {
		Island island = Island.getIsland(owner.getName());
		if(island.getLevel() >= challenge.getRequiredLevel()){
			if(!currentStatus){
				island.addXP(challenge.getXpReward());
				for(Item item: challenge.getItemReward()){
					owner.giveItem(item);
				}
			}
			else{
				island.addXP(challenge.getRepeatXpReward());
				for(Item item: challenge.getRepeatItemReward()){
					owner.giveItem(item);
				}
			}
			finalizeComplete();
		}
		else{
			owner.notice("You need to have an island of level " + challenge.getRequiredLevel()+" to complete this challenge.");
			owner.notice("Your island level is "+island.getLevel());
		}
	}

	public void finalizeComplete(){
		Canary.log.error("finalizeing");
		if(!challenge.isComplete(owner.getName()))
			challenge.Complete(owner.getName());
		owner.notice(ChatFormat.GREEN+"Challenge Complete! Here is your reward!");
	}


}
