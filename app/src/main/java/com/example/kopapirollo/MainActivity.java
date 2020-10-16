package com.example.kopapirollo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
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

public class MainActivity extends AppCompatActivity {
    private ImageView imgKo,imgOllo,imgPapir, imgGep, imgEmber;
    private TextView EredmenyEmber, EredmenyPC;
    private Button bttnPapir, bttnKo, bttnOllo;
    private int random, hp;
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
        random=rand.nextInt(4);
        hp = 4;


        bttnPapir.setOnClickListener(this);
        bttnKo.setOnClickListener(this);
        bttnOllo.setOnClickListener(this);
    }


    private void init() {
        imgGep=findViewById(R.id.image_gep);
        imgEmber=findViewById(R.id.image_ember);


        bttnKo=findViewById(R.id.buttonKo);
        bttnPapir=findViewById(R.id.buttonPapir);
        bttnOllo=findViewById(R.id.buttonOllo);


        if (random<2)
        { gepTipp="Ko"; }
        else if (random<3)
        {gepTipp="Papir";}
        else if(random<4)
        {gepTipp="Ollo";}
        else {
            //todo:hibaüzenet
        }

        builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Vesztettél!")
                .setMessage("szeretnél-e új játékot kezdeni?")
                .setNegativeButton("igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //ha igen, akkor kezdje ujra
                        //újra kell fel venni a dolgokat


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


        toast = Toast.makeText(MainActivity.this,"",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        View view = getLayoutInflater().inflate(R.layout.customtoast,(ViewGroup)findViewById(R.id.customToast));
        toast.setView(view);

    }

    @Override
    public void onClick(View v) {
            int viewId =  v.getId();
            switch (viewId){
                case R.id.buttonPapir:
                    if (gepTipp =="Ko")
                    {
                        playerWIN();
                        toast.makeText(this,"Te nyerted a kört!",toast.LENGTH_SHORT).show();
                    }else if(gepTipp=="Ollo")
                    {
                        gepWIN();
                        toast.makeText(this,"A gép nyerte a kört!",toast.LENGTH_SHORT).show();
                    }else if(gepTipp=="Papir")
                    {
                        draw();
                        toast.makeText(this,"Ez a kör döntetlen lett",toast.LENGTH_SHORT).show();
                    }else {
                    toast.makeText(this,"ennek nem kéne elöfordulnia, valami hiba van :D oops",toast.LENGTH_SHORT).show();
                    }

                    break;

                case R.id.buttonOllo:
                    if (gepTipp =="Ko")
                    {
                        gepWIN();
                        toast.makeText(this,"A gép nyerte a kört!",toast.LENGTH_SHORT).show();
                    }else if(gepTipp=="Ollo")
                    {
                        draw();
                        toast.makeText(this,"Ez a kör döntetlen lett",toast.LENGTH_SHORT).show();
                    }else if(gepTipp=="Papir")
                    {
                        playerWIN();
                        toast.makeText(this,"Te nyerted a kört!",toast.LENGTH_SHORT).show();
                    }else {
                        toast.makeText(this,"ennek nem kéne elöfordulnia, valami hiba van :D oops",toast.LENGTH_SHORT).show();
                    }

                    break;

                case R.id.buttonKo:
                    if (gepTipp =="Ko")
                    {
                     draw();
                        toast.makeText(this,"Ez a kör döntetlen lett!",toast.LENGTH_SHORT).show();
                    }else if(gepTipp=="Ollo")
                    {
                        playerWIN();
                        toast.makeText(this,"Te nyerted a kört!",toast.LENGTH_SHORT).show();
                    }else if(gepTipp=="Papir")
                    {
                        gepWIN();
                        toast.makeText(this,"A gép nyerte ezt a kört!",toast.LENGTH_SHORT).show();
                    }else {
                        toast.makeText(this,"ennek nem kéne elöfordulnia, valami hiba van :D oops",toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;


            }
        }


        //todo: számlálok
    private void gepWIN()
    {

    }


    private void playerWIN()
    {

    }


    private void draw()
    {

    }

}