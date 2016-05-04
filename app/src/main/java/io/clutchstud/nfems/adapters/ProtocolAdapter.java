package io.clutchstud.nfems.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.clutchstud.nfems.R;
import io.clutchstud.nfems.models.ProtocolRealmObject;
import io.realm.RealmResults;

/**
 * Created by a653h496 on 5/4/16.
 */
public class ProtocolAdapter extends RecyclerView.Adapter<ProtocolAdapter.ViewHolder> {


    public interface OnItemClickListener {
        void onItemClick(ProtocolRealmObject item);
    }

    private OnItemClickListener listener;


    private final RealmResults<ProtocolRealmObject> protocols;

    // Provide a suitable constructor (depends on the kind of data set)
    public ProtocolAdapter(RealmResults<ProtocolRealmObject> protocols) {
        this.protocols = protocols;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.protocolTitle);
            view = v;
        }
    }



    // Create new views (invoked by the layout manager)
    @Override
    public ProtocolAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.protocol_item, parent, false);
        // set the view's size, margins, paddings and layout parameters        ...


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(protocols.get(position).getTitle());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(protocols.get(position));
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return protocols.size();
    }
}
