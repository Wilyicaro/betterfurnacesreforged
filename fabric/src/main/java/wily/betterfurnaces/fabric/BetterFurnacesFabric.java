package wily.betterfurnaces.fabric;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.world.level.block.entity.BlockEntityType;
import team.reborn.energy.api.EnergyStorage;
import wily.betterfurnaces.BetterFurnacesReforged;
import wily.betterfurnaces.Config;
import wily.betterfurnaces.blockentity.SmeltingBlockEntity;
import wily.betterfurnaces.blockentity.InventoryBlockEntity;
import net.fabricmc.api.ModInitializer;
import wily.betterfurnaces.init.Registration;
import wily.factoryapi.base.IFluidItem;
import wily.factoryapi.base.Storages;
import wily.factoryapi.fabric.base.FabricItemFluidStorage;
import wily.ultimatefurnaces.init.RegistrationUF;

import java.util.ArrayList;
import java.util.List;


public class BetterFurnacesFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        BetterFurnacesReforged.init();
        FluidStorage.ITEM.registerForItems((s, c)-> s.getItem() instanceof IFluidItem<?> fluid ? new FabricItemFluidStorage(c,fluid.getFluidStorageBuilder()) : null, Registration.GENERATOR.get());
        Registration.BLOCK_ENTITIES.forEach(blockEntityTypeRegistrySupplier ->  ItemStorage.SIDED.registerForBlockEntity((storage, d) -> storage instanceof InventoryBlockEntity be ? (Storage<ItemVariant>) be.getStorage(Storages.ITEM,d).map((s)-> s.getHandler()).orElse(null) : null, blockEntityTypeRegistrySupplier.get()));
        List<BlockEntityType<? extends SmeltingBlockEntity>> BEs =  new ArrayList<>(List.of(Registration.IRON_FURNACE_TILE.get(),Registration.GOLD_FURNACE_TILE.get(),Registration.DIAMOND_FURNACE_TILE.get(),Registration.NETHERHOT_FURNACE_TILE.get(),Registration.EXTREME_FURNACE_TILE.get(),Registration.EXTREME_FORGE_TILE.get()));
        if (Config.enableUltimateFurnaces.get()){
            BEs.addAll(List.of(RegistrationUF.COPPER_FURNACE_TILE.get(),RegistrationUF.STEEL_FURNACE_TILE.get(),RegistrationUF.AMETHYST_FURNACE_TILE.get(), RegistrationUF.PLATINUM_FURNACE_TILE.get(),RegistrationUF.IRON_FORGE_TILE.get(),RegistrationUF.GOLD_FORGE_TILE.get(),RegistrationUF.DIAMOND_FORGE_TILE.get(),RegistrationUF.NETHERHOT_FORGE_TILE.get(),RegistrationUF.ULTIMATE_FORGE_TILE.get()));
        }
        for (BlockEntityType<? extends SmeltingBlockEntity> blockEntityType: BEs) {
            EnergyStorage.SIDED.registerForBlockEntity((storage, d) ->storage.getStorage(Storages.ENERGY,d).map(s -> (EnergyStorage) s.getHandler()).orElse(null), blockEntityType);
            FluidStorage.SIDED.registerForBlockEntity((storage, d) -> storage.getStorage(Storages.FLUID, d).map((s)-> (Storage<FluidVariant>) s.getHandler()).orElse(null), blockEntityType);
        }
    }
}
