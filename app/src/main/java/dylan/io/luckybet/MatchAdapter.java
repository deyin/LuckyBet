package dylan.io.luckybet;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

import dylan.io.luckybet.models.Match;
import dylan.io.luckybet.models.MatchParent;

public class MatchAdapter extends ExpandableRecyclerAdapter<MatchParent, Match, MatchAdapter.MatchParentViewHolder, MatchAdapter.MatchChildViewHolder> {

    /**
     * Primary constructor. Sets up {@link #mParentList} and {@link #mFlatItemList}.
     * <p>
     * Any changes to {@link #mParentList} should be made on the original instance, and notified via
     * {@link #notifyParentInserted(int)}
     * {@link #notifyParentRemoved(int)}
     * {@link #notifyParentChanged(int)}
     * {@link #notifyParentRangeInserted(int, int)}
     * {@link #notifyChildInserted(int, int)}
     * {@link #notifyChildRemoved(int, int)}
     * {@link #notifyChildChanged(int, int)}
     * methods and not the notify methods of RecyclerView.Adapter.
     *
     * @param parentList List of all parents to be displayed in the RecyclerView that this
     *                   adapter is linked to
     */
    public MatchAdapter(@NonNull List<MatchParent> parentList) {
        super(parentList);
    }

    @NonNull
    @Override
    public MatchParentViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View view = LayoutInflater.from(parentViewGroup.getContext()).inflate(R.layout.list_item_match_parent, parentViewGroup, false);
        return new MatchParentViewHolder(view);
    }

    @NonNull
    @Override
    public MatchChildViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View view = LayoutInflater.from(childViewGroup.getContext()).inflate(R.layout.list_item_match, childViewGroup, false);
        return new MatchChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(@NonNull MatchParentViewHolder parentViewHolder, int parentPosition, @NonNull MatchParent parent) {
        parentViewHolder.onBind(parentPosition, parent);
    }

    @Override
    public void onBindChildViewHolder(@NonNull MatchChildViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull Match child) {
        childViewHolder.onBind(parentPosition, childPosition, child);
    }

    class MatchParentViewHolder extends ParentViewHolder<MatchParent, Match> {

        TextView title;

        /**
         * Default constructor.
         *
         * @param itemView The {@link View} being hosted in this ViewHolder
         */
        public MatchParentViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
        }

        public void onBind(int parentPosition, MatchParent parent) {
            title.setText(parent.getTitle());
        }
    }

    class MatchChildViewHolder extends ChildViewHolder<Match> {

        /**
         * Default constructor.
         *
         * @param itemView The {@link View} being hosted in this ViewHolder
         */
        public MatchChildViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void onBind(int parentPosition, int childPosition, Match child) {

        }
    }
}
