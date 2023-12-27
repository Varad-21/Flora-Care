package com.example.skincure;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class UserHomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_user_home, container, false);
//
        CardView FaceTreatment = rootView.findViewById(R.id.contributeCard);
        CardView HairTreatment = rootView.findViewById(R.id.practiceCard);
        CardView DietPlant = rootView.findViewById(R.id.learnCard);
        CardView smartnotifier = rootView.findViewById(R.id.interestsCard);
        CardView feedback = rootView.findViewById(R.id.helpCard);
        CardView Connectwithus = rootView.findViewById(R.id.settingsCard);
//
//
        FaceTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(requireContext(), "View Various Plant Information", Toast.LENGTH_SHORT).show();
//                Fragment fragment = new UserFeedbackFragment();
//                FragmentManager fragmentManager = getParentFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.fragment_container, fragment);
//                transaction.addToBackStack(null);
//                transaction.commit();

                Intent intent = new Intent(getActivity(),UserFaceMale.class);
                startActivity(intent);


            }
        });

        HairTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(requireContext(), "Suggestions", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),UserHairMale.class);
                startActivity(intent);
            }
        });
        DietPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(requireContext(), "Buy Fertilizers", Toast.LENGTH_SHORT).show();
                Fragment fragment = new UserFeedbackFragment();
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        smartnotifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(requireContext(), "Reminders", Toast.LENGTH_SHORT).show();
                Fragment fragment = new UserNotificationFragment();
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(requireContext(), "Add Your Plant Details", Toast.LENGTH_SHORT).show();
                Fragment fragment = new UserFeedbackFragment();
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        Connectwithus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(requireContext(), "Contact With us page", Toast.LENGTH_SHORT).show();
                Fragment fragment = new UserFeedbackFragment();
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return rootView;
    }


}
