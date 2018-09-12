package com.example.sadokmm.materialtest.fragments;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.sadokmm.materialtest.R;
import com.example.sadokmm.materialtest.adapters.AdapterForList;
import com.example.sadokmm.materialtest.objects.Personne;

import java.util.ArrayList;

import network.VolleySingleton;


public class FragmentView extends Fragment implements SwipeRefreshLayout.OnRefreshListener {



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ArrayList<Personne> myList=new ArrayList<>();
    private RequestQueue requestQueue;
    private VolleySingleton volleySingleton;
    private RecyclerView mlisteItem;
    private AdapterForList adapterForList;


    private OnFragmentInteractionListener mListener;

    private SwipeRefreshLayout mySwipe;

    public FragmentView() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentView newInstance(String param1, String param2) {
        FragmentView fragment = new FragmentView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_fragment_view, container, false);



            myList.add(new Personne("Sadok","Etudiant" , (BitmapFactory.decodeResource(getResources(),R.drawable.sadok)), 2.5f)) ;
            myList.add(new Personne("Ahmed","Lassaad" , (BitmapFactory.decodeResource(getResources(),R.drawable.amine)), 4.5f)) ;
            myList.add(new Personne("Ali","Etudian" , (BitmapFactory.decodeResource(getResources(),R.drawable.monta)), 0.5f)) ;
        myList.add(new Personne("Boha","Etudiant" , (BitmapFactory.decodeResource(getResources(),R.drawable.boha)), 2.5f)) ;
        myList.add(new Personne("Fedi","Lassaad" , (BitmapFactory.decodeResource(getResources(),R.drawable.fedi)), 4.5f)) ;
        myList.add(new Personne("mohsen","yejri" , (BitmapFactory.decodeResource(getResources(),R.drawable.p)), 0.5f)) ;


        adapterForList=new AdapterForList(getActivity()) ;
        adapterForList.setMyList(myList);
        mlisteItem=view.findViewById(R.id.listItemHits);
        mlisteItem.setLayoutManager(new LinearLayoutManager(getActivity()));

        mlisteItem.setAdapter(adapterForList);


        //swipe
        mySwipe=(SwipeRefreshLayout)view.findViewById(R.id.mySwipe);
        mySwipe.setOnRefreshListener(this);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onRefresh() {
        Toast.makeText(getContext(),"Refresh successfully done",Toast.LENGTH_SHORT).show();
        mySwipe.setRefreshing(false);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
