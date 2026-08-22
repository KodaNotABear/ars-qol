package studio.akuro.arsqol.common.network;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import studio.akuro.arsqol.ArsQol;
import studio.akuro.arsqol.common.QuickCastLogic;

@EventBusSubscriber(modid = ArsQol.MOD_ID)
public class ArsQolNetwork {

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToServer(QuickCastPayload.TYPE, QuickCastPayload.STREAM_CODEC, ArsQolNetwork::handleQuickCast);
    }

    private static void handleQuickCast(QuickCastPayload payload, IPayloadContext context) {
        QuickCastLogic.castFromCurios((ServerPlayer) context.player());
    }
}
