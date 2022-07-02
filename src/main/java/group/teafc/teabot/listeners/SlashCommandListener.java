package group.teafc.teabot.listeners;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

public abstract class SlashCommandListener {

    protected abstract void execute(ChatInputInteractionEvent interactionEvent);

    public void processCommand(ChatInputInteractionEvent interactionEvent) {
        this.execute(interactionEvent);
    }
}
