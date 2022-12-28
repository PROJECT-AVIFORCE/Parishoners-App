package com.example.parishoners;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class announcement extends AppCompatActivity {
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    ArrayList<msgItem> msgItemArrayList;
    msgRecyclerAdapter adapter;

    Button buttonPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

//        getSupportActionBar().hide();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        msgItemArrayList = new ArrayList<>();

        buttonPost = findViewById(R.id.buttonPost);
        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ViewDialogPost viewDialogPost = new ViewDialogPost();
                viewDialogPost.showDialog(announcement.this);

            }
        });

        readData();
    }

    private void readData() {
        databaseReference.child("MESSAGES").orderByChild("msgDes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                msgItemArrayList.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    msgItem messages = dataSnapshot.getValue(msgItem.class);
                    msgItemArrayList.add(messages);
                }

                adapter = new msgRecyclerAdapter(announcement.this, msgItemArrayList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public class ViewDialogPost {
            public void showDialog(Context context) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.alert_dialog_post_a_new_announcement);

                EditText textdescription = dialog.findViewById(R.id.textdescription);
                Button buttonPost = dialog.findViewById(R.id.buttonPost);
                Button buttonCancel = dialog.findViewById(R.id.buttonCancel);

                buttonPost.setText("POST");

                buttonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();

                    }
                });

                buttonPost.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String id = "msg" + new Date().getTime();
                        String desc = textdescription.getText().toString();

                        if (desc.isEmpty()) {
                            Toast.makeText(context, "Please Add a new message..", Toast.LENGTH_SHORT).show();
                        } else {
                            databaseReference.child("MESSAGES").child(id).setValue(new msgItem(id, desc));
                            Toast.makeText(context, "Message Posted Successfully!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });


                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        }

    }


