package group.teafc.teabot.commands.global;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import group.teafc.teabot.commands.internal.SlashCommandImpl;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class Greet extends SlashCommandImpl {

    public Greet() {
        super("greet");
    }

    @Override
    public Mono<Void> execute(ChatInputInteractionEvent event) {
        String name = event.getOption("name")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .get();
        return event.reply()
                .withEphemeral(true)
                .withContent("Hello, " + name);
    }
}
