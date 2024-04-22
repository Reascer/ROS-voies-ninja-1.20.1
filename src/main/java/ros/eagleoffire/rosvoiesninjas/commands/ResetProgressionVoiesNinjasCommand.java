package ros.eagleoffire.rosvoiesninjas.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import org.jetbrains.annotations.NotNull;
import ros.eagleoffire.rosvoiesninjas.ProgressionVoiesNinjas.ProgressionVoiesNinjas;
import ros.eagleoffire.rosvoiesninjas.ProgressionVoiesNinjas.ProgressionVoiesNinjasProvider;

import java.util.Objects;

public class ResetProgressionVoiesNinjasCommand {

    public ResetProgressionVoiesNinjasCommand(CommandDispatcher<CommandSourceStack> Dispatcher){
        Dispatcher.register(Commands.literal("resetxp")
                .then(Commands.argument("target", StringArgumentType.string())
                .executes(commandContext ->
                ResetProgressionVoiesNinjas(commandContext.getSource(),
                        StringArgumentType.getString(commandContext, "target")))
        ));
    }

    private int ResetProgressionVoiesNinjas(CommandSourceStack source, String target) throws CommandSyntaxException {
        PlayerList OnlinePlayer = source.getServer().getPlayerList();
        @NotNull
        ServerPlayer TargetedPlayer = Objects.requireNonNull(OnlinePlayer.getPlayerByName(target));

        TargetedPlayer.getCapability(ProgressionVoiesNinjasProvider.PROGRESSION_VOIES_NINJAS).ifPresent(ProgressionVoiesNinjas::RPK);

        source.sendSuccess(() -> {
                    return Component.literal("Successfully reset player " + target);
                }, true);
        return 1;
    }

}
