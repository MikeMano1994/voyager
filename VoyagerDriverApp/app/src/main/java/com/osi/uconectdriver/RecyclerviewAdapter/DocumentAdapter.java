package com.osi.uconectdriver.RecyclerviewAdapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.osi.uconectdriver.Dataset.HelpData;
import com.osi.uconectdriver.HelpDetailsActivity;
import com.osi.uconectdriver.ParentActivity;
import com.osi.uconectdriver.R;
import com.osi.uconectdriver.interfaces.AsyncInterface;
import com.osi.uconectdriver.interfaces.myUrls;

import java.util.ArrayList;

public class DocumentAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> implements myUrls, AsyncInterface {
    public ArrayList<HelpData> mDataset;
    ParentActivity context;

    private int SCROLLLASTPOS = 0;

    public DocumentAdapter(ArrayList<HelpData> myDataset, ParentActivity gcontext) {
        mDataset = myDataset;
        this.context = gcontext;

    }

    public void add(int position, HelpData item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(String item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.document_containaer, null);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,
                                 int position) {

        ViewHolder holder = (ViewHolder) viewHolder;
        final HelpData name = mDataset.get(position);

        holder.tv_title.setText(name.supportType);
        //   holder.tv_description.setText(name.Description);
        holder.view.setId(position);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HelpDetailsActivity.class);
                intent.putExtra("data", mDataset.get(view.getId()));
                context.startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public class ViewHolder extends
            RecyclerView.ViewHolder {

        TextView tv_title;

        View view;


        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            view = itemLayoutView;
            tv_title = (TextView) itemLayoutView.findViewById(R.id.tv_title);


        }
    }


}