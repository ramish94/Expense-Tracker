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
 * This Activity does exactly the same thing as AddClaimActivity, but for ExpenseItems. Refer back to AddClaimActivity
 * for comments describing its purpose, design rationale, and any outstanding issues.
 */

package com.example.expensetracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddExpenseItemsActivity extends Activity {
	
	 String categories[] = {
				"Airfare",
				"Ground Transport",
				"Vehicle Rental",
				"Fuel",
				"Parking",
				"Registration",
				"Accomodation",
				"Meal"
		};
	 
	 String currencies[] = {
			 "CAD",
			 "USD",
			 "EUR",
			 "GBP",
			 "INR",
	 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_expense_claims);
		
		// Initialize ClaimList and ExpenseItemListManager for serializability
		
		ClaimListManager.initManager(this.getApplicationContext());
		ExpenseItemListManager.initManager(this.getApplicationContext());
		
		
	}
	
	public void addAnotherClaim(View v) {
		addExpenseItemAction();
		Intent intent = new Intent(AddExpenseItemsActivity.this, AddExpenseItemsActivity.class);
		startActivity(intent);
	}
	
	public void onClickDone(View v) {
		Intent intent = new Intent(AddExpenseItemsActivity.this, ListExpenseItemsActivity.class);
		startActivity(intent);
	}
	
	public void categoryOptions(View v) {
		final EditText categoryOptions = (EditText)findViewById(R.id.categoryEditText);
		AlertDialog.Builder builder = getAlertDialog(categories, "Select category", categoryOptions, AddExpenseItemsActivity.this);
		builder.show();
		
	}
	
	public void currencyOptions (View v) {
		final EditText currencyOptions = (EditText)findViewById(R.id.currencyEditText);
		AlertDialog.Builder builder = getAlertDialog(currencies, "Select currency", currencyOptions, AddExpenseItemsActivity.this);
		builder.show();
	}
	
	
	public static AlertDialog.Builder getAlertDialog(final String strArray[], 
			String title, final EditText editText, final Activity activity) {
		
	    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
	    alertDialogBuilder.setTitle(title);

	    alertDialogBuilder.setItems(strArray, new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	           editText.setText(strArray[which]);
	        }
	    });
	   return alertDialogBuilder;
	}
	
	public void addExpenseItemAction() {
		
		Toast.makeText(this, "Adding an expense item!!", Toast.LENGTH_SHORT).show();
		ExpenseItemListController eil = new ExpenseItemListController();
		EditText dateTextView = (EditText) findViewById(R.id.pickDateEditText);
		EditText categoryTextView = (EditText) findViewById(R.id.categoryEditText);
		EditText descriptionTextView = (EditText) findViewById(R.id.editClaimdescriptionEditText);
		EditText amountSpentTextView = (EditText) findViewById(R.id.amountSpentEditText);
		EditText currencyTextView = (EditText) findViewById(R.id.currencyEditText);
		
		eil.addExpenseItem(new ExpenseItem(dateTextView.getText().toString(), 
				categoryTextView.getText().toString(), descriptionTextView.getText().toString(),
				amountSpentTextView.getText().toString(), currencyTextView.getText().toString()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_expense_items, menu);
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