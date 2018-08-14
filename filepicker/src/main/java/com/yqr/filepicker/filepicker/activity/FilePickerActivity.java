package com.yqr.filepicker.filepicker.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yqr.filepicker.R;
import com.yqr.filepicker.filepicker.FileScannerTask;
import com.yqr.filepicker.filepicker.adapter.FilePickerShowAdapter;
import com.yqr.filepicker.filepicker.adapter.OnFileItemClickListener;
import com.yqr.filepicker.filepicker.fragment.FileAllFragment;
import com.yqr.filepicker.filepicker.fragment.FileFilterFragment;
import com.yqr.filepicker.filepicker.OnUpdateDataListener;
import com.yqr.filepicker.filepicker.PickerManager;
import com.yqr.filepicker.filepicker.model.FileEntity;
import com.yqr.filepicker.filepicker.util.FileUtils;
import com.yqr.filepicker.filepicker.util.OpenFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 文件选择
 * <li>启动方式为FilePickerActivity.toFilePickerActivity()可以从这里面查看获取结果的代码</li>
 * <li>获取结果的方式有两个：PickerManager.getInstance().files;或者data.getStringArrayListExtra("fileList");</li>
 * Author: kangwencai
 * DATA: Date on 2018/8/10.
 * PS: Not easy to write code, please indicate.
 */
public class FilePickerActivity extends AppCompatActivity implements View.OnClickListener, OnUpdateDataListener {

    public static final int WORD = 1;
    public static final int EXCEL = 2;
    public static final int PPT = 3;
    public static final int PDF = 4;


    private Button btn_common, btn_all;
    private TextView tv_size, tv_confirm;
    private Fragment commonFileFragment, allFileFragment;
    private boolean isConfirm = false;
    // 文件类型http://blog.liuxianan.com/mime-type.html
    private int fileType = PDF;

    /**
     * @param activity
     * @param REQUEST_CODE
     * @param fileType
     */
    public static void toFilePickerActivity(Activity activity, int REQUEST_CODE, int fileType) {
        Intent intent = new Intent(activity, FilePickerActivity.class);
        intent.putExtra("fileType", fileType);
        activity.startActivityForResult(intent, REQUEST_CODE);


//        @Override
//        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);
//            if (requestCode == REQ_CODE) {
//                   //返回的数据可以从下面的两种方式里面拿都在这个里面
//                  PickerManager.getInstance().files;
//        data.getStringArrayListExtra("fileList");
//            }
//
//        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_picker);
        initView();
        initEvent();
        fileType = getIntent().getIntExtra("fileType", 0);

        setFragment(1);

    }

    private void initEvent() {
        btn_common.setOnClickListener(this);
        btn_all.setOnClickListener(this);
        tv_confirm.setOnClickListener(this);
    }

    private void initView() {
        btn_common = findViewById(R.id.btn_common);
        btn_all = findViewById(R.id.btn_all);
        tv_size = findViewById(R.id.tv_size);
        tv_confirm = findViewById(R.id.tv_confirm);
        String res = "(" + 0 + "/" + PickerManager.getInstance().maxCount + ")";
        tv_confirm.setText(getString(R.string.file_select_res, res));

    }

    private void setFragment(int type) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        switch (type) {
            case 1:
                if (commonFileFragment == null) {
                    commonFileFragment = FileFilterFragment.newInstance();
                    // 设置选择的文件类型
                    Bundle bundle = new Bundle();
                    bundle.putInt("fileType", fileType);
                    commonFileFragment.setArguments(bundle);
                    ((FileFilterFragment) commonFileFragment).setOnUpdateDataListener(this);
                    fragmentTransaction.add(R.id.fl_content, commonFileFragment);
                } else {
                    fragmentTransaction.show(commonFileFragment);
                }
                break;
            case 2:
                if (allFileFragment == null) {
                    allFileFragment = FileAllFragment.newInstance();
                    ((FileAllFragment) allFileFragment).setOnUpdateDataListener(this);
                    fragmentTransaction.add(R.id.fl_content, allFileFragment);
                } else {
                    fragmentTransaction.show(allFileFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (commonFileFragment != null) {
            transaction.hide(commonFileFragment);
        }
        if (allFileFragment != null) {
            transaction.hide(allFileFragment);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_common) {
            setFragment(1);
            btn_common.setBackgroundResource(R.mipmap.no_read_pressed);
            btn_common.setTextColor(ContextCompat.getColor(this, R.color.white));
            btn_all.setBackgroundResource(R.mipmap.already_read);
            btn_all.setTextColor(ContextCompat.getColor(this, R.color.blue));

        } else if (i == R.id.btn_all) {
            setFragment(2);
            btn_common.setBackgroundResource(R.mipmap.no_read);
            btn_common.setTextColor(ContextCompat.getColor(this, R.color.blue));
            btn_all.setBackgroundResource(R.mipmap.already_read_pressed);
            btn_all.setTextColor(ContextCompat.getColor(this, R.color.white));

        } else if (i == R.id.tv_confirm) {
            isConfirm = true;
            ArrayList<String> fileList = new ArrayList<>();
            for (FileEntity entity : PickerManager.getInstance().files) {
                fileList.add(entity.getPath());
            }
            Intent intent = new Intent();
            intent.putStringArrayListExtra("fileList", fileList);
            setResult(RESULT_OK);
            finish();

        }
    }

    private long currentSize;

    @Override
    public void update(long size) {
        currentSize += size;
        tv_size.setText(getString(R.string.already_select, FileUtils.getReadableFileSize(currentSize)));
        String res = "(" + PickerManager.getInstance().files.size() + "/" + PickerManager.getInstance().maxCount + ")";
        tv_confirm.setText(getString(R.string.file_select_res, res));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!isConfirm) {
            PickerManager.getInstance().files.clear();
        }
    }
}
