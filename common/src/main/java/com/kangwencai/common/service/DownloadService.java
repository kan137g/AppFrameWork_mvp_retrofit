package com.kangwencai.common.service;

import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;



import java.io.File;

/**
 * Created by kangwencai on 2017/1/20.
 * Intent intent = new Intent(SplashScreenActivity.this, DownloadService.class);
 * intent.putExtra("url",linkUrl);
 * startService(intent);
 */

public class DownloadService extends Service {

    private BroadcastReceiver receiver;
    private String linkUrl;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            linkUrl = intent.getStringExtra("url");
            receiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/download/expressGold.apk")),
                            "application/vnd.android.package-archive");
                    startActivity(intent);
                    stopSelf();
                }
            };

            registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
            startDownload();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    private void startDownload() {
//        linkUrl = "http://download.kuaidijin.com/emp-app-release.apk";
        //当后台输入.apk结尾的字符串不是URL格式的时候，这里会崩溃
        try {
            if (linkUrl.endsWith(".apk")) {
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(linkUrl));
                request.setMimeType("application/vnd.android.package-archive");
                //设置允许使用的网络类型，这里是移动网络和wifi都可以
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "expressGold.apk");
                dm.enqueue(request);
            } else {
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(linkUrl));
                request.setMimeType("application/vnd.android.package-archive");
                //设置允许使用的网络类型，这里是移动网络和wifi都可以
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "expressGold.apk");
                dm.enqueue(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            ToastUtils.toast("下载地址错误");
            Toast.makeText(this,"",Toast.LENGTH_SHORT).show();
        }


//        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
//        Uri uri = Uri.parse(linkUrl);
//        DownloadManager.Request request = new DownloadManager.Request(uri);
//        //设置允许使用的网络类型，这里是移动网络和wifi都可以
//        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
//        //禁止发出通知，既后台下载，如果要使用这一句必须声明一个权限：android.permission.DOWNLOAD_WITHOUT_NOTIFICATION
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
//        downloadManager.enqueue(request);
    }
}