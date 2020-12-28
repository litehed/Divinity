package divine.capabilities.classes;

import net.minecraft.entity.player.EntityPlayer;

public interface IClasses {
	
	public int setClass(int classIn);
	public int getCurClass();
	
	public String className();
	
	void copy(IClasses classes, EntityPlayer player);
	void updateToServer(IClasses classes);

}
