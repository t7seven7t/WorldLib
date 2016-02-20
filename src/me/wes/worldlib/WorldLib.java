package me.wes.worldlib;

import org.apache.commons.lang.Validate;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * ******************************************************************************************************
 * Copyright (C) 2015 Wesley Smith under the Copyright Law of the United States. All rights reserved.   *
 * All code contained in this document is sole property of Wesley Smith. Do NOT distribute, reproduce,  *
 * take snippets, or take this code under your name without exclusive permission from Wesley Smith.     *
 * Not following this statement will result in a void of all agreements made, and possibly legal action.*
 * If you have any questions or concerns, please contact me via email with the address of               *
 * wesjavadev@gmail.com                                                                                 *
 * Thanks for your cooperation.                                                                         *
 * ******************************************************************************************************
 */
public class WorldLib {

    private static WorldLib instance = new WorldLib();
    private WorldManager manager;
    private FileManager fileManager;
    private boolean enabled = false;

    private WorldLib() {}

    public void enable(JavaPlugin plugin) {
        Validate.isTrue(enabled, "WorldLib has already been enabled.");

        this.manager = new WorldManager();
        this.fileManager = new FileManager(plugin);

        Validate.notNull(fileManager, "Error while accessing the FileManager.");
        fileManager.getFileContents().forEach(s -> manager.loadWorld(s));

        enabled = true;
    }

    public void disable() {
        Validate.notNull(fileManager, "Error while accessing the FileManager.");
        manager.getWorlds().forEach(w -> {
            if(!fileManager.getFileContents().contains(w.getName())) fileManager.writeToFile(w.getName());
            manager.unloadWorld(w.getName());
        });
        enabled = false;
    }

    public WorldManager getManager() {
        return manager;
    }

    public static WorldLib getInstance() {
        return instance;
    }

}
