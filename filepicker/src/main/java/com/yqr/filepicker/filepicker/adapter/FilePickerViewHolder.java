package com.yqr.filepicker.filepicker.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yqr.filepicker.R;


/**
 * Description:
 * Author: kangwencai
 * DATA: Date on 2018/8/10.
 * PS: Not easy to write code, please indicate.
 */
public class FilePickerViewHolder extends RecyclerView.ViewHolder {
    protected RelativeLayout layoutRoot;
    protected ImageView ivType,ivChoose;
    protected TextView tvName;
    protected TextView tvDetail;
    public FilePickerViewHolder(View itemView) {
        super(itemView);
        ivType = itemView.findViewById(R.id.iv_type);
        layoutRoot = itemView.findViewById(R.id.layout_item_root);
        tvName = itemView.findViewById(R.id.tv_name);
        tvDetail = itemView.findViewById(R.id.tv_detail);
        ivChoose = itemView.findViewById(R.id.iv_choose);
    }
}
