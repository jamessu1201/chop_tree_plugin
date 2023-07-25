package com.jamessu.lumberjack;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public final class Main extends JavaPlugin{
    @Override
    public void onEnable() {
        getLogger().info("start complete.");
        registerEvents();
    }
    @Override
    public  void onDisable(){

    }

    public void registerEvents(){
        PluginManager pm=getServer().getPluginManager();

        pm.registerEvents(new Event(),this);
    }
}