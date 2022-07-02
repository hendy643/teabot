package group.teafc.teabot.listeners;

import discord4j.core.object.entity.Message;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Mono;

public abstract class MessageListener {

    @Value("${bot.discord.prefix}")
    private String prefix;

    public Mono<Void> processCommand(Message eventMessage) {
        return Mono.just(eventMessage)
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .filter(message -> message.getContent().equalsIgnoreCase(prefix + "ping"))
                .flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage("Pong!"))
                .then();
    }
}
