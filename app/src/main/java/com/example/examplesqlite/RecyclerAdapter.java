//package com.example.examplesqlite;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.Random;
//
//public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
//
//    private Random random;
//
//    public RecyclerAdapter(int seed) {
//        this.random = new Random(seed);
//    }
//
//    @Override
//    public int getItemViewType(final int position) {
//        return R.layout.list_display;
//    }
//
//    @NonNull
//    @Override
//    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        // Inflate the layout for each item and return a new ViewHolder object
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_display, parent, false);
//        return new RecyclerViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
//
//        holder.name.setText(String.valueOf(random.nextInt()));
//        //holder.name.setText("User Name ");
//        //holder.details.setText("User Detail ");
//    }
//
//    @Override
//    public int getItemCount() {
//        return 10;
//    }
//
//    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
//
//        TextView name, details;
//        public RecyclerViewHolder(@NonNull View itemView) {
//            super(itemView);
//
////            name = itemView.findViewById(R.id.name_display);
////            details = itemView.findViewById(R.id.detail_display);
//        }
//    }
//}
