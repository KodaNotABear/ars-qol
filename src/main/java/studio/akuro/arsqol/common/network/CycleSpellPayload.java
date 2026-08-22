package studio.akuro.arsqol.common.network;

import com.mojang.datafixers.types.Type;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import studio.akuro.arsqol.ArsQol;

public record CycleSpellPayload(boolean forward) implements CustomPacketPayload {

    public static final Type<CycleSpellPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(ArsQol.MOD_ID,"cycle_spell"));
    public static final StreamCodec<ByteBuf, CycleSpellPayload> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.BOOL, CycleSpellPayload::forward, CycleSpellPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }
}
