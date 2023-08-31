package me.dev.nux.wildtile.util;

import dev.drawethree.xprison.enchants.XPrisonEnchants;
import dev.drawethree.xprison.enchants.api.XPrisonEnchantsAPI;
import dev.drawethree.xprison.enchants.model.XPrisonEnchantment;
import me.dev.nux.wildtile.enchants.WildTileEnchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class WildTileUtil {

    public static boolean hasWildTilePickaxe(Player player) {

        Map<XPrisonEnchantment, Integer> enchants = XPrisonEnchants.getInstance().getApi().getEnchants(player.getInventory().getItemInMainHand());

        XPrisonEnchantsAPI enchantsAPI = XPrisonEnchants.getInstance().getApi();

        if (enchants.isEmpty()) return false;

        for (XPrisonEnchantment xE : enchants.keySet()) {

            if (xE instanceof WildTileEnchantment) {

                return true;

            }

        }

        return false;

    }

    public static WildTileEnchantment getWildTileEnchantment(Player player) {

        Map<XPrisonEnchantment, Integer> enchants = XPrisonEnchants.getInstance().getApi().getEnchants(player.getInventory().getItemInMainHand());

        XPrisonEnchantsAPI enchantsAPI = XPrisonEnchants.getInstance().getApi();

        if (enchants.isEmpty()) return null;

        for (XPrisonEnchantment xE : enchants.keySet()) {

            if (xE instanceof WildTileEnchantment) {

                return (WildTileEnchantment) xE;

            }

        }

        return null;

    }

    public static int getWildTileLevel(Player player) {
        return XPrisonEnchants.getInstance().getApi().getEnchantLevel(player.getInventory().getItemInMainHand(), getWildTileEnchantment(player));
    }

}
