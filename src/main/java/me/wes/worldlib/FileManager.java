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
