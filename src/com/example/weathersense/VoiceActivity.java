package com.example.weathersense;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class VoiceActivity extends Activity implements TextToSpeech.OnInitListener{


	private static final int REQUEST_CODE = 1234;
	private ListView wordsList;
	private TextToSpeech tts1;
	private String temp;


	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.voice_recog);
		Log.d("VOICEACTIVITY", "VoiceActivity Initiated");
		ImageButton speakButton = (ImageButton) findViewById(R.id.speakButton);
		tts1 = new TextToSpeech(this, this);
		wordsList = (ListView) findViewById(R.id.list);
		// Disable button if no recognition service is present
		PackageManager pm = getPackageManager();
		List<ResolveInfo> activities = pm.queryIntentActivities(
				new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
		if (activities.size() == 0)
		{
			speakButton.setEnabled(false);
			//  speakButton.setText("Recognizer not present");
		}
	}
	@Override
	public void onDestroy() {
		// Don't forget to shut down tts!
		if (tts1 != null) {
			tts1.stop();
			tts1.shutdown();
		}
		super.onDestroy();
	}
	//Handle the action of the button being clicked.
	public void speakButtonClicked(View v) throws Exception
	{
		Sally_Intro.tts.stop();
		/*
		GetMethodEx m = new GetMethodEx();
		temp = m.getInternetData();
		Log.e("VOICEACTIVITY",temp);
		DataCollection.syncData(temp);
		*/
		
		GetForecastMethod g = new GetForecastMethod();
		temp = g.getInternetData();
		Log.e("VOICEACTIVITY", temp);
		ForecastDataCollection.syncData(temp);
		startVoiceRecognitionActivity();
	}
	//Fire an intent to start the voice recognition activity.
	private void startVoiceRecognitionActivity()
	{
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
				RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say a WeatherSense command!");
		startActivityForResult(intent, REQUEST_CODE);
	}

	//Handle the results from the voice recognition activity.
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (requestCode == REQUEST_CODE && resultCode == RESULT_OK)
		{
			// Populate the wordsList with the String values the recognition engine thought it heard
			ArrayList<String> matches = data.getStringArrayListExtra(
					RecognizerIntent.EXTRA_RESULTS);
			wordsList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
					matches));
			if ("temperature".contentEquals((String) wordsList.getAdapter().getItem(0))) {
				tts1.speak("The current temperature is " + DataCollection.getTemp() + "degrees.",TextToSpeech.QUEUE_FLUSH, null);
			} 
			else if ("Temperature".contentEquals((String) wordsList.getAdapter().getItem(0))) {
				tts1.speak("The current temperature is " + DataCollection.getTemp() + "degrees.",TextToSpeech.QUEUE_FLUSH, null);
			}
		
			else if ("Hello".contentEquals((String) wordsList.getAdapter().getItem(0))) {
				tts1.speak("Hi. How are you?", TextToSpeech.QUEUE_FLUSH, null);
			}
			else if ("hello".contentEquals((String) wordsList.getAdapter().getItem(0))) {
				tts1.speak("Hi. How are you?", TextToSpeech.QUEUE_FLUSH, null);
			}
			else if ("Hi".contentEquals((String) wordsList.getAdapter().getItem(0))) {
				tts1.speak("Hi. How are you?", TextToSpeech.QUEUE_FLUSH, null);
			}
			else if ("hi".contentEquals((String) wordsList.getAdapter().getItem(0))) {
				tts1.speak("Hi. How are you?", TextToSpeech.QUEUE_FLUSH, null);
			}
			else if ("good".contentEquals((String) wordsList.getAdapter().getItem(0))) {
				tts1.speak("Glad to hear it.", TextToSpeech.QUEUE_FLUSH, null);
			}
			else if ("Good".contentEquals((String) wordsList.getAdapter().getItem(0))) {
				tts1.speak("Glad to hear it.", TextToSpeech.QUEUE_FLUSH, null);
			}
			else if ("Thanks".contentEquals((String) wordsList.getAdapter().getItem(0))) {
				tts1.speak("No problem. Now onto the weather.", TextToSpeech.QUEUE_FLUSH, null);
			}
			else if ("thanks".contentEquals((String) wordsList.getAdapter().getItem(0))) {
				tts1.speak("No problem. Now onto the weather.", TextToSpeech.QUEUE_FLUSH, null);
			}
			else if ("thank you".contentEquals((String) wordsList.getAdapter().getItem(0))) {
				tts1.speak("No problem. Now onto the weather.", TextToSpeech.QUEUE_FLUSH, null);
			}
			else if ("Thank you".contentEquals((String) wordsList.getAdapter().getItem(0))) {
				tts1.speak("No problem. Now onto the weather.", TextToSpeech.QUEUE_FLUSH, null);
			}
			else if ("details".contentEquals((String) wordsList.getAdapter().getItem(0))) {
				tts1.speak("The minimum temperature of the day is " + DataCollection.getTempMin() + "degrees, and the maximum is " + DataCollection.getTempMax() + "degrees. The wind is moving at a speed of " + DataCollection.getSpeed() + "miles per hour. The atmospheric pressure measures at " + DataCollection.getPressure() + "hectopascals, and the outdoor humidity is " + DataCollection.getHum() + "percent.", TextToSpeech.QUEUE_FLUSH, null);
			}
			else if ("Details".contentEquals((String) wordsList.getAdapter().getItem(0))) {
				tts1.speak("The minimum temperature of the day is " + DataCollection.getTempMin() + "degrees, and the maximum is " + DataCollection.getTempMax() + "degrees. The wind is moving at a speed of " + DataCollection.getSpeed() + "miles per hour. The atmospheric pressure measures at " + DataCollection.getPressure() + "hectopascals, and the outdoor humidity is " + DataCollection.getHum() + "percent.", TextToSpeech.QUEUE_FLUSH, null);
			}
			else if ("3".contentEquals((String) wordsList.getAdapter().getItem(0))) {
				tts1.speak("This will give you a five day weather forecast.",TextToSpeech.QUEUE_FLUSH, null);
			}
			else if ("forecast".contentEquals((String) wordsList.getAdapter().getItem(0))) {
				tts1.speak("This will give you a more detailed analysis of the current weather.",TextToSpeech.QUEUE_FLUSH, null);
			}
			else if ("Forecast".contentEquals((String) wordsList.getAdapter().getItem(0))) {
				tts1.speak("This will give you a more detailed analysis of the current weather.",TextToSpeech.QUEUE_FLUSH, null);
			}
			else if ("clothing".contentEquals((String) wordsList.getAdapter().getItem(0))) {
				if (DataCollection.getTemp()>=75 && DataCollection.getTemp()<90) {
					tts1.speak("Well, since it's currently " + DataCollection.getTemp() + "degrees outside, a short sleeved shirt and shorts would be a good idea.",TextToSpeech.QUEUE_FLUSH, null);
				}
				else if (DataCollection.getTemp()>=90) {
					tts1.speak("Well, it's " + DataCollection.getTemp() + "degrees outside, so a short sleeved shirt or tank top with shorts would be a good idea considering the heat.", TextToSpeech.QUEUE_FLUSH, null);
				}
				if (DataCollection.getTemp()>=65 && DataCollection.getTemp()<75) {
					tts1.speak("Well, it's " + DataCollection.getTemp() + "degrees outside right now, so shorts with a short sleeved or long sleeved shirt would be great, but think about bringing a sweatshirt or sweater along with you.", TextToSpeech.QUEUE_FLUSH, null);
				}
				else if (DataCollection.getTemp()>=55 && DataCollection.getTemp()<65) {
					tts1.speak("Well, it's " + DataCollection.getTemp() + "degrees outside right now, so I'd suggest a short sleeved or long sleeved shirt with jeans or long pants, plus a sweatshirt or light jacket.", TextToSpeech.QUEUE_FLUSH, null);
				}
				else if (DataCollection.getTemp()>=45 && DataCollection.getTemp()<55) {
					tts1.speak("Well, it's " + DataCollection.getTemp() + "degrees outside now, so you should probably wear a long sleeved shirt with jeans or long pants and a jacket or coat.", TextToSpeech.QUEUE_FLUSH, null);
				}
				else if (DataCollection.getTemp()<45) {
					tts1.speak("Well, it's " + DataCollection.getTemp() + "degrees outside. That's pretty chilly! I'd definitely recommend long pants with a long sleeved shirt with a sweater or sweatshirt and a heavy coat. Maybe add a hat too!", TextToSpeech.QUEUE_FLUSH, null);
				}
			}
			else if ("Clothing".contentEquals((String) wordsList.getAdapter().getItem(0))) {
				if (DataCollection.getTemp()>=75 && DataCollection.getTemp()<90) {
					tts1.speak("Well, since it's currently " + DataCollection.getTemp() + "degrees outside, a short sleeved shirt and shorts would be a good idea.",TextToSpeech.QUEUE_FLUSH, null);
				}
				else if (DataCollection.getTemp()>=90) {
					tts1.speak("Well, it's " + DataCollection.getTemp() + "degrees outside, so a short sleeved shirt or tank top with shorts would be a good idea considering the heat.", TextToSpeech.QUEUE_FLUSH, null);
				}
				if (DataCollection.getTemp()>=65 && DataCollection.getTemp()<75) {
					tts1.speak("Well, it's " + DataCollection.getTemp() + "degrees outside right now, so shorts with a short sleeved or long sleeved shirt would be great, but think about bringing a sweatshirt or sweater along with you.", TextToSpeech.QUEUE_FLUSH, null);
				}
				else if (DataCollection.getTemp()>=55 && DataCollection.getTemp()<65) {
					tts1.speak("Well, it's " + DataCollection.getTemp() + "degrees outside right now, so I'd suggest a short sleeved or long sleeved shirt with jeans or long pants, plus a sweatshirt or light jacket.", TextToSpeech.QUEUE_FLUSH, null);
				}
				else if (DataCollection.getTemp()>=45 && DataCollection.getTemp()<55) {
					tts1.speak("Well, it's " + DataCollection.getTemp() + "degrees outside now, so you should probably wear a long sleeved shirt with jeans or long pants and a jacket or coat.", TextToSpeech.QUEUE_FLUSH, null);
				}
				else if (DataCollection.getTemp()<45) {
					tts1.speak("Well, it's " + DataCollection.getTemp() + "degrees outside. That's pretty chilly! I'd definitely recommend long pants with a long sleeved shirt with a sweater or sweatshirt and a heavy coat. Maybe add a hat too!", TextToSpeech.QUEUE_FLUSH, null);
				}
			}
			else {
				tts1.speak("Can you repeat that? I might have misunderstood you because I don't think that command was a menu item. Tap the screen to try again.",TextToSpeech.QUEUE_FLUSH, null);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onInit(int arg0) {
		// TODO Auto-generated method stub

	}


	//@Override
	//public String toString(){

	//	}

	public static String readFileToString(File c)throws IOException{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(c));
			StringBuffer str = new StringBuffer();
			String line = br.readLine();
			while (line != null)
			{
				str.append(line);
				str.append("\n");
				line = br.readLine();
			}

			return str.toString();
		}
		finally{

		}
	}

	public static String convertStreamToString(InputStream is) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line).append("\n");
		}
		return sb.toString();
	}

}
