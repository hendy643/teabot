package group.teafc.teabot.services;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import group.teafc.teabot.commands.SlashCommand;
import group.teafc.teabot.commands.registers.GlobalCommandRegistrar;
import group.teafc.teabot.commands.registers.GuildCommandRegistrar;
import group.teafc.teabot.db.entities.GuildEntity;
import group.teafc.teabot.db.repositories.GuildRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DiscordService {

    private final GlobalCommandRegistrar globalCommandRegistrar;
    private final GuildCommandRegistrar guildCommandsRegistrar;
    private final GatewayDiscordClient client;
    private final Collection<SlashCommand> commands;

    private final GuildRepository guildRepository;

    @Value("${bot.ids.owner.fc}")
    private Long ownerFcId;

    @Value("${bot.ids.owner.id}")
    private Long ownerId;

    @Value("${bot.discord.client.id}")
    private String clientId;

    public DiscordService(final GuildRepository guildRepository, final List<SlashCommand> slashCommands, final GatewayDiscordClient client, final GlobalCommandRegistrar globalCommands, final GuildCommandRegistrar guildCommands) {
        this.guildRepository = guildRepository;
        this.client = client;
        this.commands = slashCommands;
        this.globalCommandRegistrar = globalCommands;
        this.guildCommandsRegistrar = guildCommands;
    }

    @PostConstruct
    public void postConstruct() {
        client.on(ChatInputInteractionEvent.class, this::handle).subscribe();
        try {
            globalCommandRegistrar.registerCommands();
            registerNewGuild(ownerFcId);
        } catch (Exception e) {
            log.error("Error registering commands: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        guildRepository.findAll().forEach(guildEntity -> this.guildCommandsRegistrar.registerNewGuild(guildEntity.getGuildId()));
    }

    @Transactional
    public void registerNewGuild(long guildId) {
        var found = Optional.ofNullable(guildRepository.findByGuildId(guildId));

        if (found.isEmpty()) {
            var newGuild = new GuildEntity();
            newGuild.setGuildId(guildId);
            guildRepository.save(newGuild);
            found = Optional.ofNullable(guildRepository.findByGuildId(guildId));
        }

        found.ifPresent(guildEntity -> guildCommandsRegistrar.registerNewGuild(guildEntity.getGuildId()));
    }

    @Transactional
    public void unregisterGuild(long guildId) {

    }

    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return Flux.fromIterable(commands).filter(command -> command.getName().equals(event.getCommandName())).next().flatMap(command -> command.handle(event));
    }
}
