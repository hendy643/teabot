package group.teafc.teabot.extern.lodestone.datatypes.internal;

import java.util.EnumMap;

public enum Rank {
    QUEEN, HEAVENS_WARD, SKY_PIRATE, HIGH_HOUSE, NOBILITY, CITEAZEN;

    public static final EnumMap<Rank, String> values = new EnumMap<>(Rank.class);

    static {
        values.put(QUEEN, "Queen");
        values.put(HEAVENS_WARD, "Heavens' Ward");
        values.put(SKY_PIRATE, "Sky Pirate");
        values.put(HIGH_HOUSE, "High House");
        values.put(NOBILITY, "Nobility");
        values.put(CITEAZEN, "CiTEAzen");
    }
}
