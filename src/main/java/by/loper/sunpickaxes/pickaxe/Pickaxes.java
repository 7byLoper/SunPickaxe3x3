package by.loper.sunpickaxes.pickaxe;

import by.loper.sunpickaxes.pickaxe.impl.AutoMeltPickaxe;
import by.loper.sunpickaxes.pickaxe.impl.MagnetPickaxe;
import by.loper.sunpickaxes.pickaxe.impl.SuperPickaxe;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Pickaxes {
    public final static AbstractPickaxe AUTO_MELT = new AutoMeltPickaxe();
    public final static AbstractPickaxe MAGNET = new MagnetPickaxe();
    public final static AbstractPickaxe SUPER = new SuperPickaxe();
    // public final static AbstractPickaxe COOL_PICKAXE = new CoolPickaxe();
    // ...

    private final static List<AbstractPickaxe> PICKAXES = new ArrayList<>();

    static {
        Arrays.stream(Pickaxes.class.getDeclaredFields())
                .filter(field -> Modifier.isStatic(field.getModifiers()))
                .filter(field -> field.getType().isAssignableFrom(AbstractPickaxe.class))
                .forEach(field -> {
                    try {
                        PICKAXES.add((AbstractPickaxe) field.get(Pickaxes.class));
                    } catch (IllegalAccessException ignored) {
                    }
                });
    }

    private Pickaxes() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static AbstractPickaxe toAbstractPickaxe(ItemStack itemStack) {
        return PICKAXES.stream().filter(pickaxe -> pickaxe.is(itemStack)).findFirst().orElse(null);
    }
}