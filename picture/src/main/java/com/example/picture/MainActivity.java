package com.example.picture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView img;
    private Button button;
    //String bm = "http://wimg.spriteapp.cn/picture/2019/0417/04266590-60b7-11e9-bd0e-1866daeb0df1_wpd.jpg";
    private Bitmap image;

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
        img = (ImageView) findViewById(R.id.image);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        image = ((BitmapDrawable) img.getDrawable()).getBitmap();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                //质量压缩
                compress(image);
                break;
        }
    }

    private Bitmap compress(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 90;
        while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }
}
