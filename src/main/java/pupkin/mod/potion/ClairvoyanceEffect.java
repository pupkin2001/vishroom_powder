package pupkin.mod.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.UUID;

public class ClairvoyanceEffect extends Potion
{
	public static final Potion CLAIRVOYANCE = new ClairvoyanceEffect();

	private static final float FOG_DENSITY = 0.005F;
	private static final float FOG_START = 500.0F;
	private static final float FOG_END = 1000.0F;

	private static final UUID SPEED_MODIFIER_UUID = UUID.fromString("59F84442-AD87-403f-A260-4F24CE154978"); // Unique ID for the speed modifier

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
			IAttributeInstance movementSpeed = player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);

			if (movementSpeed.getModifier(SPEED_MODIFIER_UUID) == null) {
				movementSpeed.applyModifier(new AttributeModifier(SPEED_MODIFIER_UUID, "Clairvoyance Speed Boost", 1.2D, 2));
			}
		}
	}

	@Override
	public void removeAttributesModifiersFromEntity(@Nonnull EntityLivingBase entityLivingBaseIn, @Nonnull AbstractAttributeMap attributeMapIn, int amplifier)
	{
		if (entityLivingBaseIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLivingBaseIn;
			IAttributeInstance movementSpeed = player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);

			if (movementSpeed.getModifier(SPEED_MODIFIER_UUID) != null) {
				movementSpeed.removeModifier(Objects.requireNonNull(movementSpeed.getModifier(SPEED_MODIFIER_UUID)));
			}
		}
	}
}
