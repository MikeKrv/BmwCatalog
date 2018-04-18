package com.example.mike.bmwcatalog.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mike.bmwcatalog.R;
import com.example.mike.bmwcatalog.model.NavItem;

import java.util.List;

/**
 * Created by Mike on 27.07.2017.
 */

public class ModelsNameAdapter extends ArrayAdapter<NavItem> {
    private final Context context;
    private final List modelsName;

    public ModelsNameAdapter(Context context, List<NavItem> modelsName){
        super(context, 0, modelsName);
        this.context = context;
        this.modelsName = modelsName;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_lst_navmenu, parent, false);
        }
        NavItem item = getItem(position);
        if(item != null){
            TextView textView = (TextView) convertView.findViewById(R.id.tv_modelName);
            textView.setText(item.getModelName());
        }

        return convertView;
    }



}
