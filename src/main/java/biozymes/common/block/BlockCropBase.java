package biozymes.common.block;

import java.util.Random;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockCropBase extends BlockBush implements IBZBlock, IGrowable {
	protected final String name;
	public BlockCropBase(String name) {
		super(Material.PLANTS);
		this.name = name;

		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(this.getAgeProperty(), Integer.valueOf(0)));
		
		this.setTickRandomly(true);
		this.setCreativeTab(null);
		this.setHardness(0.0f);
		this.setSoundType(SoundType.PLANT);
		this.disableStats();
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	protected boolean canSustainBush(IBlockState state) {
		return state.getBlock() == Blocks.FARMLAND;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.25, 1.0);
	}
	
	protected abstract PropertyInteger getAgeProperty();
	
	public abstract int getMaxAge();
	
	protected int getMinLight() {
		return 9;
	}
	
	protected int getAge(IBlockState state) {
		return state.getValue(this.getAgeProperty()).intValue();
	}
	
	protected IBlockState withAge(int age) {
		return this.getDefaultState()
				.withProperty(this.getAgeProperty(), (Integer) 0);
	}
	
	protected boolean isMaxAge(IBlockState state) {
		return this.getAge(state) >= this.getMaxAge();
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		// TODO Auto-generated method stub
		int age = this.getAge(state) + this.getAgeIncrease(worldIn);
		age = Math.min(age, this.getMaxAge());
		worldIn.setBlockState(pos, this.withAge(age), 2);
	}
	
	protected int getAgeIncrease(World world) {
		return MathHelper.getRandomIntegerInRange(world.rand, 2, 5);
	}
	
	protected float getGrowthChance() {
		return 0.1f;
	}

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return !this.isMaxAge(state);
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(worldIn, pos, state, rand);
		
		if (worldIn.getLightFromNeighbors(pos) >= this.getMinLight()) {
			
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { this.getAgeProperty() });
	}
}
