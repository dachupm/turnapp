package com.darshanvineeth.turn1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class subjectAdapter extends RecyclerView.Adapter<subjectAdapter.subjectViewHolder> {

    //TODO: subjectAdapter receives 'data' from HomeFragment.
    private String[] data;
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private Context mContext;

    private static final int TYPE_NOT_CHECKED = 0;
    private static final int TYPE_CHECKED = 1;
    //TODO: todays_att to be fetched from db in order.
    private String[] todays_att = {"0", "0", "1", "0", "1", "1","0", "0", "1", "0", "0", "0"};


    subjectAdapter(Context context, String[] data, ArrayList<String> imageUrls) {


        this.data = data;
        mContext = context;
        this.mImageUrls = imageUrls;
    }

    @Override
    public subjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_NOT_CHECKED) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.list_item_layout, parent, false);
            return new subjectViewHolder(view);
        } else if (viewType == TYPE_CHECKED) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.list_item_layout_marked, parent, false);
            return new subjectViewHolder(view);

        } else return null;
    }

    @Override
    public void onBindViewHolder(subjectViewHolder holder, int position) {


        //Inflate Lecture image
        Glide.with(mContext)
                .asBitmap()
                .load(mImageUrls.get(position))
                .into(holder.lec_image);

        //TODO:set an onclck listener on 'lec_image' for lecture description

        //Inflate Lecture Name
        String subject_name = data[position];
        holder.lec_name.setText(subject_name);

    }

    @Override
    public int getItemCount() {

        return data.length;
    }

    @Override
    public int getItemViewType(int position) {

        if (todays_att[position].equals("1")) {
            return TYPE_CHECKED;
        } else if (todays_att[position].equals("0")) {
            return TYPE_NOT_CHECKED;
        }
        return position;
    }


    public class subjectViewHolder extends RecyclerView.ViewHolder {
        ImageView lec_image;
        TextView lec_name;

        public subjectViewHolder(View itemView) {
            super(itemView);
            lec_image = itemView.findViewById(R.id.lec_image);
            lec_name = itemView.findViewById(R.id.lec_name);

        }
    }
}
