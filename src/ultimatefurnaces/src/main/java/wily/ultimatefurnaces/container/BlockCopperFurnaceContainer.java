package wily.ultimatefurnaces.container;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import wily.betterfurnaces.container.BlockFurnaceContainerBase;
import wily.ultimatefurnaces.init.RegistrationUF;

public class BlockCopperFurnaceContainer extends BlockFurnaceContainerBase {

    public BlockCopperFurnaceContainer(int windowId, Level world, BlockPos pos, Inventory playerInventory, Player player) {
        super(wily.ultimatefurnaces.init.RegistrationUF.COPPER_FURNACE_CONTAINER.get(), windowId, world, pos, playerInventory, player);
    }

    public BlockCopperFurnaceContainer(int windowId, Level world, BlockPos pos, Inventory playerInventory, Player player, ContainerData fields) {
        super(wily.ultimatefurnaces.init.RegistrationUF.COPPER_FURNACE_CONTAINER.get(), windowId, world, pos, playerInventory, player, fields);
    }


    @Override
    public boolean stillValid(Player playerIn) {
        return stillValid(ContainerLevelAccess.create(te.getLevel(), te.getBlockPos()), playerEntity, RegistrationUF.COPPER_FURNACE.get());
    }
}