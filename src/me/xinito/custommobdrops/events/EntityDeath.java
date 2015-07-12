package me.xinito.custommobdrops.events;

import me.xinito.custommobdrops.Main;
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
    if ((killer instanceof Player)) {
      if ((e.getEntity() instanceof Animals)) {
        e.getDrops().add(new ItemStack(this.plugin.citem));
      } else if ((e.getEntity() instanceof Monster)) {
        e.getDrops().add(new ItemStack(this.plugin.citem));
      }
    }
  }
}
