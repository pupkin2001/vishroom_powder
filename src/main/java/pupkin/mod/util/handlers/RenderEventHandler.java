package pupkin.mod.util.handlers;

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
	public static void onRenderOverlay(RenderGameOverlayEvent event) {
		if (event.isCancelable() || !Minecraft.getMinecraft().player.isPotionActive(YellowTintEffect.YELLOW_TINT)) {
			return;
		}

		GL11.glColor4f(1F, 1F, 0.0F, 0.1F);
		GL11.glDisable(GL11.GL_LIGHTING);

		ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());

		Gui.drawRect(0, 0, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), 0xFFFF00);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glEnable(GL11.GL_LIGHTING);
	}
}
