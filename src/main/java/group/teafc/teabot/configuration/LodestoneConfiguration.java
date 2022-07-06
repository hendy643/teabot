package group.teafc.teabot.configuration;

import com.github.wompus.lodestone4j.Lodestone;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LodestoneConfiguration {

    @Bean
    public Lodestone lodestone() {
        return new Lodestone();
    }
}
