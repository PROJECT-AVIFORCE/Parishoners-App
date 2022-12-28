package com.example.parishoners;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

public class msgRecyclerAdapter extends RecyclerView.Adapter<msgRecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList<msgItem> msgItemArrayList;
    DatabaseReference databaseReference;

    public msgRecyclerAdapter(Context context, ArrayList<msgItem> msgItemArrayList) {
        this.context = context;
        this.msgItemArrayList = msgItemArrayList;
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.msg_item,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        msgItem messages = msgItemArrayList.get(position);
        holder.textdescription.setText("Description: "+messages.getMsgDes());

        holder.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialogUpdate viewDialogUpdate = new ViewDialogUpdate();
                viewDialogUpdate.showDialog(context,messages.getMsgID(),messages.getMsgDes());
            }
        });

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialogConfirmDelete viewDialogConfirmDelete = new ViewDialogConfirmDelete();
                viewDialogConfirmDelete.showDialog(context,messages.getMsgID());

            }
        });
    }

    @Override
    public int getItemCount() {
        return msgItemArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textdescription;

        Button buttonDelete;
        Button buttonUpdate;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            textdescription = itemView.findViewById(R.id.textdescription);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
            buttonUpdate = itemView.findViewById(R.id.buttonUpdate);

        }
    }

    public class ViewDialogUpdate {
        public void showDialog(Context context, String id, String des) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.alert_dialog_post_a_new_announcement);

            EditText textdescription = dialog.findViewById(R.id.textdescription);
            textdescription.setText(des);

            Button buttonUpdate = dialog.findViewById(R.id.buttonPost);
            Button buttonCancel = dialog.findViewById(R.id.buttonCancel);

            buttonUpdate.setText("UPDATE");


            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();

                }
            });

            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String newdesc = textdescription.getText().toString();

                    if (des.isEmpty()) {
                        Toast.makeText(context, "Please Add a new message..", Toast.LENGTH_SHORT).show();
                    } else {

                        if(newdesc.equals(des)){
                            Toast.makeText(context, "You dont change anything", Toast.LENGTH_SHORT).show();
                        }else{
                            databaseReference.child("MESSAGES").child(id).setValue(new msgItem(id, des));
                            Toast.makeText(context, "Message Updated Successfully!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }

                    }
                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
    }

    public class ViewDialogConfirmDelete {
        public void showDialog(Context context, String id) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.view_dialog_confirm_delete);


            Button buttonDelete = dialog.findViewById(R.id.buttonDelete);
            Button buttonCancel = dialog.findViewById(R.id.buttonCancel);




            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();

                }
            });

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    databaseReference.child("MESSAGES").child(id).removeValue();
                    Toast.makeText(context, "Message Deleted Successfully!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                }
            });


        }
    }


}
