package dylan.io.luckybet.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

import dylan.io.luckybet.persist.conveters.DateConverter;
import dylan.io.luckybet.persist.conveters.JsonObjectContainerConverter;
import dylan.io.luckybet.persist.conveters.JsonObjectConverter;

@Entity(tableName = "t_odds"
        ,foreignKeys = @ForeignKey(entity = Match.class, parentColumns = "id", childColumns = "matchId")
        ,indices = {@Index("matchId")}
)
public class Odds {

    @PrimaryKey
    @NonNull
    private String id;

    @NonNull
    private String matchId;

    @NonNull
    private String companyId;
    private String companyName;

    @TypeConverters({JsonObjectConverter.class})
    private OddsChange current;

    @TypeConverters({JsonObjectConverter.class})
    private OddsChange start;

    private double kellyIndexOfWin;
    private double kellyIndexOfDraw;
    private double kellyIndexOfLose;

    @TypeConverters({JsonObjectContainerConverter.class})
    private List<OddsChange> oddsChanges;

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getMatchId() {
        return matchId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<OddsChange> getOddsChanges() {
        return oddsChanges;
    }

    public void setOddsChanges(List<OddsChange> oddsChanges) {
        this.oddsChanges = oddsChanges;
    }

    public OddsChange getCurrent() {
        return current;
    }

    public void setCurrent(OddsChange current) {
        this.current = current;
    }

    public OddsChange getStart() {
        return start;
    }

    public void setStart(OddsChange start) {
        this.start = start;
    }

    public double getKellyIndexOfWin() {
        return kellyIndexOfWin;
    }

    public void setKellyIndexOfWin(double kellyIndexOfWin) {
        this.kellyIndexOfWin = kellyIndexOfWin;
    }

    public double getKellyIndexOfDraw() {
        return kellyIndexOfDraw;
    }

    public void setKellyIndexOfDraw(double kellyIndexOfDraw) {
        this.kellyIndexOfDraw = kellyIndexOfDraw;
    }

    public double getKellyIndexOfLose() {
        return kellyIndexOfLose;
    }

    public void setKellyIndexOfLose(double kellyIndexOfLose) {
        this.kellyIndexOfLose = kellyIndexOfLose;
    }

    public static class OddsChange {
        private String offerId;
        @TypeConverters({DateConverter.class})
        private Date time;

        private double win;
        private double draw;
        private double lose;

        public String getOfferId() {
            return offerId;
        }

        public void setOfferId(String offerId) {
            this.offerId = offerId;
        }

        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }

        public double getWin() {
            return win;
        }

        public void setWin(double win) {
            this.win = win;
        }

        public double getDraw() {
            return draw;
        }

        public void setDraw(double draw) {
            this.draw = draw;
        }

        public double getLose() {
            return lose;
        }

        public void setLose(double lose) {
            this.lose = lose;
        }
    }
}