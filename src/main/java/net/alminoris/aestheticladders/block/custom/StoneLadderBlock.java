package net.alminoris.aestheticladders.block.custom;

import net.alminoris.aestheticladders.util.helper.VoxelShapeHelper;
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
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class StoneLadderBlock extends Block implements Waterloggable
{
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public static final BooleanProperty MOSSED = BooleanProperty.of("mossed");

    private static final VoxelShape SHAPE = StoneLadderBlock.createCuboidShape(0, 0, 5, 16, 16, 11);

    public StoneLadderBlock()
    {
        super(AbstractBlock.Settings.copy(Blocks.STONE));
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(MOSSED, false));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
    {
        ItemStack stack = player.getStackInHand(hand);

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

            return ActionResult.SUCCESS;
        }

        if (stack.getItem() == Items.SHEARS && currentMossed)
        {
            if (!world.isClient)
            {
                currentMossed = false;

                world.setBlockState(pos, state
                        .with(FACING, currentFacing)
                        .with(MOSSED, currentMossed));

                stack.damage(1, player, p -> p.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            }

            return ActionResult.SUCCESS;
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        return getRotatedShape(state);
    }

    private VoxelShape getRotatedShape(BlockState state)
    {
        Direction direction = state.get(FACING);

        List<Box> boxes = new ArrayList<>();
        boxes.add(SHAPE.getBoundingBox());

        return VoxelShapeHelper.rotateShape(boxes, direction);
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

    @Override
    public BlockRenderType getRenderType(BlockState state)
    {
        return BlockRenderType.MODEL;
    }
}