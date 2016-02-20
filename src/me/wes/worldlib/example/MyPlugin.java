package me.wes.worldlib.example;

import me.wes.worldlib.WorldLib;
import org.bukkit.WorldType;
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
public class MyPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        WorldLib.getInstance().enable(this);
    }

    @Override
    public void onDisable() {
        WorldLib.getInstance().disable();
    }

    //example methods
    {
        WorldLib.getInstance().getManager().createWorld(WorldType.FLAT, "MyFlatWorld");
        WorldLib.getInstance().getManager().loadWorld("MyFlatWorld");
        WorldLib.getInstance().getManager().unloadWorld("MyFlatWorld");
        WorldLib.getInstance().getManager().deleteWorld("MtFlatWorld");
    }

}
