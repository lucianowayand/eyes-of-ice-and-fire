package io.github.lucianowayand.eyesoficeandfire;

import com.github.alexthe666.iceandfire.entity.DragonType;
import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class EnderEyes extends Item {
    public EnderEyes(Properties properties, DragonType type) {
        super(properties);
        this.type = type;
    }
    DragonType type;

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull InteractionResultHolder<ItemStack> use(Level levelIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);

        EntityDragonBase dragonFound = getClosestDragon(levelIn, playerIn, type);
        if (dragonFound == null) {
            playerIn.displayClientMessage(Component.translatable("chat.no_dragons_found"), true);
            return InteractionResultHolder.pass(itemstack);
        } else {
            playerIn.startUsingItem(handIn);
            if (levelIn instanceof ServerLevel) {
                BlockPos blockpos = new BlockPos(dragonFound.getHeadPosition().x, dragonFound.getHeadPosition().y, dragonFound.getHeadPosition().z);
                EyeOfEnder eye_of_ender_entity = new EyeOfEnder(levelIn, playerIn.getX(), playerIn.getY(0.5D), playerIn.getZ());
                eye_of_ender_entity.setItem(itemstack);
                eye_of_ender_entity.signalTo(blockpos);

                levelIn.addFreshEntity(eye_of_ender_entity);
                if (playerIn instanceof ServerPlayer) {
                    CriteriaTriggers.USED_ENDER_EYE.trigger((ServerPlayer) playerIn, blockpos);
                }

                levelIn.playSound(null, playerIn.blockPosition(), SoundEvents.ENDER_EYE_LAUNCH, SoundSource.NEUTRAL, 0.5F, 0.4F / (levelIn.getRandom().nextFloat() * 0.4F + 0.8F));
                levelIn.levelEvent(null, 1003, playerIn.blockPosition(), 0);

                if (!playerIn.isCreative()) {
                    itemstack.shrink(1);
                }

                playerIn.awardStat(Stats.ITEM_USED.get(this));
                playerIn.swing(handIn, true);
                return InteractionResultHolder.success(itemstack);
            }
            return InteractionResultHolder.consume(itemstack);
        }
    }

    private EntityDragonBase getClosestDragon(Level world, Player player, DragonType type) {
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();

        AABB box = new AABB(x-576,-64,z-576,x+576,y+320,z+576);
        List<EntityDragonBase> listOfTargets = world.getEntitiesOfClass(EntityDragonBase.class, box);
        //String s = "Identified " + listOfTargets.size() + " dragons.";
        //player.displayClientMessage(Component.literal(s), false);

        //List<LivingEntity> allEntities = world.getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT, player, box);
        //s = "Identified " + allEntities.size() + " total entities.";
        //player.displayClientMessage(Component.literal(s), false);


        float min = 0;
        EntityDragonBase closest = null;
        for (EntityDragonBase target : listOfTargets) {
            if (!target.isModelDead() && !target.isTame() && target.dragonType == type) {
                float distance = target.distanceTo(player);
                if ((min == 0) || distance < min) {
                    min = distance;
                    closest = target;
                    //s = "Found dragon, updating minimum distance";
                    //player.displayClientMessage(Component.literal(s), false);
                //} else if (distance >= min) {
                    //s = "Found further dragon, ignoring";
                    //player.displayClientMessage(Component.literal(s), false);
                }
            //} else if (target.isModelDead()) {
                //s = "Found corpse, ignoring";
                //player.displayClientMessage(Component.literal(s), false);
            //} else if (target.isTame()) {
                //s = "Found tamed dragon, ignoring";
                //player.displayClientMessage(Component.literal(s), false);
            }
        }

        return closest;
    }
}
