package studio.akuro.arsqol.mixin;

import com.hollingsworth.arsnouveau.client.gui.book.SpellSlottedScreen;
import com.hollingsworth.arsnouveau.common.items.SpellBook;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public class LivingEntityHandMixin {

    @ModifyReturnValue(method = "getItemInHand", at = @At("RETURN"))
    private ItemStack arsqol$curiosBookInHand(ItemStack stack, InteractionHand hand) {
        if (!stack.isEmpty()) return stack;

        Minecraft minecraft = Minecraft.getInstance();
        if ((Object) this != minecraft.player) return stack;
        if (!(minecraft.screen instanceof SpellSlottedScreen screen) || screen.hand != hand) return stack;

        ItemStack book = screen.bookStack;
        return book != null && book.getItem() instanceof SpellBook ? book : stack;
    }
}
