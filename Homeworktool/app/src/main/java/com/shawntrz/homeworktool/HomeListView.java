package com.shawntrz.homeworktool;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by strz on 10/10/17.
 */

public class HomeListView extends ArrayAdapter<String> {
    private String[] classNames;
    private String[] classDescription;
    private String[] classTime;
    private Activity context;
    public HomeListView(Activity context, String[] classNames, String[] classDescription, String[] classTime) {
        super(context, R.layout.class_listview, R.id.classname, classNames);

        this.context = context;
        setClassDescription(classDescription);
        setClassNames(classNames);
        setClassTime(classTime);
    }

    public String[] getClassNames() {
        return classNames;
    }

    public String[] getClassDescription() {
        return classDescription;
    }

    public String[] getClassTime() {
        return classTime;
    }

    private void setClassNames(String[] classNames) {
        this.classNames = classNames;
    }

    private void setClassDescription(String[] classDescription) {
        this.classDescription = classDescription;
    }

    private void setClassTime(String[] classTime) {
        this.classTime = classTime;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        ViewObject viewObject = null;
        if(r == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.class_listview, null,true);
            viewObject = new ViewObject(r);
            r.setTag(viewObject);
        }
        else
        {
            viewObject = (ViewObject) r.getTag();

        }
        String cls;
        if (classNames[position] == null){
            cls = "";
        }
        else{
            cls = classNames[position];
        }
        viewObject.classname.setText(cls);
        viewObject.classdate.setText(classTime[position]);
        viewObject.classdescription.setText(classDescription[position]);
        View ret = super.getView(position, convertView, parent);
        if(ret == null){
            ret = new View(context);
        }
        return ret;
    }

    class ViewObject
    {
        TextView classname;
        TextView classdate;
        TextView classdescription;
        ViewObject(View v){
            classname = v.findViewById(R.id.classname);
            classdate = v.findViewById(R.id.classdate);
            classdescription = v.findViewById(R.id.classdescription);
        }
    }
}
