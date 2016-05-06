package com.bigxplosion.obsidianplates.block;

import java.util.List;

import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 0 = normal, powered off
 * 1 = normal, powered on
 * 2 = silent, powered off
 * 3 = silent, powered on
 * 4 = shrouded, powered off
 * 5 = shrouded, powered on
 * 6 = both, powered off
 * 7 = both, powered on
 */
public abstract class BlockCustomPressurePlate extends BlockPressurePlate  {

	public static final PropertyBool SILENT = PropertyBool.create("silent");
	public static final PropertyBool SHROUDED = PropertyBool.create("shrouded");

	public BlockCustomPressurePlate() {
		super(Material.ROCK, Sensitivity.MOBS);
		this.setUnlocalizedName(getName());
		this.setRegistryName("obsidianplates", getName());
		this.setHardness(getHardness());
		this.setResistance(getResistance());
		this.setSoundType(SoundType.STONE);
		this.setDefaultState(this.blockState.getBaseState().withProperty(POWERED, false).withProperty(SILENT, false).withProperty(SHROUDED, false));
	}

	public abstract String getName();

	public abstract float getHardness();

	public abstract float getResistance();

	@Override
	protected abstract int computeRedstoneStrength(World world, BlockPos pos);

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, POWERED, SILENT, SHROUDED);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = 0;

		if (state.getValue(SILENT) && state.getValue(SHROUDED))
			meta = EnumPressurePlateType.BOTH.getMeta();
		else if (state.getValue(SILENT))
			meta = EnumPressurePlateType.SILENT.getMeta();
		else if (state.getValue(SHROUDED))
			meta = EnumPressurePlateType.SHROUDED.getMeta();
		if (state.getValue(POWERED))
			meta += 1;

		return meta;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(SILENT, EnumPressurePlateType.isSilent(meta)).withProperty(SHROUDED, EnumPressurePlateType.isShrouded(meta)).withProperty(POWERED, EnumPressurePlateType.isPowered(meta));
	}

	protected void updateState(World world, BlockPos pos, IBlockState state, int oldRedstoneStrength) {
		int i = this.computeRedstoneStrength(world, pos);
		boolean flag = oldRedstoneStrength > 0;
		boolean flag1 = i > 0;

		if (oldRedstoneStrength != i) {
			state = this.setRedstoneStrength(state, i);
			world.setBlockState(pos, state, 2);
			this.updateNeighbors(world, pos);
			world.markBlockRangeForRenderUpdate(pos, pos);
		}

		if (!state.getValue(SILENT)) {
			if (! flag1 && flag)
				this.playClickOffSound(world, pos);
			else if (flag1 && ! flag)
				this.playClickOnSound(world, pos);
		}

		if (flag1)
			world.scheduleUpdate(new BlockPos(pos), this, this.tickRate(world));
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(world.getBlockState(pos).getBlock(), 1, EnumPressurePlateType.getMetaFromStateNoPower(world.getBlockState(pos)));
	}

	@Override
	public int damageDropped(IBlockState state) {
		return EnumPressurePlateType.getMetaFromStateNoPower(state);
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
		list.add(new ItemStack(item, 1, EnumPressurePlateType.NORMAL.getMeta()));
		list.add(new ItemStack(item, 1, EnumPressurePlateType.SILENT.getMeta()));
		list.add(new ItemStack(item, 1, EnumPressurePlateType.SHROUDED.getMeta()));
		list.add(new ItemStack(item, 1, EnumPressurePlateType.BOTH.getMeta()));

	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
}
