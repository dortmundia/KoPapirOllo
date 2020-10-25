package com.example.kopapirollo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgGep, imgEmber, imgGepHP1, imgGepHP2, imgGepHP3, imgEmberHP1, imgEmberHP2, imgEmberHP3;
    private TextView dontetlen;
    private Button bttnPapir, bttnKo, bttnOllo;
    private int random, gephp, emberhp, dontetlenSzama;
    public String gepTipp;

    private Toast toast;
    private Toast custom_Toast;
    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();
        Random rand = new Random();
        random = rand.nextInt(4);



        bttnPapir.setOnClickListener(this);
        bttnKo.setOnClickListener(this);
        bttnOllo.setOnClickListener(this);
    }


    private void init() {
        emberhp = 3;
        gephp = 3;
        dontetlenSzama=0;

        dontetlen = findViewById(R.id.dontetlen);

        imgGep = findViewById(R.id.image_gep);
        imgEmber = findViewById(R.id.image_player);

        imgEmberHP1 = findViewById(R.id.image_player_hp1);
        imgEmberHP2 = findViewById(R.id.image_player_hp2);
        imgEmberHP3 = findViewById(R.id.image_player_hp3);
        imgGepHP1 = findViewById(R.id.image_gep_hp1);
        imgGepHP2 = findViewById(R.id.image_gep_hp2);
        imgGepHP3 = findViewById(R.id.image_gep_hp3);

        bttnKo = findViewById(R.id.buttonKo);
        bttnPapir = findViewById(R.id.buttonPapir);
        bttnOllo = findViewById(R.id.buttonOllo);




        builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Vesztettél!")
                .setMessage("szeretnél-e új játékot kezdeni?")
                .setNegativeButton("igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //ha igen, akkor kezdje ujra
                        //újra kell fel venni a dolgokat
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);


                    }
                })
                .setPositiveButton("nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //ha nem akkor lépjen ki az alkalmazásból
                        finish();
                    }
                })
                .setCancelable(false);
        alertDialog = builder.create();


        toast = Toast.makeText(MainActivity.this, "", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        View view = getLayoutInflater().inflate(R.layout.customtoast, (ViewGroup) findViewById(R.id.customToast));
        toast.setView(view);

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (random < 2) {
            gepTipp = "Ko";
        } else if (random < 3) {
            gepTipp = "Papir";
        } else if (random < 4) {
            gepTipp = "Ollo";
        } else {
            //tod:hibaüzenet
        }
        Random rand = new Random();

        switch (viewId) {
            case R.id.buttonPapir:
                imgEmber.setImageResource(R.drawable.paper);
                if (gepTipp == "Ko") {
                    playerWIN();
                    random = rand.nextInt(4);
                    imgGep.setImageResource(R.drawable.rock);
                    toast.makeText(this, "Te nyerted a kört!", toast.LENGTH_SHORT).show();
                } else if (gepTipp == "Ollo") {
                    gepWIN();
                    random = rand.nextInt(4);
                    imgGep.setImageResource(R.drawable.scissors);
                    toast.makeText(this, "A gép nyerte a kört!", toast.LENGTH_SHORT).show();
                } else if (gepTipp == "Papir") {
                    draw();
                    random = rand.nextInt(4);
                    imgGep.setImageResource(R.drawable.paper);
                    toast.makeText(this, "Ez a kör döntetlen lett", toast.LENGTH_SHORT).show();
                } else {
                    toast.makeText(this, "ennek nem kéne elöfordulnia, valami hiba van :D oops", toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.buttonOllo:
                imgEmber.setImageResource(R.drawable.scissors);
                if (gepTipp == "Ko") {
                    gepWIN();
                    random = rand.nextInt(4);
                    imgGep.setImageResource(R.drawable.rock);
                    toast.makeText(this, "A gép nyerte a kört!", toast.LENGTH_SHORT).show();
                } else if (gepTipp == "Ollo") {
                    draw();
                    random = rand.nextInt(4);
                    imgGep.setImageResource(R.drawable.scissors);
                    toast.makeText(this, "Ez a kör döntetlen lett", toast.LENGTH_SHORT).show();
                } else if (gepTipp == "Papir") {
                    playerWIN();
                    random = rand.nextInt(4);
                    imgGep.setImageResource(R.drawable.paper);
                    toast.makeText(this, "Te nyerted a kört!", toast.LENGTH_SHORT).show();
                } else {
                    toast.makeText(this, "ennek nem kéne elöfordulnia, valami hiba van :D oops", toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.buttonKo:
                imgEmber.setImageResource(R.drawable.rock);
                if (gepTipp == "Ko") {
                    draw();
                    random = rand.nextInt(4);
                    imgGep.setImageResource(R.drawable.rock);
                    toast.makeText(this, "Ez a kör döntetlen lett!", toast.LENGTH_SHORT).show();
                } else if (gepTipp == "Ollo") {
                    playerWIN();
                    random = rand.nextInt(4);
                    imgGep.setImageResource(R.drawable.scissors);
                    toast.makeText(this, "Te nyerted a kört!", toast.LENGTH_SHORT).show();
                } else if (gepTipp == "Papir") {
                    gepWIN();
                    random = rand.nextInt(4);
                    imgGep.setImageResource(R.drawable.paper);
                    toast.makeText(this, "A gép nyerte ezt a kört!", toast.LENGTH_SHORT).show();
                } else {
                    toast.makeText(this, "ennek nem kéne elöfordulnia, valami hiba van :D oops", toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;


        }
    }




    private void gepWIN()
    {
        if(emberhp==3)
        {
            imgEmberHP3.setImageResource(R.drawable.heart1);
        }
        else if(emberhp==2)
        {
            imgEmberHP2.setImageResource(R.drawable.heart1);
        }
        else  if(emberhp==1)
        {
            imgEmberHP1.setImageResource(R.drawable.heart1);
            alertDialog.show();
        }
        emberhp--;
    }


    private void playerWIN()
    {
        if(gephp==3)
        {
            imgGepHP3.setImageResource(R.drawable.heart1);
        }
        else if(gephp==2)
        {
            imgGepHP2.setImageResource(R.drawable.heart1);
        }
        else if(gephp==1)
        {
            imgGepHP1.setImageResource(R.drawable.heart1);

            builder.setTitle("nyertél!");
            alertDialog = builder.create();

            alertDialog.show();
        }
        gephp--;
    }


    private void draw()
    {
        dontetlenSzama++;
        dontetlen.setText("Döntetlenek száma: "+dontetlenSzama);
    }


}