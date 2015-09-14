package com.longlostbro.skyblocks.challenge;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.canarymod.Canary;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;

public class Challenges {

	private static Map<String,Challenge> challenges;
	
	public Challenges() {
		challenges = new TreeMap<String,Challenge>();
		List<Challenge> challengesList = new ChallengeParser(Canary.getServer()).getChallenges();
		for(Challenge challenge : challengesList){
			challenges.put(challenge.getName(), challenge);
		}
	}
	public static Map<String,Challenge> getChallenges(){
		return challenges;
	}
	public static Challenge getChallenge(String name){
		return challenges.get(name);
	}
	public static void GetChallengeInfo(MessageReceiver player, String challenge_name){
		Challenge challenge = challenges.get(challenge_name);
		player.notice(ChatFormat.GRAY+"---------------"+ChatFormat.BOLD+challenge.getName()+ChatFormat.GRAY+"---------------");
		player.notice(ChatFormat.GREEN+"Type: "+ChatFormat.GRAY+challenge.getType());
		player.notice(ChatFormat.GREEN+"Level: "+ChatFormat.GRAY+challenge.getRankLevel());
		player.notice(ChatFormat.GREEN+"Description: "+ChatFormat.GRAY+challenge.getDescription());
		player.notice(ChatFormat.GREEN+"Reward: "+ChatFormat.GRAY+challenge.getRewardText());
		if(challenge.isRepeatable()){
			player.notice(ChatFormat.GREEN+"Repeatable");
		}
		else
			player.notice(ChatFormat.RED+"Not Repeatable");
		player.notice(ChatFormat.GRAY+"------------------------------------------");
	}
	public static void ListChallenges(MessageReceiver player){
		try {
			player.notice("");
			player.notice(ChatFormat.GRAY+"---------------"+ChatFormat.BOLD+"Challenges"+ChatFormat.GRAY+"---------------");
			player.notice(ChatFormat.GREEN+"Green:"+ChatFormat.GRAY+"Incomplete "+ChatFormat.RED+"Red:"+ChatFormat.GRAY+"Complete "+ChatFormat.YELLOW+"Yellow:"+ChatFormat.GRAY+"Repeatable");
			player.notice(ChatFormat.GRAY+"------------------------------------------");
			for(Challenge challenge : challenges.values()){
				if(challenge.isComplete(player.getName())){
					if(challenge.isRepeatable())
						player.notice(ChatFormat.YELLOW+challenge.getName()+": "+ChatFormat.WHITE+challenge.getDescription());
					else
						player.notice(ChatFormat.RED+challenge.getName()+": "+ChatFormat.WHITE+challenge.getDescription());
				}
				else
					player.notice(ChatFormat.GREEN+challenge.getName()+": "+ChatFormat.WHITE+challenge.getDescription());
			}
			player.notice(ChatFormat.GRAY+"------------------------------------------");
			player.notice("");
		} catch (Exception e) {
			Canary.log.error("Challenges.java, ListChallenges error");
			e.printStackTrace();
		}
	}

}
