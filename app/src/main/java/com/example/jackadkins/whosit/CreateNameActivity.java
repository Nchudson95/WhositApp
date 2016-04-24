package com.example.jackadkins.whosit;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateNameActivity extends AppCompatActivity {

    class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.doneQNameButton:
                    enterQuizName();
                    launchCreateQuizAct();
                    break;
            }
        }
    }


    private EditText enterQuizNameEditText;
    private Button qnDoneButton;
    private String quizName;
    private String[] answersArray = new String[80];
    private String[] resultsArrayMap = new String[80];
    private String[] questionArray = new String[20];
    private String[] resultsArray = new String[8];
    private ButtonListener mButtonListener = new ButtonListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_name);

        enterQuizNameEditText = (EditText) findViewById(R.id.enterQuizNameEditText);
        qnDoneButton = (Button) findViewById(R.id.doneQNameButton);
        qnDoneButton.setOnClickListener(mButtonListener);

        quizName = getIntent().getStringExtra("quizName");
        if(quizName != null){
            enterQuizNameEditText.setText(quizName);
        }
        questionArray = getIntent().getStringArrayExtra("questionArray");
        answersArray = getIntent().getStringArrayExtra("answerArray");
        resultsArrayMap = getIntent().getStringArrayExtra("resultsMap");
        resultsArray = getIntent().getStringArrayExtra("resultArray");

        enterQuizNameEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    enterQuizName();
                }
                return false;

            }
        });

        enterQuizNameEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_ENTER:
                    case KeyEvent.KEYCODE_DPAD_CENTER:

                        enterQuizName();

                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(enterQuizNameEditText.getWindowToken(), 0);
                        return true;

                }
                return false;
            }
        });
    }

    private void enterQuizName(){
        quizName = enterQuizNameEditText.getText().toString();
    }

    private void launchCreateQuizAct(){
        Intent createQuizIntent = new Intent(this, CreateQuizActivity.class);
        createQuizIntent.putExtra("quizName", quizName);
        createQuizIntent.putExtra("answerArray", answersArray);
        createQuizIntent.putExtra("resultsMap", resultsArrayMap);
        createQuizIntent.putExtra("questionArray", questionArray);
        createQuizIntent.putExtra("resultArray", resultsArray);
        startActivity(createQuizIntent);
    }

}
