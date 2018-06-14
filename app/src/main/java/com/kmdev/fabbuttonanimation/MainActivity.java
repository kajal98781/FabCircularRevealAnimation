package com.kmdev.fabbuttonanimation;
import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mImageView;
    private boolean flag = true;
    private ImageButton mImageButton1, mImageButton2, mImageButton3, mImageButton4, mImageButton5;
    private LinearLayout mLayoutButtons;
    private LinearLayout mRevealView;
    private int centerX;
    private int centerY;
    private int endRadius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mImageButton1 = (ImageButton) findViewById(R.id.imageButton1);
        mImageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        mImageButton3 = (ImageButton) findViewById(R.id.imageButton3);
        mImageButton4 = (ImageButton) findViewById(R.id.imageButton4);
        mImageButton5 = (ImageButton) findViewById(R.id.imageButton5);
        mRevealView = (LinearLayout) findViewById(R.id.linearView);
        mLayoutButtons = (LinearLayout) findViewById(R.id.layoutButtons);
        //click listeners
        mImageButton1.setOnClickListener(this);
        mImageButton2.setOnClickListener(this);
        mImageButton3.setOnClickListener(this);
        mImageButton4.setOnClickListener(this);
        mImageButton5.setOnClickListener(this);
    }

    public void launchAnimation(int centerX, int centerY, int endRadius, ImageView view) {
        int startRadius = 0;
        if (flag) {
            view.setImageResource(R.drawable.ic_close_white_24dp);
            FrameLayout.LayoutParams parameters = (FrameLayout.LayoutParams)
                    mRevealView.getLayoutParams();
            parameters.height = mImageView.getHeight();
            mRevealView.setLayoutParams(parameters);
            Animator anim = null;
            // create the animator for this view (the start radius is zero)
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                anim = ViewAnimationUtils.createCircularReveal(mRevealView, centerX, centerY, 0, endRadius);
            }
            anim.setDuration(500);
            // make the view visible and start the animation
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    mLayoutButtons.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                }

                @Override
                public void onAnimationRepeat(Animator animator) {
                }
            });
            mRevealView.setVisibility(View.VISIBLE);
            anim.start();
            flag = false;
        } else {
            view.setImageResource(R.drawable.ic_message_white_24dp);
            Animator anim = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                anim = ViewAnimationUtils.createCircularReveal(mRevealView, centerX, centerY, endRadius, 0);
            }
            anim.setDuration(400);
            // make the view visible and start the animation
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    mRevealView.setVisibility(View.GONE);
                    mLayoutButtons.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                }

                @Override
                public void onAnimationRepeat(Animator animator) {
                }
            });
            anim.start();
            flag = true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButton1:
                //get the center for the clipping circle
                centerX = mImageView.getLeft();
                centerY = mImageView.getTop();
                // get the final radius for the clipping circle
                endRadius = (int) Math.hypot(mImageView.getWidth(), mImageView.getHeight());
                launchAnimation(centerX, centerY, endRadius, mImageButton1);
                break;
            case R.id.imageButton2:
                centerX = mImageView.getRight();
                centerY = mImageView.getTop();
                endRadius = (int) Math.hypot(mImageView.getWidth(), mImageView.getHeight());
                launchAnimation(centerX, centerY, endRadius, mImageButton2);
                break;
            case R.id.imageButton3:
                centerX = (mImageView.getLeft() + mImageView.getRight()) / 2;
                centerY = (mImageView.getTop() + mImageView.getBottom()) / 2;
                endRadius = (int) Math.hypot(mImageView.getWidth(), mImageView.getHeight());
                launchAnimation(centerX, centerY, endRadius, mImageButton3);
                break;
            case R.id.imageButton4:
                centerX = mImageView.getLeft();
                centerY = mImageView.getBottom();
                endRadius = (int) Math.hypot(mImageView.getWidth(), mImageView.getHeight());
                launchAnimation(centerX, centerY, endRadius, mImageButton4);
                break;
            case R.id.imageButton5:
                centerX = mImageView.getRight();
                centerY = mImageView.getBottom();
                endRadius = (int) Math.hypot(mImageView.getWidth(), mImageView.getHeight());
                launchAnimation(centerX, centerY, endRadius, mImageButton5);
                break;
        }
    }
}
