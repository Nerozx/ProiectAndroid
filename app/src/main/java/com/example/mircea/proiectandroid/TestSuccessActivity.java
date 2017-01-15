package com.example.mircea.proiectandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.mircea.proiectandroid.model.Users;

public class TestSuccessActivity extends AppCompatActivity {


    private Button finishButton;
    private Users loggedUser;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_success);
        setTitle("Congratulations!");
        Intent i = getIntent();
        loggedUser = (Users) i.getSerializableExtra("logged");
        finishButton = (Button) findViewById(R.id.finish_button);
        img = (ImageView)findViewById(R.id.circle);

        float radius = 100;
        Paint paint = new Paint();
        Paint paint2 = new Paint();
        paint.setColor(Color.parseColor("#1fb501"));
        paint.setStyle(Paint.Style.FILL);
        paint2.setColor(Color.WHITE);
        paint2.setStyle(Paint.Style.STROKE);
        Path path = new Path();


        Bitmap bmp = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        canvas.drawCircle(bmp.getWidth()/2, bmp.getHeight()/2, 150, paint);
        path.moveTo(0, bmp.getHeight()/2);
        path.lineTo(bmp.getWidth()/2, bmp.getHeight());
        path.moveTo(bmp.getWidth()/2, bmp.getHeight());
        path.lineTo(bmp.getWidth(),0 );
        path.close();
        paint2.setStrokeWidth(40);
        paint2.setPathEffect(null);
        canvas.drawPath(path, paint2);

        img.setImageBitmap(bmp);


        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent new_activity = new Intent(TestSuccessActivity.this, ProfessorActivity.class);
                new_activity.putExtra("userLogat", loggedUser);
                TestSuccessActivity.this.startActivity(new_activity);
            }
        });


        }
    @Override
    public boolean onKeyDown ( int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent new_activity = new Intent(TestSuccessActivity.this, ProfessorActivity.class);
            new_activity.putExtra("userLogat", loggedUser);
            TestSuccessActivity.this.startActivity(new_activity);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

