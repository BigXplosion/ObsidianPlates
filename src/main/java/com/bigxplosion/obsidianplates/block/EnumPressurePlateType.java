package com.bigxplosion.obsidianplates.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;

/**
 * Never use ordinal()! always use getMeta()!
 *
 * 0 = normal, powered off
 * 1 = normal, powered on
 * 2 = silent, powered off
 * 3 = silent, powered on
 * 4 = shrouded, powered off
 * 5 = shrouded, powered on
 * 6 = both, powered off
 * 7 = both, powered on
 */
public enum EnumPressurePlateType implements IStringSerializable {
	NORMAL("normal"),
	SILENT("silent"),
	SHROUDED("shrouded"),
	BOTH("both");

	private String name;

	EnumPressurePlateType(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	public int getMeta() {
		return ordinal() * 2;
	}

	public static boolean isSilent(int meta) {
		return meta == SILENT.getMeta() || meta == BOTH.getMeta();
	}

	public static boolean isShrouded(int meta) {
		return meta == SHROUDED.getMeta() || meta == BOTH.getMeta();
	}

	public static boolean isPowered(int meta) {
		return (meta % 2) != 0;
	}

	public static int getMetaFromStateNoPower(IBlockState state) {
		int meta = 0;

		if (state.getValue(BlockCustomPressurePlate.SILENT) && state.getValue(BlockCustomPressurePlate.SHROUDED))
			meta = BOTH.getMeta();
		else if (state.getValue(BlockCustomPressurePlate.SILENT))
			meta = SILENT.getMeta();
		else if (state.getValue(BlockCustomPressurePlate.SHROUDED))
			meta = SHROUDED.getMeta();

		return meta;
	}

	public static EnumPressurePlateType fromMeta(int meta) {
		return values()[(int) Math.floor(meta / 2)];
	}
}
