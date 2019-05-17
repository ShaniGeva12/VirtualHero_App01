package com.geva.shani.virtualhero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class choose_side extends AppCompatActivity {
    private TextView welcome_str;
    private RelativeLayout rl;
    private String device_language;
    private Button darkSideBtn;
    private Button lightSideBtn;
    private Button continueBtn;
    private Button cont_btn;
    private int light_flag;
    private int dark_flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_side);

        light_flag=0;
        dark_flag=0;

        cont_btn = findViewById(R.id.choice_enter_btn);

        rl = findViewById(R.id.main_layout);
        device_language = Locale.getDefault().getDisplayLanguage().toString();
        welcome_str = findViewById(R.id.title_ly);

        if(device_language.contentEquals("עברית") ||
                device_language.contentEquals("Hebrew") ||
                device_language.contentEquals("hebrew") ||
                device_language.contentEquals("iw_IL") ||
                device_language.contentEquals("iw") )
        {
            rl.setBackgroundResource(R.drawable.superheroes_white_background_sml);
        }

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.EXTRA_USERNAME);

        // Capture the layout's TextView and set the string as its text
        welcome_str.setText(getResources().getString(R.string.wlcm_str) + " , " + name );

        darkSideBtn = findViewById(R.id.vilians_btn);
        lightSideBtn = findViewById(R.id.superheros_btn);
        continueBtn = findViewById(R.id.choice_enter_btn);

        lightSideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(light_flag==0)
                {
                    lightSideBtn.setBackgroundResource(R.drawable.button01_bg_rounded_corners);
                    light_flag = 1;
                }
                else
                {
                    lightSideBtn.setBackgroundResource(R.drawable.button_bg_rounded_corners);
                    light_flag = 0;
                }
            }
        });

        darkSideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dark_flag==0)
                {
                    darkSideBtn.setBackgroundResource(R.drawable.button01_bg_rounded_corners);
                    dark_flag = 1;
                }
                else
                {
                    darkSideBtn.setBackgroundResource(R.drawable.button_bg_rounded_corners);
                    dark_flag = 0;
                }
            }
        });


        cont_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movePage(view, "");
                //change move page to get the flags and also sex from radio group
            }
        });




    }

    public void movePage(View view, String name)
    {
        Intent intent = new Intent(choose_side.this, choose_side.class);
        //intent.putExtra(EXTRA_USERNAME , name);
        startActivity(intent);
    }
}
