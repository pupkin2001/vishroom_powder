package pupkin.mod.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;

import javax.annotation.Nonnull;
import java.util.UUID;

public class ClairvoyanceEffect extends Potion
{
	public static final Potion CLAIRVOYANCE = new ClairvoyanceEffect();
	private static final float FOG_DENSITY = 0.005F;
	private static final float FOG_START = 500.0F;
	private static final float FOG_END = 1000.0F;
	private static final UUID CLAIRVOYANCE_MODIFIER_UUID = UUID.fromString("59F84442-AD87-403f-A260-4F24CE154978");
	private static final String MOVEMENT_SPEED_MODIFIER_NAME = "Clairvoyance Speed Boost";
	private static final String MINING_SPEED_MODIFIER_NAME = "Clairvoyance Mining Speed Boost";

	private ClairvoyanceEffect()
	{
		super(false, 0xd3bfe2);
		setRegistryName("clairvoyance");
		setPotionName("effect.clairvoyance");
	}

	public static float getFogDensity()
	{
		return FOG_DENSITY;
	}

	public static float getFogStart()
	{
		return FOG_START;
	}

	public static float getFogEnd()
	{
		return FOG_END;
	}

	@Override
	public void performEffect(@Nonnull EntityLivingBase entity, int amplifier)
	{
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			modifyAttribute(player, SharedMonsterAttributes.MOVEMENT_SPEED, MOVEMENT_SPEED_MODIFIER_NAME, 10 + 0.5 * amplifier);
			modifyAttribute(player, SharedMonsterAttributes.ATTACK_SPEED, MINING_SPEED_MODIFIER_NAME, 10 + 0.5 * amplifier);
		}
	}

	@Override
	public void removeAttributesModifiersFromEntity(@Nonnull EntityLivingBase entityLivingBaseIn, @Nonnull AbstractAttributeMap attributeMapIn, int amplifier)
	{
		if (entityLivingBaseIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLivingBaseIn;
			removeAttributeModifier(player, SharedMonsterAttributes.MOVEMENT_SPEED);
			removeAttributeModifier(player, SharedMonsterAttributes.ATTACK_SPEED);
		}
	}

	private void modifyAttribute(EntityPlayer player, net.minecraft.entity.ai.attributes.IAttribute attribute, String modifierName, double modifierValue)
	{
		IAttributeInstance attributeInstance = player.getEntityAttribute(attribute);
		AttributeModifier modifier = new AttributeModifier(CLAIRVOYANCE_MODIFIER_UUID, modifierName, modifierValue, 0); // Use a duration of 0 for permanent effect
		if (attributeInstance.getModifier(CLAIRVOYANCE_MODIFIER_UUID) == null) {
			attributeInstance.applyModifier(modifier);
		}
	}

	private void removeAttributeModifier(EntityPlayer player, net.minecraft.entity.ai.attributes.IAttribute attribute)
	{
		IAttributeInstance attributeInstance = player.getEntityAttribute(attribute);
		AttributeModifier modifier = attributeInstance.getModifier(CLAIRVOYANCE_MODIFIER_UUID);
		if (modifier != null) {
			attributeInstance.removeModifier(modifier);
		}
	}
}
