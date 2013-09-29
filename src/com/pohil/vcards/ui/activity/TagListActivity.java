package com.pohil.vcards.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.pohil.vcards.App;
import com.pohil.vcards.R;
import com.pohil.vcards.model.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagListActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_list_view);
        final ArrayList<Tag> tags = App.getDbManager().getTagDao().getAllTags();
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new TagAdapter(this, tags));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(TagListActivity.this, CardActivity.class);
                intent.putExtra(WordListActivity.TAG_TAG, tags.get(position));
                startActivity(intent);
            }
        });
    }

    class TagAdapter extends ArrayAdapter<Tag> {

        LayoutInflater inflater;

        public TagAdapter(Context context, List<Tag> objects) {
            super(context, 0 , objects);
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View layout  = convertView;
            if (layout == null) {
                layout = inflater.inflate(R.layout.tag_item, parent, false);
            }
            Tag tag = getItem(position);
            TextView tagName = (TextView) layout.findViewById(R.id.tag_name);
            tagName.setText(tag.name);

            return layout;
        }
    }

}
