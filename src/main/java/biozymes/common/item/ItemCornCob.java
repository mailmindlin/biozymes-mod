package biozymes.common.item;

import biozymes.common.Biozymes;
import biozymes.common.block.BiozymesBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCornCob extends ItemFood implements IBZItem, IPlantable {

	public ItemCornCob() {
		super(4, false);
		this.setCreativeTab(Biozymes.instance.creativeTab);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		// TODO Auto-generated method stub
		
		BlockPos cropPos = pos.up();
		if (!playerIn.canPlayerEdit(cropPos, facing, stack) || !worldIn.isAirBlock(cropPos))
			return EnumActionResult.PASS; // Can't place where the crop would go
		
		IBlockState blockState = worldIn.getBlockState(pos);
		Block soil = blockState.getBlock();
		if (!soil.canSustainPlant(blockState, worldIn, pos, EnumFacing.UP, this))
			return EnumActionResult.PASS;// Block can't sustain plant
		
		
		stack.splitStack(1);//TODO: fix for complex seeds
		worldIn.setBlockState(cropPos, this.getPlant(worldIn, cropPos));
		return EnumActionResult.SUCCESS;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Crop;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		return BiozymesBlocks.blockCorn.getDefaultState();
	}

	@Override
	public String getName() {
		return "corn";
	}
	
}
