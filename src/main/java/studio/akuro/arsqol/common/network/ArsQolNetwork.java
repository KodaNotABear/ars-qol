package studio.akuro.arsqol.common.network;

import com.hollingsworth.arsnouveau.api.registry.SpellCasterRegistry;
import com.hollingsworth.arsnouveau.api.spell.AbstractCaster;
import com.hollingsworth.arsnouveau.api.util.StackUtil;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import studio.akuro.arsqol.ArsQol;
import studio.akuro.arsqol.common.CuriosBookUtil;
import studio.akuro.arsqol.common.QuickCastLogic;

@EventBusSubscriber(modid = ArsQol.MOD_ID)
public class ArsQolNetwork {

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToServer(QuickCastPayload.TYPE, QuickCastPayload.STREAM_CODEC, ArsQolNetwork::handleQuickCast);
        registrar.playToServer(CycleSpellPayload.TYPE, CycleSpellPayload.STREAM_CODEC, ArsQolNetwork::handleCycle);
    }

    private static void handleQuickCast(QuickCastPayload payload, IPayloadContext context) {
        QuickCastLogic.castFromCurios((ServerPlayer) context.player());
    }

    private static void handleCycle(CycleSpellPayload payload, IPayloadContext context) {
        if (!StackUtil.getHeldSpellbook(context.player()).isEmpty()) return;
        ItemStack book = CuriosBookUtil.findBook(context.player());
        if (book.isEmpty()) return;
        AbstractCaster<?> caster = SpellCasterRegistry.from(book);
        if (caster == null) return;
        if (payload.forward()) {
            caster.setNextSlot().saveToStack(book);
        } else {
            caster.setPreviousSlot().saveToStack(book);
        }
    }
}
