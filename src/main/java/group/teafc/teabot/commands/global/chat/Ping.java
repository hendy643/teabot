package group.teafc.teabot.commands.global.chat;

import net.exploitables.slashlib.commands.standard.TopCommand;
import net.exploitables.slashlib.context.ChatContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class Ping extends TopCommand {

  public Ping() {
    super("ping", "Get a Pong!");
  }

  @Override
  public Mono<ChatContext> executeChat(ChatContext context) {
    return context.getEvent().reply("Pong!").withEphemeral(true).thenReturn(context);
  }
}
