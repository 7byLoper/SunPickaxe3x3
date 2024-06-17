package by.loper.sunpickaxes.pickaxe;

import by.loper.sunpickaxes.SunPickaxes;
import by.loper.sunpickaxes.util.ColorUtil;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public abstract class AbstractPickaxe {
    private static final NamespacedKey PICKAXE_KEY;

    static {
        PICKAXE_KEY = new NamespacedKey(SunPickaxes.getInstance(), "sunPickaxe");
    }

    protected final ItemStack itemStack = new ItemStack(Material.NETHERITE_PICKAXE);

    protected AbstractPickaxe() {
        if (this.itemStack.getItemMeta() == null) {
            return;
        }

        ItemMeta itemMeta = this.itemStack.getItemMeta();

        itemMeta.setDisplayName(this.getDisplayName());
        itemMeta.setLore(this.getLore());

        PersistentDataContainer container = itemMeta.getPersistentDataContainer();

        container.set(PICKAXE_KEY, PersistentDataType.STRING, this.getIdentifier());

        this.itemStack.setItemMeta(itemMeta);
    }

    public abstract void onBreak(BlockBreakEvent event);

    public abstract String getIdentifier();

    public boolean is(ItemStack itemStack) {
        if (itemStack == null || itemStack.getItemMeta() == null) {
            return false;
        }

        ItemMeta itemMeta = itemStack.getItemMeta();

        PersistentDataContainer container = itemMeta.getPersistentDataContainer();

        String identifier = container.get(PICKAXE_KEY, PersistentDataType.STRING);

        return identifier != null && identifier.equals(this.getIdentifier());
    }

    public void giveTo(Player player) {
        player.getInventory().addItem(this.itemStack);
    }

    protected ConfigurationSection getConfig() {
        String name = StringUtils.capitalize(this.getIdentifier().toLowerCase());

        String path = String.format("Pickaxe.%s", name);

        return SunPickaxes.getInstance().getConfig().getConfigurationSection(path);
    }

    protected String getDisplayName() {
        return ColorUtil.translate(this.getConfig().getString("DisplayName"));
    }

    protected List<String> getLore() {
        return ColorUtil.translate(this.getConfig().getStringList("Lore"));
    }
}