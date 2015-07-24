package me.xinito.custommobdrops.events;

import me.xinito.custommobdrops.Main;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class EntityDeath
  implements Listener
{
  private Main plugin;
  
  public EntityDeath(Main plugin)
  {
    this.plugin = plugin;
  }
  
  @EventHandler
  public void onKill(EntityDeathEvent e)
  {
    Entity killer = e.getEntity().getKiller();
	double chance = plugin.getConfig().getDouble("Item.dropchance",0)/100;
	Random random = new Random();
	
    if ((killer instanceof Player)) {
      if ((e.getEntity() instanceof Animals)) {
    	  
    	  if(random.nextDouble() <= chance) {
      		e.getDrops().add(new ItemStack(this.plugin.citem));
      	}
        
      } else if ((e.getEntity() instanceof Monster)) {
    	  
    	  if(random.nextDouble() <= chance) {
    		e.getDrops().add(new ItemStack(this.plugin.citem));
    	}

      }
    }
  }
  
  @EventHandler
  public void xpBottles(EntityDeathEvent e) {
	  if (plugin.getConfig().getBoolean("Item.xpbottles") == true) {
		  e.setDroppedExp(0);
		  e.getDrops().add(new ItemStack(Material.EXP_BOTTLE, 1));
	  }
  }
}
