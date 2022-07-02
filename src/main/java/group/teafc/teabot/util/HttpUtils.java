package group.teafc.teabot.util;

import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Log4j2
@Component
public class HttpUtils {

    @Value("${bot.config.user-agent}")
    private String userAgent;

    public String doRequest(String url) {
        try {
            return Unirest.get(url).header("user-agent", userAgent).asString().getBody();
        } catch (UnirestException ex) {
            log.warn("unable to get {}", url);
            log.debug(Arrays.toString(ex.getStackTrace()));

        }
        return "";
    }
}
