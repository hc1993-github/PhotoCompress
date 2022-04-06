package com.supore.photocompress;

import static androidx.core.app.ActivityCompat.startActivityForResult;
import static com.google.zxing.util.Constant.INTENT_EXTRA_KEY_QR_SCAN;
import static com.google.zxing.util.Constant.REQ_QR_CODE;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.activity.CaptureActivity;

public class ThirdActivity extends AppCompatActivity {
    TextView tv1;
    TextView tv2;
    ImageView img;
    EditText editText;
    TextView tv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        img = findViewById(R.id.img);
        editText = findViewById(R.id.edit);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                if("".equals(text)){
                    return;
                }
                img.setImageBitmap(EncodingUtil.createNormal(text,500,500,null));
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                if("".equals(text)){
                    return;
                }
                Bitmap resource = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
                img.setImageBitmap(EncodingUtil.createNormal(text,500,500,resource));
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThirdActivity.this, CaptureActivity.class);
                startActivityForResult(intent,REQ_QR_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ_QR_CODE && resultCode==RESULT_OK){
            Bundle bundle = data.getExtras();
            String result = bundle.getString(INTENT_EXTRA_KEY_QR_SCAN);
            Toast.makeText(ThirdActivity.this,result,Toast.LENGTH_SHORT).show();
        }
    }
}