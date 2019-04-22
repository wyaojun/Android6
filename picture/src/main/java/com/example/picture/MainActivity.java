package com.example.picture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shizhefei.view.largeimage.LargeImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


public class MainActivity extends AppCompatActivity {
    private LargeImageView imag;

    /*在Android应用中耗内存大户就是图片的加载，在读取图片资源Bitmap的时候,分给虚拟机的堆栈大小只有8M,如超出了就OOM
     * Bitmap类的构造方法是私有的，所以开发者不能直接new一个Bitmap对象，只能通过BitmapFactory类的各种静态方法来实例化一个Bitmap。
     *对于图片，内存优化中有两个手段，一是减少图片本身所占的内存、
     * 二是缓存经常使用的图片，避免重复创建Bitmap文件，增加内存的开支。
     *
     * 对图片的优化就是尺寸，质量，采样率来进行压缩
     * （一）图片质量的压缩
     * （二）比例大小压缩从路径获取图片
     * （三）比例大小压缩Bitmap
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        imag = (LargeImageView) findViewById(R.id.imag);
        imag.setImage(R.mipmap.aaa);
    }
}



    /*private Bitmap compressImage(Bitmap image) {
        //Android应用开发中三种常见的图片压缩方法，分别是：质量压缩法、
        // 比例压缩法（根据路径获取图片并压缩）和比例压缩法（根据Bitmap图片压缩）。
        //质量压缩法
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) { //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    //图片按比例大小压缩方法（根据路径获取图片并压缩）
    private Bitmap getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
    }


    //图片按比例大小压缩方法（根据Bitmap图片压缩）
    private Bitmap comp(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 1024) {
            //判断如果图片大于1M,
        }
    }*/
