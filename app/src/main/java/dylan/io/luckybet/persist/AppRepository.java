package dylan.io.luckybet.persist;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.Date;
import java.util.List;

import dylan.io.luckybet.models.Handicap;
import dylan.io.luckybet.models.Match;
import dylan.io.luckybet.models.Odds;
import dylan.io.luckybet.persist.daos.BaseDao;
import dylan.io.luckybet.persist.daos.HandicapDao;
import dylan.io.luckybet.persist.daos.MatchDao;
import dylan.io.luckybet.persist.daos.OddsDao;


public class AppRepository {
    private MatchDao mMatchDao;
    private OddsDao mOddsDao;
    private HandicapDao mHandicapDao;

    public AppRepository(Application application) {
        AppRoomDatabase database = AppRoomDatabase.getInstance(application);
        this.mMatchDao = database.matchDao();
        this.mOddsDao = database.oddsDao();
        this.mHandicapDao = database.handicapDao();
    }

    public LiveData<List<Match>> getMatchesByDate(Date start, Date end) {
        return mMatchDao.getMatchesByDate(start, end);
    }

    public LiveData<List<Odds>> getAllOdds(String matchId) {
        return mOddsDao.getAllOdds(matchId);
    }

    public LiveData<List<Handicap>> getAllHandicaps(String matchId) {
        return mHandicapDao.getAllHandicaps(matchId);
    }

    public <T> void insert(T... array) {
        new InsertAsyncTask<T>(mMatchDao).execute(array);
    }

    private static class InsertAsyncTask<T> extends AsyncTask<T, Void, Void> {

        final BaseDao<T> mDao;

        public InsertAsyncTask(BaseDao dao) {
            this.mDao = dao;
        }

        @Override
        protected Void doInBackground(T... array) {
            mDao.insert(array);
            return null;
        }
    }
}
