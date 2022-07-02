package group.teafc.teabot.extern.lodestone;

public enum LodestoneType {

    FREE_COMPANY("freecompany"), CHARACTER("character"), ITEM("item"), UNKNOWN("unknown");

    private final String dataType;

    LodestoneType(String dataType) {
        this.dataType = dataType;
    }

    public String get() {
        return dataType;
    }
}
