package divine.capabilities.classes;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ClassesStorage implements IStorage<IClasses>{

	@Override
	public NBTBase writeNBT(Capability<IClasses> capability, IClasses instance, EnumFacing side) {
		return new NBTTagInt(instance.getCurClass());
	}

	@Override
	public void readNBT(Capability<IClasses> capability, IClasses instance, EnumFacing side, NBTBase nbt) {
		instance.setClass(((NBTTagInt) nbt).getInt());
		
	}

}
