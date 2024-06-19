package by.loper.sunpickaxes.pickaxe.impl;

import by.loper.sunpickaxes.pickaxe.AbstractPickaxe;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public final class AutoMeltPickaxe extends AbstractPickaxe {
    private static final Map<Material, Material> MELTING_RESULT = new HashMap<>();

    static {
        MELTING_RESULT.put(Material.IRON_ORE, Material.IRON_INGOT);
        MELTING_RESULT.put(Material.GOLD_ORE, Material.GOLD_INGOT);
        MELTING_RESULT.put(Material.SAND, Material.GLASS);
    }

    @Override
    public void onBreak(BlockBreakEvent event) {
        Material resultMaterial = MELTING_RESULT.get(event.getBlock().getType());

        Location location = event.getBlock().getLocation();

        World world = location.getWorld();

        if (resultMaterial != null && world != null) {
            event.setDropItems(false);
            world.dropItemNaturally(location, new ItemStack(resultMaterial));
        }
    }

    @Override
    public String getIdentifier() {
        return "melt";
    }
}