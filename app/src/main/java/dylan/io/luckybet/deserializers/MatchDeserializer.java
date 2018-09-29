package dylan.io.luckybet.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dylan.io.luckybet.models.Match;
import dylan.io.luckybet.persist.conveters.DateConverter;

public class MatchDeserializer extends StdDeserializer<Match> {

    static Pattern PATTERN_NUM = Pattern.compile("周([一二三四五六日])(.*)");

    public MatchDeserializer() {
        this(null);
    }

    protected MatchDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Match deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Match match = new Match();

        JsonNode root = parser.getCodec().readTree(parser);

        // id
        match.setId(root.get("sporttery_matchid").asText());

        // sequence
        String text = root.get("s_num").asText();
        Matcher matcher = PATTERN_NUM.matcher(text);
        if (matcher.find()) {
            String number = matcher.group(2);
            match.setSequence(Integer.parseInt(number));
        }

        // time
        Date date = DateConverter.dateFromYmdHmsString(root.get("date_cn").asText() + " " + root.get("time_cn").asText());
        match.setTime(date);

        // league
        Match.League league = new Match.League(root.get("l_id_dc").asText(), root.get("l_cn").asText(),
                root.get("l_cn_abbr").asText(), Integer.parseInt(root.get("gameweek").asText()));
        match.setLeague(league);

        // home team
        Match.Team.Standings homeStandings  = new Match.Team.Standings(
                  Integer.parseInt(root.get("hwin_h").asText())
                , Integer.parseInt(root.get("hdraw_h").asText())
                , Integer.parseInt(root.get("hlose_h").asText())
                , Integer.parseInt(root.get("hpoint_h").asText()));
        Match.Team home = new Match.Team(
                root.get("h_id_dc").asText(), root.get("h_cn").asText(),
                root.get("h_cn_abbr").asText(),
                Integer.parseInt(root.get("table_h").asText()), homeStandings);
        match.setHome(home);

        // away team
        Match.Team.Standings awayStandings  = new Match.Team.Standings(
                Integer.parseInt(root.get("awin_a").asText())
                , Integer.parseInt(root.get("adraw_a").asText())
                , Integer.parseInt(root.get("alose_a").asText())
                , Integer.parseInt(root.get("apoint_a").asText()));
        Match.Team away = new Match.Team(
                root.get("a_id_dc").asText(), root.get("a_cn").asText(),
                root.get("a_cn_abbr").asText(),
                Integer.parseInt(root.get("table_a").asText()), awayStandings);
        match.setAway(away);

        return match;
    }
}
