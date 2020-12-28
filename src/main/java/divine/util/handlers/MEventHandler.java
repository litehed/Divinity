package divine.util.handlers;

import net.minecraft.client.Minecraft;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import divine.capabilities.race.IRaces;
import divine.capabilities.race.RacesCap;
import divine.client.gui.GuiTestCreation;
import divine.init.MKeyBindings;
import divine.main.Reference;

@EventBusSubscriber(modid = Reference.MODID)
public class MEventHandler {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public static void onEvent(KeyInputEvent event)
	{
		if(MKeyBindings.keyBindings.get(0).isPressed())
			Minecraft.getMinecraft().displayGuiScreen(new GuiTestCreation());	
	}
	@SubscribeEvent
	public static void onPlayerSleep(PlayerSleepInBedEvent event) {
		IRaces race = RacesCap.get(event.getEntityPlayer());
		System.out.println("Current Race: " + race.getRace());
	}
	 
}
