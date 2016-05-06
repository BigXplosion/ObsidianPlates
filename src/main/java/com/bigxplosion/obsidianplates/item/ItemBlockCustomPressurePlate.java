package com.bigxplosion.obsidianplates.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import com.bigxplosion.obsidianplates.block.BlockCustomPressurePlate;
import com.bigxplosion.obsidianplates.block.EnumPressurePlateType;

public class ItemBlockCustomPressurePlate extends ItemBlock {

	public ItemBlockCustomPressurePlate(BlockCustomPressurePlate block) {
		super(block);
		this.setHasSubtypes(true);
		this.setRegistryName("obsidianplates", block.getName());
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return block.getUnlocalizedName() + "." + EnumPressurePlateType.fromMeta(stack.getMetadata()).getName();
	}
}
