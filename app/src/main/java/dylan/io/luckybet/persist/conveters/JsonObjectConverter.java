package dylan.io.luckybet.persist.conveters;

import android.arch.persistence.room.TypeConverter;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import dylan.io.luckybet.models.Handicap;
import dylan.io.luckybet.models.Match;
import dylan.io.luckybet.models.Odds;

public class JsonObjectConverter {

    static ObjectMapper MAPPER = new ObjectMapper();

    @TypeConverter
    public Match.League converLeagueFromJson(String json) {
        try {
            return MAPPER.readValue(json, Match.League.class);
        } catch (IOException e) {
            Log.e(JsonObjectConverter.class.getName(), "converFromJson: ", e);
        }
        return null;
    }

    @TypeConverter
    public Match.Team converTeamFromJson(String json) {
        try {
            return MAPPER.readValue(json, Match.Team.class);
        } catch (IOException e) {
            Log.e(JsonObjectConverter.class.getName(), "converFromJson: ", e);
        }
        return null;
    }


    @TypeConverter
    public Match.Team.Standings convertStandingsFromJson(String json) {
        try {
            return MAPPER.readValue(json,  Match.Team.Standings.class);
        } catch (IOException e) {
            Log.e(JsonObjectConverter.class.getName(), "converFromJson: ", e);
        }
        return null;
    }

    @TypeConverter
    public Odds.OddsChange converOddsChangeFromJson(String json) {
        try {
            return MAPPER.readValue(json, Odds.OddsChange.class);
        } catch (IOException e) {
            Log.e(JsonObjectConverter.class.getName(), "converFromJson: ", e);
        }
        return null;
    }

    @TypeConverter
    public Handicap.HandicapChange converHandicapChangeFromJson(String json) {
        try {
            return MAPPER.readValue(json, Handicap.HandicapChange.class);
        } catch (IOException e) {
            Log.e(JsonObjectConverter.class.getName(), "converFromJson: ", e);
        }
        return null;
    }

    @TypeConverter
    public String converToJson(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (IOException e) {
            Log.e(JsonObjectConverter.class.getName(), "converToJson: ", e);
        }
        return null;
    }


}
