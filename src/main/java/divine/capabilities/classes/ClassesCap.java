package divine.capabilities.classes;

import divine.packets.PacketDispatcher;
import divine.packets.client.ClassPacket;
import divine.packets.server.ClassServerPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ClassesCap implements IClasses{
	
	private int curClass = 0;
	private String className = "Warrior";
	
	@Override
	public int setClass(int classIn) {
		curClass = classIn;
		return MathHelper.clamp(curClass, 0, 3);
	}

	@Override
	public int getCurClass() {
		return curClass;
	}

	@Override
	public String className() {
		switch(curClass){
		case 0:
			className = "Warrior";
			break;
		case 1:
			className = "Thief";
			break;
		case 2:
			className = "Sorcerer";
			break;
		case 3:
			className = "Deprived";
			break;
		}
		return className;
	}

	@Override
	public void copy(IClasses classes, EntityPlayer player) {	
		this.setClass(classes.getCurClass());
		PacketDispatcher.sendTo((IMessage)new ClassPacket(classes), (EntityPlayerMP)player);
	}

	@Override
	public void updateToServer(IClasses classes) {
		PacketDispatcher.sendToServer((IMessage)new ClassServerPacket(classes));
	}
	
	public static IClasses get(EntityPlayer player) {
		return player.getCapability(ClassesProvider.CLASSES, null);
	}

}
