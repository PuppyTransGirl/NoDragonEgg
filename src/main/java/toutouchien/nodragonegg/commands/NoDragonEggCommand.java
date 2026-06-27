package toutouchien.nodragonegg.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import toutouchien.nodragonegg.NoDragonEgg;
import toutouchien.nodragonegg.utils.CommandUtils;
import toutouchien.nodragonegg.utils.MathUtils;
import org.bukkit.Bukkit;

public final class NoDragonEggCommand {
    private NoDragonEggCommand() {
        throw new IllegalStateException("Command class");
    }

    public static LiteralCommandNode<CommandSourceStack> get() {
        return Commands.literal("nodragonegg")
                .requires(css -> CommandUtils.defaultRequirements(css, "nodragonegg.command.nodragonegg"))
                .then(reloadCommand())
                .build();
    }

    private static LiteralArgumentBuilder<CommandSourceStack> reloadCommand() {
        return Commands.literal("reload")
                .requires(css -> CommandUtils.defaultRequirements(css, "nodragonegg.command.nodragonegg.reload"))
                .executes(ctx -> {
                    CommandSender sender = CommandUtils.sender(ctx);

                    sender.sendMessage(MiniMessage.miniMessage().deserialize(
                        "<gradient:#4C00FF:#E356E5>ɴᴏᴅʀᴀɢᴏɴᴇɢɢ</gradient> <#B0AEC1>»</#B0AEC1> <#B0AEC1>Reloading NoDragonEgg...</#B0AEC1>"
                    ));
                    long startNanos = System.nanoTime();

                    Bukkit.getAsyncScheduler().runNow(NoDragonEgg.instance(), task -> {
                        try {
                            NoDragonEgg.instance().reload();
                            double timeTaken = (System.nanoTime() - startNanos) / 1_000_000D;

                            sender.sendMessage(MiniMessage.miniMessage().deserialize(
                                "<gradient:#4C00FF:#E356E5>ɴᴏᴅʀᴀɢᴏɴᴇɢɢ</gradient> <#B0AEC1>»</#B0AEC1> <#7AF291>NoDragonEgg has been reloaded. (" + MathUtils.decimalRound(timeTaken, 2) + " ms)</#7AF291>"
                            ));
                        } catch (Exception e) {
                            NoDragonEgg.instance().getSLF4JLogger().error("Failed to reload NoDragonEgg", e);
                            sender.sendMessage(MiniMessage.miniMessage().deserialize(
                                "<gradient:#4C00FF:#E356E5>ɴᴏᴅʀᴀɢᴏɴᴇɢɢ</gradient> <#B0AEC1>»</#B0AEC1> <#F27474>An error occurred while reloading NoDragonEgg. Please check the console for more information.</#F27474>"
                            ));
                        }
                    });

                    return Command.SINGLE_SUCCESS;
                });
    }
}
