package pupkin.mod.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import pupkin.mod.potion.YellowTintEffect;

@Mod.EventBusSubscriber
public class RenderEventHandler {

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void onRenderOverlay(RenderGameOverlayEvent.Pre event) {
		if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
			Minecraft mc = Minecraft.getMinecraft();
			if (mc.player != null && mc.player.isPotionActive(YellowTintEffect.YELLOW_TINT)) {
				// Отключаем тест глубины перед отрисовкой
				GL11.glDisable(GL11.GL_DEPTH_TEST);

				ScaledResolution scaledResolution = new ScaledResolution(mc);

				// Запоминаем текущие матрицы OpenGL
				GL11.glPushMatrix();

				// Рисуем желтый прямоугольник в текущем буфере
				GL11.glColor4f(1.0F, 1.0F, 0.0F, 0.2F);
				Gui.drawRect(0, 0, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), 0xFFFF00);

				// Восстанавливаем матрицы OpenGL
				GL11.glPopMatrix();

				// Включаем тест глубины после отрисовки
				GL11.glEnable(GL11.GL_DEPTH_TEST);

				// Отменяем отрисовку интерфейса по умолчанию
				event.setCanceled(true);
			}
		}
	}
}
