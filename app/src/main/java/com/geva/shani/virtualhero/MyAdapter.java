package com.geva.shani.virtualhero;

import android.app.Dialog;
import android.app.SearchManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
   // private final Context context;
    private final String str;
    private final Context context;
    private ArrayList<Character> mDataset_character;




    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView cv;
        TextView characterName;
        TextView characterSex;

        Dialog enterMeetingTimeDialog;

        private ImageButton call_IB;
        private ImageButton mail_IB;
        private ImageButton addContact_IB;
        private ImageButton search_web_IB;
        private ImageButton calender_IB;
        private ImageButton alarm_IB;
        private ImageButton web_IB;

        //TextView personAge;
        //ImageView personPhoto;
        //public TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            //this.context = current;
            cv = (CardView)itemView.findViewById(R.id.card_view);
            characterName = (TextView)itemView.findViewById(R.id.hero_name_txt);
            characterSex = (TextView)itemView.findViewById(R.id.hero_sex_txt);

            call_IB  = (ImageButton)itemView.findViewById(R.id.call_IB);
            mail_IB = (ImageButton)itemView.findViewById(R.id.mail_IB);
            addContact_IB = (ImageButton)itemView.findViewById(R.id.addContact_IB);
            search_web_IB = (ImageButton)itemView.findViewById(R.id.search_web_IB);
            calender_IB = (ImageButton)itemView.findViewById(R.id.calender_IB);
            alarm_IB = (ImageButton)itemView.findViewById(R.id.alarm_IB);
            web_IB = (ImageButton)itemView.findViewById(R.id.web_IB);

        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Context current,Character[] myDataset, String sex, int dark, int light) {
        this.context = current;

        mDataset_character = new ArrayList<>();
        str = sex;
        for(int i=0; i<myDataset.length; i++)
        {
            if(sex.contentEquals(context.getResources().getString(R.string.unknown_sex))) {
                if (dark == 1 && myDataset[i].isVillain())
                    mDataset_character.add(myDataset[i]);
                if (light == 1 && myDataset[i].isHero())
                    mDataset_character.add(myDataset[i]);
            }
            else
            {
                if (dark == 1 && myDataset[i].isVillain() && myDataset[i].getSex().contentEquals(sex))
                    mDataset_character.add(myDataset[i]);
                if (light == 1 && myDataset[i].isHero() && myDataset[i].getSex().contentEquals(sex))
                    mDataset_character.add(myDataset[i]);
            }
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.hero_card, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.characterName.setText(mDataset_character.get(position).getName());
        holder.characterSex.setText(mDataset_character.get(position).getSex());

        holder.call_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to do
                dialPhoneNumber(mDataset_character.get(position).getPhone());
            }
        });

        holder.mail_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to do composeEmail
                String subject = context.getResources().getString(R.string.hello)
                        + " , " + mDataset_character.get(position).getName();
                String addresses[] = new String[1];
                addresses[0] = mDataset_character.get(position).getMail();
                composeEmail( addresses, subject);
            }
        });

        holder.addContact_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to do insertContact
                insertContact(mDataset_character.get(position).getName(),
                        mDataset_character.get(position).getMail(),
                        mDataset_character.get(position).getPhone() );
            }
        });

        holder.search_web_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to do searchWeb
                if(mDataset_character.get(position).isHero())
                    searchWeb(context.getResources().getString(R.string.hero) +
                            " " + mDataset_character.get(position).getName());
                else if(mDataset_character.get(position).isVillain())
                    searchWeb(context.getResources().getString(R.string.vilian) +
                            " " + mDataset_character.get(position).getName());
            }
        });

        //Contact us Handler
        holder.enterMeetingTimeDialog = new Dialog(context);

        holder.calender_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to do addEvent
                // openEnterMeetingTimeDialog(); enterMeetingTimeDialog
                addEvent(context.getResources().getString(R.string.meeting) +
                        " " + mDataset_character.get(position).getName() ,
                        context.getResources().getString(R.string.hit_holon) );
            }
        });

        holder.alarm_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to do createAlarm
                createAlarm(context.getResources().getString(R.string.meeting) +
                        " " + mDataset_character.get(position).getName() , 10 , 0);
            }
        });

        holder.web_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to do openWebPage
                openWebPage(mDataset_character.get(position).getWebsite());
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset_character.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        //intent.putExtra(Intent.EXTRA_STREAM, attachment);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public void insertContact(String name, String email, String phone) {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public void searchWeb(String query) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, query);
        if (intent.resolveActivity( context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public void addEvent(String title, String location) {
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, title)
                .putExtra(CalendarContract.Events.EVENT_LOCATION, location);
        /*
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end);
        */
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

}
