package me.dev.nux.wildtile.mining;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.List;

public class Levels {

    public static void mineLevel1(BlockFace lastBlockFaceHit, Block centerBlock, BlockFace playerFacing,
                                  List<Block> blocksToBreak) {

        blocksToBreak.add(WildTileMechanics.getBlockAtRight(centerBlock, lastBlockFaceHit, playerFacing));

        mineBlocks(blocksToBreak);

    }

    public static void mineLevel2(BlockFace lastBlockFaceHit, Block centerBlock, BlockFace playerFacing,
                                  List<Block> blocksToBreak) {

        Block block = WildTileMechanics.getBlockAbove(centerBlock, playerFacing, lastBlockFaceHit);
        blocksToBreak.add(block);
        blocksToBreak.add(WildTileMechanics.getBlockAtRight(centerBlock, lastBlockFaceHit, playerFacing));
        blocksToBreak.add(WildTileMechanics.getBlockAtRight(block, lastBlockFaceHit, playerFacing));

        mineBlocks(blocksToBreak);

    }

    public static void mineLevel3(BlockFace lastBlockFaceHit, Block centerBlock, BlockFace playerFacing,
                                  List<Block> blocksToBreak) {

        Block rightBlock = WildTileMechanics.getBlockAtRight(centerBlock, lastBlockFaceHit, playerFacing);

        Block tempCenter = rightBlock;

        for (int i = 0; i <= 2; ++i) {

            blocksToBreak.add(WildTileMechanics.getBlockAbove(tempCenter, playerFacing, lastBlockFaceHit));
            blocksToBreak.add(tempCenter.getRelative(WildTileMechanics.getBlockAboveFace(
                    tempCenter, playerFacing, lastBlockFaceHit
            ).getOppositeFace()));

            blocksToBreak.add(tempCenter);

            tempCenter = tempCenter.getRelative(WildTileMechanics.getBlockFaceAtRight(
                    centerBlock, lastBlockFaceHit, playerFacing
            ).getOppositeFace());

        }

        mineBlocks(blocksToBreak);

    }

    public static void mineLevel4(BlockFace lastBlockFaceHit, Block centerBlock, BlockFace playerFacing,
                                  List<Block> blocksToBreak) {

        Block mostRight = centerBlock;

        for (int i = 0; i < 2; ++i) {

            mostRight = WildTileMechanics.getBlockAtRight(mostRight, lastBlockFaceHit, playerFacing);

        }

        for (int i = 0; i <= 3; ++i) {

            Block up = mostRight;

            for (int y = 0; y <= 2; ++y) {
                blocksToBreak.add(up);
                up = WildTileMechanics.getBlockAbove(up, playerFacing, lastBlockFaceHit);
            }

            blocksToBreak.add(mostRight.getRelative(WildTileMechanics.getBlockAboveFace(mostRight, playerFacing, lastBlockFaceHit).getOppositeFace()));
            mostRight = mostRight.getRelative(WildTileMechanics.getBlockFaceAtRight(mostRight, lastBlockFaceHit, playerFacing).getOppositeFace());

        }

        mineBlocks(blocksToBreak);

    }

    public static void mineLevel5(BlockFace lastBlockFaceHit, Block centerBlock, BlockFace playerFacing,
                                  List<Block> blocksToBreak) {


        Block mostRight = centerBlock;

        for (int i = 0; i < 2; ++i) {
            mostRight = WildTileMechanics.getBlockAtRight(mostRight, lastBlockFaceHit, playerFacing);
        }

        for (int i = 0; i < 5; ++i) {

            Block up = mostRight;
            Block down = mostRight;

            for (int y = 0; y < 3; y++) {
                blocksToBreak.add(up);
                up = WildTileMechanics.getBlockAbove(up, playerFacing, lastBlockFaceHit);

                blocksToBreak.add(down);

                down = down.getRelative(WildTileMechanics.getBlockAboveFace(
                        centerBlock, playerFacing, lastBlockFaceHit
                ).getOppositeFace());

            }

            mostRight = mostRight.getRelative(WildTileMechanics.getBlockFaceAtRight(
                    centerBlock, lastBlockFaceHit, playerFacing).getOppositeFace());

        }

        mineBlocks(blocksToBreak);


    }

    private static void mineBlocks(List<Block> blocksToBreak) {

        for (Block b : blocksToBreak) {
            if (b.getType().equals(Material.BEDROCK))
                continue;
            b.breakNaturally();
        }

    }

}
