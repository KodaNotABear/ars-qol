package studio.akuro.arsqol.mixin;

import com.hollingsworth.arsnouveau.client.gui.book.SpellSlottedScreen;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import studio.akuro.arsqol.common.CuriosBookUtil;

@Mixin(SpellSlottedScreen.class)
public class SpellSlottedScreenMixin {

    @Shadow
    public Player player;

    @ModifyExpressionValue(method="<init>(Lnet/minecraft/world/InteractionHand;)V", remap = false, at = @At(value="INVOKE", target = "Lnet/minecraft/world/entity/player/Player;getItemInHand(Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/item/ItemStack;"))
    private ItemStack arsqol$useCuriosBook(ItemStack stack) {
        if (!stack.isEmpty()) return stack;
        return CuriosBookUtil.findBook(player);
    }
}
