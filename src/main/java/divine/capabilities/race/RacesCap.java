package divine.capabilities.race;

import divine.packets.PacketDispatcher;
import divine.packets.client.RacePacket;
import divine.packets.server.RaceServerPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class RacesCap implements IRaces{
	
	private int race;
	private String name;
	
	@Override
	public int setRace(int race) {
		this.race = race;
		return MathHelper.clamp(this.race, 0, 2);
	}
	
	@Override
	public int getRace() {
		return race;
	}

	@Override
	public void copy(IRaces races, EntityPlayer player) {
		this.setRace(races.getRace());
		PacketDispatcher.sendTo((IMessage)new RacePacket(races), (EntityPlayerMP)player);
	}

	@Override
	public void updateToServer(IRaces races) {
		PacketDispatcher.sendToServer((IMessage)new RaceServerPacket(races));
	}
	
	public static IRaces get(EntityPlayer player) {
		return player.getCapability(RaceProvider.RACES, null);
		
	}
	public String raceName() {
		switch(race){
		case 0:
			name = "Human";
			break;
		case 1:
			name = "Deity";
			break;
		case 2:
			name = "Demon";
			break;
		}
		return name;
	}
}
