package com.bigxplosion.obsidianplates.block;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockObsidianPressurePlate extends BlockCustomPressurePlate {

	@Override
	public String getName() {
		return "obsidianPressurePlate";
	}

	@Override
	public float getHardness() {
		return 50f;
	}

	@Override
	public float getResistance() {
		return 2000f;
	}

	@Override
	protected int computeRedstoneStrength(World world, BlockPos pos) {
		List<? extends Entity> entities = world.getEntitiesWithinAABB(EntityPlayer.class, PRESSURE_AABB.offset(pos));
		if (!entities.isEmpty()) {
			for (Entity ent : entities) {
				if (!ent.doesEntityNotTriggerPressurePlate())
					return 15;
			}
		}
		return 0;
	}
}
