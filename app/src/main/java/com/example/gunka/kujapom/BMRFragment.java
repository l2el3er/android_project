package com.example.gunka.kujapom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


public class BMRFragment extends Fragment {
    private RadioButton male, female;
    private EditText height, weight, age;
    private RadioButton radAc1,radAc2, radAc3, radAc4, radAc5 ;
    private Button calculate;
    private Dialog resultDialog;
    double myheight, myweight, myage;
    double rateBmrMale, rateBmrFemale, bmrMale, totalMale, bmrFemale, totalFemale;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bmr, container, false);


        male = ( RadioButton ) v.findViewById(R.id.radio_male);
        female = ( RadioButton ) v.findViewById(R.id.radio_female);
        //male.setChecked ( true );
        height = (EditText)v.findViewById(R.id.edtHeight);
        weight = (EditText)v.findViewById(R.id.edtWeight);
        age = (EditText)v.findViewById(R.id.edtAge);

        radAc1 = ( RadioButton ) v.findViewById(R.id.radActivity1);
        radAc2 = ( RadioButton ) v.findViewById(R.id.radActivity2);
        radAc3 = ( RadioButton ) v.findViewById(R.id.radActivity3);
        radAc4 = ( RadioButton ) v.findViewById(R.id.radActivity4);
        radAc5 = ( RadioButton ) v.findViewById(R.id.radActivity5);


        calculate = (Button) v.findViewById(R.id.btnCalculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myheight = (double) (Double.parseDouble(height.getText().toString()));
                myweight = (double) (Double.parseDouble(weight.getText().toString()));
                myage = (double) (Double.parseDouble(age.getText().toString()));


                if (male.isChecked()) {
                    bmrMale = (int) (13.7 * myweight) + (5 * myheight) - (6.8 * myage);
                    totalMale = (int) 66 + bmrMale;
                    if (radAc1.isChecked()) {
                        rateBmrMale = (int) (totalMale * 1.2);
                    } else if (radAc2.isChecked()) {
                        rateBmrMale = (int) (totalMale * 1.375);
                    } else if (radAc3.isChecked()) {
                        rateBmrMale = (int) (totalMale * 1.55);
                    } else if (radAc4.isChecked()) {
                        rateBmrMale = (int) (totalMale * 1.725);
                    } else if (radAc5.isChecked()) {
                        rateBmrMale = (int) (totalMale * 1.9);
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("BMR (Male) Results");
                    builder.setMessage("พลังงานที่จำเป็นพื้นฐาน: " + totalMale + " Kcal." + "\nพลังงานที่ใช้ในแต่ละวัน : " + rateBmrMale + " Kcal.");
                    //builder.setMessage("พลังงานที่ใช้ในแต่ละวัน : " + rateBmrMale + " Kcal.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //finish();
                        }
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                } else if (female.isChecked()) {
                    bmrFemale = (int) (9.6 * myweight) + (1.8 * myheight) - (4.7 * myage);
                    totalFemale = (int) 665 + bmrFemale;
                    if (radAc1.isChecked()) {
                        rateBmrFemale = (int) (totalFemale * 1.2);
                    } else if (radAc2.isChecked()) {
                        rateBmrFemale = (int) (totalFemale * 1.375);
                    } else if (radAc3.isChecked()) {
                        rateBmrFemale = (int) (totalFemale * 1.55);
                    } else if (radAc4.isChecked()) {
                        rateBmrFemale = (int) (totalFemale * 1.725);
                    } else if (radAc5.isChecked()) {
                        rateBmrFemale = (int) (totalFemale * 1.9);
                    }
                    //}

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("BMR (Female) Results");
                    builder.setMessage("พลังงานที่จำเป็นพื้นฐาน: " + totalFemale + " Kcal." + "\nพลังงานที่ใช้ในแต่ละวัน : " + rateBmrFemale + " Kcal.");
                    //builder.setMessage("พลังงานที่ใช้ในแต่ละวัน : " + rateBmrMale + " Kcal.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //finish();
                        }
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

            }
        });
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
