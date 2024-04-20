package ros.eagleoffire.rosvoiesninjas.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.NotNull;
import ros.eagleoffire.rosvoiesninjas.ProgressionVoiesNinjas.ProgressionVoiesNinjasProvider;

import java.util.Objects;

public class GetProgressionVoiesNinjasCommand {

    public GetProgressionVoiesNinjasCommand(CommandDispatcher<CommandSourceStack> Dispatcher){
        Dispatcher.register(Commands.literal("getxp")
                .then(Commands.argument("target", StringArgumentType.string())
                .executes(commandContext ->
                GetProgressionVoiesNinjas(commandContext.getSource(),
                        StringArgumentType.getString(commandContext, "target")))
        ));
    }

    private int GetProgressionVoiesNinjas(CommandSourceStack source, String target) throws CommandSyntaxException {
        PlayerList OnlinePlayer = source.getServer().getPlayerList();
        @NotNull
        ServerPlayer TargetedPlayer = Objects.requireNonNull(OnlinePlayer.getPlayerByName(target));

        TargetedPlayer.getCapability(ProgressionVoiesNinjasProvider.PROGRESSION_VOIES_NINJAS).ifPresent(progression -> {
           source.sendSuccess(() -> {
            return Component.literal("Player " + target + " have " + progression.getExperience());
        }, true);
        });
        return 1;
    }

}
