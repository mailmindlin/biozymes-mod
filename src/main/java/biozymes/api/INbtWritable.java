package biozymes.api;

import net.minecraft.nbt.NBTTagCompound;

public interface INbtWritable {
	NBTTagCompound writeToNBT(NBTTagCompound compound);
}
