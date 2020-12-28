package divine.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ServerProxy implements IProxy{

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FMLInitializationEvent event) {
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serverStarting(FMLServerStartingEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EntityPlayer getPlayerEntityFromContext(MessageContext context) {
		return context.getServerHandler().player;
	}

	@Override
	public IThreadListener getThreadFromContext(MessageContext context) {
		return context.getServerHandler().player.getServer();
	}

}
