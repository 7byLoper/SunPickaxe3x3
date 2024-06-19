package by.loper.sunpickaxes.pickaxe.impl;

import by.loper.sunpickaxes.pickaxe.AbstractPickaxe;
import org.bukkit.event.block.BlockBreakEvent;

public final class MagnetPickaxe extends AbstractPickaxe {
    @Override
    public void onBreak(BlockBreakEvent event) {
        event.getBlock().getDrops().forEach(event.getPlayer().getInventory()::addItem);
        event.setDropItems(false);
    }

    @Override
    public String getIdentifier() {
        return "magnet";
    }
}
