package com.hnit.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ApplicationAdapter extends BaseAdapter {
    private List<ApplicationInfo> apps;
    private LayoutInflater inflater;

    public ApplicationAdapter(Context context, List<ApplicationInfo> infos) {
        this.apps = infos;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return apps.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.app_adapter_list_item, null);
            holder.icon = (ImageView)convertView.findViewById(R.id.app_ico);
            holder.name = (TextView)convertView.findViewById(R.id.app_name);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.icon.setImageDrawable(apps.get(position).getIcon());
        holder.name.setText(apps.get(position).getName());
        return convertView;
    }

    class ViewHolder {
        ImageView icon;
        TextView name;
    }
}
