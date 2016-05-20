package com.example.gunka.kujapom;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by gunka on 17-May-16.
 */
public class BMIFragment extends Fragment implements TextToSpeech.OnInitListener{
    private TextToSpeech tts;
    private EditText EditText01;
    private EditText EditText02;
    private TextView EditText03;
    private TextView EditText04;
    private Button submit;
    //private MediaPlayer sound;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        tts = new TextToSpeech(getActivity(), this);
        View v = inflater.inflate(R.layout.fragment_bmi, container, false);
        EditText01 = (EditText)v.findViewById(R.id.EditText01);
        EditText02 = (EditText)v.findViewById(R.id.EditText02);
        EditText03 = (TextView)v.findViewById(R.id.EditText03);
        EditText04 = (TextView)v.findViewById(R.id.EditText04);
        submit = (Button)v.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(String.valueOf(EditText01.length()))==0||Integer.parseInt(String.valueOf(EditText02.length()))==0){
                    Log.i("cp", "null");
                    Toast.makeText(getActivity(), "กรุณากรอกให้ครบ", Toast.LENGTH_LONG).show();
                    return;
                }
                Float a1 = (float) (Float.parseFloat(EditText01.getText().toString()));
                Float b1 = (float) (Float.parseFloat(EditText02.getText().toString()));
                Float c1 = (float) (b1 * b1) / 10000;
                Float total = a1 / c1;


                if (total < 18.5) {
                    EditText04.setText("ผอมมากเกินไปนะตัวเอง");
                    tts.speak("ผอมมากเกินไปนะตัวเอง", TextToSpeech.QUEUE_FLUSH, null);

                    //sound = MediaPlayer.create(getActivity(), R.raw.thin);
                    //sound.start();
                } else if (total <= 23.4) {
                    EditText04.setText("ปกติดีจ่ะ");
                    tts.speak("ปกติดีจ่ะ", TextToSpeech.QUEUE_FLUSH, null);

                    // sound = MediaPlayer.create(getActivity(), R.raw.normal);
                    //sound.start();
                } else if (total <= 28.4) {
                    EditText04.setText("น้ำหนักตัวมากเกินไปแล้ว!");
                    tts.speak("น้ำหนักตัวมากเกินไปแล้ว", TextToSpeech.QUEUE_FLUSH, null);

                    // sound = MediaPlayer.create(getActivity(), R.raw.fatover);
                   // sound.start();
                } else if (total <= 34.9) {
                    EditText04.setText("อ้วนมากไปแล้วนะ! ลดน้ำหนักด่วน (ระดับ 1)");
                    tts.speak("อ้วนมากไปแล้วนะ! ลดน้ำหนักด่วน (ระดับ 1)", TextToSpeech.QUEUE_FLUSH, null);

                    //sound = MediaPlayer.create(getActivity(), R.raw.fatburn);
                    //sound.start();
                } else if (total <= 39.9) {
                    EditText04.setText("อ้วนมาก หยุดกินไปเลยเธอ (ระดับ 2)");
                    tts.speak("อ้วนมาก หยุดกินไปเลยเธอ (ระดับ 2)", TextToSpeech.QUEUE_FLUSH, null);

                    //sound = MediaPlayer.create(getActivity(), R.raw.fatstopeat);
                    //sound.start();
                } else if (total > 40) {
                    EditText04.setText("อีกนิดตัวจะแตกแล้วนะ อ้วนแล้วยังไม่เจียม!");
                    tts.speak("อีกนิดตัวจะแตกแล้วนะ อ้วนแล้วยังไม่เจียม!", TextToSpeech.QUEUE_FLUSH, null);

                    //sound = MediaPlayer.create(getActivity(), R.raw.fatboom);
                    //sound.start();
                }

                String BMI = String.format("%.02f", total);

                EditText03.setText(BMI.toString()); //ค่า BMI ที่โชว์หน้าแรก
            }
        });
        return v;
    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS) {
            tts.setLanguage(new Locale("th"));
            //tts.speak("", TextToSpeech.QUEUE_FLUSH, null);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        tts.shutdown();
    }

}
