package biozymes.common.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCropCorn extends BlockCropBase {
	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 3);

	public BlockCropCorn() {
		super("cropCorn");
		
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return true;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.withAge(meta);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return this.getAge(state);
	}

	@Override
	protected PropertyInteger getAgeProperty() {
		return BlockCropCorn.AGE;
	}

	@Override
	public int getMaxAge() {
		return 3;
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> result = new ArrayList<>();
		
		return result;
	}
	
}
