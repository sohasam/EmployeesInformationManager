package com.example.employeesinformationmanager.ui.adapter;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeesinformationmanager.data.roomdatabase.entities.Employee;
import com.example.employeesinformationmanager.R;
import com.example.employeesinformationmanager.contracts.IHomeContract;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class InformationListAdapter extends RecyclerView.Adapter<InformationListAdapter.InformationListViewHolder> {
    private List<Employee> employeesArray=new ArrayList<>();;
    private Context context;
    private IHomeContract.IHomeView  homeView;

    public InformationListAdapter(List<Employee> employeesArray, Context context, IHomeContract.IHomeView  homeView ) {
        this.employeesArray = employeesArray;
        this.context = context;
        this.homeView=homeView;
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

final Employee employee  =employeesArray.get(position);
        holder.nameTextView.setText(employee.getName());
        holder.emailTextView.setText(employee.getEmail());

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeView.showDetailsView(employee);

            }
        });
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeView.showEditView(employee);
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeView.showDeleteDialogue(employee);

            }
        });

        if(employee.getImgUri()!=null) {
         //   if (checkPermissionREAD_EXTERNAL_STORAGE(context)) {

            Bitmap bitmap = null;
            try {
                bitmap = getBitmapFromUri(Uri.parse(new File(employee.getImgUri()).toString()));

            } catch (IOException e) {
                e.printStackTrace();
            }
//                try {
//                    bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.parse(employee.getImgUri()));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

            holder.img.setImageBitmap(bitmap);
            //   getBitmap(context.getContentResolver(),Uri.parse(employee.getImgUri())));
            //}
        }
        //img




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

       ImageView img;
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
         img =  itemView.findViewById(R.id.item_img_view) ;
        }
    }

    /////////////////////////////////////////
    private Bitmap getBitmapFromUri(Uri uri) throws IOException {

        ParcelFileDescriptor parcelFileDescriptor =
                context.getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }
///
public static final Bitmap getBitmap(ContentResolver cr, Uri url)
        throws FileNotFoundException, IOException {
    InputStream input = cr.openInputStream(url);
    Bitmap bitmap = BitmapFactory.decodeStream(input);
    input.close();
    return bitmap;
}
}
/////////////////////////////////////////////////////////////////////////////////////