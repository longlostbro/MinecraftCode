package com.longlostbro.skyblocks;
/*
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.logging.log4j.Level;
import net.canarymod.Canary;
import net.canarymod.api.world.position.Position;
import net.canarymod.plugin.Plugin;
import com.sk89q.canarymod.worldedit.CanaryWorld;
import com.sk89q.canarymod.worldedit.CanaryWorldEdit;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.extent.clipboard.io.*;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.util.io.Closer;
import com.sk89q.worldedit.util.io.file.FilenameException;
import com.sk89q.worldedit.world.registry.WorldData;*/
/** Handles all methods used from world edit
 * 
 * @author longlostbro
 *
 */
public class WorldEditHandler {
	/** A WorldEdit representation of SkyWorld
	 * 
	 */
	/*public CanaryWorld world = new CanaryWorld(Canary.getServer().getWorld("SkyWorld_NORMAL"));
	
	public WorldEditHandler(){
	}
	public static Plugin getWorldEdit() {
		final Plugin plugin = Canary.manager().getPlugin("WorldEdit");

		if (plugin == null || !(plugin instanceof CanaryWorldEdit)) { return null; }

		return (CanaryWorldEdit)plugin;
	}
	public ClipboardHolder loadSchematic(String schematicName) throws FilenameException {
		
        ClipboardFormat format = ClipboardFormat.findByAlias("schematic");
        

        Closer closer = Closer.create();
        try {
                InputStream fis = closer.register(this.getClass().getClassLoader().getResourceAsStream(schematicName));
                BufferedInputStream bis = closer.register(new BufferedInputStream(fis));
                ClipboardReader reader = format.getReader(bis);

                WorldData worldData = new CanaryWorld(Canary.getServer().getWorld("SkyWorld_NORMAL")).getWorldData();
                Clipboard clipboard = reader.read(worldData);
                return new ClipboardHolder(clipboard, worldData);
            
        } catch (IOException e) {
            SkyBlocks.logger.log(Level.WARN, "Failed to load a saved clipboard", e);
        } finally {
            try {
                closer.close();
            } catch (IOException ignored) {
            }
        }
		return null;
    }
	public void pasteSchematic(CanaryWorld world, Position center, String schematicName) throws FileNotFoundException, IOException, MaxChangedBlocksException, FilenameException{
		EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(world, -1);
		ClipboardHolder holder = new WorldEditHandler().loadSchematic(schematicName);
		Vector to = new Vector(center.getX(),center.getY(),center.getZ());
		Operation operation = holder.createPaste(editSession, editSession.getWorld().getWorldData()).to(to).ignoreAirBlocks(true).build();
		Operations.completeLegacy(operation);
	}*/
}