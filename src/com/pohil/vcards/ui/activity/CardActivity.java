package com.pohil.vcards.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import com.pohil.vcards.App;
import com.pohil.vcards.R;
import com.pohil.vcards.model.Tag;
import com.pohil.vcards.model.Word;
import com.pohil.vcards.ui.adapter.CardAdapter;
import com.pohil.vcards.ui.animation.Rotate3dAnimation;

import java.util.ArrayList;

public class CardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_screen);
        ViewPager pager = (ViewPager) findViewById(R.id.word_pager);
        Tag tag = getIntent().getParcelableExtra(WordListActivity.TAG_TAG);
        ArrayList<Word> words = App.getDbManager().getWordDao().getTagWord(tag);
        pager.setAdapter(new CardAdapter(this, words));
    }


}
