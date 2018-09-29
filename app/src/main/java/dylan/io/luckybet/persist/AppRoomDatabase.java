package dylan.io.luckybet.persist;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import dylan.io.luckybet.models.Handicap;
import dylan.io.luckybet.models.Match;
import dylan.io.luckybet.models.Odds;
import dylan.io.luckybet.persist.daos.HandicapDao;
import dylan.io.luckybet.persist.daos.MatchDao;
import dylan.io.luckybet.persist.daos.OddsDao;

@Database(entities = {Match.class, Odds.class, Handicap.class}, version = 1)
public abstract class AppRoomDatabase extends RoomDatabase {

    private static volatile AppRoomDatabase instance;

    public static AppRoomDatabase getInstance(@NonNull final Context context) {
        if (instance == null) {
            synchronized (AppRoomDatabase.class) {
                if (instance == null) { // double check
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppRoomDatabase.class, "luckybet_database").build();
                }
            }
        }
        return instance;
    }

    public abstract MatchDao matchDao();

    public abstract OddsDao oddsDao();

    public abstract HandicapDao handicapDao();


}
