package com.example.graphics;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.graphics.Bitmap;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class
MainActivity extends AppCompatActivity {


    ImageView imageView;
    Button blinkBTN, rotateBTN, fadeBTN, moveBTN, slideBTN, zoomBTN, carBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.owl);
        blinkBTN = findViewById(R.id.blinkBtn);
        rotateBTN = findViewById(R.id.rotateBtn);
        fadeBTN = findViewById(R.id.fadeBtn);
        moveBTN = findViewById(R.id.moveBtn);
        slideBTN = findViewById(R.id.slideBtn);
        zoomBTN = findViewById(R.id.zoomBtn);
        carBTN = findViewById(R.id.carBtn);

        blinkBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To add blink animation
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                imageView.startAnimation(animation);
            }
        });
        rotateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To add rotate animation
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                imageView.startAnimation(animation);
            }
        });
        fadeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To add fade animation
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                imageView.startAnimation(animation);
            }
        });
        moveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To add move animation
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
                imageView.startAnimation(animation);
            }
        });
        slideBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To add slide animation
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);
                imageView.startAnimation(animation);
            }
        });

        zoomBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To add zoom animation
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
                imageView.startAnimation(animation);
            }
        });

        carBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

                paint.setColor(Color.BLACK);
                canvas.drawLine(50, 50, 50, 250, paint);

                paint.setColor(Color.BLACK);
                canvas.drawCircle(200, 200, 30, paint);

                paint.setColor(Color.YELLOW);
                canvas.drawRect(100, 200, 150,30, paint);

                paint.setColor(Color.RED);
                canvas.drawArc(200,50,290,150, 135,180,true, paint);


                imageView.setImageBitmap(bitmap);
            }
        });
    }
}
