package divine.capabilities.race;

import net.minecraft.entity.player.EntityPlayer;

public interface IRaces {
	public enum Races{
		Deity, Demon, Human
	}
	public int setRace(int race);
	public int getRace();
	
	public String raceName();
	
	void copy(IRaces races, EntityPlayer player);

	void updateToServer(IRaces races);
}
