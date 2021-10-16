package wily.ultimatefurnaces.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import wily.betterfurnaces.container.BlockFurnaceContainerBase;
import wily.ultimatefurnaces.init.Registration;

public class BlockUltimateFurnaceContainer extends BlockFurnaceContainerBase {

    public BlockUltimateFurnaceContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(wily.ultimatefurnaces.init.Registration.ULTIMATE_FURNACE_CONTAINER.get(), windowId, world, pos, playerInventory, player);
    }

    public BlockUltimateFurnaceContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player, IIntArray fields) {
        super(wily.ultimatefurnaces.init.Registration.ULTIMATE_FURNACE_CONTAINER.get(), windowId, world, pos, playerInventory, player, fields);
    }


    @Override
    public boolean stillValid(PlayerEntity playerIn) {
        return stillValid(IWorldPosCallable.create(te.getLevel(), te.getBlockPos()), playerEntity, Registration.ULTIMATE_FURNACE.get());
    }
}
