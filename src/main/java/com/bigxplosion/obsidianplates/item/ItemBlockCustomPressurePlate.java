package com.bigxplosion.obsidianplates.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import com.bigxplosion.obsidianplates.block.EnumPressurePlateType;

public class ItemBlockCustomPressurePlate extends ItemBlock {

	public ItemBlockCustomPressurePlate(Block block) {
		super(block);
		this.setHasSubtypes(true);
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
