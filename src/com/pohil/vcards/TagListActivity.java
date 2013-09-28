package com.pohil.vcards;

import android.app.Activity;
import android.os.Bundle;
import com.pohil.vcards.dao.WordDao;
import com.pohil.vcards.model.Tag;
import com.pohil.vcards.model.Word;

import java.util.ArrayList;

public class TagListActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ArrayList<Tag> tags = App.getDbManager().getTagDao().getAllTags();
    }




}
