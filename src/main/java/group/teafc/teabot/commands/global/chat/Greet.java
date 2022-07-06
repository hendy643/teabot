package group.teafc.teabot.commands.global.chat;

import net.exploitables.slashlib.commands.standard.TopCommand;
import net.exploitables.slashlib.context.ChatContext;
import net.exploitables.slashlib.utility.OptionBuilder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class Greet extends TopCommand {

    public Greet() {
        super("greet", "Says a nice hello");
        addOption(OptionBuilder.requiredString("name", "your name").build());
    }

    @Override
    public Mono<ChatContext> executeChat(ChatContext context) {
        return Mono.just(context.getOptions().getString("name"))
            .flatMap(name -> context.getEvent()
                .reply("Hello %s".formatted(name))
                .withEphemeral(true))
            .thenReturn(context);
    }
}
