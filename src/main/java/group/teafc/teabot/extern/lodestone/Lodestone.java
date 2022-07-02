package group.teafc.teabot.extern.lodestone;

import fr.whimtrip.ext.jwhthtmltopojo.HtmlToPojoEngine;
import fr.whimtrip.ext.jwhthtmltopojo.intrf.HtmlAdapter;
import group.teafc.teabot.extern.lodestone.datatypes.Character;
import group.teafc.teabot.extern.lodestone.datatypes.FreeCompany;
import group.teafc.teabot.extern.lodestone.datatypes.Item;
import group.teafc.teabot.extern.lodestone.datatypes.LodestoneDataType;
import group.teafc.teabot.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class Lodestone {

    private final HttpUtils httpUtil;

    @Value("${bot.config.lodestone.baseUrl}")
    private String baseUrl;

    public Lodestone(HttpUtils httpUtil) {
        this.httpUtil = httpUtil;
    }

    public Optional<LodestoneDataType> getById(final LodestoneType type, final String id) {
        var url = String.format(baseUrl, type.get(), id);
        return get(url, type, id);
    }

    private Optional<LodestoneDataType> get(String url, LodestoneType type, String id) {
        var response = httpUtil.doRequest(url);

        if (response.isEmpty()) {
            log.warn("no response from: {}", url);
            return Optional.empty();
        }

        var item = parseRawPageData(response, type);
        if (item instanceof Character) {
            ((Character) item).setGcRank(((Character) item).getGcRank().stripLeading());
        }
        item.setId(id);
        return Optional.of(item);
    }

    private LodestoneDataType parseRawPageData(String content, LodestoneType dType) {
        HtmlToPojoEngine htmlToPojoEngine = HtmlToPojoEngine.create();
        @SuppressWarnings("rawtypes") HtmlAdapter adapter = switch (dType) {
            case FREE_COMPANY -> htmlToPojoEngine.adapter(FreeCompany.class);
            case ITEM -> htmlToPojoEngine.adapter(Item.class);
            case CHARACTER -> htmlToPojoEngine.adapter(Character.class);

            default -> htmlToPojoEngine.adapter(LodestoneDataType.class);
        };

        return (LodestoneDataType) adapter.fromHtml(content);
    }
}
