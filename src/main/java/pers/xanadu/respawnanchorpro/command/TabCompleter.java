package pers.xanadu.respawnanchorpro.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TabCompleter implements org.bukkit.command.TabCompleter {
    private static final List<String> arguments_1 = Arrays.asList("reload");
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            for (String s : arguments_1) {
                if (s.toLowerCase().startsWith(args[0].toLowerCase())) {
                    result.add(s);
                }
            }
            return result;
        }
        return null;
    }
}
