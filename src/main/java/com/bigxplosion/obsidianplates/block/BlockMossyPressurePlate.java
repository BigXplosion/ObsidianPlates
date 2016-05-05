package com.bigxplosion.obsidianplates.block;

import java.util.List;

import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockMossyPressurePlate extends BlockPressurePlate {

	public BlockMossyPressurePlate() {
		super(Material.rock, Sensitivity.MOBS);
		this.setUnlocalizedName("mossyPressurePlate");
		this.setHardness(2f);
		this.setResistance(10f);
		this.setStepSound(soundTypeStone);
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
