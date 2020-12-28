package divine.packets.server;

import java.io.IOException;

import divine.capabilities.classes.ClassesProvider;
import divine.capabilities.classes.IClasses;
import divine.packets.AbstractMessage.AbstractServerMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

public class ClassServerPacket extends AbstractServerMessage<ClassServerPacket>{

	private int curClass;
	
	public ClassServerPacket() {}
	
	public ClassServerPacket(final IClasses classes) {
		this.curClass = classes.getCurClass();
	}
	
	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		curClass = buffer.readInt();
		
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		buffer.writeInt(curClass);
		
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		if(side.isServer()) {
			IClasses classes = player.getCapability(ClassesProvider.CLASSES, null);
			classes.setClass(curClass);
		}
	}

}
