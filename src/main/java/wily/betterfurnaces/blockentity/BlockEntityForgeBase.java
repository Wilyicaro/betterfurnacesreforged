package wily.betterfurnaces.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public abstract class BlockEntityForgeBase extends BlockEntitySmeltingBase {
    @Override
    public int FUEL() {return 3;}
    @Override
    public int UPGRADEORE(){ return 7;}
    @Override
    public int UPGRADEENDER(){ return 8;}
    @Override
    public int UPGRADEXP(){ return 9;}
    @Override
    public int UPGRADEFLUID(){ return 10;}
    @Override
    public int UPGRADEENERGY(){ return 10;}
    @Override
    public int UPGRADEFACTORY(){return 11;}
    @Override
    public int UPGRADECOLOR() {return 12;}
    @Override
    public int[] INPUTS(){ return new int[]{0,1,2};}
    @Override
    public int[] OUTPUTS(){ return new int[]{4,5,6};}
    @Override
    public int EnergyUse() {return 1800;}
    @Override
    public int LiquidCapacity() {return 8000;}
    @Override
    public boolean isForge(){ return true;}
    @Override
    public Direction facing(){
        return this.getBlockState().getValue(BlockStateProperties.FACING);
    }

    public BlockEntityForgeBase(BlockEntityType<?> tileentitytypeIn, BlockPos pos, BlockState state) {
        super(tileentitytypeIn, pos, state, 14);
    }

    @Override
    public boolean inputSlotsEmpty(){
        return (!this.inventory.get(FINPUT()).isEmpty() || !this.inventory.get(FINPUT() +1).isEmpty() || !this.inventory.get(FINPUT() + 2).isEmpty());
    }
    @Override
    public boolean smeltValid(){
        return (this.canSmelt(irecipeSlot(FINPUT()).orElse(null), FINPUT(), FOUTPUT()) || this.canSmelt(irecipeSlot(FINPUT() + 1).orElse(null), FINPUT() + 1, FOUTPUT() + 1) || this.canSmelt(irecipeSlot(FINPUT() + 2).orElse(null), FINPUT() + 2, FOUTPUT() + 2));
    }
    @Override
    public void trySmelt(){
        this.smeltItem(irecipeSlot(FINPUT()).orElse(null), FINPUT(), correspondentOutputSlot(FINPUT()));
        this.smeltItem(irecipeSlot(FINPUT() + 1).orElse(null), FINPUT() + 1, correspondentOutputSlot(FINPUT() + 1));
        this.smeltItem(irecipeSlot(FINPUT() + 2).orElse(null), FINPUT() + 2, correspondentOutputSlot(FINPUT() + 2));
    }
}