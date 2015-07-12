package me.xinito.custommobdrops;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class API
{
  private Main plugin;
  
  public API(Main plugin)
  {
    this.plugin = plugin;
  }
  
  public ItemStack createItem(Material material, int amount, short shrt, String displayname, String lore)
  {
    ItemStack item = new ItemStack(material, amount, shrt);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(displayname);
    ArrayList<String> Lore = new ArrayList();
    Lore.add(lore);
    meta.setLore(Lore);
    
    item.setItemMeta(meta);
    
    if (!(this.plugin.getConfig().getString("Item.enchantment").contains("NONE")))
    {
    	Enchantment enchant = Enchantment.getByName(this.plugin.getConfig().getString("Item.enchantment"));
    	item.addUnsafeEnchantment(enchant, this.plugin.getConfig().getInt("Item.enchantmentLevel"));
    }

    return item;
  }
}
