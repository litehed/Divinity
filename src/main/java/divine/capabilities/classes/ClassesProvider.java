package divine.capabilities.classes;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class ClassesProvider implements ICapabilitySerializable<NBTBase>{

	@CapabilityInject(IClasses.class)
	public static final Capability<IClasses> CLASSES = null;
	
	private IClasses instance = CLASSES.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CLASSES;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == CLASSES ? CLASSES.<T>cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return CLASSES.getStorage().writeNBT(CLASSES, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		CLASSES.getStorage().readNBT(CLASSES, this.instance, null, nbt);
	}

}
