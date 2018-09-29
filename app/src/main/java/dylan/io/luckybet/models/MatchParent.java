package dylan.io.luckybet.models;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

public class MatchParent implements Parent<Match> {

    private final String title;

    private final List<Match> childList;

    public MatchParent(String title, List<Match> childList) {
        this.title = title;
        this.childList = childList;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public List<Match> getChildList() {
        return childList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
