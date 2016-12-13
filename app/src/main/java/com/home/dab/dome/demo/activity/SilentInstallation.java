package com.home.dab.dome.demo.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.home.dab.dome.R;

import java.io.File;

import static com.home.dab.dome.demo.Constant.fileName;
import static com.home.dab.dome.demo.Constant.fileStoreDir;

public class SilentInstallation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silent_installation);
    }

    public void install(View view) {
        // 核心是下面几句代码
        File dir = new File(fileStoreDir);
        if (!dir.exists()) {
            boolean mkdirs = dir.mkdirs();
        }
        File file = new File(dir, fileName);
        if (dir.exists()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(android.content.Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file),
                    "application/vnd.android.package-archive");
            this.startActivity(intent);
        } else {
            Toast.makeText(this, "文件不存在", Toast.LENGTH_SHORT).show();
        }
    }
}
