package pupkin.mod.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;

import javax.annotation.Nonnull;

public class YellowTintEffect extends Potion
{
	public static final Potion YELLOW_TINT = new YellowTintEffect();

	private static final float FOG_DENSITY = 0.05F;
	private static final float FOG_START = 0.0F;
	private static final float FOG_END = 640.0F;

	private YellowTintEffect()
	{
		super(true, 0xFFFF00);
		setRegistryName("yellow_tint");
		setPotionName("effect.yellow_tint");
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
	}
}
