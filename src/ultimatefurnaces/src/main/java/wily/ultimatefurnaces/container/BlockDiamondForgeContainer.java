package wily.ultimatefurnaces.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import wily.betterfurnaces.container.BlockForgeContainerBase;
import wily.ultimatefurnaces.init.Registration;

public class BlockDiamondForgeContainer extends BlockForgeContainerBase {

    public BlockDiamondForgeContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(Registration.DIAMOND_FORGE_CONTAINER.get(), windowId, world, pos, playerInventory, player);
    }

    public BlockDiamondForgeContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player, IIntArray fields) {
        super(Registration.DIAMOND_FORGE_CONTAINER.get(), windowId, world, pos, playerInventory, player, fields);
    }


    @Override
    public boolean stillValid(PlayerEntity playerIn) {
        return stillValid(IWorldPosCallable.create(te.getLevel(), te.getBlockPos()), playerEntity, Registration.DIAMOND_FORGE.get());
    }
}
