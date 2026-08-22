package studio.akuro.arsqol.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import studio.akuro.arsqol.ArsQol;

public record QuickCastPayload() implements CustomPacketPayload {

    public static final QuickCastPayload INSTANCE = new QuickCastPayload();

    public static final Type<QuickCastPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(ArsQol.MOD_ID, "quick_cast"));

    public static final StreamCodec<ByteBuf, QuickCastPayload> STREAM_CODEC = StreamCodec.unit(INSTANCE);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
