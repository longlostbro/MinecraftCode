package com.longlostbro.skyblocks.island;
import com.longlostbro.skyblocks.command.RemoveIslandCommand;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.position.Position;

public class IslandJoin {

	public Island island;
	public Player owner;
	
	public IslandJoin(Player player, Player owner) {
		this.owner = owner;
		island = Island.getIsland(owner.getName());
		island.getGuard().addPlayer(player);
		island.addPlayer(player);
		Position pos = island.getPosition();
		player.teleportTo(pos.getX(), pos.getY(), pos.getZ(), island.getWorld());
		new RemoveIslandCommand().Execute(player);
	}

}
