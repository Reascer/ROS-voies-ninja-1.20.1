package ros.eagleoffire.rosvoiesninjas.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
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

public class RemoveProgressionVoiesNinjasCommand {

    public RemoveProgressionVoiesNinjasCommand(CommandDispatcher<CommandSourceStack> Dispatcher){
        Dispatcher.register(Commands.literal("rmxp")
                .then(Commands.argument("target", StringArgumentType.string())
                .then(Commands.argument("voie ninja", StringArgumentType.string())
                .then(Commands.argument("quantité", IntegerArgumentType.integer())
                .executes(commandContext ->
                RemoveProgressionVoiesNinjas(commandContext.getSource(),
                        StringArgumentType.getString(commandContext, "target"),
                        StringArgumentType.getString(commandContext, "voie ninja"),
                        IntegerArgumentType.getInteger(commandContext, "quantité") ))
        ))));
    }

    private int RemoveProgressionVoiesNinjas(CommandSourceStack source, String target, String voie, int qts) throws CommandSyntaxException {
        PlayerList OnlinePlayer = source.getServer().getPlayerList();
        @NotNull
        ServerPlayer TargetedPlayer = Objects.requireNonNull(OnlinePlayer.getPlayerByName(target));

        TargetedPlayer.getCapability(ProgressionVoiesNinjasProvider.PROGRESSION_VOIES_NINJAS).ifPresent(progression -> {
           progression.subXP(progression.getByName(voie),qts);
        });

        source.sendSuccess(() -> {
                    return Component.literal("Successfully removed " + qts + "XP to " + voie + " for player " + target);
                }, true);
        return 1;
    }

}
