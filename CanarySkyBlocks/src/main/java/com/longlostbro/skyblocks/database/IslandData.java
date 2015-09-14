package com.longlostbro.skyblocks.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.canarymod.Canary;
import net.canarymod.api.world.position.Position;
import net.canarymod.database.Column;
import net.canarymod.database.Database;
import net.canarymod.database.Column.DataType;
import net.canarymod.database.exceptions.DatabaseReadException;
import net.canarymod.database.DataAccess;
/** Structure for the database that contains the islands data
 * 
 * @author longlostbro
 *
 */
public class IslandData extends DataAccess {

    @Column(columnName = "owner_name", dataType = DataType.STRING)
    public String owner_name;
    @Column(columnName = "players_joined", isList = true, dataType = DataType.STRING)
    public ArrayList<String> players_joined;
    @Column(columnName = "challenges_complete", isList = true, dataType = DataType.STRING)
    public ArrayList<String> challenges_complete;
    @Column(columnName = "xp", dataType = DataType.INTEGER)
    public int xp;
    @Column(columnName = "x", dataType = DataType.LONG)
    public long x;
    @Column(columnName = "y", dataType = DataType.LONG)
    public long y;
    @Column(columnName = "z", dataType = DataType.LONG)
    public long z;
    @Column(columnName = "time_created", dataType = DataType.LONG)
    public long time_created;
    @Column(columnName = "last_used", dataType = DataType.LONG)
    public long last_used;

	public IslandData() {
		super("IslandData");
	}
	
	@Override
	public DataAccess getInstance() {
		// TODO Auto-generated method stub
		return new IslandData();
	}
	
	public static boolean removeIslandData(String owner_name){
        try{
			Map<String, Object> filter = new HashMap<String, Object>();
	        filter.put("owner_name", owner_name);
	        IslandData id = new IslandData();
			Database.get().load(id, filter);
		    if(id.hasData()) {
		        Database.get().removeAll(id, filter);
		    }
        }
        catch(Exception e){
	    	Canary.log.error("Island already exists, IslandData.java removeIslandData");
        	e.printStackTrace();
        	return false;
        }
        return true;
	}
	
	public static IslandData getIslandData(String owner_name){
		try{
			HashMap<String, Object> filter = new HashMap<String, Object>();
	        filter.put("owner_name", owner_name);
	        IslandData id = new IslandData();
			Database.get().load(id, filter);
		    if(id.hasData()) {
		    	return id;
		    }
		    else{
		    	return null;
		    }
        }
        catch(DatabaseReadException e){
	    	Canary.log.error("Island already exists, IslandData.java getIslandData");
        	e.printStackTrace();
        	return null;
        }
	}
	public static IslandData insertIslandData(String owner_name, ArrayList<String> players, ArrayList<String> challenges_complete, Position position, long time_created, long last_used, int xp){
		IslandData id = null;
        try{
        	id = new IslandData();
			HashMap<String, Object> filter;
			filter = new HashMap<String, Object>();
	        filter.put("owner_name", owner_name);
			Database.get().load(id, filter);
		    if(id.hasData()) {
		    	Canary.log.error("Island already exists, IslandData.java addIsland");
		    }
		    else{
		    	id.owner_name = owner_name;
		    	id.players_joined = players;
		    	id.challenges_complete = challenges_complete;
		    	id.xp = xp;
		    	id.x = (long) position.getX();
		    	id.y = (long) position.getY();
		    	id.z = (long) position.getZ();
		    	id.time_created = time_created;
		    	id.last_used = last_used;
		    	Database.get().update(id, filter);
		    }
        }
        catch(Exception e){
        	Canary.log.error("Error, IslandData.java: insertIslandData");
        	e.printStackTrace();
        }
	    return id;
	}
	public static void setXP(int xp, String owner_name) {
		try {
			HashMap<String, Object> filter = new HashMap<String, Object>();
	        filter.put("owner_name", owner_name);
	        IslandData ID = new IslandData();
			Database.get().load(ID, filter);
			ID.xp = xp;
			Database.get().update(ID, filter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void setPlayers(ArrayList<String> players, String owner_name) {
		try {
			HashMap<String, Object> filter = new HashMap<String, Object>();
	        filter.put("owner_name", owner_name);
	        IslandData ID = new IslandData();
			Database.get().load(ID, filter);
			ID.players_joined = players;
			Database.get().update(ID, filter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void setChallengesComplete(ArrayList<String> challenges_complete, String owner_name) {
		try {
			HashMap<String, Object> filter = new HashMap<String, Object>();
	        filter.put("owner_name", owner_name);
	        IslandData ID = new IslandData();
			Database.get().load(ID, filter);
			ID.challenges_complete = challenges_complete;
			Database.get().update(ID, filter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addChallengeComplete(String owner_name, String challenge_name) {
		try {
			HashMap<String, Object> filter = new HashMap<String, Object>();
	        filter.put("owner_name", owner_name);
	        IslandData ID = new IslandData();
			Database.get().load(ID, filter);
			if(!ID.challenges_complete.contains(challenge_name))
				ID.challenges_complete.add(challenge_name);
			Database.get().update(ID, filter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void setPosition(Position v) {
		try {
			HashMap<String, Object> filter = new HashMap<String, Object>();
	        filter.put("owner_name", this.owner_name);
	        IslandData ID = new IslandData();
			Database.get().load(ID, filter);
			ID.x = v.getBlockX();
			ID.y = v.getBlockY();
			ID.z = v.getBlockZ();
			Database.get().update(ID, filter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
