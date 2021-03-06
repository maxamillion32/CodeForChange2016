package com.example.bobbyranjan.ybsandroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bobbyranjan.ybsandroid.models.Patient;
import com.example.bobbyranjan.ybsandroid.service.AsyncResultListener;
import com.example.bobbyranjan.ybsandroid.service.AsyncResultTask;
import com.example.bobbyranjan.ybsandroid.service.PatientService;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewPatientFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewPatientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewPatientFragment extends Fragment implements AsyncResultListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String patientId;
    private String unusedForNow;

    private OnFragmentInteractionListener mListener;

    TextView mName;
    TextView mHusbandName;
    TextView mVillage;
    TextView mDOB;
    TextView mAge;

    View view;

    public ViewPatientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewPatientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewPatientFragment newInstance(String param1, String param2) {
        ViewPatientFragment fragment = new ViewPatientFragment();
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
            patientId = getArguments().getString(ARG_PARAM1);
            unusedForNow = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AsyncResultTask task = new AsyncResultTask(this);
        PatientService.getPatient(patientId,task);
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_view_patient, container, false);
        mName = (TextView)view.findViewById(R.id.VNPPatientName);
        mHusbandName = (TextView)view.findViewById(R.id.VNPHusbandName);
        mVillage = (TextView)view.findViewById(R.id.VNPVillage);
        mDOB = (TextView)view.findViewById(R.id.VNPDOB);
        mAge = (TextView)view.findViewById(R.id.VNPAge);
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
    public void processResult(Object result) {
        Patient patient = (Patient) result;
        mName.setText(patient.getName());
        mHusbandName.setText(patient.getHusbandsName());
        mVillage.setText(patient.getLocation());
        mDOB.setText(patient.getDateOfBirth());
        mAge.setText(String.valueOf(patient.getAge()));
    }

    @Override
    public void processResults(Object... results) {

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
