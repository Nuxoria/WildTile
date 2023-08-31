package me.dev.nux.wildtile.enchants;

import dev.drawethree.xprison.XPrison;
import dev.drawethree.xprison.enchants.XPrisonEnchants;
import dev.drawethree.xprison.enchants.api.XPrisonEnchantsAPI;
import dev.drawethree.xprison.enchants.model.XPrisonEnchantment;
import dev.drawethree.xprison.pickaxelevels.model.PickaxeLevel;
import me.dev.nux.wildtile.mining.WildTileMechanics;
import me.dev.nux.wildtile.util.WildTileUtil;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Optional;

public class WildTileEnchantment extends XPrisonEnchantment implements Listener {

    public WildTileEnchantment() {
        super(XPrisonEnchants.getInstance(), 99);
    }

    @Override
    public String getAuthor() {
        return "Nuxoria";
    }

    @Override
    public void onEquip(Player player, ItemStack itemStack, int i) {
    }

    @Override
    public void onUnequip(Player player, ItemStack itemStack, int i) {}

    @Override
    public void onBlockBreak(BlockBreakEvent blockBreakEvent, int i) {
    }

    @EventHandler
    public void onWildTileBlockBreak(BlockBreakEvent e) {

        if (WildTileUtil.hasWildTilePickaxe(e.getPlayer())) {

            int level = WildTileUtil.getWildTileLevel(e.getPlayer());
            Block b = e.getBlock();

            WildTileMechanics.mineArea(e.getPlayer(), b, level);

        }

    }

    @Override
    public double getChanceToTrigger(int i) {
        return 0;
    }
}
