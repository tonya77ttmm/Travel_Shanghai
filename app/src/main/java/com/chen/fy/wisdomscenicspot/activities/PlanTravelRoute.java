package com.chen.fy.wisdomscenicspot.activities;


import android.support.v7.app.AppCompatActivity;

import android.widget.TextView;
import com.chen.fy.wisdomscenicspot.R;
import com.chen.fy.wisdomscenicspot.model.Plan;


public class PlanTravelRoute extends AppCompatActivity {

    public void line(float day) {

    Object[][] plan = Plan.plan_travel(day);
        String myline = null;
    for(int i=0;i<plan.length;i++) {
        myline = String.valueOf(plan[i][0]);
        myline += " ";
        myline += String.valueOf(plan[i][1]);
        myline += " ";
        myline += String.valueOf(plan[i][2]);
        myline += "/n";
    }
        TextView textView2= (TextView) findViewById(R.id.textView2);
        textView2.setText(myline);
    }

}
