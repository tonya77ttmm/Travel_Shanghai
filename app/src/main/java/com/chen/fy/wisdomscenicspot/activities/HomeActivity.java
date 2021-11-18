package com.chen.fy.wisdomscenicspot.activities;


import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chen.fy.wisdomscenicspot.R;

public class HomeActivity extends AppCompatActivity implements OnChildClickListener {

    private ExpandableListView mListView = null;
    private ExpandAdapter mAdapter = null;
    private List<List<Item>> mData = new ArrayList<List<Item>>();

    private int[] mGroupArrays = new int[] {

            R.array.tianlongbabu,
            R.array.shediaoyingxiongzhuan,
            R.array.shendiaoxialv,
            R.array.tijiaoshijian
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        mListView = new ExpandableListView(this);
        mListView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        setContentView(mListView);
        mListView.setGroupIndicator(getResources().getDrawable(R.drawable.expander_floder));
        mAdapter = new ExpandAdapter(this, mData);
        mListView.setAdapter(mAdapter);
        mListView.setDescendantFocusability(ExpandableListView.FOCUS_AFTER_DESCENDANTS);
        mListView.setOnChildClickListener(this);
    }


    /*
     * ChildView 设置 布局很可能onChildClick进不来，要在 ChildView layout 里加上
     * android:descendantFocusability="blocksDescendants",
     * 还有isChildSelectable里返回true
     */
    @Override
    public boolean onChildClick(ExpandableListView parent, View v,
                                int groupPosition, int childPosition, long id) {
        // TODO Auto-generated method stub
        Item item = mAdapter.getChild(groupPosition, childPosition);
        new AlertDialog.Builder(this)
                .setTitle(item.getName())
                .setMessage(item.getName())
                .setIcon(android.R.drawable.ic_menu_more)
                .setNegativeButton(android.R.string.cancel,
                        new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub

                            }
                        }).create().show();
        return true;
    }

    private void initData() {
        for (int i = 0; i < mGroupArrays.length; i++) {
            List<Item> list = new ArrayList<Item>();
            String[] childs = getStringArray(mGroupArrays[i]);
            //String[] details = getStringArray(mDetailIds[i]);
            for (int j = 0; j < childs.length; j++) {
                Item item = new Item( childs[j]);
                list.add(item);
            }
            mData.add(list);
        }
    }

    private String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_button, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub

        switch (item.getItemId()) {

            case R.id.submit:
               Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(this, PlanTravelRoute.class);
//                PlanTravelRoute Plan = null;
//                Plan.line(1);
                startActivity(intent1);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);//?

        }

    }

}