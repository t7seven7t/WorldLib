# WorldLib

A world management system for [Bukkit](http://bukkit.org/).

## How to Use
### Registration
```java
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
```
Simple enough, eh? Call `WorldLib#enable(JavaPlugin)` with your main class as the parameter to enable the system. When you're done with it (really when the server shuts down), call `WorldLib#disable()`. This will save worlds, etc.

### Usage
```java
    //example methods
    {
        WorldLib.getInstance().getManager().createWorld(WorldType.FLAT, "MyFlatWorld");
        WorldLib.getInstance().getManager().loadWorld("MyFlatWorld");
        WorldLib.getInstance().getManager().unloadWorld("MyFlatWorld");
        WorldLib.getInstance().getManager().deleteWorld("MtFlatWorld");
    }
```
All of these methods are self explanatory. Each one takes a string of the world name as the parameter. These do not need to be called like this either, it is simply showing the methods of WorldManager. You can attempt a creation, deletion, etc of a world as many times as you would like, but it will do nothing if a world with that name already exists.

How you use this well.
