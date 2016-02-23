package me.wes.worldlib;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class WorldManager {

    private final Map<Integer, World> worlds = new HashMap<>();

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
