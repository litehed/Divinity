package divine.capabilities.race;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class RaceStorage implements IStorage<IRaces>{

	@Override
	public NBTBase writeNBT(Capability<IRaces> capability, IRaces instance, EnumFacing side) {
		return new NBTTagInt(instance.getRace());
	}

	@Override
	public void readNBT(Capability<IRaces> capability, IRaces instance, EnumFacing side, NBTBase nbt) {
		instance.setRace(((NBTTagInt) nbt).getInt());
		
	}

}
