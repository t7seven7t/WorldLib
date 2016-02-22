package me.wes.worldlib;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
public class WorldManager {

    private Map<Integer, World> worlds = new HashMap<>();

    WorldManager() {}

    public Collection<World> getWorlds() {
        return worlds.values();
    }

    public int getWorldId(String world) {
        for(int id : worlds.keySet()) if(worlds.get(id).equals(Bukkit.getWorld(world))) return id;
        return -1;
    }

    public void loadWorld(String name) {
        if(!worlds.containsValue(Bukkit.getWorld(name))) {
            World w = Bukkit.getServer().createWorld(new WorldCreator(name));
            worlds.put(worlds.size(), w);
        }
    }

    public void createWorld(WorldType type, String name) {
        if(!worlds.containsValue(Bukkit.getWorld(name))) {
            World w = Bukkit.getServer().createWorld(new WorldCreator(name).type(type));
            worlds.put(worlds.size(), w);
        }
    }

    public void unloadWorld(String name) {
        if(worlds.containsValue(Bukkit.getWorld(name))) {
            Bukkit.getWorld(name).save();
            Bukkit.getServer().unloadWorld(Bukkit.getWorld(name), true);
            worlds.remove(getWorldId(name));
        }
    }

    public void deleteWorld(String name) {
        if(worlds.containsKey(getWorldId(name))) unloadWorld(name);
        boolean deleted = new File(name+"/").delete();
        if(!deleted) throw new RuntimeException("Unable to delete world " + name + ".");
    }

}
