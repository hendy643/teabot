package group.teafc.teabot.commands.global.chat;

import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import net.exploitables.slashlib.commands.standard.TopCommand;
import net.exploitables.slashlib.context.ChatContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class Invite extends TopCommand {

    @Value("${bot.discord.client.id}")
    private String clientId;

    String inviteUrl;

    public Invite() {
        super("invite", "Get an invite link for your own server");
        inviteUrl =
                "https://discord.com/oauth2/authorize?client_id=%s&permissions=8&scope=bot applications.commands"
                        .formatted(clientId).replace(" ", "%20");
    }

    @Override
    public Mono<ChatContext> executeChat(ChatContext context) {
        EmbedCreateSpec spec = EmbedCreateSpec.builder().color(Color.SUMMER_SKY)
                .title("TeaBot Invite Link").addField("Link", inviteUrl, true).build();
        return context.getEvent().reply().withEphemeral(true).withEmbeds(spec).thenReturn(context);
    }
}
