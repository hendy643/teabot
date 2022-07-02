package group.teafc.teabot.commands.internal;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import group.teafc.teabot.commands.SlashCommand;
import reactor.core.publisher.Mono;

public abstract class SlashCommandImpl implements SlashCommand {

    protected String name;

    protected SlashCommandImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    protected abstract Mono<Void> execute(ChatInputInteractionEvent event);
    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        if(!event.getInteraction().getUser().isBot())
            return execute(event);
        return Mono.empty();
    }
}
