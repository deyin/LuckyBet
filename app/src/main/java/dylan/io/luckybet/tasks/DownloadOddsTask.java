package dylan.io.luckybet.tasks;

import android.os.AsyncTask;

import java.util.List;

import dylan.io.luckybet.models.Odds;

public class DownloadOddsTask extends AsyncTask<Void, Void, List<Odds>> {

    final String URL_ODDS_LIST_OF_MATCH = "http://i.sporttery.cn/api/fb_match_info/get_europe/?mid=" + "matchId"; // matchId should be replaced real id
    final String URL_ODDS_OF_COMPANY_OF_MATCH = "http://i.sporttery.cn/api/fb_match_info/get_book_europe/?mid=" + "matchId" + "&bid=" + "companyId"; // matchId&companyId should be replaced real id

    final String matchId;

    DownloadOddsTask(String matchId) {
        this.matchId = matchId;
    }

    @Override
    protected List<Odds> doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPostExecute(List<Odds> odds) {
        super.onPostExecute(odds);
    }

    static class DownloadOddsChangeTask extends AsyncTask<Void, Void, List<Odds.OddsChange>> {

        final String companyId;

        DownloadOddsChangeTask(String companyId) {
            this.companyId = companyId;
        }

        @Override
        protected List<Odds.OddsChange> doInBackground(Void... voids) {
            return null;
        }
    }
}