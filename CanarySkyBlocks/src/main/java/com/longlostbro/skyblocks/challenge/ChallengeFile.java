package com.longlostbro.skyblocks.challenge;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import net.canarymod.Canary;

import com.longlostbro.skyblocks.SkyBlocks;

public class ChallengeFile {
	private static ChallengeFile instance;
	@SuppressWarnings("unused")
	private File file;
    
	public ChallengeFile(File config,boolean create) {
		if(create){
	    	Canary.log.debug("creating config file");
			try {
				FileUtils.copyURLToFile(SkyBlocks.getInstance().getClass().getClassLoader().getResource("resources/Challenges.yml"), config);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		file = config;
	}

	public static ChallengeFile getInstance(){
		return instance;
	}

}
