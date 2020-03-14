package org.example;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * Load jar file plugin inside custom classloader.
 */
public class FileSystemLoader {

    public static final String PLUGIN_DIR = System.getProperty("user.home") + "/demo/plugins";
    private static FileSystemLoader instance;

    private URLClassLoader pluginClassLoader;


    private  FileSystemLoader() {
        this.checkPluginDir();
        this.loadPlugins();
    }

    public static FileSystemLoader getInstance(){
        if(instance == null) {
            instance = new FileSystemLoader();
        }
        return instance;
    }

    public void loadPlugins() {
        // Url that will contains all plugins file found inside jar.
        List<URL> urls = new ArrayList<>();
        // Pointer to the pluginDir
        File pluginDir = new File(PLUGIN_DIR);
        // Exclude file that are not Jar File
        File[] plugins = pluginDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".jar"));
        for(int i = 0; i < plugins.length; i++) {
            File plugin = plugins[i];
            try {
                // Add the jar urls to the urls list to load into the plugin classloader
                urls.add(new URL("jar", "", plugin.toURI().toURL() + "!/"));
            } catch (IOException e) {
                throw new RuntimeException("Can't load the plugin " + plugin.getName() + ". Skipping", e);
            }
        }
        // Create the classloader.
        pluginClassLoader = new URLClassLoader(urls.toArray(new URL[urls.size()]), ClassLoader.getSystemClassLoader());
    }

    /**
     * Return the plugin classloader that contain all found plugin
     * @return URLClassLoader The plugin classLoader
     */
    public URLClassLoader getPluginClassLoader() {
        return this.pluginClassLoader;
    }

    /**
     * Check if the plugin dir exist and if not, create it.
     */
    private void checkPluginDir() {
        File pDir = new File(PLUGIN_DIR);
        if(!pDir.exists()) {
            pDir.mkdirs();
        }
    }
}
