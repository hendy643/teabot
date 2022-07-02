package group.teafc.teabot.commands.registers;

import discord4j.common.JacksonResources;
import discord4j.core.GatewayDiscordClient;
import discord4j.discordjson.json.ApplicationCommandRequest;
import discord4j.rest.RestClient;
import discord4j.rest.service.ApplicationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ConstantConditions")
@Component
@Log4j2
public class GlobalCommandRegistrar {

    private final RestClient restClient;

    public GlobalCommandRegistrar(final GatewayDiscordClient restClient) {
        this.restClient = restClient.getRestClient();
    }

    public void registerCommands() throws Exception {
        final JacksonResources d4jMapper = JacksonResources.create();
        PathMatchingResourcePatternResolver matcher = new PathMatchingResourcePatternResolver();
        final ApplicationService applicationService = restClient.getApplicationService();
        final long applicationId = restClient.getApplicationId().block();
        List<ApplicationCommandRequest> commands = new ArrayList<>();
        for (Resource resource : matcher.getResources("commands/global/*.json")) {
            ApplicationCommandRequest request = d4jMapper.getObjectMapper().readValue(resource.getInputStream(), ApplicationCommandRequest.class);
            commands.add(request);
        }
        applicationService.bulkOverwriteGlobalApplicationCommand(applicationId, commands).doOnNext(command -> log.debug("Successfully registered Global Command {}", command.name())).doOnError(e -> log.error("Failed to register global command", e)).subscribe();
    }
}
