package com.example.employeesinformationmanager.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.example.employeesinformationmanager.R;
import com.example.employeesinformationmanager.models.EmployeeModel;

import java.util.ArrayList;
import java.util.List;

public class InformationListAdapter extends RecyclerView.Adapter<InformationListAdapter.InformationListViewHolder> {
    private List<EmployeeModel> employeesArray=new ArrayList<>();;
    private Context context;

    public InformationListAdapter(List<EmployeeModel> employeesArray, Context context) {
        this.employeesArray = employeesArray;
        this.context = context;
    }

    @NonNull
    @Override
    public InformationListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        InformationListViewHolder informationListViewHolder;
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        informationListViewHolder = new InformationListAdapter.InformationListViewHolder( inflater.inflate(R.layout.list_item , parent,false));
        return informationListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InformationListViewHolder holder, int position) {


        holder.nameTextView.setText(employeesArray.get(position).getName());
        holder.emailTextView.setText(employeesArray.get(position).getEmail());

        //img
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG123", "onClick:editButton ");
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG123", "onClick:deleteButton ");
            }
        });

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG123", "onClick: ");
            }
        });


    }

    @Override
    public int getItemCount() {
        return employeesArray.size();
    }

    public class InformationListViewHolder extends RecyclerView.ViewHolder {

      private   TextView nameTextView;
      private   TextView emailTextView;
      private   Button  editButton;
      private   Button  deleteButton;

       // ImageView img;
        View layoutView;
        ConstraintLayout constraintLayout;
        public InformationListViewHolder(@NonNull View itemView) {
            super(itemView);
            /// find by id
            layoutView = itemView;
            constraintLayout =itemView.findViewById(R.id.constrain_list_item);
            nameTextView  = itemView.findViewById(R.id.text_view_name);
            emailTextView  = itemView.findViewById(R.id.text_view_email);
            editButton  = itemView.findViewById(R.id.btn_edit);
            deleteButton  = itemView.findViewById(R.id.btn_delete);
              //  img =  itemView.findViewById(R.id.imageView) ;
        }
    }

}
