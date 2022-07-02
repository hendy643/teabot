package group.teafc.teabot.extern.lodestone.datatypes;

import fr.whimtrip.ext.jwhthtmltopojo.annotation.ReplaceWith;
import fr.whimtrip.ext.jwhthtmltopojo.annotation.Selector;
import group.teafc.teabot.extern.lodestone.Lodestone;
import group.teafc.teabot.extern.lodestone.LodestoneType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FreeCompany extends LodestoneDataType {

    @Selector(value = "p.entry__freecompany__name")
    private String name;

    @Selector(value = "p.freecompany__text__message")
    private String slogan;

    @Selector(value = "p.freecompany__text > freecompany__text__tag")
    @ReplaceWith(value = "»|«", with = "")
    private String tag;

    public FreeCompany() {
        super(LodestoneType.FREE_COMPANY);
    }


    private void getMembersFromLodestone() {

    }
}
