package wily.ultimatefurnaces.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import wily.betterfurnaces.blocks.BlockFurnaceBase;
import wily.ultimatefurnaces.init.RegistrationUF;
import wily.ultimatefurnaces.blockentity.BlockEntityCopperFurnace;

import javax.annotation.Nullable;

public class BlockCopperFurnace extends BlockFurnaceBase {

    public static final String COPPER_FURNACE = "copper_furnace";

    public BlockCopperFurnace(Properties properties) {
        super(properties);
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new BlockEntityCopperFurnace(p_153215_, p_153216_);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createFurnaceTicker(level, type, RegistrationUF.COPPER_FURNACE_TILE.get());
    }
}