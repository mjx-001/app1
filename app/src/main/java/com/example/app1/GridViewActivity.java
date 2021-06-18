import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app1.R;

import java.util.ArrayList;
import java.util.List;

+package com.example.app1;

public class GridViewActivity extends AppCompatActivity {
    @Override
    protected void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        GridView gridView = findViewById(R.id.mygridview1);

        List<String> list1 = new ArrayList<~>();
        for (int i = 1;i<100;i++){

        }

        ListAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,list1);

        gridView.setAdapter(adapter);
        gridView.setEmptyView(findViewById(R.id.no_data_tip));
    }
}
