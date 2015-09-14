package com.longlostbro.skyblocks.challenge;

import com.longlostbro.skyblocks.database.IslandData;

import net.canarymod.api.inventory.Item;

public class Challenge {

	private String name;
	private String description;
	private String rankLevel;
	private String type;
	private Item[] requiredItems;
	private int requiredLevel;
	private boolean takeItems;
	private Item[] itemReward;
	private String permissionReward;
	private String rewardText;
	private int currencyReward;
	private int xpReward;
	private boolean repeatable;
	private Item[] repeatItemReward;
	private String repeatItemRewardText;
	private int repeatCurrencyReward;
	private int repeatXpReward;
	
	public Challenge() {
		// TODO Auto-generated constructor stub
	}

	public int getRequiredLevel() {
		return requiredLevel;
	}

	public void setRequiredLevel(int requiredLevel) {
		this.requiredLevel = requiredLevel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRankLevel() {
		return rankLevel;
	}

	public void setRankLevel(String rankLevel) {
		this.rankLevel = rankLevel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Item[] getRequiredItems() {
		return requiredItems;
	}

	public void setRequiredItems(Item[] requiredItems) {
		this.requiredItems = requiredItems;
	}
	
	public boolean isTakeItems() {
		return takeItems;
	}

	public void setTakeItems(boolean takeItems) {
		this.takeItems = takeItems;
	}

	public Item[] getItemReward() {
		return itemReward;
	}

	public void setItemReward(Item[] itemReward) {
		this.itemReward = itemReward;
	}

	public String getPermissionReward() {
		return permissionReward;
	}

	public void setPermissionReward(String permissionReward) {
		this.permissionReward = permissionReward;
	}

	public String getRewardText() {
		return rewardText;
	}

	public void setRewardText(String rewardText) {
		this.rewardText = rewardText;
	}

	public int getCurrencyReward() {
		return currencyReward;
	}

	public void setCurrencyReward(int currencyReward) {
		this.currencyReward = currencyReward;
	}

	public int getXpReward() {
		return xpReward;
	}

	public void setXpReward(int xpReward) {
		this.xpReward = xpReward;
	}

	public boolean isRepeatable() {
		return repeatable;
	}

	public void setRepeatable(boolean repeatable) {
		this.repeatable = repeatable;
	}

	public Item[] getRepeatItemReward() {
		return repeatItemReward;
	}

	public void setRepeatItemReward(Item[] repeatItemReward) {
		this.repeatItemReward = repeatItemReward;
	}

	public String getRepeatItemRewardText() {
		return repeatItemRewardText;
	}

	public void setRepeatItemRewardText(String repeatItemRewardText) {
		this.repeatItemRewardText = repeatItemRewardText;
	}

	public int getRepeatCurrencyReward() {
		return repeatCurrencyReward;
	}

	public void setRepeatCurrencyReward(int repeatCurrencyReward) {
		this.repeatCurrencyReward = repeatCurrencyReward;
	}

	public int getRepeatXpReward() {
		return repeatXpReward;
	}

	public void setRepeatXpReward(int repeatXpReward) {
		this.repeatXpReward = repeatXpReward;
	}


	public boolean isComplete(String owner_name) {
		IslandData ID = IslandData.getIslandData(owner_name);
		return ID.challenges_complete.contains(getName());
	}

	public void Complete(String owner_name) {
		IslandData.addChallengeComplete(owner_name, getName());
		
	}

	
}
