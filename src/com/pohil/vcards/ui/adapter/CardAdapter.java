package com.pohil.vcards.ui.adapter;


import android.content.Context;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import com.pohil.vcards.R;
import com.pohil.vcards.model.Word;
import com.pohil.vcards.ui.animation.Rotate3dAnimation;

import java.util.ArrayList;

public class CardAdapter extends android.support.v4.view.PagerAdapter {
    private static final int ANIMATION_DURATION = 250;
    Context context;
    ArrayList<Word> wordList;
    LayoutInflater inflater;

    public CardAdapter(Context context, ArrayList<Word> wordList) {
        this.wordList = wordList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object instantiateItem(ViewGroup viewContainer, int position) {
        View view = inflater.inflate(R.layout.card_item, viewContainer, false);
        ViewGroup container = (ViewGroup) view.findViewById(R.id.card_container);
        View wordView = view.findViewById(R.id.word_layout);
        View translateView = view.findViewById(R.id.translate_layout);
        TextView wordTextView = (TextView) view.findViewById(R.id.word_text);
        TextView translateTextView = (TextView) view.findViewById(R.id.translate_text);
        wordTextView.setText(wordList.get(position).word);
        translateTextView.setText(wordList.get(position).translation);

        final AnimationView aView = new AnimationView();
        aView.container = container;
        aView.wordView = wordView;
        aView.translateView = translateView;

        wordView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aView.animationType = 1;
                applyRotation(aView, 0, 90);
            }
        });
        translateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aView.animationType = -1;
                applyRotation(aView, 180, 90);
            }
        });

        wordTextView.setClickable(true);
        wordTextView.setFocusable(true);
        container.setPersistentDrawingCache(ViewGroup.PERSISTENT_ANIMATION_CACHE);
        viewContainer.addView(view);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return wordList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view.equals(o);
    }

    class AnimationView {
        ViewGroup container;
        View wordView;
        View translateView;
        int animationType;
    }

    private void applyRotation(AnimationView aView, float start, float end) {
        // Find the center of the container
        final float centerX = aView.container.getWidth() / 2.0f;
        final float centerY = aView.container.getHeight() / 2.0f;

        // Create a new 3D rotation with the supplied parameter
        // The animation listener is used to trigger the next animation
        final Rotate3dAnimation rotation =
                new Rotate3dAnimation(start, end, centerX, centerY, 310.0f, true);
        rotation.setDuration(ANIMATION_DURATION);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new DisplayNextView(aView));

        aView.container.startAnimation(rotation);
        aView.container.invalidate();
    }

    private final class DisplayNextView implements Animation.AnimationListener {
        private final AnimationView aView;

        private DisplayNextView(AnimationView aView) {
           this.aView = aView;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            aView.container.post(new SwapViews(aView));
        }

        public void onAnimationRepeat(Animation animation) {

        }
    }

    private final class SwapViews implements Runnable {
        AnimationView aView;

        public SwapViews(AnimationView aView) {
           this.aView = aView;
        }

        public void run() {
            final float centerX = aView.container.getWidth() / 2.0f;
            final float centerY = aView.container.getHeight() / 2.0f;
            Rotate3dAnimation rotation = null;

            if (aView.animationType == 1) {
                aView.wordView.setVisibility(View.GONE);
                aView.translateView.setVisibility(View.VISIBLE);
                aView.translateView.requestFocus();

                rotation = new Rotate3dAnimation(90, 180, centerX, centerY, 310.0f, false);
            } else {
                aView.translateView.setVisibility(View.GONE);
                aView.wordView.setVisibility(View.VISIBLE);
                aView.wordView.requestFocus();

                rotation = new Rotate3dAnimation(90, 0, centerX, centerY, 310.0f, false);
            }

            rotation.setDuration(ANIMATION_DURATION);
            rotation.setFillAfter(true);
            rotation.setInterpolator(new DecelerateInterpolator());

            aView.container.startAnimation(rotation);
        }
    }
}

