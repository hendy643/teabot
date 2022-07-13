package group.teafc.teabot.configuration;

import group.teafc.lodestone.Lodestone;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LodestoneConfiguration {

  @Bean
  public Lodestone lodestone() {
    return Lodestone.getInstance();
  }
}
