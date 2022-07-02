package group.teafc.teabot.commands.global;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import group.teafc.teabot.commands.internal.SlashCommandImpl;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class Ping extends SlashCommandImpl {

    public Ping() {
        super("ping");
    }

    @Override
    public Mono<Void> execute(ChatInputInteractionEvent event) {
        //We reply to the command with "Pong!" and make sure it is ephemeral (only the command user can see it)
        return event.reply().withEphemeral(true).withContent("Pong!");
    }
}
