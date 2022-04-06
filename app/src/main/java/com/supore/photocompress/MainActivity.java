package com.supore.photocompress;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    SeekBar seekBar;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.img);
        seekBar = findViewById(R.id.bar);
        textView = findViewById(R.id.tv);
        File file = new File(Environment.getExternalStorageDirectory(),"cat.jpg");
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        imageView.setImageBitmap(bitmap);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.d("MainActivity","进度"+i);
                //imageView.setDrawingCacheEnabled(true);
                if(i>0){
                    Bitmap newbitmap = resetBitmap(bitmap,i);
                    //imageView.setDrawingCacheEnabled(false);
                    imageView.setImageBitmap(newbitmap);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    File file = new File(Environment.getExternalStorageDirectory(),"new.jpg");
                    if(file.exists()){
                        file.delete();
                    }
                    imageView.setDrawingCacheEnabled(true);
                    Bitmap cache = imageView.getDrawingCache();
                    FileOutputStream outputStream = new FileOutputStream(file);
                    cache.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                    outputStream.flush();
                    outputStream.close();
                    imageView.setDrawingCacheEnabled(false);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private Bitmap resetBitmap(Bitmap bitmap, int i) {
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap1);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ColorMatrix saturationColorMatrix = new ColorMatrix();
        saturationColorMatrix.setSaturation(i);
        ColorMatrix ImageMatrix = new ColorMatrix();
        ImageMatrix.postConcat(saturationColorMatrix);
        paint.setColorFilter(new ColorMatrixColorFilter(ImageMatrix));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return bitmap1;
    }
}