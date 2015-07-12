package me.xinito.custommobdrops.commands;

import me.xinito.custommobdrops.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemGive
  implements CommandExecutor
{
  private Main plugin;
  
  public ItemGive(Main plugin)
  {
    this.plugin = plugin;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a)
  {
    if (!sender.hasPermission("cmditem.give"))
    {
      sender.sendMessage("§cNo permission!");
      return false;
    }
    if (!(sender instanceof Player))
    {
      sender.sendMessage("§cOnly ingame players can use this command!");
      return false;
    }
    if (a.length < 1)
    {
      sender.sendMessage("§cUsage: /cmditem give");
    }
    else if (a.length == 1)
    {
      Player p = (Player)sender;
      p.getInventory().addItem(new ItemStack[] { new ItemStack(this.plugin.citem) });
      p.sendMessage("§aSuccesfully added the custom item in your inventory!");
    }
    return false;
  }
}