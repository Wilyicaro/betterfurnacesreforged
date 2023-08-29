package wily.betterfurnaces.inventory;

import dev.architectury.fluid.FluidStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.Level;
import wily.betterfurnaces.BetterFurnacesReforged;
import wily.betterfurnaces.blockentity.SmeltingBlockEntity;
import wily.betterfurnaces.init.Registration;
import wily.betterfurnaces.network.Messages;
import wily.betterfurnaces.network.PacketSyncEnergy;
import wily.betterfurnaces.network.PacketSyncFluid;
import wily.factoryapi.base.Storages;


public class SmeltingMenu extends AbstractInventoryMenu<SmeltingBlockEntity> {



    public SmeltingMenu(MenuType<?> containerType, int windowId, Level world, BlockPos pos, Inventory playerInventory, Player player) {
        this(containerType, windowId, world, pos, playerInventory, player, new SimpleContainerData(5));
    }
    public SmeltingMenu(int windowId, Level world, BlockPos pos, Inventory playerInventory, Player player) {
        this(Registration.FURNACE_CONTAINER.get(), windowId, world, pos, playerInventory, player, new SimpleContainerData(5));
    }

    public SmeltingMenu(MenuType<?> containerType, int windowId, Level world, BlockPos pos, Inventory playerInventory, Player player, ContainerData fields) {
        super(containerType, windowId, world, pos, playerInventory, player, fields);
        checkContainerDataCount(this.fields, 5);
    }


    public boolean showInventoryButtons() {
        return this.fields.get(4) == 1;
    }

    public int getRedstoneMode() {
        return this.be.getRedstoneSetting();
    }

    public int getComSub() {
        return this.be.getRedstoneComSub();
    }

    public boolean getAutoInput() {
        return this.be.getAutoInput() == 1;
    }

    public boolean getAutoOutput() {
        return this.be.getAutoOutput() == 1;
    }

    public Component getTooltip(int index) {
        switch (be.furnaceSettings.get(index))
        {
            case 1:
                return Component.translatable("tooltip." + BetterFurnacesReforged.MOD_ID + ".gui_input");
            case 2:
                return Component.translatable("tooltip." + BetterFurnacesReforged.MOD_ID + ".gui_output");
            case 3:
                return Component.translatable("tooltip." + BetterFurnacesReforged.MOD_ID + ".gui_input_output");
            case 4:
                return Component.translatable("tooltip." + BetterFurnacesReforged.MOD_ID + ".gui_fuel");
            default:
                return Component.translatable("tooltip." + BetterFurnacesReforged.MOD_ID + ".gui_none");
        }
    }

    public int getSettingTop()
    {
        return this.be.getSettingTop();
    }

    public int getSettingBottom()
    {
        return this.be.getSettingBottom();
    }

    public int getSettingFront()
    {
        return this.be.getSettingFront();
    }

    public int getSettingBack()
    {
        return this.be.getSettingBack();
    }

    public int getSettingLeft()
    {
        return this.be.getSettingLeft();
    }

    public int getSettingRight()
    {
        return this.be.getSettingRight();
    }

    public int getIndexFront()
    {
        return this.be.getIndexFront();
    }

    public int getIndexBack()
    {
        return this.be.getIndexBack();
    }

    public int getIndexLeft()
    {
        return this.be.getIndexLeft();
    }

    public int getIndexRight()
    {
        return this.be.getIndexRight();
    }

    public BlockPos getPos() {
        return this.be.getBlockPos();
    }

    public int getCookScaled(int pixels) {
        int i = this.fields.get(2);
        int j = this.fields.get(3);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    public int getBurnLeftScaled(int pixels) {
        int i = this.fields.get(1);
        if (i == 0) {
            i = 200;
        }

        return this.fields.get(0) * pixels / i;
    }
    public int getEnergyStored() {
        return be.energyStorage.getEnergyStored();
    }
    public int getMaxEnergyStored() {
        return be.energyStorage.getMaxEnergyStored();
    }
    public int BurnTimeGet(){
        return this.fields.get(0);
    }
    protected void updateChanges() {
        super.updateChanges();
        if (player instanceof ServerPlayer sp) {
            for (Direction d : Direction.values()) {
                be.getStorage(Storages.FLUID, d).ifPresent(t-> Messages.INSTANCE.sendToPlayer(sp, new PacketSyncFluid(be.getBlockPos(), d, t.getFluidStack())));
            }
            Messages.INSTANCE.sendToPlayer(sp, new PacketSyncEnergy(be.getBlockPos(),  be.energyStorage.getEnergyStored()));
        }
    }
    @Override
    public boolean stillValid(Player playerIn) {
        return stillValid(ContainerLevelAccess.create(be.getLevel(), be.getBlockPos()), player, be.getBlockState().getBlock());
    }
}