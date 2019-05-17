package com.geva.shani.virtualhero;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView welcome_str;
    private EditText user_name;
    private int visible_flag;
    private Button enterIntoAppBtn;
    private RelativeLayout rl;
    private String device_language;


    public static final String EXTRA_USERNAME = "com.geva.shani.virtualhero.USERNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_name = findViewById(R.id.name_enter);
        enterIntoAppBtn = findViewById(R.id.btn);

        enterIntoAppBtn.setWidth(user_name.getWidth());
        //not sure if it works, but makes no problems in the code so what the heck

        rl = findViewById(R.id.main_layout);
        device_language = Locale.getDefault().getDisplayLanguage().toString();
       // Toast.makeText(MainActivity.this, device_language, Toast.LENGTH_SHORT).show();



        if(device_language.contentEquals("עברית") ||
                device_language.contentEquals("Hebrew") ||
                device_language.contentEquals("hebrew") ||
                device_language.contentEquals("iw_IL") ||
                device_language.contentEquals("iw") )
        {
            rl.setBackgroundResource(R.drawable.supergroup_vh_new_logo_heb_brushed_sml);
        }



        user_name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    //btn click
                    enterIntoAppBtn.callOnClick();
                    handled = true;
                }
                return handled;
            }
        });


        enterIntoAppBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = user_name.getText().toString();
                if(n.contentEquals("") || n.contentEquals(getResources().getString(R.string.enter)) || n.contentEquals(" "))
                {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.login_must), Toast.LENGTH_SHORT).show();
                }
               else
                {
                    movePage(view, n);
                }

            }
        });

        //String n = user_name.getText().toString();


    }

    public void movePage(View view, String name)
    {
        Intent intent = new Intent(MainActivity.this, choose_side.class);
        intent.putExtra(EXTRA_USERNAME , name);
        startActivity(intent);
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}


