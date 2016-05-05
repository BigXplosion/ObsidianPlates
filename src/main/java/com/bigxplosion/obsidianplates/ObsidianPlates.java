package com.bigxplosion.obsidianplates;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.bigxplosion.obsidianplates.block.BlockMossyPressurePlate;
import com.bigxplosion.obsidianplates.block.BlockObsidianPressurePlate;
import com.bigxplosion.obsidianplates.lib.Reference;
import com.bigxplosion.obsidianplates.proxy.CommonProxy;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
public class ObsidianPlates {

	@Mod.Instance(Reference.MOD_ID)
	public static ObsidianPlates INSTANCE;

	@SidedProxy(serverSide = "com.bigxplosion.obsidianplates.proxy.CommonProxy", clientSide = "com.bigxplosion.obsidianplates.proxy.ClientProxy")
	public static CommonProxy PROXY;

	public static Block obsidianPressurePlate;
	public static Block mossyPressurePlate;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		obsidianPressurePlate = new BlockObsidianPressurePlate();
		GameRegistry.registerBlock(obsidianPressurePlate, "obsidianplates:obsidianPressurePlate");
		PROXY.registerInventoryModel(obsidianPressurePlate, "obsidianPressurePlate");
		GameRegistry.addRecipe(new ItemStack(obsidianPressurePlate), "OO", 'O', new ItemStack(Blocks.obsidian));

		mossyPressurePlate = new BlockMossyPressurePlate();
		GameRegistry.registerBlock(mossyPressurePlate, "obsidianplates:mossyPressurePlate");
		PROXY.registerInventoryModel(mossyPressurePlate, "mossyPressurePlate");
		GameRegistry.addRecipe(new ItemStack(mossyPressurePlate), "MM", 'M', new ItemStack(Blocks.mossy_cobblestone));
	}
}
