package divine.capabilities.race;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class RaceProvider implements ICapabilitySerializable<NBTBase>{

	@CapabilityInject(IRaces.class)
	public static final Capability<IRaces> RACES = null;
	
	private IRaces instance = RACES.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == RACES;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == RACES ? RACES.<T>cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return RACES.getStorage().writeNBT(RACES, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		RACES.getStorage().readNBT(RACES, this.instance, null, nbt);
	}

}
