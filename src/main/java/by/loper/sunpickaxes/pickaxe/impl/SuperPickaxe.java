package by.loper.sunpickaxes.pickaxe.impl;

import by.loper.sunpickaxes.pickaxe.AbstractPickaxe;
import by.loper.sunpickaxes.util.WorldBlockHelper;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.List;

public final class SuperPickaxe extends AbstractPickaxe {
    @Override
    public void onBreak(BlockBreakEvent event) {
        Location blockLocation = event.getBlock().getLocation();
        World world = event.getPlayer().getWorld();
        Player player = event.getPlayer();

        List<Block> lastTwoTargetBlocks = player.getLastTwoTargetBlocks(null, 100);

        BlockFace blockFace = lastTwoTargetBlocks.get(1).getFace(lastTwoTargetBlocks.get(0));

        if (blockFace == null) {
            return;
        }

        WorldBlockHelper.selectNxNx1(world, blockLocation, blockFace, this.getLevel()).stream()
                .filter(this::isWhitelisted)
                .filter(block -> isBlockInAllowedRegion(block, player))
                .forEach(Block::breakNaturally);
    }

    @Override
    public String getIdentifier() {
        return "3x3";
    }

    @Override
    protected String getDisplayName() {
        return super.getDisplayName().replaceAll("%level%", String.valueOf(this.getLevel()));
    }

    private boolean isBlockInAllowedRegion(Block block, Player player) {
        LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        Location location = block.getLocation();
        ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(location));

        if (set.size() <= 0) {
            return true;
        }

        for (ProtectedRegion region : set) {
            if (!(region.isMember(localPlayer) || region.isOwner(localPlayer))) {
                return false;
            }
        }

        return set.testState(null, Flags.BLOCK_BREAK);
    }

    private boolean isWhitelisted(Block block) {
        return this.getConfig().getStringList("WhiteListBlock").stream()
                .map(material -> Material.valueOf(material.toUpperCase()))
                .anyMatch(material -> material.equals(block.getType()));
    }

    private int getLevel() {
        return this.getConfig().getInt("Level", 1);
    }
}