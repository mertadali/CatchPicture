package com.mertadali.catchkenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

// Bu kısımda gerekli tanımlamaları yaptık.



    TextView scoreText;
    TextView timeText;
    int score;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    int bestScor;
    int scorControl;
    int scor1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //initiliaze denir bu tanımlamaya.
        // Burada yukarıda tanımladıklarımızın id lerini tanımladık.
        timeText = (TextView) findViewById(R.id.timeText);
         scoreText = (TextView) findViewById(R.id.scoreText);
         imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);



        // imageviewlerin hepsini bir diziye attık ve elemanlarına ulaşabiliriz artık.

        imageArray = new ImageView[] {imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};
        hideImages();
        score=0;

        //Sürenin nasıl olacağına kaçtan geriye doğru hangi aralıkta gideceğini CountDownTimer ile belirledik.

         new CountDownTimer(20000,1000){

             @Override
             public void onTick(long l) {
                 timeText.setText("Time:" +l/1000);


             }
             /*Bu kısım ise oyun bittiğinde runnableyi sonlandır ve artık görseli gösterme kısımını belirtlir. */

             @Override
             public void onFinish() {
                 timeText.setText("Over");

                 handler.removeCallbacks(runnable);
                 for (ImageView image: imageArray) {
                     image.setVisibility(View.INVISIBLE);
                 }

                 // Burada ise oyun bittikten sonra tekrar oynamak ister misiniz diye bir ifade çıksın istiyoruz.
                 AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                 alert.setTitle("Restart?");
                 alert.setCancelable(false); // Uyarı mesajında farklı bir yere dokununca app çökmesin diye.
                 alert.setMessage("Do you want to restart game?");

                 // Bu kısımda çıkan ekranda "Evet" tuşuna basarsa ne olsun "Hayır'a basarsa ne olsun kısmıdır.
                 alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         // Yeniden başlatmak için

                         Intent intent = getIntent();
                         finish();
                         startActivity(intent);



                     }
                 });
                 alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         Toast.makeText(MainActivity.this,"Game Over!",Toast.LENGTH_LONG);

                     }
                 });
                 alert.show();


             }
         }.start();
    }



    public void increaseScore(View view){  // Tıklandığında puan arttırmak için bu kısımı yaptık.
        score++;
        scoreText.setText("Score:" +score);

        /* dizinin elemanlarını gizli yaptık ve sonrasında random şekilde 1 tanesi görünür olacak
        bunun için runnable kullandık handler runnable kullanabilme için var.*/

    }
    public void hideImages(){

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image: imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                    Random random = new Random();
                    int i = random.nextInt(9);
                    imageArray[i].setVisibility(View.VISIBLE);
                    handler.postDelayed(runnable,1000);
                }


        };
        handler.post(runnable);


    }
}