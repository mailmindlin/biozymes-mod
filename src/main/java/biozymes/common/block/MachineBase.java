package biozymes.common.block;

import javax.annotation.Nullable;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class MachineBase<TE extends TileEntity> extends BZBlockBase {
	
	public MachineBase(String name) {
		super(Material.IRON, MapColor.BLACK, name);
		setHardness(4.0f);
		setResistance(16.0f);
	}
	
	public MachineBase(Material material, MapColor color, String name) {
		super(material, color, name);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Nullable
	@Override
	public abstract TE createTileEntity(World world, IBlockState state);
	
	@SuppressWarnings("unchecked")
	public TE getTileEntity(IBlockAccess world, BlockPos pos) {
		return (TE) world.getTileEntity(pos);
	}
}
