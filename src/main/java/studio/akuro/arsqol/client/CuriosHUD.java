package studio.akuro.arsqol.client;

import com.hollingsworth.arsnouveau.api.registry.SpellCasterRegistry;
import com.hollingsworth.arsnouveau.api.spell.AbstractCaster;
import com.hollingsworth.arsnouveau.api.util.StackUtil;
import com.hollingsworth.arsnouveau.setup.config.Config;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.world.item.ItemStack;
import studio.akuro.arsqol.common.CuriosBookUtil;

public class CuriosHUD {
    public static final LayeredDraw.Layer OVERLAY = CuriosHUD::renderOverlay;

    private static void renderOverlay(GuiGraphics graphics, DeltaTracker deltaTracker) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.options.hideGui) return;

        ItemStack book = CuriosBookUtil.fallbackBook(minecraft.player);
        if (book.isEmpty()) return;
        AbstractCaster<?> caster = SpellCasterRegistry.from(book);
        if (caster == null) return;

        String str = caster.getCurrentSlot() + 1 + " " + caster.getSpellName();
        graphics.drawString(minecraft.font, str, Config.SPELLNAME_X_OFFSET.get(), minecraft.getWindow().getGuiScaledHeight() - Config.SPELLNAME_Y_OFFSET.get(), 0xFFFFFF);
    }
}
