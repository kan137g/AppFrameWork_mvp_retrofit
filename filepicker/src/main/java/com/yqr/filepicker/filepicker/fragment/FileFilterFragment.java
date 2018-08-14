package com.yqr.filepicker.filepicker.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.yqr.filepicker.R;
import com.yqr.filepicker.filepicker.FileScannerTask;
import com.yqr.filepicker.filepicker.OnUpdateDataListener;
import com.yqr.filepicker.filepicker.PickerManager;
import com.yqr.filepicker.filepicker.activity.FilePickerActivity;
import com.yqr.filepicker.filepicker.adapter.CommonFileAdapter;
import com.yqr.filepicker.filepicker.adapter.OnFileItemClickListener;
import com.yqr.filepicker.filepicker.model.FileEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * Description: 筛选出来的指定文件
 * Author: kangwencai
 * DATA: Date on 2018/8/10.
 * PS: Not easy to write code, please indicate.
 */
public class FileFilterFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private TextView mEmptyView;
    private ProgressBar mProgressBar;
    private CommonFileAdapter mCommonFileAdapter;
    private OnUpdateDataListener mOnUpdateDataListener;
    //    private String fileType = "pdf";
//    private String fileType = "application/vnd.ms-excel";
    private int fileType = FilePickerActivity.EXCEL;

    public void setOnUpdateDataListener(OnUpdateDataListener onUpdateDataListener) {
        mOnUpdateDataListener = onUpdateDataListener;
    }

    public static FileFilterFragment newInstance() {
        return new FileFilterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_file_normal, null);
        initView(view);
        fileType = getArguments().getInt("fileType", FilePickerActivity.EXCEL);
        initData();
        return view;
    }


    private void initView(View view) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView = view.findViewById(R.id.rl_normal_file);
        mRecyclerView.setLayoutManager(layoutManager);
        mEmptyView = view.findViewById(R.id.empty_view);
        mProgressBar = view.findViewById(R.id.progress);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void initData() {
        new FileScannerTask(getContext(), fileType, new FileScannerTask.FileScannerListener() {
            @Override
            public void scannerResult(List<FileEntity> entities) {
                mProgressBar.setVisibility(View.GONE);
                if (entities != null && entities.size() > 0) {
                    mEmptyView.setVisibility(View.GONE);
                } else {
                    mEmptyView.setVisibility(View.VISIBLE);
                }
                mCommonFileAdapter = new CommonFileAdapter(getContext(), entities);
                mRecyclerView.setAdapter(mCommonFileAdapter);
                iniEvent(entities);
            }
        }).execute();
    }

    private void iniEvent(final List<FileEntity> entities) {
        mCommonFileAdapter.setOnItemClickListener(new OnFileItemClickListener() {
            @Override
            public void click(int position) {
                FileEntity entity = entities.get(position);
                String absolutePath = entity.getPath();
                ArrayList<FileEntity> files = PickerManager.getInstance().files;
                if (files.contains(entity)) {
                    files.remove(entity);
                    if (mOnUpdateDataListener != null) {
                        mOnUpdateDataListener.update(-Long.parseLong(entity.getSize()));
                    }
                    entity.setSelected(!entity.isSelected());
                    mCommonFileAdapter.notifyDataSetChanged();
                } else {
                    if (PickerManager.getInstance().files.size() < PickerManager.getInstance().maxCount) {
                        files.add(entity);
                        if (mOnUpdateDataListener != null) {
                            mOnUpdateDataListener.update(Long.parseLong(entity.getSize()));
                        }
                        entity.setSelected(!entity.isSelected());
                        mCommonFileAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), getString(R.string.file_select_max, PickerManager.getInstance().maxCount), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
