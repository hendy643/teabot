package group.teafc.teabot.extern.lodestone.datatypes;

import fr.whimtrip.ext.jwhthtmltopojo.annotation.Selector;
import group.teafc.teabot.extern.lodestone.LodestoneType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Character extends LodestoneDataType {

    @Selector(value = "p.frame__chara__name")
    private String name;

    @Selector(value = "p.frame__chara__title")
    private String title;

    @Selector(
            value = "div.character-block__box > p.character-block__name",
            format = "(Miqo'te|Au Ra|\\w+)\\s(.+)\\s\\/ (♀|♂)",
            indexForRegexPattern = 1)
    private String race;

    @Selector(
            value = "div.character-block__box > p.character-block__name",
            format = "(Miqo'te|Au Ra|\\w+)\\s(.+)\\s\\/ (♀|♂)",
            indexForRegexPattern = 2)
    private String clan;

    @Selector(value = "div.character__freecompany__name > h4 > a")
    private String fcName;

    @Selector(
            value =
                    "div.character__profile__data__detail > div:nth-child(4) > div.character-block__box > p.character-block__name",
            format = "^\\s*(.+)\\s+/\\s*(.+)",
            indexForRegexPattern = 1)
    private String grandCompany;

    @Selector(
            value =
                    "div.character__profile__data__detail > div:nth-child(4) > div.character-block__box > p.character-block__name",
            format = "^\\s*(.+)\\s+/\\s*(.+)",
            indexForRegexPattern = 2)
    private String gcRank;

    public Character() {
        super(LodestoneType.CHARACTER);
    }
}
