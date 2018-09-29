package dylan.io.luckybet.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

import dylan.io.luckybet.persist.conveters.JsonObjectContainerConverter;
import dylan.io.luckybet.persist.conveters.JsonObjectConverter;

@Entity(tableName = "t_handicap"
        ,foreignKeys = @ForeignKey(entity = Match.class, parentColumns = "id", childColumns = "matchId")
        ,indices = {@Index("matchId")}
)
public class Handicap {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    private String matchId;

    private String companyId;
    private String companyName;

    @TypeConverters({JsonObjectConverter.class})
    private HandicapChange current;

    @TypeConverters({JsonObjectConverter.class})
    private HandicapChange start;

    private double kellyIndexOfHome;
    private double kellyIndexOfAway;

    @TypeConverters({JsonObjectContainerConverter.class})
    private List<HandicapChange> handicapChanges;

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
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

    public HandicapChange getCurrent() {
        return current;
    }

    public void setCurrent(HandicapChange current) {
        this.current = current;
    }

    public HandicapChange getStart() {
        return start;
    }

    public void setStart(HandicapChange start) {
        this.start = start;
    }

    public double getKellyIndexOfHome() {
        return kellyIndexOfHome;
    }

    public void setKellyIndexOfHome(double kellyIndexOfHome) {
        this.kellyIndexOfHome = kellyIndexOfHome;
    }

    public double getKellyIndexOfAway() {
        return kellyIndexOfAway;
    }

    public void setKellyIndexOfAway(double kellyIndexOfAway) {
        this.kellyIndexOfAway = kellyIndexOfAway;
    }

    public List<HandicapChange> getHandicapChanges() {
        return handicapChanges;
    }

    public void setHandicapChanges(List<HandicapChange> handicapChanges) {
        this.handicapChanges = handicapChanges;
    }

    public static class HandicapChange {

        private String offerId;

        private Date time;

        private String name;

        private double waterOfHome;

        private double waterOfAway;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getWaterOfHome() {
            return waterOfHome;
        }

        public void setWaterOfHome(double waterOfHome) {
            this.waterOfHome = waterOfHome;
        }

        public double getWaterOfAway() {
            return waterOfAway;
        }

        public void setWaterOfAway(double waterOfAway) {
            this.waterOfAway = waterOfAway;
        }
    }
}