package com.supore.photocompress;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.supore.photocompress.bean.Person;
import com.supore.photocompress.database.BaseDataBase;

public class FiveActivity extends AppCompatActivity {
    TextView textView1;
    TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
        textView1 = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        BaseDataBase base = Room.databaseBuilder(this, BaseDataBase.class, "person").allowMainThreadQueries().build();

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                base.personDao().insertPerson(new Person("zhangsan", 1));
                base.personDao().insertPerson(new Person("lisi", 1));
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person zhangsan = base.personDao().queryByName("zhangsan");
                base.personDao().deletePerson(zhangsan);
            }
        });
    }
}