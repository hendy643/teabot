package group.teafc.teabot.extern.lodestone.datatypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import group.teafc.teabot.extern.lodestone.LodestoneType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LodestoneDataType {

    protected String id;
    protected LodestoneType type;

    protected LodestoneDataType(LodestoneType type) {
        this.type = type;
    }

    public static LodestoneDataType empty() {
        return new LodestoneDataType(LodestoneType.UNKNOWN);
    }

    public String toJson() {
        var mapper = new JsonMapper();
        var ret = String.format("{\"Error\":\"Unable to map %s to JSON\"}", type.get());
        try {
            ret = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return ret;
    }

    @Override
    public String toString() {
        return toJson();
    }
}
