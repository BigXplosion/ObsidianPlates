package com.bigxplosion.obsidianplates.block;

import java.util.List;

import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockObsidianPressurePlate extends BlockPressurePlate {

	public BlockObsidianPressurePlate() {
		super(Material.rock, Sensitivity.MOBS);
		this.setUnlocalizedName("obsidianPressurePlate");
		this.setHardness(50f);
		this.setResistance(2000f);
		this.setStepSound(soundTypeStone);
	}

	@Override
	protected int computeRedstoneStrength(World world, BlockPos pos) {
		List<? extends Entity> entities = world.getEntitiesWithinAABB(EntityPlayer.class, getSensitiveAABB(pos));
		if (!entities.isEmpty()) {
			for (Entity ent : entities) {
				if (!ent.doesEntityNotTriggerPressurePlate())
					return 15;
			}
		}
		return 0;
	}
}
