package com.example.gauthambt.fragmentpractise;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class Subtraction extends Fragment {

    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, enter;
    EditText eT;
    TextView tV1, tV2, tVTitle, tVResult, tVQuestionNumber, tvTimer, scoreTV;
    int i, j, result, enteredResult, questionNumber, score;
    CountDownTimer cdt;
    Boolean enterFlag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_subtraction, container, false);

        questionNumber = 1;
        score = 0;
        eT = (EditText) v.findViewById(R.id.ans);
        tV1 = (TextView) v.findViewById(R.id.n1);
        tV2 = (TextView) v.findViewById(R.id.n2);
        enter = (Button) v.findViewById(R.id.enter);
        tVResult = (TextView) v.findViewById(R.id.result);
        tVQuestionNumber = (TextView) v.findViewById(R.id.questionNumber);
        tvTimer = (TextView) v.findViewById(R.id.timer);
        scoreTV = (TextView) v.findViewById(R.id.scoreTV);
        b1 = (Button) v.findViewById(R.id.b1);
        b2 = (Button) v.findViewById(R.id.b2);
        b3 = (Button) v.findViewById(R.id.b3);
        b4 = (Button) v.findViewById(R.id.b4);
        b5 = (Button) v.findViewById(R.id.b5);
        b6 = (Button) v.findViewById(R.id.b6);
        b7 = (Button) v.findViewById(R.id.b7);
        b8 = (Button) v.findViewById(R.id.b8);
        b9 = (Button) v.findViewById(R.id.b9);
        b10 = (Button) v.findViewById(R.id.b10);

        cdt = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText("Time \n" + (int) (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                //questionNumber++;
                displayQuestionNumber();
                randomize();
            }
        };

        randomize();

        displayQuestionNumber();

        eT.setText("");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue(1);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue(2);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue(3);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue(4);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue(5);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue(6);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue(7);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue(8);
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue(9);
            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue(0);
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValue();
                return;
            }
        });

        return v;
    }

    private void displayQuestionNumber() {
        if (questionNumber > 10) {
            //displayScore();
            if (getContext() != null)
            {
                new AlertDialog.Builder(getActivity())
                        .setTitle("FINAL SCORE")
                        .setMessage("Score: " + score + "/10")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(getContext(), MainActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);

                            }
                        })
                        .show();
            }
        } else {
            String questionNo = "Question " + questionNumber + "/10";
            tVQuestionNumber.setText(questionNo);
            questionNumber++;
        }
    }

    public void randomize() {
        Random rand = new Random();

        i = rand.nextInt(10);

        j = rand.nextInt(10);

        if (i < j) {
            int temp;
            temp = i;
            i = j;
            j = temp;
        }

        result = i - j;

        displayNumbers();
    }

    public void displayNumbers() {
        tV1.setText(Integer.toString(i));
        tV2.setText(Integer.toString(j));

        cdt.start();
    }

    public void setValue(int input) {
        int x, y;
        String str;

        str = eT.getText().toString();

        if (str == null || "".equals(str)) {
            eT.setText(Integer.toString(input));

            singleCompare(input);
        } else {
            x = Integer.valueOf(str);

            y = x * 10 + input;

            str = Integer.toString(y);

            eT.setText(str);

            getValue();
        }
    }

    private void singleCompare(int input) {
        if (result == input)
            getValue();

        return;
    }

    public void getValue() {
        String str;
        str = eT.getText().toString();

        if (str == null || "".equals(str)) {
            tVResult.setText("Please Enter Value");
        } else {
            enteredResult = Integer.valueOf(str);
        }

        compareResults();
    }

    public void compareResults() {

        if (result == enteredResult) {
            tVResult.setText("Answer is Right");
            score++;
            displayQuestionNumber();
            displayScore();
            eT.setText("");
            randomize();
        } else {
            tVResult.setText("Answer is wrong.");
            eT.setText("");
            displayQuestionNumber();
            randomize();
        }

        displayScore();

    }

    public void displayScore() {
        String displayString;
        displayString = "Score is " + score + "/10";
        scoreTV.setText(displayString);
    }
}