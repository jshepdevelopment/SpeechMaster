package com.rickgoldman.speechmaster;

/**
 * Created by Jason Shepherd on 1/23/2015.
 */

import java.util.ArrayList;
import java.util.Random;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Context;
import android.graphics.PorterDuff;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class VoiceRecognitionActivity extends ActionBarActivity implements RecognitionListener {

    private TextView returnedText, textPhrase;
    private ToggleButton toggleButton;
    private Button randomButton;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "VoiceRecognitionActivity";
    private int challengeLevel = 0; //0 for beginner, 1 for intermediate, 2 for advanced level

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textPhrase = (TextView) findViewById(R.id.textPhrase);
        returnedText = (TextView) findViewById(R.id.textView1);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton1);
        randomButton = (Button) findViewById(R.id.randomButton);

        progressBar.setVisibility(View.INVISIBLE);
        speech = SpeechRecognizer.createSpeechRecognizer(this);
        speech.setRecognitionListener(this);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,
                "en");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                this.getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);

        toggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setIndeterminate(true);
                    speech.startListening(recognizerIntent);
                } else {
                    progressBar.setIndeterminate(false);
                    progressBar.setVisibility(View.INVISIBLE);
                    speech.stopListening();
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_about:
                aboutApp();
                return true;

            case R.id.action_beginner:
                setBeginner();
                return true;

            case R.id.action_intermediate:
                setIntermediate();
                return true;

            case R.id.action_advanced:
                setAdvanced();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setBeginner() {

        //Set everything to beginner "mode"
        challengeLevel = 0;

        ImageView image = (ImageView) findViewById(R.id.imageView1);
        image.setImageResource(R.drawable.blue_bg);

        Context context = getApplicationContext();
        CharSequence text = "Beginner";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void setIntermediate() {

        //Set everything to intermediate "mode"
        challengeLevel = 1;

        ImageView image = (ImageView) findViewById(R.id.imageView1);
        image.setImageResource(R.drawable.green_bg);

        Context context = getApplicationContext();
        CharSequence text = "Intermediate";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void setAdvanced() {

        //Set everything to advanced "mode"
        challengeLevel = 2;

        ImageView image = (ImageView) findViewById(R.id.imageView1);
        image.setImageResource(R.drawable.red_bg);

        Context context = getApplicationContext();
        CharSequence text = "Advanced";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void aboutApp() {
        //Do something with settings.
        Context context = getApplicationContext();
        CharSequence text = "Speech Master!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void makePhrase(View view) {

        Random rn = new Random();
        int phraseNum = rn.nextInt(7) + 1;

        //beginner
        if (challengeLevel==0) {
            int[] phraseArray = new int[]{R.string.b1, R.string.b2, R.string.b3,
                    R.string.b4, R.string.b5, R.string.b6, R.string.b7, R.string.b8};

            textPhrase.setText(phraseArray[phraseNum]);
        }

        //intermediate
        if (challengeLevel==1) {
            int[] phraseArray = new int[]{R.string.i1, R.string.i2, R.string.i3,
                    R.string.i4, R.string.i5, R.string.i6, R.string.i7, R.string.i8};

            textPhrase.setText(phraseArray[phraseNum]);
        }

        //advanced
        if (challengeLevel==2) {
            int[] phraseArray = new int[]{R.string.a1, R.string.a2, R.string.a3,
                    R.string.a4, R.string.a5, R.string.a6, R.string.a7, R.string.a8};

            textPhrase.setText(phraseArray[phraseNum]);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (speech != null) {
            speech.destroy();
            Log.i(LOG_TAG, "destroy");
        }

    }

    @Override
    public void onBeginningOfSpeech() {
        Log.i(LOG_TAG, "onBeginningOfSpeech");
        progressBar.setIndeterminate(false);
        progressBar.setMax(10);
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        Log.i(LOG_TAG, "onBufferReceived: " + buffer);
    }

    @Override
    public void onEndOfSpeech() {
        Log.i(LOG_TAG, "onEndOfSpeech");
        progressBar.setIndeterminate(true);
        toggleButton.setChecked(false);
    }

    @Override
    public void onError(int errorCode) {
        String errorMessage = getErrorText(errorCode);
        Log.d(LOG_TAG, "FAILED " + errorMessage);
        returnedText.setText(errorMessage);
        toggleButton.setChecked(false);
    }

    @Override
    public void onEvent(int arg0, Bundle arg1) {
        Log.i(LOG_TAG, "onEvent");
    }

    @Override
    public void onPartialResults(Bundle arg0) {
        Log.i(LOG_TAG, "onPartialResults");
    }

    @Override
    public void onReadyForSpeech(Bundle arg0) {
        Log.i(LOG_TAG, "onReadyForSpeech");
    }

    @Override
    public void onResults(Bundle results) {
        Log.i(LOG_TAG, "onResults");
        ArrayList<String> matches = results
                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String text = "";
        for (String result : matches)
            text += result + "\n";

        returnedText.setText(text);
    }

    @Override
    public void onRmsChanged(float rmsdB) {
        Log.i(LOG_TAG, "onRmsChanged: " + rmsdB);
        progressBar.setProgress((int) rmsdB);
    }

    public static String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Can't record audio. Try again.";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Press speak and try again.";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "You can't use this app! Try again.";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Sorry, there is something wrong with the network. Try again.";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Sorry, the network is taking too long. Try again.";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "Couldn't understand you, try again. Try again.";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "Busy with something else. Try again.";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "Sorry, the server can't hear you. Try again.";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "Speak louder please!";
                break;
            default:
                message = "Didn't understand. Try again.";
                break;
        }
        return message;
    }

}
