package dylan.io.luckybet;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dylan.io.luckybet.models.Match;
import dylan.io.luckybet.models.MatchParent;
import dylan.io.luckybet.persist.AppViewModel;
import dylan.io.luckybet.persist.conveters.DateConverter;
import dylan.io.luckybet.tasks.DownloadMatchTask;
import dylan.io.luckybet.utils.DateUtils;

public class MainActivity extends AppCompatActivity {

    private AppViewModel mAppViewModel;

    /// UI
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    MatchAdapter matchAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUi();

        mAppViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
    }

    private void setupUi() {
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadMatchList();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        recyclerView = findViewById(R.id.rv_matches);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        matchAdapter = new MatchAdapter(new ArrayList());
        recyclerView.setAdapter(matchAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        loadMatchList();
    }

    private void loadMatchList() {
        Date today = new Date();
        Date yesterday = DateUtils.getDaysBefore(today, 1);
        Date tomorrow = DateUtils.getDaysAfter(today, 1);

        mAppViewModel.getMatchesByDate(yesterday, tomorrow).observe(this, new Observer<List<Match>>() {
            @Override
            public void onChanged(@Nullable List<Match> matches) {
                if (matches == null || matches.isEmpty()) { // not found in db, download from network and insert into db
                    new DownloadMatchTask(MainActivity.this).execute();
                }
                boolean preserveExpansionState = true;
                List<MatchParent> parentList = buildMatchParentList(matches);
                matchAdapter.setParentList(parentList, preserveExpansionState);
                matchAdapter.notifyDataSetChanged();
            }
        });
    }

    private List<MatchParent> buildMatchParentList(@Nullable List<Match> matches) {

        List<MatchParent> parentList = new ArrayList<>();

        Map<Date, List<Match>> map = groupByMatchTime(matches);

        Set<Map.Entry<Date, List<Match>>> entries = map.entrySet();
        for (Map.Entry<Date, List<Match>> entry : entries) {
            Date date = entry.getKey();
            List<Match> matchList = entry.getValue();
            int size = matchList.size();
            String title = DateConverter.dateToYmdString(date) + " 共 "  + size + "场比赛";
            MatchParent parent = new MatchParent(title, matchList);
            parentList.add(parent);
        }

        return parentList;
    }

    @NonNull
    private Map<Date, List<Match>> groupByMatchTime(@Nullable List<Match> matches) {
        Map<Date, List<Match>> map = new HashMap<>();
        for (Match m : matches) {
            Date time = m.getTime();
            Date startTime = DateUtils.getMatchStartTime(time);
            List<Match> matchList = map.get(startTime);
            if (matchList == null) {
                matchList = new ArrayList<>();
            }
            matchList.add(m);
            map.put(startTime, matchList);
        }
        return map;
    }
}
