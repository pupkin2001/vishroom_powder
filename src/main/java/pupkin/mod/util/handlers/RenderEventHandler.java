package pupkin.mod.util.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import pupkin.mod.potion.ClairvoyanceEffect;
import pupkin.mod.potion.YellowTintEffect;

import java.nio.FloatBuffer;

@Mod.EventBusSubscriber
public class RenderEventHandler
{
	private static float defaultFogDensity = 0.0F;
	private static float defaultFogStart = 0.0F;
	private static float defaultFogEnd = 1.0F;

	@SubscribeEvent(priority = EventPriority.NORMAL)
	@SideOnly(Side.CLIENT)
	public static void onRenderWorldLast(EntityViewRenderEvent.FogColors event)
	{
		Minecraft mc = Minecraft.getMinecraft();
		if (mc.player != null) {
			Potion yellowTint = YellowTintEffect.YELLOW_TINT;
			Potion clairvoyance = ClairvoyanceEffect.CLAIRVOYANCE;

			if (mc.player.isPotionActive(yellowTint) || mc.player.isPotionActive(clairvoyance)) {
				Potion activePotion = mc.player.getActivePotionEffect(yellowTint) != null ? yellowTint : clairvoyance;

				int liquidColor = activePotion.getLiquidColor();
				float red = (float) (liquidColor >> 16 & 255) / 255.0F;
				float green = (float) (liquidColor >> 8 & 255) / 255.0F;
				float blue = (float) (liquidColor & 255) / 255.0F;

				if (defaultFogDensity == 0.0F) {
					saveDefaultFogValues();
				}

				// Fog color
				GL11.glFog(GL11.GL_FOG_COLOR, setColorBuffer(red, green, blue));

				// Fog mode
				GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);

				// Fog parameters
				GL11.glFogf(GL11.GL_FOG_DENSITY, activePotion instanceof YellowTintEffect ? YellowTintEffect.getFogDensity() : ClairvoyanceEffect.getFogDensity());
				GL11.glFogf(GL11.GL_FOG_START, activePotion instanceof YellowTintEffect ? YellowTintEffect.getFogStart() : ClairvoyanceEffect.getFogStart());
				GL11.glFogf(GL11.GL_FOG_END, activePotion instanceof YellowTintEffect ? YellowTintEffect.getFogEnd() : ClairvoyanceEffect.getFogEnd());

				event.setRed(red);
				event.setGreen(green);
				event.setBlue(blue);
			} else {
				GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);
				GL11.glFogf(GL11.GL_FOG_DENSITY, defaultFogDensity);
				GL11.glFogf(GL11.GL_FOG_START, defaultFogStart);
				GL11.glFogf(GL11.GL_FOG_END, defaultFogEnd);
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	@SideOnly(Side.CLIENT)
	public static void onRenderOverlay(RenderGameOverlayEvent event)
	{
		if (event.isCancelable() || !Minecraft.getMinecraft().player.isPotionActive(YellowTintEffect.YELLOW_TINT)) {
			return;
		}

		GlStateManager.disableDepth();
		GlStateManager.disableAlpha();
		GlStateManager.disableBlend();

		Potion potion = YellowTintEffect.YELLOW_TINT;
		int color = potion.getLiquidColor();
		float red = (float) (color >> 16 & 255) / 255.0F;
		float green = (float) (color >> 8 & 255) / 255.0F;
		float blue = (float) (color & 255) / 255.0F;

		GL11.glColor4f(red, green, blue, 0.1F);
		GL11.glDisable(GL11.GL_LIGHTING);

		int r = (int) (red * 255);
		int g = (int) (green * 255);
		int b = (int) (blue * 255);

		ScaledResolution resolution = event.getResolution();
		Gui.drawRect(0, 0, resolution.getScaledWidth(), resolution.getScaledHeight(), (r << 16) | (g << 8) | b);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glEnable(GL11.GL_LIGHTING);

		GlStateManager.enableAlpha();
		GlStateManager.enableDepth();
	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	@SideOnly(Side.CLIENT)
	public static void onRenderOverlayClairvoyance(RenderGameOverlayEvent.Pre event)
	{
		if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
			Minecraft mc = Minecraft.getMinecraft();
			if (mc.player != null && mc.player.isPotionActive(ClairvoyanceEffect.CLAIRVOYANCE)) {
				GL11.glDisable(GL11.GL_DEPTH_TEST);

				ScaledResolution scaledResolution = new ScaledResolution(mc);

				GL11.glPushMatrix();

				GL11.glColor4f(1.0F, 1.0F, 0.0F, 0.2F);
				Gui.drawRect(0, 0, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), 0xFFFF00);

				GL11.glPopMatrix();

				GL11.glEnable(GL11.GL_DEPTH_TEST);
				event.setCanceled(true);
			}
		}
	}

	private static FloatBuffer setColorBuffer(float red, float green, float blue)
	{
		FloatBuffer colorBuffer = BufferUtils.createFloatBuffer(4);
		colorBuffer.put(red).put(green).put(blue).put(1.0F);
		colorBuffer.flip();
		return colorBuffer;
	}

	private static void saveDefaultFogValues()
	{
		defaultFogDensity = GL11.glGetFloat(GL11.GL_FOG_DENSITY);
		defaultFogStart = GL11.glGetFloat(GL11.GL_FOG_START);
		defaultFogEnd = GL11.glGetFloat(GL11.GL_FOG_END);
	}
}
