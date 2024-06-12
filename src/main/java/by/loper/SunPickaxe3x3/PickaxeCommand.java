package by.loper.SunPickaxe3x3;

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
        if(args.length == 1){
            TabComplete.add("give");
            TabComplete.add("reload");
            TabComplete.add("help");
            return TabComplete;
        }


        return null;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player){
            Player p = (Player) commandSender;
            if(args[0].equalsIgnoreCase("give") ){
                PickaxeManager.givePickaxe(p);
                p.sendMessage("§6§lКИРКИ §8| §fВы §aуспешно§f получили кирку");
            }
            else if (args[0].equalsIgnoreCase("reload") ){
                SunPickaxe3x3.getInstance().reloadConfig();
                p.sendMessage("§6§lКИРКИ §8| §fПлагин §aуспешно§f перезагружен");
            }
            else if (args[0].equalsIgnoreCase("help")) {
                p.sendMessage("§6§lКИРКИ §8| §fПомощь по плагину:");
                p.sendMessage("§6/sunpickaxe reload §8| §fПерезагрузка конфига");
                p.sendMessage("§6/sunpickaxe give §8| §fполучение кирки");
            }else {
                p.sendMessage("§6§lКИРКИ §8| §fПомощь по плагину:");
                p.sendMessage("§6/sunpickaxe reload §8| §fПерезагрузка конфига");
                p.sendMessage("§6/sunpickaxe give §8| §fполучение кирки");
            }
        }else{
             if (args[0].equalsIgnoreCase("reload") ){
                SunPickaxe3x3.getInstance().reloadConfig();
                commandSender.sendMessage("§6§lКИРКИ §8| §fПлагин §aуспешно§f перезагружен");
            }else {
                 commandSender.sendMessage("§6§lКИРКИ §8| §cДанная комманда доступна только игрокам");
             }
        }

        return true;
    }
}
