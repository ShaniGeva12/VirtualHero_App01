package com.geva.shani.virtualhero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class choose_side extends AppCompatActivity {

    public static final String EXTRA_DARK_FLAG = "com.geva.shani.virtualhero.DARK_FLAG";
    public static final String EXTRA_LIGHT_FLAG = "com.geva.shani.virtualhero.LIGHT_FLAG";
    public static final String EXTRA_CH_SEX = "com.geva.shani.virtualhero.CH_SEX";


    private TextView welcome_str;
    private RelativeLayout rl;
    private String device_language;
    private Button darkSideBtn;
    private Button lightSideBtn;
    private Button continueBtn;
    private Button cont_btn;
    private int light_flag;
    private int dark_flag;
    private RadioGroup radioSexGroup;
    private String ch_sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_side);

        light_flag=0;
        dark_flag=0;

        cont_btn = findViewById(R.id.choice_enter_btn);

        radioSexGroup  = (RadioGroup)findViewById(R.id.sexRadioGroup);

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
                    lightSideBtn.setBackgroundResource(R.drawable.button02_bg_rounded_corners);
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
                    darkSideBtn.setBackgroundResource(R.drawable.button02_bg_rounded_corners);
                    dark_flag = 0;
                }
            }
        });



        cont_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = radioSexGroup.getCheckedRadioButtonId();
                switch(selectedId){
                    case R.id.male_checkbox:
                        // do operations specific to this selection
                        ch_sex = getResources().getString(R.string.male);
                        break;
                    case R.id.female_checkbox:
                        ch_sex = getResources().getString(R.string.female);
                        break;

                    default:
                        ch_sex = getResources().getString(R.string.unknown_sex);
                        break;
                }

                if(dark_flag==0 && light_flag==0)
                    Toast.makeText(choose_side.this, getResources().getString(R.string.gotta_choose), Toast.LENGTH_SHORT).show();
                else
                    movePage(view, dark_flag , light_flag,ch_sex);
            }
        });




    }

    public void movePage(View view,int dark_flag,int light_flag, String charter_sex)
    {
        Intent intent = new Intent(choose_side.this, heros_list.class);
        intent.putExtra(EXTRA_DARK_FLAG , dark_flag);
        intent.putExtra(EXTRA_LIGHT_FLAG , light_flag);
        intent.putExtra(EXTRA_CH_SEX , charter_sex);
        startActivity(intent);
    }
}
