package com.bigxplosion.obsidianplates.block;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockMossyPressurePlate extends BlockCustomPressurePlate {

	@Override
	public String getName() {
		return "mossyPressurePlate";
	}

	@Override
	public float getHardness() {
		return 2f;
	}

	@Override
	public float getResistance() {
		return 10f;
	}

	@Override
	protected int computeRedstoneStrength(World world, BlockPos pos) {
		List<? extends Entity> entities = world.getEntitiesWithinAABB(EntityLiving.class, getSensitiveAABB(pos));
		if (!entities.isEmpty()) {
			for (Entity ent : entities) {
				if (!ent.doesEntityNotTriggerPressurePlate() && !(ent instanceof EntityPlayer))
					return 15;
			}
		}
		return 0;
	}
}
