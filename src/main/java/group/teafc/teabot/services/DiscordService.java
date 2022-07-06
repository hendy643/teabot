package group.teafc.teabot.services;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.EventDispatcher;
import discord4j.core.object.presence.ClientActivity;
import discord4j.core.object.presence.ClientPresence;
import discord4j.gateway.intent.IntentSet;
import group.teafc.teabot.commands.global.chat.Greet;
import group.teafc.teabot.commands.global.chat.Invite;
import group.teafc.teabot.commands.global.chat.Ping;
import group.teafc.teabot.db.repositories.GuildRepository;
import lombok.extern.slf4j.Slf4j;
import net.exploitables.slashlib.SlashLib;
import net.exploitables.slashlib.SlashLibBuilder;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Slf4j
@Service
public class DiscordService {

  private DiscordClient discordClient;
  private GatewayDiscordClient client;

  private final GuildRepository guildRepository;

  private long applicationId;

  private SlashLib slashLib;
  private EventDispatcher dispatcher;

  @Value("${bot.ids.owner.fc}")
  private Long ownerFcId;

  @Value("${bot.ids.owner.id}")
  private Long ownerId;

  @Value("${bot.discord.client.id}")
  private String clientId;

  @Value("${bot.discord.token}")
  private String token;

  public DiscordService(final GuildRepository guildRepository) {
    this.guildRepository = guildRepository;
  }

  @PostConstruct
  private void postConstruct() {
    discordClient = DiscordClient.create(token);
    dispatcher = EventDispatcher.builder().build();
    initCommandLib();
    initDiscord();
  }

  private void initDiscord() {
    log.debug("Initialising Discord4J");
    client =
        discordClient.gateway().setEventDispatcher(dispatcher).setEnabledIntents(IntentSet.all())
            .setInitialPresence(
                ignore -> ClientPresence.online(ClientActivity.listening("to your every word")))
            .login().block();

    applicationId = Optional.ofNullable(discordClient.getApplicationId().block()).orElse(0L);
    slashLib.getCommandRegister().registerGlobalCommands(discordClient.getApplicationService(),
        applicationId);
  }

  private void initCommandLib() {
    log.debug("Building Commands");
    var slashLibBuilder = SlashLibBuilder.create().addGlobalChatCommand(new Ping())
        .addGlobalChatCommand(new Invite()).addGlobalChatCommand(new Greet());

    slashLib = slashLibBuilder.build();
    slashLib.registerAsListener(dispatcher);
  }

  public final DiscordClient getDiscordClient() {
    return discordClient;
  }

  public final SlashLib getSlashLib() {
    return slashLib;
  }

  public final GatewayDiscordClient getDiscordGateway() {
    return client;
  }

  public final long getApplicationId() {
    return applicationId;
  }
}
