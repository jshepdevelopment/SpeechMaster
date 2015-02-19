package com.rickgoldman.speechmaster;

/**
 * Created by Jason Shepherd
 * Copyright 2015
 */

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Context;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class VoiceRecognitionActivity extends ActionBarActivity implements RecognitionListener {

    private TextToSpeech textSpeech;
    private TextView returnedText, textPhrase;
    private ToggleButton toggleButton;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "VoiceRecognitionActivity";
    private int challengeLevel = 0; //0 for beginner, 1 for intermediate, 2 for advanced level

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        textPhrase = (TextView) findViewById(R.id.textPhrase);
        returnedText = (TextView) findViewById(R.id.textView1);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton1);

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

        textPhrase.setText(R.string.next_please);

        textSpeech = new TextToSpeech(getApplicationContext(),
            new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        textSpeech.setLanguage(Locale.US);
                    }
                }
            });


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

        final Context context = this;

        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_about:

                Intent intent = new Intent(context, AboutScreen.class);
                startActivity(intent);

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

    public void makePhrase(View view) {

        Random rn = new Random();
        int phraseNum;

        //beginner
        if (challengeLevel==0) {
            phraseNum = rn.nextInt(49) + 1;

            int[] phraseArray = new int[]{R.string.b1, R.string.b2, R.string.b3,
                    R.string.b4, R.string.b5, R.string.b6, R.string.b7, R.string.b8,
                    R.string.b9, R.string.b10, R.string.b11, R.string.b12, R.string.b13,
                    R.string.b14, R.string.b15, R.string.b16, R.string.b17, R.string.b18,
                    R.string.b19, R.string.b20, R.string.b21, R.string.b22, R.string.b23,
                    R.string.b24, R.string.b25, R.string.b26, R.string.b27, R.string.b28,
                    R.string.b29, R.string.b30, R.string.b31, R.string.b32, R.string.b33,
                    R.string.b34, R.string.b35, R.string.b36, R.string.b37, R.string.b38,
                    R.string.b39, R.string.b40, R.string.b41, R.string.b42, R.string.b43,
                    R.string.b44, R.string.b45, R.string.b46, R.string.b47, R.string.b48,
                    R.string.b49, R.string.b50};

            textPhrase.setText(phraseArray[phraseNum]);
        }

        //intermediate
        if (challengeLevel==1) {
            phraseNum = rn.nextInt(49) + 1;
            int[] phraseArray = new int[]{R.string.i1, R.string.i2, R.string.i3,
                    R.string.i4, R.string.i5, R.string.i6, R.string.i7, R.string.i8,
                    R.string.i9, R.string.i10, R.string.i11, R.string.i12, R.string.i13,
                    R.string.i14, R.string.i15, R.string.i16, R.string.i17, R.string.i18,
                    R.string.i19, R.string.i20, R.string.i21, R.string.i22, R.string.i23,
                    R.string.i24, R.string.i25, R.string.i26, R.string.i27, R.string.i28,
                    R.string.i29, R.string.i30, R.string.i31, R.string.i32, R.string.i33,
                    R.string.i34, R.string.i35, R.string.i36, R.string.i37, R.string.i38,
                    R.string.i39, R.string.i40, R.string.i41, R.string.i42, R.string.i43,
                    R.string.i44, R.string.i45, R.string.i46, R.string.i47, R.string.i48,
                    R.string.i49, R.string.i50};

            textPhrase.setText(phraseArray[phraseNum]);
        }

        //advanced
        if (challengeLevel==2) {
            phraseNum = rn.nextInt(49) + 1;
            int[] phraseArray = new int[]{R.string.a1, R.string.a2, R.string.a3,
                    R.string.a4, R.string.a5, R.string.a6, R.string.a7, R.string.a8,
                    R.string.a9, R.string.a10, R.string.a11, R.string.a12, R.string.a13,
                    R.string.a14, R.string.a15, R.string.a16, R.string.a17, R.string.a18,
                    R.string.a19, R.string.a20, R.string.a21, R.string.a22, R.string.a23,
                    R.string.a24, R.string.a25, R.string.a26, R.string.a27, R.string.a28,
                    R.string.a29, R.string.a30, R.string.a31, R.string.a32, R.string.a33,
                    R.string.a34, R.string.a35, R.string.a36, R.string.a37, R.string.a38,
                    R.string.a39, R.string.a40, R.string.a41, R.string.a42, R.string.a43,
                    R.string.a44, R.string.a45, R.string.a46, R.string.a47, R.string.a48,
                    R.string.a49, R.string.a50};
            textPhrase.setText(phraseArray[phraseNum]);
        }
    }

    public void speakText(View view){
        String toSpeak = textPhrase.getText().toString();
        String firstLine = toSpeak.substring(0, toSpeak.indexOf("/"));

            textSpeech.speak(firstLine, TextToSpeech.QUEUE_FLUSH, null);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() { super.onPause(); }

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
                message = "Couldn't understand you. Try again.";
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
