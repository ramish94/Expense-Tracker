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

/*
 * AddClaimActivity.java
 * 
 * The purpose of this class is to fulfill the UI and front end function of adding a claim, this aptly name "AddClaimActivity"
 * It simply puts together all the pieces from the add_claim XML layout, and calls the addClaimAction() method to add claims to
 * the ArrayList
 *
 * Design Rationale: Very similar to others. Using delegation and OOP concepts so that code integrity can be maintained
 * 
 * No outstanding issues
 */

package com.example.expensetracker;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddClaimActivity extends Activity {
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_claim);
		
		// Initialize ClaimList and ExpenseItemListManager for serializability
		ClaimListManager.initManager(this.getApplicationContext());
		ExpenseItemListManager.initManager(this.getApplicationContext());
	}
	
	// Method for showing AlertDialog when a button is pressed. Called from the OnClick function in the XML file
	public void showExpenseItemsAlertDialog(View v) {
		addClaimAction();
		
		Builder alertBuilder = new AlertDialog.Builder(AddClaimActivity.this);
		alertBuilder.setTitle("Add expense items?");
		alertBuilder.setMessage("Do you want to add expense items to this claim?");
		
		// Positive and Negative buttons. Setting up a AlertDialog and calling the appropriate onClick function

		alertBuilder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				expenseItemsOptionYes();
			}
		});
		
		alertBuilder.setPositiveButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				expenseItemsOptionNo();
			}
		});
		alertBuilder.show();
	}
	
	// starting the appropriate activites according to the User's response. 
	
	public void expenseItemsOptionYes() {
		Intent intent = new Intent(AddClaimActivity.this, AddExpenseItemsActivity.class);
		startActivity(intent);
	}
	
	public void expenseItemsOptionNo() {
		Intent intent = new Intent(AddClaimActivity.this, MainActivity.class);
		startActivity(intent);
	}
	
	// Add claim action. Called to store text from EditText fields from the add_claim xml file, and then 
	// finally adds claim to the arrayList.
	
	public void addClaimAction() {
		
		Toast.makeText(this, "Adding a claim!", Toast.LENGTH_SHORT).show();
		ClaimListController cl = new ClaimListController();
		
		EditText nameTextView = (EditText) findViewById(R.id.editClaimNameEditText);
		EditText startDateTextView = (EditText) findViewById(R.id.editStartDateEditText);
		EditText endDateTextView = (EditText) findViewById(R.id.editEndDateEditText);
		EditText descriptionTextView = (EditText) findViewById(R.id.editAddClaimdescriptionEditText);
		
		cl.addClaim(new Claim(nameTextView.getText().toString(), startDateTextView.getText().toString(),
				endDateTextView.getText().toString(), descriptionTextView.getText().toString()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_claim, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.addAnotherClaimButton) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
