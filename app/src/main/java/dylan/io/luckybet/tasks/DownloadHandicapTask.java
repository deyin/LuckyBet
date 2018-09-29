package dylan.io.luckybet.tasks;

import android.os.AsyncTask;

import java.util.List;

import dylan.io.luckybet.models.Handicap;
import dylan.io.luckybet.models.Match;

public class DownloadHandicapTask extends AsyncTask<Void, Void, List<Handicap>> {

    final String URL_HANDICAP_LIST_OF_MATCH = "http://i.sporttery.cn/api/fb_match_info/get_asia/?mid=" + "matchId"; // matchId should be replaced real id
    final String URL_HANDICAP_OF_COMPANY_OF_MATCH = "http://i.sporttery.cn/api/fb_match_info/get_book_asia/?mid=" + "matchId" + "&bid=" + "companyId"; // matchId&companyId should be replaced real id

    final String matchId;

    DownloadHandicapTask(String matchId) {
        this.matchId = matchId;
    }

    @Override
    protected List<Handicap> doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPostExecute(List<Handicap> handicaps) {
        super.onPostExecute(handicaps);
    }

    static class DownloadHandicapChangeTask extends AsyncTask<Void, Void, List<Handicap.HandicapChange>> {

        final String companyId;

        DownloadHandicapChangeTask(String companyId) {
            this.companyId = companyId;
        }

        @Override
        protected List<Handicap.HandicapChange> doInBackground(Void... voids) {
            return null;
        }
    }
}