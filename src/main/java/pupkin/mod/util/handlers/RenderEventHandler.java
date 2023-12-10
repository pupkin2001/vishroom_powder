package pupkin.mod.util.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import pupkin.mod.potion.YellowTintEffect;

import java.nio.FloatBuffer;

@Mod.EventBusSubscriber
public class RenderEventHandler
{
	private static float defaultFogDensity = 0.0F;
	private static float defaultFogStart = 0.0F;
	private static float defaultFogEnd = 1.0F;

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void onRenderWorldLast(EntityViewRenderEvent.FogColors event)
	{
		if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.isPotionActive(YellowTintEffect.YELLOW_TINT)) {
			Potion potion = YellowTintEffect.YELLOW_TINT;
			int color = potion.getLiquidColor();
			float red = (float) (color >> 16 & 255) / 255.0F;
			float green = (float) (color >> 8 & 255) / 255.0F;
			float blue = (float) (color & 255) / 255.0F;

			if (defaultFogDensity == 0.0F) {
				saveDefaultFogValues();
			}

			// Fog color
			GL11.glFog(GL11.GL_FOG_COLOR, setColorBuffer(red, green, blue));

			// Fog mode
			GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);

			// Fog parameters
			GL11.glFogf(GL11.GL_FOG_DENSITY, 0.05F); // Плотность тумана 1 всегда заполнит экран, 0 только окрасит дальний горизонт
			GL11.glFogf(GL11.GL_FOG_START, 0.0F); // Откуда начинается туман
			GL11.glFogf(GL11.GL_FOG_END, 640.0F); // Дальность видимости

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

	@SubscribeEvent(priority = EventPriority.NORMAL)
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

		// Convert color to integer in the range 0-255
		int r = (int) (red * 255);
		int g = (int) (green * 255);
		int b = (int) (blue * 255);

		// Draw rectangle with color
		Gui.drawRect(0, 0, event.getResolution().getScaledWidth(), event.getResolution().getScaledHeight(), (r << 16) | (g << 8) | b);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glEnable(GL11.GL_LIGHTING);

		GlStateManager.enableAlpha();
		GlStateManager.enableDepth();
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
