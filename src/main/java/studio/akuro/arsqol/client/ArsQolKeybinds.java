package studio.akuro.arsqol.client;

import com.hollingsworth.arsnouveau.client.gui.book.GuiSpellBook;
import com.hollingsworth.arsnouveau.client.registry.ModKeyBindings;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;
import studio.akuro.arsqol.ArsQol;
import studio.akuro.arsqol.common.CuriosBookUtil;
import studio.akuro.arsqol.common.network.CycleSpellPayload;
import studio.akuro.arsqol.common.network.QuickCastPayload;

@EventBusSubscriber(modid = ArsQol.MOD_ID, value = Dist.CLIENT)
public class ArsQolKeybinds {

    private static int ticksSinceCast = 0;

    public static final KeyMapping QUICK_CAST = new KeyMapping(
            "key.ars_qol.quick_cast",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            "key.categories.ars_qol");

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(QUICK_CAST);
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        if (QUICK_CAST.isDown() && ticksSinceCast >= 4) {
            ticksSinceCast = 0;
            PacketDistributor.sendToServer(QuickCastPayload.INSTANCE);
        }
        ticksSinceCast++;

        while (ModKeyBindings.NEXT_SLOT.consumeClick()) {
            PacketDistributor.sendToServer(new CycleSpellPayload(true));
        }

        while (ModKeyBindings.PREVIOUS_SLOT.consumeClick()) {
            PacketDistributor.sendToServer(new CycleSpellPayload(false));
        }

        while (ModKeyBindings.OPEN_BOOK.consumeClick()) {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.screen != null) continue;
            ItemStack book = CuriosBookUtil.fallbackBook(minecraft.player);
            if (book.isEmpty()) continue;
            minecraft.setScreen(new GuiSpellBook(InteractionHand.MAIN_HAND));
        }
    }
}
