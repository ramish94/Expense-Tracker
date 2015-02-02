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
 * This is the MainActivity file. It contains a listView object that displays the claims created by the users. Once the claim 
 * is created  the user can click and hold the claim on the listView in order to summon the AlertDialog from where the User 
 * can edit/delete it, as well as email the claim, mark it as returned/approved (to which the listView item gets it's 
 * background colored according to the action chosen. It's quite nifty. I recommend trying it out (go on, I'll wait).
 * 
 * THIS APPLICATION DOES NOT SUPPORT (YET, WORK IN PROGRESS):
 * 	- Sorting/Ordering claims 
 * 	- Showing total currency amounts for a claim when listed
 */

package com.example.expensetracker;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	
	//Options for the AlertDialogBuilder
	 String claimsOnHoldOptions[] = {
				"Delete",
				"Edit",
				"View expense items",
				"Email this claim",
				"Mark as Returned",
				"Mark as Approved",
				"Cancel"
		};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Initialize ClaimList and ExpenseItemListManager for serializability
		ClaimListManager.initManager(this.getApplicationContext());
		ExpenseItemListManager.initManager(this.getApplicationContext());
		 
		// Initialize listView, arrayList, and arrayAdapter for storing claims
		final ListView displayClaimsListView = (ListView) findViewById(R.id.ClaimsListView);
		Collection<Claim> claims = ClaimListController.getClaimList().getClaims();
		final ArrayList<Claim> list = new ArrayList<Claim>(claims);
		final ArrayAdapter<Claim> claimAdapter = new ArrayAdapter<Claim>(this, 
				android.R.layout.simple_list_item_1, list);
		displayClaimsListView.setAdapter(claimAdapter);
		
		// Added an observer pattern here
		ClaimListController.getClaimList().addListener(new Listener() {
			@Override
			public void update() {
				list.clear();
				Collection<Claim> claims = ClaimListController.getClaimList().getClaims();
				list.addAll(claims);
				claimAdapter.notifyDataSetChanged();
			}
		});
		
		// When the user holds onto a listView item for too long
		displayClaimsListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Claim claim = list.get(position);
				AlertDialog.Builder builder = getAlertDialog(claimsOnHoldOptions, "Select an option",
						claim, MainActivity.this, list.indexOf(claim), claim, displayClaimsListView);
				builder.show();
				return false;
			}
		});
	}
	
	// initialize AlertDialogBuilder, and setup all the options through strArray, and title
	// pass on claim, Activity, in, for moving data through other activites
	public AlertDialog.Builder getAlertDialog(final String strArray[], 
			String title, final Claim claim, final Activity activity, final int in, final Claim cl,
			final ListView displayListView) {
		
	    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
	    alertDialogBuilder.setTitle(title);

	    alertDialogBuilder.setItems(strArray, new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	        	if (which == 0) { 
	        		// Remove claim
	        		ClaimListController.getClaimList().removeClaim(claim);
	        	}
	        	else if (which == 1) {
	        		// Edit Claim
	        		Intent intent = new Intent(MainActivity.this, EditClaimActivity.class);
	        		intent.putExtra("index", in);
	        		intent.putExtra("claim", cl);
	        		startActivity(intent);
	        	}
	        	else if (which == 2) {
	        		// View expenseItems for this claim
	        		Intent intent = new Intent(MainActivity.this, ListExpenseItemsActivity.class);
	        		startActivity(intent);
	        	}
	        	else if (which == 3) {
	        		Intent intent = new Intent(MainActivity.this, EmailClaimActivity.class);
	        		startActivity(intent);
	        	}
	        	else if (which == 4) {
	        		// Mark as returned
	        		Toast.makeText(MainActivity.this, "Claim marked as returned", Toast.LENGTH_LONG).show();
	        		displayListView.getChildAt(in).setBackgroundColor(Color.RED);
	        	}
	        	else if (which == 5) {
	        		// Mark as approved
	        		Toast.makeText(MainActivity.this, "Claim marked as approved", Toast.LENGTH_LONG).show();
	        		displayListView.getChildAt(in).setBackgroundColor(Color.GREEN);
	        	}
	        	else if (which == 6) {
	        		//Dismiss dialog box
	        		dialog.dismiss();
	        	}
	        }
	    });
	   return alertDialogBuilder;
	}
	
	public void onClickAddClaimButton(View v) {
		// What to do if a user clicks the "Add Claim" on the MainActivity. Called in onCreate in the XML file
		Intent intent = new Intent(MainActivity.this, AddClaimActivity.class);
		startActivity(intent);
	}
	
	public void onClickCheckExpenseItemButton(View v) {
		// What to do if a user clicks the "Check Expense Claim" on the MainActivity. Called in onCreate in the XML file
		Intent intent = new Intent(MainActivity.this, ListExpenseItemsActivity.class);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void editClaim(MenuItem menu) {
		Toast.makeText(this, "Edit Claims", Toast.LENGTH_SHORT).show();
	}	
	
}
