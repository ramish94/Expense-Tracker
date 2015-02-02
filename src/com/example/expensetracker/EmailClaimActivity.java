package com.example.expensetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/*
 * 
 * 
 * 
 * 
 * 
 * 
 * STILL NEED TO COMPLETE
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */

public class EmailClaimActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email_claim);
	}
	
	public void onClickSendButton(View v) {
		EditText subjectLine = (EditText) findViewById(R.id.emailSubject);
		EditText emailAddress = (EditText) findViewById(R.id.emailAddress);
		
		String subject = subjectLine.getText().toString();
		String emailAd = emailAddress.getText().toString();

		final Intent email = new Intent(android.content.Intent.ACTION_SENDTO);
		
		  email.putExtra(Intent.EXTRA_EMAIL, new String[]{ emailAd});
		  email.putExtra(Intent.EXTRA_SUBJECT, subject);

		  email.setType("plain/text");

		  startActivity(Intent.createChooser(email, "Choose an Email client:"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.email_claim, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
