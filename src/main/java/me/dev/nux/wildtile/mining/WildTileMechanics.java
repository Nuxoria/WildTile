package me.dev.nux.wildtile.mining;

import javafx.util.Pair;
import me.dev.nux.wildtile.events.PlayerInteractListener;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WildTileMechanics {

    public static void mineArea(Player p, Block centerBlock, int level) {

        World w = centerBlock.getWorld();

        BlockFace lastBlockFaceHit = PlayerInteractListener.lastBlockFaceHit;

        BlockFace inFrontFace = lastBlockFaceHit.getOppositeFace();

        List<Block> blocksToBreak = new ArrayList<>();

        switch (level) {

            case 1: {

                Levels.mineLevel1(lastBlockFaceHit, centerBlock, BlockFace.valueOf(getCardinalDirection(p).toUpperCase()), blocksToBreak);

                break;

            }

            case 2: {

                Levels.mineLevel2(lastBlockFaceHit, centerBlock, BlockFace.valueOf(getCardinalDirection(p).toUpperCase()), blocksToBreak);

                break;
            }

            case 3: {

                Levels.mineLevel3(lastBlockFaceHit, centerBlock, BlockFace.valueOf(getCardinalDirection(p).toUpperCase()), blocksToBreak);

                break;

            }

            case 4: {

                Levels.mineLevel4(lastBlockFaceHit, centerBlock, BlockFace.valueOf(getCardinalDirection(p).toUpperCase()), blocksToBreak);

                break;

            }

            case 5: {

                Levels.mineLevel5(lastBlockFaceHit, centerBlock, BlockFace.valueOf(getCardinalDirection(p).toUpperCase()), blocksToBreak);

                break;

            }

        }

    }

    /**
     * Get the cardinal compass direction of a player.
     *
     * @param player
     * @return
     */
    public static String getCardinalDirection(Player player) {
        double rot = ((player.getLocation().getYaw()) + 180) % 360;
        if (rot < 0) {
            rot += 360.0;
        }
        return getDirection(rot);
    }

    public static BlockFace getRightBlockFace(BlockFace centerBlockFace) {

        if (centerBlockFace == BlockFace.NORTH)
            return BlockFace.EAST;
        if (centerBlockFace == BlockFace.EAST)
            return BlockFace.SOUTH;
        if (centerBlockFace == BlockFace.SOUTH)
            return BlockFace.WEST;
        if (centerBlockFace == BlockFace.WEST)
            return BlockFace.NORTH;

        return null;


    }

    /**
     * Converts a rotation to a cardinal direction name.
     *
     * @param rot
     * @return
     */
    private static String getDirection(double rot) {
        if (0 <= rot && rot < 22.5) {
            return "North";
        } else if (22.5 <= rot && rot < 67.5) {
            return "North";
        } else if (67.5 <= rot && rot < 112.5) {
            return "East";
        } else if (112.5 <= rot && rot < 157.5) {
            return "South";
        } else if (157.5 <= rot && rot < 202.5) {
            return "South";
        } else if (202.5 <= rot && rot < 247.5) {
            return "South";
        } else if (247.5 <= rot && rot < 292.5) {
            return "West";
        } else if (292.5 <= rot && rot < 337.5) {
            return "North";
        } else if (337.5 <= rot && rot < 360.0) {
            return "North";
        } else {
            return String.valueOf(rot);
        }
    }

    public static Block getBlockAtRight(Block centerBlock, BlockFace lastBlockFaceHit, BlockFace playerFacing) {

        if (!isHittingFromSide(lastBlockFaceHit)) {

            return centerBlock.getRelative(getRightBlockFace(playerFacing));

        } else {

            return centerBlock.getRelative(getRightBlockFace(lastBlockFaceHit.getOppositeFace()));

        }

    }

    public static BlockFace getBlockFaceAtRight(Block centerBlock, BlockFace lastBlockFaceHit, BlockFace playerFacing) {

        if (!isHittingFromSide(lastBlockFaceHit)) {

            return getRightBlockFace(playerFacing);

        } else {

            return getRightBlockFace(lastBlockFaceHit.getOppositeFace());

        }

    }

    public static Block getBlockAbove(Block centerBlock, BlockFace playerFacing, BlockFace lastHitBlockFace) {

        if (!isHittingFromSide(lastHitBlockFace)) {

            if (lastHitBlockFace == BlockFace.DOWN)
                return centerBlock.getRelative(playerFacing.getOppositeFace());

            return centerBlock.getRelative(playerFacing);

        } else {

            return centerBlock.getRelative(BlockFace.UP);

        }

    }

    public static BlockFace getBlockAboveFace(Block centerBlock, BlockFace playerFacing, BlockFace lastHitBlockFace) {

        if (!isHittingFromSide(lastHitBlockFace)) {

            if (lastHitBlockFace == BlockFace.DOWN)
                return playerFacing.getOppositeFace();

            return playerFacing;

        } else {

            return BlockFace.UP;

        }

    }

    public static boolean isHittingFromSide(BlockFace lastHitBlockFace) {

        return lastHitBlockFace != BlockFace.UP && lastHitBlockFace != BlockFace.DOWN;

    }

}
