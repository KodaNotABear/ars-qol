package studio.akuro.arsqol.mixin;

import com.hollingsworth.arsnouveau.common.network.PacketUpdateCaster;
import com.hollingsworth.arsnouveau.common.network.PacketUpdateParticleTimeline;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import studio.akuro.arsqol.common.CuriosBookUtil;

@Mixin({PacketUpdateCaster.class, PacketUpdateParticleTimeline.class})
public class SpellBookSavePacketMixin {

    @ModifyExpressionValue(method = "onServerReceived", remap = false, at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;getItemInHand(Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/item/ItemStack;"))
    private ItemStack arsqol$useCuriosBook(ItemStack stack, MinecraftServer server, ServerPlayer player) {
        if (!stack.isEmpty()) return stack;
        return CuriosBookUtil.findBook(player);
    }
}
