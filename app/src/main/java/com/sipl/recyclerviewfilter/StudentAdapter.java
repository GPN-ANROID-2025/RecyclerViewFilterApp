package com.sipl.recyclerviewfilter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> implements Filterable {


    ArrayList<Student>  originalList;
    ArrayList<Student> filteredList;

    public StudentAdapter(ArrayList<Student> originalList) {
        this.originalList = originalList;
        this.filteredList=originalList;
    }

    @NonNull
    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_student,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder holder, int position) {

        holder.tvName.setText(filteredList.get(position).getName());
        holder.tvCity.setText(filteredList.get(position).getCity());


    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String qwery=charSequence.toString().toLowerCase().trim();

                ArrayList<Student> tempList=new ArrayList<>();


                if(qwery.length()<1)
                {
                    tempList.addAll(originalList);
                }else{

                    for(Student student:originalList)
                    {

                        if(student.getName().toLowerCase().contains(qwery) || student.getCity().toLowerCase().contains(qwery) )
                        {
                            tempList.add(student);
                        }

                    }

                }


                FilterResults filterResults=new FilterResults();
                filterResults.values=tempList;
                filteredList=tempList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                notifyDataSetChanged();

            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvCity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCity=itemView.findViewById(R.id.tvCity);
            tvName=itemView.findViewById(R.id.tvName);
        }
    }
}
