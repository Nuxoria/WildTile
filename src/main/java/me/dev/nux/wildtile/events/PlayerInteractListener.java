package me.dev.nux.wildtile.events;

import me.dev.nux.wildtile.util.WildTileUtil;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    public static BlockFace lastBlockFaceHit;

    @EventHandler
    public void onBlockInteract(PlayerInteractEvent e) {

        if (WildTileUtil.hasWildTilePickaxe(e.getPlayer())) {

            if (e.getAction() == Action.LEFT_CLICK_BLOCK)
                lastBlockFaceHit = e.getBlockFace();

        }

    }

}
