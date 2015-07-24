package me.xinito.custommobdrops;

import me.xinito.custommobdrops.commands.ItemGive;
import me.xinito.custommobdrops.events.EntityDeath;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener
{
  public Main plugin;
  public API api;
  public ItemStack citem;
  FileConfiguration config = getConfig();
  
  public void loadConfiguration()
  {
    this.config.options().header("Color codes are supported. Only use Material names in the materialName, they can be found here: http://jd.bukkit.org/rb/apidocs/org/bukkit/Material.html , Enchant names can be found here: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/enchantments/Enchantment.html");
    
    String path = "Item";
    getConfig().addDefault(path, "");
    if (!getConfig().contains("Item.materialName")) {
      getConfig().set("Item.materialName", "APPLE");
    }
    if (!getConfig().contains("Item.displayName")) {
      getConfig().set("Item.displayName", "&5Name");
    }
    if (!getConfig().contains("Item.lore")) {
      getConfig().set("Item.lore", "&6Lore");
    }
    if (!getConfig().contains("Item.amount")) {
      getConfig().set("Item.amount", Integer.valueOf(1));
    }
    if (!getConfig().contains("Item.dropchance")) {
    	getConfig().set("Item.dropchance", Double.valueOf(100.0));
    }
    
    if (!getConfig().contains("Item.enchantment")) {
    	getConfig().set("Item.enchantment", "NONE");
    }
    if (!getConfig().contains("Item.enchantmentLevel")) {
    	getConfig().set("Item.enchantmentLevel", Integer.valueOf(1));
    }
    
    if (!getConfig().contains("Item.monsters")) {
    	getConfig().set("Item.monsters", Boolean.TRUE);
    }
    
    if (!getConfig().contains("Item.animals")) {
    	getConfig().set("Item.animals", Boolean.TRUE);
    }
    
    if (!getConfig().contains("Item.xpbottles")) {
    	getConfig().set("Item.xpbottles", Boolean.FALSE);
    }
    getConfig().options().copyDefaults(true);
    
    saveConfig();
  }
  
  public void onEnable()
  {
    loadConfiguration();
    
    PluginManager pm = Bukkit.getPluginManager();
    pm.registerEvents(new EntityDeath(this), this);
    
    getCommand("cmditem").setExecutor(new ItemGive(this));
    
    this.plugin = this;
    
    this.api = new API(this);
    
    this.citem = new ItemStack(this.plugin.api.createItem(Material.getMaterial(this.plugin.getConfig().getString("Item.materialName")), this.plugin.getConfig().getInt("Item.amount"), (short)0, this.plugin.getConfig().getString("Item.displayName").replaceAll("&", "§"), this.plugin.getConfig().getString("Item.lore").replaceAll("&", "§")));
  }
  
  public void onDisable()
  {
    reloadConfig();
  }
}
