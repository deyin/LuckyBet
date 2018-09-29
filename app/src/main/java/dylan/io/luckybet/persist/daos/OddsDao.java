package dylan.io.luckybet.persist.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import dylan.io.luckybet.models.Handicap;
import dylan.io.luckybet.models.Odds;

@Dao
public interface OddsDao extends BaseDao<Handicap> {

    @Query("SELECT * FROM t_odds WHERE matchId = :matchId")
    LiveData<List<Odds>> getAllOdds(String matchId);

}
