package com.example.admin.myproject.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.myproject.R;
import com.example.admin.myproject.activities.view.RectView;
import com.example.admin.myproject.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2/6/2018.
 */

public class GestureActivity extends AppCompatActivity implements View.OnTouchListener {

    @BindView(R.id.rectView)
    RectView rectView;

    @BindView(R.id.rlContent)
    RelativeLayout rlContent;

    @BindView(R.id.tvAngle)
    TextView tvAngle;

    private double currentAngle = 0;
    private double previousAngle = 0;
    private float downX;
    private float downY;
    private float moveX;
    private float moveY;
    private int HORIZONTAL_GESTURE_HEIGHT = 100;
    private double displayWidth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        displayWidth = Utils.getDisplayWidth(this);
        tvAngle.setText(String.valueOf(currentAngle));
        rlContent.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                downX = event.getX();
                downY = event.getY();
                currentAngle = 0;
                tvAngle.setText(String.valueOf(currentAngle));
                rectView.reset();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                moveX = event.getX();
                moveY = event.getY();
                if (Math.abs(downY - moveY) <= HORIZONTAL_GESTURE_HEIGHT) {
                    previousAngle = currentAngle;
                    currentAngle = 180 * (moveX - downX) / displayWidth;
                    rectView.rotate((float) currentAngle);
                    tvAngle.setText(String.valueOf(currentAngle));
                } else {
                    previousAngle = currentAngle = 0;
                    rectView.reset();
                    tvAngle.setText(String.valueOf(currentAngle));
                    break;
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                previousAngle = currentAngle = 0;
                rectView.reset();
                tvAngle.setText(String.valueOf(currentAngle));
                break;
            }
        }
        return true;
    }
}
