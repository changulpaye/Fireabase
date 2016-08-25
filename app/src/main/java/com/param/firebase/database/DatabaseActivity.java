package com.param.firebase.database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.param.firebase.R;
import com.param.firebase.database.models.Data;
import com.param.firebase.database.models.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DatabaseActivity extends AppCompatActivity {

    @BindView(R.id.buttonMessage)
    Button mButtonMsg;
    @BindView(R.id.editTextMessage)
    EditText mEditTextMessage;
    private int counter = 1;
    FirebaseDatabase database;
    DatabaseReference dbref;
    private String TAG = "PARAMDB";

    private List<Data> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        ButterKnife.bind(this);
        mButtonMsg.setOnClickListener(mOnClickListener);
        database = FirebaseDatabase.getInstance();
        dbref = database.getReference("health/english/deaseases");
        addChildEventListener();
       // readData();
    }



    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String message = mEditTextMessage.getText().toString();
            if(message.isEmpty()) {
                mEditTextMessage.setError("Add message");
            }else {
              /*  writeNewUser(message, "Parmehwar changulapye", "changulpaye@gmail.com");
                writeNewPost("param","changulpaye", "Learning mad easy", " From this post you" +
                        "will learn new thing");*/

                writeNewData(message, "Some desc about the data", "/data/img.jpg", 1, "Title", false, false);
            }



        }
    };





    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        dbref.child("users").child(userId).setValue(user);
    }

    private void writeNewData(String id, String desc, String url, long like, String name, final boolean c, boolean s){


        final Data data = new Data(id, name, desc, url,like, c, s);
        dbref.child(id).setValue(data);
 /*       dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Count " ,""+dataSnapshot.getChildrenCount());
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Data post = postSnapshot.getValue(Data.class);
                    Log.e("Get Data", post.getId());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/


    }



    private void addChildEventListener() {

        dbref.addChildEventListener(new ChildEventListener() {
            // Retrieve new posts as they are added to the database
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
                Data data1 = snapshot.getValue(Data.class);
                System.out.println("Name: " + data1.getName());
                System.out.println("Title: " + data1.getDesc());


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
            //... ChildEventListener also defines onChildChanged, onChildRemoved,
            //    onChildMoved and onCanceled, covered in later sections.
        });
    }
    //Read from DB
    private void readData() {

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d(TAG, "Value is: " +  dataSnapshot.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
    }
}
