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
		
		ClaimListManager.initManager(this.getApplicationContext());
		ExpenseItemListManager.initManager(this.getApplicationContext());
	}
	
	public void showExpenseItemsAlertDialog(View v) {
		addClaimAction();
		
		Builder alertBuilder = new AlertDialog.Builder(AddClaimActivity.this);
		alertBuilder.setTitle("Add expense items?");
		alertBuilder.setMessage("Do you want to add expense items to this claim?");
		
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
	
	public void expenseItemsOptionYes() {
		Intent intent = new Intent(AddClaimActivity.this, AddExpenseItemsActivity.class);
		startActivity(intent);
	}
	
	public void expenseItemsOptionNo() {
		Intent intent = new Intent(AddClaimActivity.this, MainActivity.class);
		startActivity(intent);
	}
	
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
