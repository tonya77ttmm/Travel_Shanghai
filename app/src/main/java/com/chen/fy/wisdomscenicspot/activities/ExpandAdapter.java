package com.chen.fy.wisdomscenicspot.activities;



import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.fy.wisdomscenicspot.R;

public class ExpandAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private LayoutInflater mInflater = null;
    private String[]   mGroupStrings = null;
    private List<List<Item>>   mData = null;
    public String getDaynum() {
        return daynum;
    }

    public void setDaynum(String daynum) {
        this.daynum = daynum;
    }

    private String daynum;

    public ExpandAdapter(Context ctx, List<List<Item>> list) {
        mContext = ctx;
        mInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mGroupStrings = mContext.getResources().getStringArray(R.array.groups);
        mData = list;
    }

    public void setData(List<List<Item>> list) {
        mData = list;
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        return mData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // TODO Auto-generated method stub
        return mData.get(groupPosition).size();
    }

    @Override
    public List<Item> getGroup(int groupPosition) {
        // TODO Auto-generated method stub
        return mData.get(groupPosition);
    }

    @Override
    public Item getChild(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return mData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.group_item_layout, null);

        }
        GroupViewHolder holder = new GroupViewHolder();
        holder.mGroupName = (TextView) convertView
                .findViewById(R.id.group_name);
        holder.mGroupName.setText(mGroupStrings[groupPosition]);
        holder.mGroupCount = (TextView) convertView
                .findViewById(R.id.group_count);
        holder.mGroupCount.setText("[" + mData.get(groupPosition).size() + "]");
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ChildViewHolder holder=null;
        if (convertView == null) {
            holder=new ChildViewHolder();
            convertView = mInflater.inflate(R.layout.child_item_layout, null);
            holder.mChildName = (CheckBox) convertView.findViewById(R.id.item_name);
            convertView.setTag(holder);
        }
        else {
            holder = (ChildViewHolder)  convertView.getTag();
        }


        holder.mChildName.setText(getChild(groupPosition, childPosition)
                .getName());

        holder.mChildName.setChecked(mData.get(groupPosition).get(childPosition).isChecked);
        holder.mChildName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                boolean isChecked= mData.get(groupPosition).get(childPosition).isChecked;
                String name=mData.get(groupPosition).get(childPosition).getName();

                if(!isChecked)
                {
                    mData.get(groupPosition).get(childPosition).isChecked = true;
                    if(groupPosition==3)
                    {
                        setDaynum(name);
                        Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();
                    }
                    // checked.add(mData.get(groupPosition).get(childPosition));

                }else
                {
                    mData.get(groupPosition).get(childPosition).isChecked = false;
                }


            }


        });

        return convertView;
    }

   /* @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ChildViewHolder holder=null;
        if (convertView == null) {
            holder=new ChildViewHolder();
            convertView = mInflater.inflate(R.layout.child_item_layout, null);
            holder.mChildName = (CheckBox) convertView.findViewById(R.id.item_name);
            convertView.setTag(holder);
        }
        else {
            holder = (ChildViewHolder)  convertView.getTag();
        }


        holder.mChildName.setText(getChild(groupPosition, childPosition)
                .getName());
        holder.mChildName.setChecked(mData.get(groupPosition).get(childPosition).isChecked);


        return convertView;
    }*/

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        /*??????????????????ChildView???????????????????????????true*/
        return true;
    }

    private class GroupViewHolder {
        TextView mGroupName;
        TextView mGroupCount;
    }

    private class ChildViewHolder {
        //ImageView mIcon;
        //TextView mChildName;
        //TextView mDetail;
        CheckBox  mChildName;
    }

}
