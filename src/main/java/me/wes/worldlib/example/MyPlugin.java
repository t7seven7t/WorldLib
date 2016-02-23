package me.wes.worldlib.example;

import me.wes.worldlib.WorldLib;
import org.bukkit.WorldType;
import org.bukkit.plugin.java.JavaPlugin;

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
