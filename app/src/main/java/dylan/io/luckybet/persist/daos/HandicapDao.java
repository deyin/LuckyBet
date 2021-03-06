package dylan.io.luckybet.persist.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import dylan.io.luckybet.models.Handicap;

@Dao
public interface HandicapDao extends BaseDao<Handicap> {

    @Query("SELECT * FROM t_handicap WHERE matchId = :matchId")
    LiveData<List<Handicap>> getAllHandicaps(String matchId);

}
