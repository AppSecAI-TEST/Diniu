package com.workapp.auto.carterminal.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by wanggibin on 2017/6/1.
 */

public class BitmapUtil {
    private static String name;
    private static String srcPath;
    public static void saveBitmap(Bitmap bitmap, Context context) {
        Long now = System.currentTimeMillis();
        name = now.toString() + ".jpg";
        String fileNmae = Environment.getExternalStorageDirectory().toString() + File.separator + "niudi/image/" + name + ".jpg";
        srcPath = fileNmae;
        System.out.println(srcPath + "----------保存路径1");
        File myCaptureFile = new File(fileNmae);
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                if (!myCaptureFile.getParentFile().exists()) {
                    myCaptureFile.getParentFile().mkdirs();
                }
                BufferedOutputStream bos;
                bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
                bos.flush();
                bos.close();
            } else {
                Toast.makeText(context,"保存失败，sd卡无效",Toast.LENGTH_SHORT).show();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }


    public static String getSrcPath() {
        return srcPath;
    }

}
