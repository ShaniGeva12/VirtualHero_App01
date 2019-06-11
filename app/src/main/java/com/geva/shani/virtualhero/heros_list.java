package com.geva.shani.virtualhero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class heros_list extends AppCompatActivity {

    private TextView titleTxt;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heros_list);

        titleTxt = findViewById(R.id.title_txt);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String ch_sex = intent.getStringExtra(choose_side.EXTRA_CH_SEX);
        int dark_flag = intent.getIntExtra(choose_side.EXTRA_DARK_FLAG , 0);
        int light_flag = intent.getIntExtra(choose_side.EXTRA_LIGHT_FLAG , 0);

        titleTxt.setTextSize(15);

        // Capture the layout's TextView and set the string as its text
        if(dark_flag==1 && light_flag==1)
            titleTxt.setText(" " + getResources().getString(R.string.your_choise) + "\n" + "  " + getResources().getString(R.string.hero)
                    +  " / " + getResources().getString(R.string.vilian) +  " " + ch_sex);
        else if(dark_flag == 1)
            titleTxt.setText(" " + getResources().getString(R.string.your_choise) + " "
                    +  " " + getResources().getString(R.string.vilian) +  " " + ch_sex);
        else if(light_flag == 1)
            titleTxt.setText(" " + getResources().getString(R.string.your_choise) + " "
                    + getResources().getString(R.string.hero) +  " " + ch_sex);

        //----------------------------------------------------------------------------------
        //String name,String phone, String mail, String website,boolean isHero,boolean isVillain
        Character characters[] = new Character[8];

        characters[0] = new Character(
                getResources().getString(R.string.batman),
                getResources().getString(R.string.male),
                "0543279666",
                "batman@gmail.com",
                "https://en.wikipedia.org/wiki/Batman",
                true,
                false
        );

        characters[1] = new Character(
                getResources().getString(R.string.superman),
                getResources().getString(R.string.male),
                "0543279666",
                "superman@gmail.com",
                "https://en.wikipedia.org/wiki/Superman",
                true,
                false
        );

        characters[2] = new Character(
                getResources().getString(R.string.iron_man),
                getResources().getString(R.string.male),
                "0543279666",
                "iron_man@gmail.com",
                "https://en.wikipedia.org/wiki/Iron_Man",
                true,
                false
        );

        characters[3] = new Character(
                getResources().getString(R.string.captain_america),
                getResources().getString(R.string.male),
                "0543279666",
                "captain_america@gmail.com",
                "https://en.wikipedia.org/wiki/Captain_America",
                true,
                false
        );

        //Catwoman is both a hero & villain
        characters[4] = new Character(
                getResources().getString(R.string.catwoman),
                getResources().getString(R.string.female),
                "0543279666",
                "catwoman@gmail.com",
                "https://en.wikipedia.org/wiki/Catwoman",
                true,
                true
        );

        characters[5] = new Character(
                getResources().getString(R.string.joker),
                getResources().getString(R.string.male),
                "0543279666",
                "joker@gmail.com",
                "https://en.wikipedia.org/wiki/Joker_(character)",
                false,
                true
        );

        characters[6] = new Character(
                getResources().getString(R.string.harley_quinn),
                getResources().getString(R.string.female),
                "0543279666",
                "harley_quinn@gmail.com",
                "https://en.wikipedia.org/wiki/Harley_Quinn",
                false,
                true
        );

        characters[7] = new Character(
                getResources().getString(R.string.thanos),
                getResources().getString(R.string.male),
                "0543279666",
                "thanos@gmail.com",
                "https://en.wikipedia.org/wiki/Thanos",
                false,
                true
        );

        //----------------------------------------------------------------------------------

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(this, characters ,ch_sex , dark_flag , light_flag);
        recyclerView.setAdapter(mAdapter);


    }
}
