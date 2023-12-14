package pupkin.mod.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class DistortionEffect extends Potion
{
	public static final Potion DISTORTION = new DistortionEffect();
	public static final ResourceLocation TEXTURE = new ResourceLocation("vishroom_powder:textures/screenspace/sweet_sugar_cube_engi.png");

	private static final float DISTORTION_STRENGTH = 0.2F;

	private DistortionEffect()
	{
		super(true, 0x00FF00);
		setRegistryName("distortion");
		setPotionName("effect.distortion");
	}

	public static float getDistortionStrength()
	{
		return DISTORTION_STRENGTH;
	}

	@Override
	public void performEffect(@Nonnull EntityLivingBase entity, int amplifier)
	{
	}
}
