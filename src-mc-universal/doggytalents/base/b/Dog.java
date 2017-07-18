package doggytalents.base.b;

import doggytalents.entity.EntityDog;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

/**
 * 1.10.2 Code
 */
public class Dog extends EntityDog {

	public Dog(World word) {
		super(word);
	}

	@Override
	protected SoundEvent getHurtSound() {
        return SoundEvents.ENTITY_WOLF_HURT;
    }
	
	@Override
	public boolean shouldAttackEntity(EntityLivingBase target, EntityLivingBase owner) {
        if(!(target instanceof EntityCreeper) && !(target instanceof EntityGhast)) {
            if(target instanceof EntityDog) {
            	EntityDog entitydog = (EntityDog)target;

                if(entitydog.isTamed() && entitydog.getOwner() == owner)
                    return false;
            }
            else if(target instanceof EntityWolf) {
            	EntityWolf entitywolf = (EntityWolf)target;

                if(entitywolf.isTamed() && entitywolf.getOwner() == owner)
                    return false;
            }

            if(target instanceof EntityPlayer && owner instanceof EntityPlayer && !((EntityPlayer)owner).canAttackPlayer((EntityPlayer)target))
                return false;
            else if(target == owner)
            	return false;
            else
                return !(target instanceof EntityHorse) || !((EntityHorse)target).isTame();
        }
        
        return false;
    }
	
	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand, ItemStack stack) {
		return processInteractGENERAL(player, hand) ? true : super.processInteract(player, hand, stack);
	}
}
