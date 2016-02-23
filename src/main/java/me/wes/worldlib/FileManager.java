package me.wes.worldlib;

import org.apache.commons.lang.Validate;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

class FileManager {

    private File worldsFile;

    public FileManager(JavaPlugin plugin) {
        File pluginFolder = plugin.getDataFolder();
        pluginFolder.mkdirs();

        File worldLibFolder = new File(pluginFolder, "WorldLib");
        worldLibFolder.mkdirs();

        worldsFile = new File(worldLibFolder, "worlds.dat");
        if(!worldsFile.exists()) try {
            worldsFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(String s) {
        try {
            Validate.notNull(worldsFile, "Unable to use access worlds.dat.");

            FileWriter writer = new FileWriter(worldsFile, true);
            PrintWriter printWriter = new PrintWriter(writer);

            printWriter.println(s);
            printWriter.flush();
            printWriter.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Collection<String> getFileContents() {
        try {
            Validate.notNull(worldsFile, "Unable to use access worlds.dat.");

            Collection<String> collection = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(worldsFile));

            while(reader.readLine() != null) collection.add(reader.readLine());

            return collection;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}