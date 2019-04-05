package biozymes.common.block;

import java.util.Random;

import biozymes.common.tile.TECrop;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockArbitraryCrop extends BlockCropBase implements ITileEntityProvider {

	public BlockArbitraryCrop() {
		super("cropsEvery");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected PropertyInteger getAgeProperty() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public int getMaxAge() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TECrop createNewTileEntity(World worldIn, int meta) {
		return new TECrop();
	}
	
	public TECrop getTileEntity(IBlockAccess world, BlockPos pos) {
		return (TECrop) world.getTileEntity(pos);
	}
}
