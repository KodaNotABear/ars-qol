package studio.akuro.arsqol.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import studio.akuro.arsqol.ArsQol;

public record QuickCastSlotPayload(int slot) implements CustomPacketPayload {

    public static final Type<QuickCastSlotPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(ArsQol.MOD_ID, "quick_cast_slot"));

    public static final StreamCodec<ByteBuf, QuickCastSlotPayload> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.VAR_INT, QuickCastSlotPayload::slot, QuickCastSlotPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }
}
