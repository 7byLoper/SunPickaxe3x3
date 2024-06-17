package by.loper.sunpickaxes.util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.ArrayList;
import java.util.List;

public final class WorldBlockHelper {
    private WorldBlockHelper() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static List<Block> selectCuboid(World world, Location pos1, Location pos2) {
        List<Block> blocks = new ArrayList<>();

        int xMin = Math.min(pos1.getBlockX(), pos2.getBlockX());
        int xMax = Math.max(pos1.getBlockX(), pos2.getBlockX());

        int yMin = Math.min(pos1.getBlockY(), pos2.getBlockY());
        int yMax = Math.max(pos1.getBlockY(), pos2.getBlockY());

        int zMin = Math.min(pos1.getBlockZ(), pos2.getBlockZ());
        int zMax = Math.max(pos1.getBlockZ(), pos2.getBlockZ());

        for (int x = xMin; x <= xMax; x++) {
            for (int y = yMin; y <= yMax; y++) {
                for (int z = zMin; z <= zMax; z++) {
                    blocks.add(world.getBlockAt(x, y, z));
                }
            }
        }

        return blocks;
    }

    public static List<Block> selectNxNx1(World world, Location pos, BlockFace face, int n) {
        switch (face) {
            case WEST:
            case EAST:
                return selectCuboid(world, pos.clone().add(0, n, n), pos.clone().add(0, -n, -n));
            case UP:
            case DOWN:
                return selectCuboid(world, pos.clone().add(n, 0, n), pos.clone().add(-n, 0, -n));
            case NORTH:
            case SOUTH:
                return selectCuboid(world, pos.clone().add(n, n, 0), pos.clone().add(-n, -n, 0));
        }

        return new ArrayList<>();
    }
}
