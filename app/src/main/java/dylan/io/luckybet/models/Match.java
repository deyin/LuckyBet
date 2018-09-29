package dylan.io.luckybet.models;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

import dylan.io.luckybet.deserializers.MatchDeserializer;
import dylan.io.luckybet.persist.conveters.DateConverter;
import dylan.io.luckybet.persist.conveters.JsonObjectConverter;

@Entity(tableName = "t_match")
@JsonDeserialize(using = MatchDeserializer.class)
public class Match {

    @PrimaryKey
    @NonNull
    private String id;

    private int sequence;

    @TypeConverters({DateConverter.class})
    private Date time;

    @TypeConverters({JsonObjectConverter.class})
    @Embedded(prefix = "league")
    private League league;

    @TypeConverters({JsonObjectConverter.class})
    @Embedded(prefix = "home")
    private Team home;

    @TypeConverters({JsonObjectConverter.class})
    @Embedded(prefix = "away")
    private Team away;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Team getHome() {
        return home;
    }

    public void setHome(Team home) {
        this.home = home;
    }

    public Team getAway() {
        return away;
    }

    public void setAway(Team away) {
        this.away = away;
    }

    public static class League {
        private String id;

        private String name;
        private String abbrName;

        private int round;

        public League(String id, String name, String abbrName, int round) {
            this.id = id;
            this.name = name;
            this.abbrName = abbrName;
            this.round = round;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAbbrName() {
            return abbrName;
        }

        public void setAbbrName(String abbrName) {
            this.abbrName = abbrName;
        }

        public int getRound() {
            return round;
        }

        public void setRound(int round) {
            this.round = round;
        }
    }

    public static class Team {
        private String id;
        private String name;
        private String abbrName;
        private int order;
        @TypeConverters({JsonObjectConverter.class})
        @Embedded
        private Standings standings;

        public Team(String id, String name, String abbrName, int order, Standings standings) {
            this.id = id;
            this.name = name;
            this.abbrName = abbrName;
            this.order = order;
            this.standings = standings;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAbbrName() {
            return abbrName;
        }

        public void setAbbrName(String abbrName) {
            this.abbrName = abbrName;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public Standings getStandings() {
            return standings;
        }

        public void setStandings(Standings standings) {
            this.standings = standings;
        }

        public static class Standings {
            private int win;
            private int draw;
            private int lose;

            private int points;

            public Standings(int win, int draw, int lose, int points) {
                this.win = win;
                this.draw = draw;
                this.lose = lose;
                this.points = points;
            }

            public int getWin() {
                return win;
            }

            public void setWin(int win) {
                this.win = win;
            }

            public int getDraw() {
                return draw;
            }

            public void setDraw(int draw) {
                this.draw = draw;
            }

            public int getLose() {
                return lose;
            }

            public void setLose(int lose) {
                this.lose = lose;
            }

            public int getPoints() {
                return points;
            }

            public void setPoints(int points) {
                this.points = points;
            }

            @Override
            public String toString() {
                return "Standings{" +
                        "win=" + win +
                        ", draw=" + draw +
                        ", lose=" + lose +
                        ", points=" + points +
                        '}';
            }
        } // end class Standings

        @Override
        public String toString() {
            return "Team{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", abbrName='" + abbrName + '\'' +
                    ", order=" + order +
                    ", standings=" + standings +
                    '}';
        }
    } // end class Team

    @Override
    public String toString() {
        return "Match{" +
                "id='" + id + '\'' +
                ", sequence=" + sequence +
                ", time=" + time +
                ", league=" + league +
                ", home=" + home +
                ", away=" + away +
                '}';
    }
} // end class Match
