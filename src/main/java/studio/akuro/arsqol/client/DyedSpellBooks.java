package studio.akuro.arsqol.client;

import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import studio.akuro.arsqol.ArsQol;

public class DyedSpellBooks {
    public static final ResourceLocation GOLD_TEXTURE = ResourceLocation.fromNamespaceAndPath(ArsQol.MOD_ID, "textures/item/spellbook_gold_overlay.png");
    public static final ResourceLocation DYED_TEXTURE = ResourceLocation.fromNamespaceAndPath(ArsQol.MOD_ID, "textures/item/spellbook_grayscale.png");

    public static boolean isDyed(ItemStack stack) {
        return stack != null && stack.has(DataComponents.DYED_COLOR);
    }
}
