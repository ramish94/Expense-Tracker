/*	
 * 	Expense Tracker: An application that tracks expense reports and items.
    Copyright (C) 2015 Ramish Syed

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.example.expensetracker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class EmailClaimActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email_claim);
		
		// Initialize ClaimList and ExpenseItemListManager for serializability
		ClaimListManager.initManager(this.getApplicationContext());
		ExpenseItemListManager.initManager(this.getApplicationContext());
		
		Bundle extras = getIntent().getExtras();
		final Claim claim = (Claim) extras.get("claim");
		
		final String name = claim.getName().toString();
		final String startDate = claim.getStartDate().toString();
		final String endDate = claim.getEndDate().toString();
		final String description = claim.getDescription().toString();
		
        final Button button = (Button) findViewById(R.id.sendEmailButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickSendButton(name, startDate, endDate, description);
            }
        });
	}
	
	public void onClickSendButton(String name, String startDate, String endDate, String description) {
		
		EditText subjectLine = (EditText) findViewById(R.id.emailSubject);
		EditText emailAddress = (EditText) findViewById(R.id.emailAdd);
		
		String subject = subjectLine.getText().toString();
		String emailAd = emailAddress.getText().toString();
		String emailText = "CLAIM" + 
		"\n" + "\n" + 
		"Name: " + name + "\n" + 
		"Start Date: " + startDate + "\n" + 
		"End Date: " + endDate + "\n" + 
		"Additional Details: " + description + 
		"\n" + "\n";
		
		
		// Copy pasted from https://stackoverflow.com/questions/8284706/send-email-via-gmail
		
		Intent send = new Intent(Intent.ACTION_SENDTO);
		String uriText = "mailto:" + Uri.encode(emailAd) + 
		          "?subject=" + Uri.encode(subject) + 
		          "&body=" + Uri.encode(emailText);
		Uri uri = Uri.parse(uriText);

		send.setData(uri);
		startActivity(Intent.createChooser(send, "Send mail..."));
		  
		  
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
