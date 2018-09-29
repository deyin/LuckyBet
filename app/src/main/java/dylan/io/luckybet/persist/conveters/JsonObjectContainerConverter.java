package dylan.io.luckybet.persist.conveters;

import android.arch.persistence.room.TypeConverter;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import dylan.io.luckybet.models.Handicap;
import dylan.io.luckybet.models.Odds;

public class JsonObjectContainerConverter {

    static ObjectMapper MAPPER = new ObjectMapper();

    @TypeConverter
    public List<Odds> converOddsListFromJson(String json) {
        try {
            return MAPPER.readValue(json, new TypeReference<List<Odds>>() {
            });
        } catch (IOException e) {
            Log.e(JsonObjectContainerConverter.class.getName(), "converFromJson: ", e);
        }
        return Collections.emptyList();
    }

    @TypeConverter
    public List<Odds.OddsChange> converOddChangeListFromJson(String json) {
        try {
            return MAPPER.readValue(json, new TypeReference<List<Odds.OddsChange>>() {
            });
        } catch (IOException e) {
            Log.e(JsonObjectContainerConverter.class.getName(), "converFromJson: ", e);
        }
        return null;
    }

    @TypeConverter
    public List<Handicap> converHandicapListFromJson(String json) {
        try {
            return MAPPER.readValue(json, new TypeReference<List<Handicap>>() {
            });
        } catch (IOException e) {
            Log.e(JsonObjectContainerConverter.class.getName(), "converFromJson: ", e);
        }
        return null;
    }

    @TypeConverter
    public List<Handicap.HandicapChange> converHandicapChangeListFromJson(String json) {
        try {
            return MAPPER.readValue(json, new TypeReference<List<Handicap.HandicapChange>>() {
            });
        } catch (IOException e) {
            Log.e(JsonObjectContainerConverter.class.getName(), "converFromJson: ", e);
        }
        return null;
    }

    @TypeConverter
    public String converToJson(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (IOException e) {
            Log.e(JsonObjectContainerConverter.class.getName(), "converToJson: ", e);
        }
        return null;
    }


}
