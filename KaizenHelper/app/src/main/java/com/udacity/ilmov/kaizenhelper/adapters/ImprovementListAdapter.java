package com.udacity.ilmov.kaizenhelper.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.udacity.ilmov.kaizenhelper.R;
import com.udacity.ilmov.kaizenhelper.activities.ImproveProcessActivity;
import com.udacity.ilmov.kaizenhelper.data.KaizenContract;
import com.udacity.ilmov.kaizenhelper.data.KaizenProvider;
import com.udacity.ilmov.kaizenhelper.models.Improvement;

import java.util.ArrayList;

/**
 * Created by ilmov
 */

public class ImprovementListAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Improvement> improvements;
    private Context mContext;

    public ImprovementListAdapter(Context context, ArrayList<Improvement> improvements) {
        this.mContext = context;
        this.improvements = improvements;
    }

    @Override
    public int getCount() {
        return improvements.size();
    }

    @Override
    public Object getItem(int position) {
        return improvements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_business_process, null);
        }

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.list_item_business_process_container);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ImproveProcessActivity.class);
                Bundle bundle = new Bundle();
                bundle.putLong(ImproveProcessActivity.PROCESS_ID, improvements.get(position).getId());
                bundle.putString(ImproveProcessActivity.PROCESS, improvements.get(position).getName());
                bundle.putFloat(ImproveProcessActivity.RATING, improvements.get(position).getRating());
                bundle.putString(ImproveProcessActivity.DESCRIPTION, improvements.get(position).getWhatToImprove());

                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

        view.findViewById(R.id.delete_list_item_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mSelection = KaizenContract.Improvements._ID + " = ?";
                String [] selectionArgs = {String.valueOf(improvements.get(position).getId())};
                mContext.getContentResolver().delete(KaizenProvider.KAIZEN_CONTENT_URI, mSelection, selectionArgs);
                improvements.remove(position);
                notifyDataSetChanged();
            }
        });

        TextView improvementName = (TextView) view.findViewById(R.id.list_process_name);
        TextView rating = (TextView) view.findViewById(R.id.list_process_rating);
        improvementName.setText(improvements.get(position).getName());
        rating.setText(String.valueOf(improvements.get(position).getRating()));


        return view;
    }
}
