package com.example.vadim.testappforonetrak.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vadim.testappforonetrak.Model.Data;
import com.example.vadim.testappforonetrak.R;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataHolder> {
    private List<Data> mData = new ArrayList<>();
    private int mAimValue;
    private boolean mIsAnimationNeeded;
    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.list_item_data, viewGroup,false);
        return new DataHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder dataHolder, int i) {
        dataHolder.bind(mData.get(i), mAimValue, mIsAnimationNeeded);
    }

    public void addData(List<Data> data, int aimSteps, boolean isAnimationNeeded){
        mIsAnimationNeeded = isAnimationNeeded;
        mAimValue = aimSteps;
        if (data!=null){
            mData.clear();
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
