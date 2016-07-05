package com.dharmendra;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

/**
 * Dharmendra
 */
public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.ViewHolder> {

    private ImageLoader imageLoader;
    private Context context;

    //List of issuePojos
    List<IssuePojo> issuePojos;

    public IssueAdapter(List<IssuePojo> issuePojos, Context context) {
        super();
        //Getting all the issuePojos
        this.issuePojos = issuePojos;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.issue_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        try {
            IssuePojo issuePojo = issuePojos.get(position);


            holder.textViewIssueName.setText("Title:"+ issuePojo.getIssueTitle());
            holder.textViewDescription.setText("Description:"+ issuePojo.getDescriptions());
            holder.textViewIssueRepoterName.setText("Issue Repoter Name:"+ issuePojo.getReporterName());
            holder.textViewCreatedBy.setText("Last Update Time:"+ issuePojo.getLatestUpdatedTime());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return issuePojos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewIssueName;
        public TextView textViewDescription;
        public TextView textViewIssueRepoterName;
        public TextView textViewCreatedBy;


        public ViewHolder(View itemView) {
            super(itemView);

            textViewIssueName = (TextView) itemView.findViewById(R.id.issue_name_textView);
            textViewDescription = (TextView) itemView.findViewById(R.id.issue_description_textView);
            textViewIssueRepoterName = (TextView) itemView.findViewById(R.id.reporter_name_textView);
            textViewCreatedBy = (TextView) itemView.findViewById(R.id.recent_updated_time_textView);

        }
    }
}
