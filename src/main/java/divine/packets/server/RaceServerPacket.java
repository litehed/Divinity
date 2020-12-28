package divine.packets.server;

import java.io.IOException;

import divine.capabilities.race.IRaces;
import divine.capabilities.race.RaceProvider;
import divine.packets.AbstractMessage.AbstractServerMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

public class RaceServerPacket extends AbstractServerMessage<RaceServerPacket>{

	private int race;
	public RaceServerPacket() {
		
	}
	public RaceServerPacket(final IRaces race) {
		this.race = race.getRace();
	}
	
	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		race = buffer.readInt();
		
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		buffer.writeInt(race);
		
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		if(side.isServer()) {
			IRaces races = player.getCapability(RaceProvider.RACES, null);
			races.setRace(race);
		}
	}

}
