package com.bigxplosion.obsidianplates.block;

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

	public boolean isSilent() {
		return getMeta() == 2 || getMeta() == 6;
	}

	public static EnumPressurePlateType fromMeta(int meta) {
		return values()[(int) Math.floor(meta / 2)];
	}
}
