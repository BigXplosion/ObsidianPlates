package com.bigxplosion.obsidianplates.proxy;

import net.minecraft.block.Block;

public class CommonProxy {

	public void registerInventoryModel(Block block, String name) {
		registerInventoryModel(block, 0, name);
	}

	public void registerInventoryModel(Block block, int meta, String name) {

	}
}
