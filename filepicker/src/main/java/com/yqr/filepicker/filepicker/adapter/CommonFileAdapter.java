package com.yqr.filepicker.filepicker.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.yqr.filepicker.R;
import com.yqr.filepicker.filepicker.model.FileEntity;
import com.yqr.filepicker.filepicker.util.FileUtils;

import java.io.File;
import java.util.List;

/**
 * Description: 全部文件
 * Author: kangwencai
 * DATA: Date on 2018/8/10.
 * PS: Not easy to write code, please indicate.
 */

public class CommonFileAdapter extends RecyclerView.Adapter<FilePickerViewHolder> {
    private Context mContext;
    private List<FileEntity> mData;
    private OnFileItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnFileItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public CommonFileAdapter(Context context, List<FileEntity> data) {
        this.mContext = context;
        mData = data;
    }

    @Override
    public FilePickerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_file_picker, parent, false);
        return new FilePickerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final FilePickerViewHolder holder, int position) {
        final FileEntity entity = mData.get(position);
        holder.tvName.setText(entity.getName());
//        holder.tvDetail.setText(entity.getMimeType());

        holder.tvDetail.setText(FileUtils.getFileSzie(Long.parseLong(entity.getSize())));
        String title = entity.getFileType().getTitle();
        if (entity.isSelected()) {
            holder.ivChoose.setImageResource(R.mipmap.file_choice);
        } else {
            holder.ivChoose.setImageResource(R.mipmap.file_no_selection);
        }
        if (title.equals("IMG")) {
            Glide.with(mContext).load(new File(entity.getPath())).into(holder.ivType);
        } else {
            holder.ivType.setImageResource(entity.getFileType().getIconStyle());
        }
        holder.layoutRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.click(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
