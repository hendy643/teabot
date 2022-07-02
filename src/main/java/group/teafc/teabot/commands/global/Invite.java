package group.teafc.teabot.commands.global;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import group.teafc.teabot.commands.internal.SlashCommandImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class Invite extends SlashCommandImpl {

    @Value("${bot.discord.client.id}")
    private String clientId;

    String inviteUrl;

    public Invite() {
        super("invite");
        inviteUrl = "https://discord.com/oauth2/authorize?client_id=%s&permissions=8&scope=bot applications.commands".formatted(clientId).replace(" ", "%20");
    }

    @Override
    public Mono<Void> execute(ChatInputInteractionEvent event) {
        EmbedCreateSpec spec = EmbedCreateSpec.builder().color(Color.SUMMER_SKY).title("TeaBot Invite Link").addField("Link", inviteUrl, true).build();
        return event.reply().withEphemeral(true).withEmbeds(spec);
    }
}
