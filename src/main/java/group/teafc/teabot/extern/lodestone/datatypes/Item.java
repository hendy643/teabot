package group.teafc.teabot.extern.lodestone.datatypes;

import group.teafc.teabot.extern.lodestone.LodestoneType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Item extends LodestoneDataType {

    public Item() {
        super(LodestoneType.ITEM);
    }
}
