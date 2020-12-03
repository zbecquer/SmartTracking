package net.sytes.smart_tracking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Main extends AppCompatActivity {

    private View buttonNewService;
    private View buttonEndService;
    private View buttonCheckReg;
    private View imageAsAButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        buttonNewService = (Button) findViewById(R.id.buttonNewService);
        buttonEndService = (Button) findViewById(R.id.buttonEndService);
        buttonCheckReg = (Button) findViewById(R.id.buttonCheckReg);
        imageAsAButton = (ImageView) findViewById(R.id.imageConfig);

        imageAsAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentES = new Intent(Main.this, RegActivity.class);
                startActivity(intentES);
            }
        });

        buttonNewService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNS = new Intent(Main.this, NewServiceActivity.class);
                startActivity(intentNS);
            }
        });
        buttonEndService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentES = new Intent(Main.this, RegActivity.class);
                startActivity(intentES);
            }
        });
        buttonCheckReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCR = new Intent(Main.this, RegActivity.class);
                startActivity(intentCR);
            }
        });



    }

    int count = 0;
    @Override
    public void onBackPressed() {
        if(count == 0)
        {
            Toast.makeText(getApplicationContext(), "Presione de nuevo para salir", Toast.LENGTH_SHORT).show();
            count++;
        }
        else
        {
            super.onBackPressed();
        }

        new CountDownTimer(3000, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished) {
                finish();
            }

            @Override
            public void onFinish() {
                count = 0;
            }
        };
    }
}