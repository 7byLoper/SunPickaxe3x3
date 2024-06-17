package by.loper.sunpickaxes.command;

import by.loper.sunpickaxes.SunPickaxes;
import by.loper.sunpickaxes.pickaxe.Pickaxes;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Subcommand;

@Command({"sunpickaxe", "pickaxe"})
public final class PickaxeCommand {
    @Subcommand("give magnet")
    public void giveMagnet(Player sender) {
        Pickaxes.MAGNET.giveTo(sender);

        sender.sendMessage("§6§lКИРКИ §8| §fВы §aуспешно§f получили кирку");
    }

    @Subcommand({"give autoMelting", "give melt", "give automelt"})
    public void giveAutoMelt(Player sender) {
        Pickaxes.AUTO_MELT.giveTo(sender);

        sender.sendMessage("§6§lКИРКИ §8| §fВы §aуспешно§f получили кирку");
    }

    @Subcommand({"give 3x3", "give super"})
    public void give3x3(Player sender) {
        Pickaxes.SUPER.giveTo(sender);

        sender.sendMessage("§6§lКИРКИ §8| §fВы §aуспешно§f получили кирку");
    }

    @Subcommand("reload")
    public void reload(Player sender) {
        SunPickaxes.getInstance().reloadConfig();

        sender.sendMessage("§6§lКИРКИ §8| §fПлагин §aуспешно§f перезагружен");
    }

    @Subcommand("help")
    public void help(Player sender) {
        sender.sendMessage("§6§lКИРКИ §8| §fПомощь по плагину:");
        sender.sendMessage("§6/sunpickaxe reload §8| §fПерезагрузка конфига");
        sender.sendMessage("§6/sunpickaxe give (кирка) §8| §fполучение кирки");
    }
}