package divine.main;

import divine.capabilities.classes.ClassesCap;
import divine.capabilities.classes.ClassesStorage;
import divine.capabilities.classes.IClasses;
import divine.capabilities.race.IRaces;
import divine.capabilities.race.RaceStorage;
import divine.capabilities.race.RacesCap;
import divine.packets.PacketDispatcher;
import divine.proxies.IProxy;
import divine.util.handlers.CapabilityHandler;
import divine.util.handlers.MEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Version {
	
	@Instance
	public static Version instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;
	
	@EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
		proxy.preInit(event);
		PacketDispatcher.registerPackets();
    }
	
	@EventHandler
    public void init(FMLInitializationEvent event)
    {
		proxy.init(event);
		MinecraftForge.EVENT_BUS.register(new MEventHandler());
		
		CapabilityManager.INSTANCE.register(IRaces.class, new RaceStorage(), RacesCap::new);
		CapabilityManager.INSTANCE.register(IClasses.class, new ClassesStorage(), ClassesCap::new);
		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
    }
	
	@EventHandler
    public void postinit(FMLPostInitializationEvent event)
    {
		proxy.postInit(event);
    }
	
	@EventHandler
    public void serverStart(FMLServerStartingEvent event)
    {
        // DEBUG
        System.out.println("Server starting");

        proxy.serverStarting(event);
    }
}

