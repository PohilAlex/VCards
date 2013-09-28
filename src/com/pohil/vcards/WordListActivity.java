package com.pohil.vcards;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.pohil.vcards.model.Tag;
import com.pohil.vcards.model.Word;

import java.util.ArrayList;
import java.util.List;

public class WordListActivity extends Activity {

    public final static String TAG_TAG = "WordListActivity.TAG_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_list_view);
        Tag tag = getIntent().getParcelableExtra(TAG_TAG);
        ListView listView = (ListView) findViewById(R.id.list_view);
        ArrayList<Word> words = App.getDbManager().getWordDao().getTagWord(tag);
        listView.setAdapter(new WordAdapter(this, words));
    }

    class WordAdapter extends ArrayAdapter<Word> {

        LayoutInflater inflater;

        public WordAdapter(Context context,  List<Word> objects) {
            super(context, 0, objects);
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View layout = convertView;
            ViewHolder holder;
            if (layout == null) {
                layout = inflater.inflate(R.layout.word_item, parent, false);
                holder = new ViewHolder(layout);
                layout.setTag(holder);
            } else {
                holder = (ViewHolder) layout.getTag();
            }
            Word item = getItem(position);
            holder.word.setText(item.word);
            holder.translation.setText(item.translation);
            return layout;
        }

        class ViewHolder {

            TextView word;
            TextView translation;

            ViewHolder(View layout) {
                word = (TextView) layout.findViewById(R.id.word_text);
                translation = (TextView) layout.findViewById(R.id.translation_text);
            }
        }
    }
}
