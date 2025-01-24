package net.kaupenjoe.tutorialmod.init;

import net.kaupenjoe.tutorialmod.api.block.BOPBlocks;
import net.kaupenjoe.tutorialmod.api.item.BOPItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.BlockHitResult;

import static glitchcore.util.BlockHelper.*;

public class ModVanillaCompat
{

    @SuppressWarnings("all")
    public static void setup()
    {
        //Dispenser Behavior
        DispenseItemBehavior dispenseBucketBehavior = new DefaultDispenseItemBehavior() {
            private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

            public ItemStack execute(BlockSource p_123561_, ItemStack p_123562_) {
                DispensibleContainerItem dispensiblecontaineritem = (DispensibleContainerItem)p_123562_.getItem();
                BlockPos blockpos = p_123561_.getPos().relative(p_123561_.getBlockState().getValue(DispenserBlock.FACING));
                Level level = p_123561_.getLevel();
                if (dispensiblecontaineritem.emptyContents((Player)null, level, blockpos, (BlockHitResult)null)) {
                    dispensiblecontaineritem.checkExtraContent((Player)null, level, p_123562_, blockpos);
                    return new ItemStack(Items.BUCKET);
                } else {
                    return this.defaultDispenseItemBehavior.dispense(p_123561_, p_123562_);
                }
            }
        };

        //todo: bring back when creative tab is solved.
        //Flammability
        registerFlammable(BOPBlocks.REDWOOD_LEAVES, 30, 60);
        registerFlammable(BOPBlocks.REDWOOD_LOG, 5, 5);
        registerFlammable(BOPBlocks.REDWOOD_WOOD, 5, 5);
        registerFlammable(BOPBlocks.WILLOW_VINE, 15, 100);
        registerFlammable(BOPBlocks.SPANISH_MOSS, 60, 100);
//        registerFlammable(BOPBlocks.DEAD_BRANCH, 60, 100);

        //TODO: Bring back! but this causes crack right now.
//        registerCompostable(0.3F, BOPBlocks.REDWOOD_SAPLING);
//        registerCompostable(0.3F, BOPBlocks.REDWOOD_LEAVES);
//        registerCompostable(0.5F, BOPBlocks.WILLOW_VINE);
//        registerCompostable(0.5F, BOPBlocks.SPANISH_MOSS);
//        registerCompostable(0.3F, BOPBlocks.DEAD_BRANCH);
//        registerTillable(BOPBlocks.ORIGIN_GRASS_BLOCK, HoeItem::onlyIfAirAbove, Blocks.FARMLAND.defaultBlockState());
    }
}