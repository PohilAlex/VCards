package com.pohil.vcards.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.*;
import android.support.v7.view.ActionMode;
import android.view.*;
import android.support.v4.view.*;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.pohil.vcards.App;
import com.pohil.vcards.R;
import com.pohil.vcards.model.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagListActivity extends ActionBarActivity {

    ActionMode actionMode;
    ArrayList<Tag> tags;
    int selectPosition;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_list_view);
        tags = App.getDbManager().getTagDao().getAllTags();
        final ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new TagAdapter(this, tags));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(TagListActivity.this, CardActivity.class);
                intent.putExtra(WordListActivity.TAG_TAG, tags.get(position));
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                selectPosition = position;
                listView.setSelected(true);
                listView.setSelection(position);
                actionMode = startSupportActionMode(new TagActionMode());
                return true;
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

    class TagActionMode implements android.support.v7.view.ActionMode.Callback {


        @Override
        public boolean onCreateActionMode(android.support.v7.view.ActionMode actionMode, Menu menu) {
            MenuInflater inflater = new MenuInflater(TagListActivity.this);
            inflater.inflate(R.menu.tags_action_mode, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(android.support.v7.view.ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(android.support.v7.view.ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.edit_tag:
                    actionMode.finish();
                    Intent intent = new Intent(TagListActivity.this, WordListActivity.class);
                    intent.putExtra(WordListActivity.TAG_TAG, tags.get(selectPosition));
                    startActivity(intent);
                    return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(android.support.v7.view.ActionMode actionMode) {

        }
    }

}
