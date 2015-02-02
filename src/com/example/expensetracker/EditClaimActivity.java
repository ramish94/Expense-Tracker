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
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditClaimActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_claim);
		
		ClaimListManager.initManager(this.getApplicationContext());
		ExpenseItemListManager.initManager(this.getApplicationContext());
		
		Bundle extras = getIntent().getExtras();
		final int claim_index = extras.getInt("index");
		final Claim claim = (Claim) extras.get("claim");
		
		EditText nameTextView = (EditText) findViewById(R.id.newClaimName);
		EditText startDateTextView = (EditText) findViewById(R.id.newStartDate);
		EditText endDateTextView = (EditText) findViewById(R.id.newEndDate);
		EditText descriptionTextView = (EditText) findViewById(R.id.newDescription);
		
		nameTextView.setText(claim.getName().toString());
		startDateTextView.setText(claim.getStartDate().toString());
		endDateTextView.setText(claim.getEndDate().toString());
		descriptionTextView.setText(claim.getDescription().toString());
		
		final Button button = (Button) findViewById(R.id.updateButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editClaimAction(claim_index);
                onClickUpdate();
            }
        });
	}
	
	public void editClaimAction(int i) {
		
		Toast.makeText(this, "Updating claim!", Toast.LENGTH_SHORT).show();
		ClaimListController cl = new ClaimListController();
		
		EditText nameTextView = (EditText) findViewById(R.id.newClaimName);
		EditText startDateTextView = (EditText) findViewById(R.id.newStartDate);
		EditText endDateTextView = (EditText) findViewById(R.id.newEndDate);
		EditText descriptionTextView = (EditText) findViewById(R.id.newDescription);
		
		cl.editClaim(new Claim(nameTextView.getText().toString(), startDateTextView.getText().toString(),
				endDateTextView.getText().toString(), descriptionTextView.getText().toString()), i);
	}
	
	public void onClickUpdate(){
		Intent intent = new Intent(EditClaimActivity.this, MainActivity.class);
		startActivity(intent);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_claim, menu);
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
