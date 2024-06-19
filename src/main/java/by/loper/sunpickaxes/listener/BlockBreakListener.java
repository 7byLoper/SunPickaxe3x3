package by.loper.sunpickaxes.listener;

import by.loper.sunpickaxes.pickaxe.AbstractPickaxe;
import by.loper.sunpickaxes.pickaxe.Pickaxes;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public final class BlockBreakListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();

        AbstractPickaxe pickaxe = Pickaxes.toAbstractPickaxe(itemStack);

        boolean playerInCreative = event.getPlayer().getGameMode() == GameMode.CREATIVE;

        if (pickaxe != null && (pickaxe.workInCreative() || !playerInCreative)) {
            pickaxe.onBreak(event);
        }
    }
}

