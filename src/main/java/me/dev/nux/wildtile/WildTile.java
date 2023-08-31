package me.dev.nux.wildtile;

import dev.drawethree.xprison.XPrison;
import dev.drawethree.xprison.enchants.XPrisonEnchants;
import dev.drawethree.xprison.enchants.api.XPrisonEnchantsAPI;
import me.dev.nux.wildtile.enchants.WildTileEnchantment;
import me.dev.nux.wildtile.events.PlayerInteractListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class WildTile extends JavaPlugin {

    private static WildTile instance;

    private WildTileEnchantment wildTileEnchantment;

    private XPrisonEnchantsAPI api;

    @Override
    public void onEnable() {

        if (!Bukkit.getPluginManager().isPluginEnabled("X-Prison")) {
            this.getLogger().warning("Unable to hook into UltraPrisonCore! Disabling...");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        XPrison xPrison = XPrison.getInstance();

        if (!xPrison.isModuleEnabled(XPrisonEnchants.MODULE_NAME)) {
            this.getLogger().warning("Enchants module is disabled! Disabling...");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.api = xPrison.getEnchants().getApi();

        this.wildTileEnchantment = new WildTileEnchantment();

        this.api.registerEnchant(this.wildTileEnchantment);

        instance = this;

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[WildTile] Successfully enabled!");

        Bukkit.getPluginManager().registerEvents(new WildTileEnchantment(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);

    }

    @Override
    public void onDisable() {
        this.api.unregisterEnchant(this.wildTileEnchantment);
    }

    public static WildTile getInstance() {
        return instance;
    }

}
