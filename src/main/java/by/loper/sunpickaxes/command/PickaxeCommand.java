package by.loper.sunpickaxes.command;

import by.loper.sunpickaxes.SunPickaxes;
import by.loper.sunpickaxes.pickaxe.Pickaxes;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PickaxeCommand implements TabCompleter, CommandExecutor {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        List<String> TabComplete = new ArrayList<>();
        if (args.length == 1) {

            TabComplete.add("give");
            TabComplete.add("reload");
            TabComplete.add("help");

            return TabComplete;

        } else if (args[0].equalsIgnoreCase("give")) {

            TabComplete.add("magnet");
            TabComplete.add("autoMelting");
            TabComplete.add("3x3");

            return TabComplete;

        }
        return TabComplete;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (args[0].equalsIgnoreCase("give")) {
                if (args[1].equalsIgnoreCase("magnet")) {

                    Pickaxes.MAGNET.giveTo(player);
                    player.sendMessage("§6§lКИРКИ §8| §fВы §aуспешно§f получили кирку");
                    return true;

                } else if (args[1].equalsIgnoreCase("3x3")) {

                    Pickaxes.SUPER.giveTo(player);
                    player.sendMessage("§6§lКИРКИ §8| §fВы §aуспешно§f получили кирку");
                    return true;

                } else if (args[1].equalsIgnoreCase("autoMelting")) {

                    Pickaxes.AUTO_MELT.giveTo(player);
                    player.sendMessage("§6§lКИРКИ §8| §fВы §aуспешно§f получили кирку");
                    return true;

                } else {
                    player.sendMessage("§6§lКИРКИ §8| §fПомощь по плагину:");
                    player.sendMessage("§6/sunpickaxe reload §8| §fПерезагрузка конфига");
                    player.sendMessage("§6/sunpickaxe give (кирка) §8| §fполучение кирки");
                    return true;
                }
            } else if (args[0].equalsIgnoreCase("reload")) {

                by.loper.sunpickaxes.SunPickaxes.getInstance().reloadConfig();
                player.sendMessage("§6§lКИРКИ §8| §fПлагин §aуспешно§f перезагружен");
                return true;

            } else if (args[0].equalsIgnoreCase("help")) {

                player.sendMessage("§6§lКИРКИ §8| §fПомощь по плагину:");
                player.sendMessage("§6/sunpickaxe reload §8| §fПерезагрузка конфига");
                player.sendMessage("§6/sunpickaxe give (кирка) §8| §fполучение кирки");
                return true;

            } else {

                player.sendMessage("§6§lКИРКИ §8| §fПомощь по плагину:");
                player.sendMessage("§6/sunpickaxe reload §8| §fПерезагрузка конфига");
                player.sendMessage("§6/sunpickaxe give (кирка) §8| §fполучение кирки");
                return true;

            }
        } else {
            if (args[0].equalsIgnoreCase("reload")) {

                SunPickaxes.getInstance().reloadConfig();
                commandSender.sendMessage("§6§lКИРКИ §8| §fПлагин §aуспешно§f перезагружен");
                return true;

            } else {

                commandSender.sendMessage("§6§lКИРКИ §8| §cДанная комманда доступна только игрокам");
                return true;

            }
        }
    }
}