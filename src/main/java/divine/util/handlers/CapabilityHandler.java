package divine.util.handlers;

import divine.capabilities.classes.ClassesProvider;
import divine.capabilities.classes.IClasses;
import divine.capabilities.race.IRaces;
import divine.capabilities.race.RaceProvider;
import divine.main.Reference;
import divine.packets.PacketDispatcher;
import divine.packets.client.ClassPacket;
import divine.packets.client.RacePacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class CapabilityHandler {
	public static final ResourceLocation RACES = new ResourceLocation(Reference.MODID, "race");
	public static final ResourceLocation CLASSES = new ResourceLocation(Reference.MODID, "classes");
	 
	@SubscribeEvent
	 public void attachCapability(final AttachCapabilitiesEvent<Entity> event) {
	        final Object object = event.getObject();
	        if (!(object instanceof EntityPlayer)) {
	            return;
	        }

		 event.addCapability(RACES, new RaceProvider());
		 event.addCapability(CLASSES, new ClassesProvider());
	 }
	
	@SubscribeEvent
	public static void playerClone(PlayerEvent.Clone event) {
		final EntityPlayer player = event.getEntityPlayer();
		final IRaces newRace = player.getCapability(RaceProvider.RACES, null);
		final IRaces oldRace = event.getOriginal().getCapability(RaceProvider.RACES, null);
		newRace.copy(oldRace, player);
		
		final IClasses newClass = player.getCapability(ClassesProvider.CLASSES, null);
		final IClasses oldClass = event.getOriginal().getCapability(ClassesProvider.CLASSES, null);
		newClass.copy(oldClass, player);
	}
	@SubscribeEvent
    public void playerLoggedIn(final PlayerLoggedInEvent event) {
        final EntityPlayer player = event.player;
		final IRaces race = player.getCapability(RaceProvider.RACES, null);
		final IClasses classes = player.getCapability(ClassesProvider.CLASSES, null);
        PacketDispatcher.sendTo(new RacePacket(race), (EntityPlayerMP) player);
        PacketDispatcher.sendTo(new ClassPacket(classes), (EntityPlayerMP) player);

 }
    @SubscribeEvent
    public void onPlayerSpawn(final EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            final EntityPlayer player = (EntityPlayer)event.getEntity();
            final IRaces race = player.getCapability(RaceProvider.RACES, null);
            final IClasses classes = player.getCapability(ClassesProvider.CLASSES, null);
            if (!player.world.isRemote) {
            	PacketDispatcher.sendTo(new RacePacket(race), (EntityPlayerMP) player);
            	PacketDispatcher.sendTo(new ClassPacket(classes), (EntityPlayerMP) player);

            }}
        }
}
