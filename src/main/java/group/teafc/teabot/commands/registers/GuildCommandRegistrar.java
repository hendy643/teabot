package group.teafc.teabot.commands.registers;

import discord4j.common.JacksonResources;
import discord4j.core.GatewayDiscordClient;
import discord4j.discordjson.json.ApplicationCommandRequest;
import discord4j.rest.RestClient;
import discord4j.rest.service.ApplicationService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ConstantConditions")
@Component
@Slf4j
public class GuildCommandRegistrar {
    private final RestClient restClient;

    public GuildCommandRegistrar(final GatewayDiscordClient restClient) {
        this.restClient = restClient.getRestClient();
    }

    @SneakyThrows
    public void registerNewGuild(long guildId) {
        final JacksonResources d4jMapper = JacksonResources.create();
        PathMatchingResourcePatternResolver matcher = new PathMatchingResourcePatternResolver();
        final ApplicationService applicationService = restClient.getApplicationService();
        final long applicationId = restClient.getApplicationId().block();
        List<ApplicationCommandRequest> commands = new ArrayList<>();
        for (Resource resource : matcher.getResources("commands/guild/*.json")) {
            ApplicationCommandRequest request = d4jMapper.getObjectMapper().readValue(resource.getInputStream(), ApplicationCommandRequest.class);
            commands.add(request);
        }
        applicationService.bulkOverwriteGuildApplicationCommand(applicationId, guildId, commands).doOnNext(ignore -> log.debug("Successfully registered Guild Commands")).doOnError(e -> log.error("Failed to register global commands", e)).subscribe();

    }
}

