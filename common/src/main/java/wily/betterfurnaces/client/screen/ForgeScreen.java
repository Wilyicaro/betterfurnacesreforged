package wily.betterfurnaces.client.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import wily.betterfurnaces.BetterFurnacesReforged;
import wily.betterfurnaces.inventory.ForgeMenu;



public class ForgeScreen extends SmeltingScreen<ForgeMenu> {

    public ResourceLocation GUI() { return new ResourceLocation(BetterFurnacesReforged.MOD_ID + ":" + "textures/container/forge_gui.png");}
    protected int factoryShowButtonY(){return 44;}
    protected int[] fluidTankPos() { return new int[]{26,98};} // 20x22pixels
    protected int[] energyCellPos() { return new int[]{8,62};} // 16x34pixels
    protected int[] xpTankPos() { return new int[]{126,102};} // 16x16pixels

    public ForgeScreen(ForgeMenu t, Inventory inv, Component name) {
        super(t, inv, name);
    }

    @Override
    protected void init() {
        imageHeight = 206;
        super.init();
    }

    @Override
    protected void blitSlotsLayer(PoseStack stack, boolean input, boolean both, boolean fuel, boolean output){
        if (input || both) {
            this.blit(stack, relX() + 26, relY() + 61, 0, 171, 18, 18);
            this.blit(stack, relX() + 44, relY() + 61, 0, 171, 18, 18);
            this.blit(stack, relX() + 62, relY() + 61, 0, 171, 18, 18);
        }
        if (output || both) {
            this.blit(stack, relX() + 103, relY() + 75, 0, 229, 62, 26);
        }
        if (fuel) {
            this.blit(stack, relX() + 7, relY() + 99, 18, 171, 18, 18);
        }
    }
    @Override
    protected void blitSmeltingSprites(PoseStack matrix) {
        int i;
        if (this.getMenu().BurnTimeGet() > 0) {
            i = this.getMenu().getBurnLeftScaled(13);
            this.blit(matrix, relX() + 28, relY() + 93 - i, 176, 12 - i, 14, i + 1);
            this.blit(matrix, relX() + 46, relY() + 93 - i, 176, 12 - i, 14, i + 1);
            this.blit(matrix, relX() + 64, relY() + 93 - i, 176, 12 - i, 14, i + 1);
        }

        i = this.getMenu().getCookScaled(24);
        this.blit(matrix, relX() + 80, relY() + 80, 176, 14, i + 1, 16);
    }

}