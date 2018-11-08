package com.rah.mcpro;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.viewholder> {
    SortedList<Room> list;
    Context context;

    public adapter(Context context) {
        this.context=context;
        list = new SortedList<>(Room.class, new SortedList.Callback<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                int i= o1.getDist_in_km().compareTo(o2.getDist_in_km());
//                Log.d("myTag", ""+o1.getDist_in_km()+" "+o2.getDist_in_km()+"="+i+"");
                if(i==0){
                    return -1;
                }
                else {
                    return i;
                }
            }

            @Override
            public boolean areContentsTheSame(Room oldItem, Room newItem) {
                return oldItem.getDist_in_km().equals(newItem.getDist_in_km());
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areItemsTheSame(Room item1, Room item2) {
                return item1.getDist_in_km().equals(item2.getDist_in_km());
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });
    }
    public void addAll(List<Room> rooms) {
        list.beginBatchedUpdates();
        for (int i = 0; i < rooms.size(); i++) {
            list.add(rooms.get(i));
        }
        list.endBatchedUpdates();
    }
//    public Room get(int position) {
//        return list.get(position);
//    }
//
//    public void clear() {
//        list.beginBatchedUpdates();
//        //remove items at end, to avoid unnecessary array shifting
//        while (list.size() > 0) {
//            list.removeItemAt(list.size() - 1);
//        }
//        list.endBatchedUpdates();
//    }


    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent, false);
        return new viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(viewholder holder, int position) {
        Room room = list.get(position);
        holder.textView2.setText(room.getDist_in_km());
        holder.textView.setText(room.getGroundName());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Chatrooms.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewholder extends RecyclerView.ViewHolder{
        Button button;
        TextView textView,textView2;
        CardView cardView;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            button=itemView.findViewById(R.id.join);
            textView=itemView.findViewById(R.id.groundname);
            textView2=itemView.findViewById(R.id.ground_distance);
            cardView=itemView.findViewById(R.id.card);
        }
    }
}
