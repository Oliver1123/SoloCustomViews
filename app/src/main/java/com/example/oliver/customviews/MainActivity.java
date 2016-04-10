package com.example.oliver.customviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import com.github.amlcurran.showcaseview.OnShowcaseEventListener;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.SimpleShowcaseEventListener;
import com.github.amlcurran.showcaseview.targets.ActionViewTarget;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    ShowcaseView showcaseView;
    Switch mSwitch;
    SwitchButton mSwitchButton;
    SeekBar mSeekBar;
    Button mButton;
    Random r = new Random();
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        Target viewTarget = new ViewTarget(R.id.CustomSwitch, this);
        showcaseView = new ShowcaseView.Builder(this)
                .setTarget(viewTarget)
                .setContentTitle("Title ")
                .setContentText("Message")
//                .singleShot(42)
                .setShowcaseEventListener(new SimpleShowcaseEventListener() {
                    @Override
                    public void onShowcaseViewHide(ShowcaseView showcaseView) {
                        super.onShowcaseViewHide(showcaseView);
                        Log.d(TAG, "onShowcaseViewHide");
                    }
                })
                .setStyle(R.style.CustomShowcaseTheme3)
                .setOnClickListener(this)
                .build();

        RelativeLayout.LayoutParams lps = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lps.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lps.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        int margin = ((Number) (getResources().getDisplayMetrics().density * 12)).intValue();
        lps.setMargins(margin, margin, margin, margin);
        showcaseView.setButtonPosition(lps);
    }

    private void initViews() {
        mSwitch = (Switch) findViewById(R.id.CustomSwitch);
        mSwitchButton = (SwitchButton) findViewById(R.id.sb_text);
        mSeekBar = (SeekBar) findViewById(R.id.CustomSeekBar);
        mButton = (Button) findViewById(R.id.btnButton);
    }

    @Override
    public void onClick(View v) {
        counter++;
        showcaseView.setContentText("Counter: " + counter);
        Log.d(TAG, "get showCaseX " + showcaseView.getShowcaseX()  + " y: " + showcaseView.getShowcaseY());

        switch (counter) {
            case 0:
                showcaseView.setShowcase(new ViewTarget(mSwitch), true);
                break;
            case 1:
                showcaseView.setShowcase(new ViewTarget(mSwitchButton), true);
                break;
            case 2:
                showcaseView.setShowcase(new ViewTarget(mSeekBar), true);
                break;
            case 3:
                showcaseView.setShowcase(new ViewTarget(mButton), true);
                counter = -1;
                break;
        }


    }
}
