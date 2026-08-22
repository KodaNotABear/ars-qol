package studio.akuro.arsqol.mixin;

import com.hollingsworth.arsnouveau.api.registry.SpellCasterRegistry;
import com.hollingsworth.arsnouveau.api.spell.AbstractCaster;
import com.hollingsworth.arsnouveau.api.util.StackUtil;
import com.hollingsworth.arsnouveau.common.network.PacketSetCasterSlot;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import studio.akuro.arsqol.common.CuriosBookUtil;

@Mixin(PacketSetCasterSlot.class)
public class PacketSetCasterSlotMixin {

    @Shadow
    public int slot;

    @Inject(method="onServerReceived", at = @At("HEAD"), remap = false, cancellable = true)
    private void arsqol$curiosFallback(MinecraftServer server, ServerPlayer player, CallbackInfo ci) {
        if (!StackUtil.getHeldSpellbook(player).isEmpty()) return;
        ItemStack book = CuriosBookUtil.findBook(player);
        if (book.isEmpty()) return;
        AbstractCaster<?> caster = SpellCasterRegistry.from(book);
        if (caster == null) return;
        caster.setCurrentSlot(slot).saveToStack(book);
        ci.cancel();
    }
}
