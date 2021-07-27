package id.ac.farz.mfarishanifw_uas.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.ac.farz.mfarishanifw_uas.DetailFriendPage;
import id.ac.farz.mfarishanifw_uas.R;
import id.ac.farz.mfarishanifw_uas.database.DataBaseHelper;
import id.ac.farz.mfarishanifw_uas.model.FriendModel;

public class ListFriendsAdapter extends RecyclerView.Adapter<id.ac.farz.mfarishanifw_uas.adapter.ListFriendsAdapter.ListViewHolder> {
    private ArrayList<FriendModel> listFriend;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    Context thiscontext;

    public ListFriendsAdapter(ArrayList<FriendModel> list) {
        this.listFriend = list;
    }

    @NonNull
    @Override
    public id.ac.farz.mfarishanifw_uas.adapter.ListFriendsAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType ) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_of_my_friend, viewGroup, false);
        return new ListViewHolder(view);
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        thiscontext = recyclerView.getContext();
    }

    @Override
    public void onBindViewHolder(@NonNull final ListFriendsAdapter.ListViewHolder holder, int position) {
        FriendModel friend = listFriend.get(position);
        //Glide.with(holder.itemView.getContext())
        //        .load(friend.getPhoto())
        //        .apply(new RequestOptions().override(55, 55))
        //        .into(holder.imgPhoto);
        holder.tvName.setText(friend.getName());
        holder.tvBod.setText(friend.getBod());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listFriend.get(holder.getAdapterPosition()));
            }
        });

        holder.btnMore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){//View v) {

                Intent moveIntent = new Intent( v.getContext(), DetailFriendPage.class);
                moveIntent.putExtra(DetailFriendPage.mname, listFriend.get(holder.getAdapterPosition()).getName());
                moveIntent.putExtra(DetailFriendPage.mdetail, listFriend.get(holder.getAdapterPosition()).getDetail());
                moveIntent.putExtra(DetailFriendPage.msex, listFriend.get(holder.getAdapterPosition()).getSex());
                moveIntent.putExtra(DetailFriendPage.mbod, listFriend.get(holder.getAdapterPosition()).getBod());
                moveIntent.putExtra(String.valueOf(DetailFriendPage.mphoto), listFriend.get(holder.getAdapterPosition()).getPhoto());

                v.getContext().startActivity(moveIntent);
                //Toast.makeText(holder.itemView.getContext(), "Favorite " +
                //        listFriend.get(holder.getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();

            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(thiscontext);

                String dihapus = listFriend.get(holder.getAdapterPosition()).getName();

                FriendModel clicked = (FriendModel) listFriend.get(holder.getAdapterPosition());//parent.getItemAtPosition(position);
                dataBaseHelper.deleteOne(clicked);
                Toast.makeText(thiscontext, dihapus + " telah dihapus, silahkan refresh halaman", Toast.LENGTH_SHORT).show();

            }

        });


    }

    @Override
    public int getItemCount() {
        return listFriend.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvBod;
        Button btnMore, btnDelete;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            //imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvBod = itemView.findViewById(R.id.tv_item_bod);
            btnMore = itemView.findViewById(R.id.btn_more);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(FriendModel data);
    }

}
