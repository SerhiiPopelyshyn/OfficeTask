package com.example.officetask;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<ContactObject> contactObject;
    public MyRecyclerViewAdapter(Context context, ArrayList<ContactObject> ContactObject,
                                 RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.contactObject = ContactObject;
        this.recyclerViewInterface = recyclerViewInterface;
    }
    @NonNull
    @Override
    public MyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row,parent,false);
        return new MyRecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }
    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.nameContact.setText(contactObject.get(position).getNameContact());
        holder.numberContact.setText(contactObject.get(position).getNumberContact());
        holder.emailContact.setText(contactObject.get(position).getEmailContact());
    }
    @Override
    public int getItemCount() {
        return contactObject.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nameContact, numberContact, emailContact;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            nameContact = itemView.findViewById(R.id.nameContact);
            numberContact = itemView.findViewById(R.id.numberContact);
            emailContact = itemView.findViewById(R.id.emailContact);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    if(recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }

                }
            });

        }
    }
}
