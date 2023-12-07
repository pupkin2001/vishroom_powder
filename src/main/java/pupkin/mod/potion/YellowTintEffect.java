package pupkin.mod.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;

public class YellowTintEffect extends Potion {
	public static final Potion YELLOW_TINT = new YellowTintEffect();

	public YellowTintEffect() {
		super(false, 0xFFFF00);
		setRegistryName("yellow_tint");
		setPotionName("effect.yellow_tint");
	}

	@Override
	public void performEffect(@Nonnull EntityLivingBase entity, int amplifier) {

	}
}
