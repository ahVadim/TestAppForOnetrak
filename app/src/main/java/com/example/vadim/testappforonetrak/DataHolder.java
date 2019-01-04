package com.example.vadim.testappforonetrak;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.vadim.testappforonetrak.Model.Data;

import java.util.zip.Inflater;

public class DataHolder extends RecyclerView.ViewHolder {

    private TextView mTVDate;
    private TextView mTVFullSteps;
    private TextView mTVStepsRun;
    private TextView mTVStepsAerobic;
    private TextView mTVStepsWalk;
    private ProgressBar mRunProgress;
    private ProgressBar mAerobicProgress;
    private ProgressBar mWalkProgress;
    private LinearLayout mLinearLayout;
    private View mView;
    private int mAimSteps = 4000;
    private int mSumSteps;

    public DataHolder(@NonNull View itemView) {
        super(itemView);
        mTVDate = itemView.findViewById(R.id.tv_date);
        mTVFullSteps = itemView.findViewById(R.id.tv_full_steps);
        mTVStepsRun = itemView.findViewById(R.id.tv_run_steps);
        mTVStepsAerobic = itemView.findViewById(R.id.tv_aerobic_steps);
        mTVStepsWalk = itemView.findViewById(R.id.tv_walk_steps);
        mRunProgress = itemView.findViewById(R.id.pb_run);
        mAerobicProgress = itemView.findViewById(R.id.pb_aerobic);
        mWalkProgress = itemView.findViewById(R.id.pb_walk);
        mAerobicProgress.setProgressTintList(ColorStateList.valueOf(Color.rgb(59,20,18)));
        mWalkProgress.setProgressTintList(ColorStateList.valueOf(Color.rgb(238,45,123)));
        mRunProgress.setProgressTintList(ColorStateList.valueOf(Color.rgb(190,147,117)));
        mLinearLayout = itemView.findViewById(R.id.item_container);
        mView = itemView.findViewById(R.id.success_layout);

    }

    public void bind(Data data, int aimSteps){
        mAimSteps = aimSteps;
        mLinearLayout.setBackgroundColor(0);
        mSumSteps = data.getSumSteps();
        mTVStepsWalk.setText(data.getStringWalk());
        mTVStepsAerobic.setText(data.getStringAerobic());
        mTVStepsRun.setText(data.getStringRun());

      /*  LinearLayout linearLayout = itemView.findViewById(R.id.linear_progress);
        linearLayout.setWeightSum((float)mSumSteps/mSumSteps);*/

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mWalkProgress.getLayoutParams();
        layoutParams.weight = (float)data.getWalk()/mSumSteps;
        mWalkProgress.setLayoutParams(layoutParams);

        LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) mRunProgress.getLayoutParams();
        layoutParams1.weight = (float)data.getRun()/mSumSteps;
        mRunProgress.setLayoutParams(layoutParams1);

        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) mAerobicProgress.getLayoutParams();
        layoutParams2.weight = (float)data.getAerobic()/mSumSteps;
        mAerobicProgress.setLayoutParams(layoutParams2);

        mTVDate.setText(data.getDate());

        mLinearLayout.removeView(mView);
        mView.setVisibility(View.VISIBLE);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,100).setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator1) {
                int animProgress = (int)valueAnimator.getAnimatedValue();
                mTVFullSteps.setText(Integer.toString(mSumSteps * animProgress/100)+"/" + Integer.toString(mAimSteps));
                mAerobicProgress.setProgress(animProgress);
                mRunProgress.setProgress(animProgress);
                mWalkProgress.setProgress(animProgress);
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {            }
            @Override
            public void onAnimationEnd(Animator animator) {
                if(mSumSteps >= mAimSteps) {
                    mLinearLayout.addView(mView);
                    mLinearLayout.setBackgroundColor(Color.argb(100,152,251,152));
                }else {
                }
            }
            @Override
            public void onAnimationCancel(Animator animator) {            }
            @Override
            public void onAnimationRepeat(Animator animator) {            }
        });
        valueAnimator.start();
    }
}
