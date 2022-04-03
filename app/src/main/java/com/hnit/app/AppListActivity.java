package com.hnit.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppListActivity extends Activity {
    private ListView mListView;
    private ApplicationAdapter mAdapter;
    private List<ApplicationInfo> apps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);

        mListView = (ListView)findViewById(R.id.mylist);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setComponent(apps.get(position).getIntent());
                startActivity(intent);
            }
        });
        apps = loadAppInfomation(this);
        mAdapter = new ApplicationAdapter(this, apps);
        mListView.setAdapter(mAdapter);
    }

    private List<ApplicationInfo> loadAppInfomation(Context context) {
        List<ApplicationInfo> apps = new ArrayList<ApplicationInfo>();
        PackageManager pm = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
        Collections.sort(infos, new ResolveInfo.DisplayNameComparator(pm));
        if(infos != null) {
            apps.clear();
            for(int i = 0;i < infos.size();i++) {
                ApplicationInfo app = new ApplicationInfo();
                ResolveInfo info = infos.get(i);
                app.setName(info.loadLabel(pm).toString());
                app.setIcon(info.loadIcon(pm));

                app.setIntent(new ComponentName(info.activityInfo.packageName, info.activityInfo.name));
                apps.add(app);
            }
        }
        return apps;
    }
}