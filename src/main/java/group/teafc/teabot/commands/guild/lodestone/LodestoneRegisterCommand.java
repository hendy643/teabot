package group.teafc.teabot.commands.guild.lodestone;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import group.teafc.teabot.commands.internal.SlashCommandImpl;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class LodestoneRegisterCommand extends SlashCommandImpl {

    public LodestoneRegisterCommand() {
        super("register");
    }

    @Override
    protected Mono<Void> execute(ChatInputInteractionEvent event) {
        return Mono.empty();
    }
}
