package com.example.plmakal2.myapplication100;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Lista extends Activity {
    private ListView listView1;

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private DatabaseReference DataRef;
    private String userID;
    private boolean isSuperUser;
    private int counter;


    public class RowBean {

        public int icon;
        public String title;

        public RowBean() {

        }

        public RowBean(int icon, String title) {

            this.icon = icon;
            this.title = title;
        }
    }

    public class RowAdapter extends ArrayAdapter<RowBean> {

        Context context;
        int layoutResourceId;
        RowBean data[] = null;

        public RowAdapter(Context context, int layoutResourceId, RowBean[] data) {
            super(context, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.context = context;
            this.data = data;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            RowBeanHolder holder = null;

            if (row == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                row = inflater.inflate(layoutResourceId, parent, false);

                holder = new RowBeanHolder();
                holder.imgIcon = (ImageView) row.findViewById(R.id.imgIcon);
                holder.txtTitle = (TextView) row.findViewById(R.id.txtTitle);

                row.setTag(holder);
            } else {
                holder = (RowBeanHolder) row.getTag();
            }

            RowBean object = data[position];
            holder.txtTitle.setText(object.title);
            holder.imgIcon.setImageResource(object.icon);

            return row;
        }

        public class RowBeanHolder {
            ImageView imgIcon;
            TextView txtTitle;
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        mAuth = FirebaseAuth.getInstance();

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        //myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        mFirebaseDatabase.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user2 = dataSnapshot.getValue(User.class);
                isSuperUser = user2.superuser;
                counter = user2.counter;


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        RowBean RowBean_data[] = new RowBean[]{

                new RowBean(R.drawable.abr, ""),
                new RowBean(R.drawable.abrm, ""),
                new RowBean(R.drawable.badmintomania2, ""),
                new RowBean(R.drawable.calypso2, ""),
                new RowBean(R.drawable.pobrane, ""),
                new RowBean(R.drawable.paczek2, ""),
                new RowBean(R.drawable.powerbad2, ""),
                new RowBean(R.drawable.wacha, ""),
        };

        RowAdapter adapter = new RowAdapter(this,
                R.layout.row, RowBean_data);

        listView1 = (ListView) findViewById(R.id.Lista);

        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //DataRef = FirebaseDatabase.getInstance().getReference().child(userID);




                //position=0;
                switch (position) {
                    case 0: {

                        if( isSuperUser == false) {
                            Intent mIntent = new Intent(view.getContext(), Activity12.class);
                            startActivity(mIntent);
                            break;
                        }
                        if( isSuperUser == true && counter == 1) {
                            Intent mIntent = new Intent(view.getContext(), Activity11.class);
                            startActivity(mIntent);
                            break;
                        }
                        else
                        {
                            break;

                        }
                    }
                    case 1: {

                        if( isSuperUser == false) {
                            Intent mIntent = new Intent(view.getContext(), Activity22.class);
                            startActivity(mIntent);
                            break;
                        }
                        if( isSuperUser == true && counter == 2) {
                            Intent mIntent = new Intent(view.getContext(), Activity21.class);
                            startActivity(mIntent);
                            break;
                        }
                        else
                        {
                            break;

                        }

                    }
                    case 2: {

                        if( isSuperUser == false) {
                            Intent mIntent = new Intent(view.getContext(), Activity33.class);
                            startActivity(mIntent);
                            break;
                        }
                        if( isSuperUser == true && counter ==3) {
                            Intent mIntent = new Intent(view.getContext(), Activity31.class);
                            startActivity(mIntent);
                            break;
                        }
                        else
                        {
                            break;

                        }

                    }
                    case 3: {
                        Uri uri = Uri.parse("https://www.calypso.com.pl/oferta/badminton"); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    }
                    case 4: {
                        Uri uri = Uri.parse("http://kahuna.com.pl/badminton"); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    }
                    case 5: {


                        if (isSuperUser == false) {
                            Intent mIntent = new Intent(view.getContext(), Activity66.class);
                            startActivity(mIntent);
                            break;
                        }
                        if (isSuperUser == true && counter == 5) {
                            Intent mIntent = new Intent(view.getContext(), Activity61.class);
                            startActivity(mIntent);
                            break;
                        }
                        else
                        {
                            break;

                        }
                    }

                    case 6: {

                        if( isSuperUser == false) {
                            Intent mIntent = new Intent(view.getContext(), Activity77.class);
                            startActivity(mIntent);
                            break;
                        }
                        if( isSuperUser == true && counter == 6) {
                            Intent mIntent = new Intent(view.getContext(), Activity71.class);
                            startActivity(mIntent);
                            break;
                        }
                        else
                        {
                            break;

                        }

                    }
                    case 7: {

                        if( isSuperUser == false) {
                            Intent mIntent = new Intent(view.getContext(), Activity88.class);
                            startActivity(mIntent);
                            break;
                        }
                        if( isSuperUser == true && counter == 7) {
                            Intent mIntent = new Intent(view.getContext(), Activity81.class);
                            startActivity(mIntent);
                            break;
                        }
                        else
                        {
                            break;

                        }

                    }
                }


            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Lista.this, LoginActivity.class));

            }

        });

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Lista.this, MapsActivity.class));

            }

        });





    }

}
