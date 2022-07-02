package group.teafc.teabot.listeners;

import discord4j.core.event.domain.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;


public interface EventListener<T extends Event> {

    Logger log = LoggerFactory.getLogger(EventListener.class);
    Class<T> getEventType();

    Mono<Void> execute(T event);

    default Mono<Void> handleError(Throwable error) {
        log.error("Whoops, an error happened in EventListener.class.\n{}", error.getMessage());
        return Mono.empty();
    }
}
