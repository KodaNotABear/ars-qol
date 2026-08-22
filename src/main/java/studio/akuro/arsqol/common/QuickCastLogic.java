package studio.akuro.arsqol.common;

import com.hollingsworth.arsnouveau.api.registry.SpellCasterRegistry;
import com.hollingsworth.arsnouveau.api.spell.AbstractCaster;
import com.hollingsworth.arsnouveau.api.spell.Spell;
import com.hollingsworth.arsnouveau.api.spell.SpellContext;
import com.hollingsworth.arsnouveau.api.spell.SpellResolver;
import com.hollingsworth.arsnouveau.api.spell.wrapped_caster.PlayerCaster;
import com.hollingsworth.arsnouveau.api.util.SpellUtil;
import com.hollingsworth.arsnouveau.common.items.SpellBook;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentSensitive;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class QuickCastLogic {

    private static final int COOLDOWN_TICKS = 4;
    private static final Map<UUID, Long> LAST_CAST = new HashMap<>();

    public static void castFromCurios(ServerPlayer player) {

        long now = player.level().getGameTime();
        if (now - LAST_CAST.getOrDefault(player.getUUID(), 0L) < COOLDOWN_TICKS) return;

        ItemStack book = CuriosBookUtil.findBook(player);
        LAST_CAST.put(player.getUUID(), now);

        AbstractCaster<?> caster = SpellCasterRegistry.from(book);
        Spell spell = caster.getSpell();
        if (!spell.isValid()) return;

        SpellContext context =  new SpellContext(player.level(), spell, player, new PlayerCaster(player), book);
        SpellResolver resolver = new SpellResolver(context);

        boolean isSensitive = spell.getBuffsAtIndex(0, player, AugmentSensitive.INSTANCE) > 0;
        double range = 0.5 + player.getAttribute(Attributes.BLOCK_INTERACTION_RANGE).getValue();
        HitResult result = SpellUtil.rayTrace(player, range, 1, isSensitive);

        if (result instanceof EntityHitResult  ehr && ehr.getEntity() instanceof LivingEntity living) {
            resolver.onCastOnEntity(book, living, InteractionHand.MAIN_HAND);
            return;
        }

        if (result instanceof BlockHitResult bhr && (result.getType() == HitResult.Type.BLOCK || isSensitive)) {
            UseOnContext useContext = new UseOnContext(player, InteractionHand.MAIN_HAND, bhr);
            resolver.onCastOnBlock(useContext);
            return;
        }
        resolver.onCast(book, player.level());
    }
}
