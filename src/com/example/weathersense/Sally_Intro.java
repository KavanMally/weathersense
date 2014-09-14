package com.example.weathersense;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;

public class Sally_Intro extends Activity implements
TextToSpeech.OnInitListener {
	/** Called when the activity is first created. */

	public static TextToSpeech tts;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.voice_recog);

		tts = new TextToSpeech(this, this);

		//NEW CHANGE! Fetches curr philly data b4 button press
		GetMethodEx m = new GetMethodEx();
		String temp = null;
		try {
			temp = m.getInternetData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.e("VOICEACTIVITY",temp);
		DataCollection.syncData(temp);

	}

	@Override
	public void onDestroy() {
		// Don't forget to shut down tts!
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}
		super.onDestroy();
	}

	@Override
	public void onInit(int status) {

		if (status == TextToSpeech.SUCCESS) {

			int result = tts.setLanguage(Locale.US);

			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("TTS", "This Language is not supported");
				tts.setSpeechRate((float) .8);
			} else {
				tts.speak("Hello, and welcome to Weather Sense! My name is Sally, and I'm here to inform you about the weather. Say temperature for the current temperature. Say details for a more detailed analysis on the current weather. Say forecast for a five day weather forecast. And finally, say clothing for clothing suggestions based on the current weather. Tap anywhere on the screen and start speaking after the beep!",TextToSpeech.QUEUE_FLUSH, null);
			}

		} else {
			Log.e("TTS", "Initilization Failed!");
		}

		Intent otherIntent = new Intent(this, VoiceActivity.class);
		startActivity(otherIntent);

	}
}