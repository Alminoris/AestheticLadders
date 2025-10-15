package net.alminoris.aestheticladders.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class StoneLadderBlock extends Block implements Waterloggable
{
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public static final BooleanProperty MOSSED = BooleanProperty.of("mossed");

    public StoneLadderBlock()
    {
        super(AbstractBlock.Settings.copy(Blocks.STONE));
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(MOSSED, false));
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
    {
        boolean currentMossed = state.get(MOSSED);
        Direction currentFacing = state.get(FACING);

        if (stack.getItem() == Blocks.VINE.asItem() && !currentMossed)
        {
            if (!world.isClient)
            {
                currentMossed = true;

                world.setBlockState(pos, state
                        .with(FACING, currentFacing)
                        .with(MOSSED, currentMossed));

                stack.decrement(1);
            }

            return ItemActionResult.SUCCESS;
        }

        if (stack.getItem() == Items.SHEARS && currentMossed)
        {
            if (!world.isClient)
            {
                currentMossed = false;

                world.setBlockState(pos, state
                        .with(FACING, currentFacing)
                        .with(MOSSED, currentMossed));

                stack.damage(1, player, EquipmentSlot.MAINHAND);
            }

            return ItemActionResult.SUCCESS;
        }

        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx)
    {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        builder.add(FACING, MOSSED);
    }
}