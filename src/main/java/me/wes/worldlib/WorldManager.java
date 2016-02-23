/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Wesley Smith
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
