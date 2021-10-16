package wily.betterfurnaces.tileentity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraftforge.common.ForgeConfigSpec;
import wily.betterfurnaces.Config;
import wily.betterfurnaces.container.BlockGoldFurnaceContainer;
import wily.betterfurnaces.init.Registration;

public class BlockGoldFurnaceTile extends BlockFurnaceTileBase {
    public BlockGoldFurnaceTile() {
        super(Registration.GOLD_FURNACE_TILE.get());
    }

    @Override
   public ForgeConfigSpec.IntValue getCookTimeConfig() {
        return Config.goldTierSpeed;
    }

    @Override
    public String IgetName() {
        return "block.betterfurnacesreforged.gold_furnace";
    }

    @Override
    public Container IcreateMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new BlockGoldFurnaceContainer(i, level, worldPosition, playerInventory, playerEntity, this.fields);
    }

}
