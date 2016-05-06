package com.bigxplosion.obsidianplates;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.bigxplosion.obsidianplates.block.BlockCustomPressurePlate;
import com.bigxplosion.obsidianplates.block.BlockMossyPressurePlate;
import com.bigxplosion.obsidianplates.block.BlockObsidianPressurePlate;
import com.bigxplosion.obsidianplates.block.EnumPressurePlateType;
import com.bigxplosion.obsidianplates.item.ItemBlockCustomPressurePlate;
import com.bigxplosion.obsidianplates.lib.Reference;
import com.bigxplosion.obsidianplates.proxy.CommonProxy;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
public class ObsidianPlates {

	@Mod.Instance(Reference.MOD_ID)
	public static ObsidianPlates INSTANCE;

	@SidedProxy(serverSide = "com.bigxplosion.obsidianplates.proxy.CommonProxy", clientSide = "com.bigxplosion.obsidianplates.proxy.ClientProxy")
	public static CommonProxy PROXY;

	public static BlockCustomPressurePlate obsidianPressurePlate;
	public static BlockCustomPressurePlate mossyPressurePlate;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		obsidianPressurePlate = new BlockObsidianPressurePlate();
		registerBlock(obsidianPressurePlate);
		//registerInventoryModels(obsidianPressurePlate, "obsidianPressurePlate");
		registerRecipes(obsidianPressurePlate, Blocks.OBSIDIAN);

		mossyPressurePlate = new BlockMossyPressurePlate();
		registerBlock(mossyPressurePlate);
		registerInventoryModels(mossyPressurePlate, "mossyPressurePlate");
		registerRecipes(mossyPressurePlate, Blocks.MOSSY_COBBLESTONE);
	}

	private void registerBlock(BlockCustomPressurePlate block) {
		GameRegistry.register(block);
		GameRegistry.register(new ItemBlockCustomPressurePlate(block));
	}

	private void registerInventoryModels(Block block, String name) {
		PROXY.registerInventoryModel(block, EnumPressurePlateType.NORMAL.getMeta(), name);
		PROXY.registerInventoryModel(block, EnumPressurePlateType.SILENT.getMeta(), name);
		PROXY.registerInventoryModel(block, EnumPressurePlateType.SHROUDED.getMeta(), name, "inventory_shrouded");
		PROXY.registerInventoryModel(block, EnumPressurePlateType.BOTH.getMeta(), name, "inventory_shrouded");
	}

	private void registerRecipes(Block result, Block base) {
		//Normal
		GameRegistry.addRecipe(new ItemStack(result), "XX", 'X', new ItemStack(base));

		//Silent
		GameRegistry.addRecipe(new ItemStack(result, 1, EnumPressurePlateType.SILENT.getMeta()), "X", "W", 'W', new ItemStack(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE), 'X', new ItemStack(result, 1, EnumPressurePlateType.NORMAL.getMeta()));

		//Shrouded
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(result, 1, EnumPressurePlateType.SHROUDED.getMeta()), "QGQ", "GXG", "QGQ", 'Q', "gemQuartz", 'G', "dustGlowstone", 'X', new ItemStack(result, 1, EnumPressurePlateType.NORMAL.getMeta())));

		//Both
		GameRegistry.addRecipe(new ItemStack(result, 1, EnumPressurePlateType.BOTH.getMeta()), "X", "W", 'W', new ItemStack(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE), 'X', new ItemStack(result, 1, EnumPressurePlateType.SHROUDED.getMeta()));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(result, 1, EnumPressurePlateType.BOTH.getMeta()), "QGQ", "GXG", "QGQ", 'Q', "gemQuartz", 'G', "dustGlowstone", 'X', new ItemStack(result, 1, EnumPressurePlateType.SILENT.getMeta())));
	}
}
