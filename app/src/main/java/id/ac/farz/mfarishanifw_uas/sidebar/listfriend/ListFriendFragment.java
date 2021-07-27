package id.ac.farz.mfarishanifw_uas.sidebar.listfriend;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import id.ac.farz.mfarishanifw_uas.FriendRegistActivity;
import id.ac.farz.mfarishanifw_uas.R;
import id.ac.farz.mfarishanifw_uas.adapter.ListFriendsAdapter;
import id.ac.farz.mfarishanifw_uas.database.DataBaseHelper;
import id.ac.farz.mfarishanifw_uas.model.FriendModel;
//import id.ac.farz.mfarishanifw_uas.adapter.AdapterAlumni;

public class ListFriendFragment extends Fragment {
    private RecyclerView rvFriends;
    private ArrayList<FriendModel> list = new ArrayList<>();
    //ListFriendsAdapter friend = new ListFriendsAdapter.OnItemClickCallback();

    private String title = "Friend List";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_friend,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fab = getView().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FriendRegistActivity.class);
                startActivity(intent);
            }
        });
        int count = new DataBaseHelper(getActivity()).checkEmpty();if(count == 0){ defaultFriend(); }
        else {
            final ArrayList<FriendModel> dlist = new DataBaseHelper(getActivity()).getListData();
            rvFriends = view.findViewById(R.id.rv_friends);
            rvFriends.setHasFixedSize(true);
            list.addAll(dlist);
            showList();
        }
    }

    private void showList() {
        rvFriends.setLayoutManager(new LinearLayoutManager(getContext()));
        ListFriendsAdapter listFriendsAdapter = new ListFriendsAdapter(list);
        rvFriends.setAdapter(listFriendsAdapter);

        /*listFriendsAdapter.setOnItemClickCallback(new OnItemClickListener() {
            @Override
            public void onItemClicked(FriendModel data) {
                showSelectedFriend(data);
            }});*/
    }

    public void defaultFriend(){
        FriendModel friendModel = null;

        try {
            friendModel = new FriendModel(-1, "NPC", "Genderless", "-", "a Filler"); //id -1 itu sementara untuk default
        } catch (Exception e) {

        }

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity()); //constructor DBH butuh param, kasih konteks sesuai di class DataBaseHelper

        boolean sukses = dataBaseHelper.addOne(friendModel);


    }

}