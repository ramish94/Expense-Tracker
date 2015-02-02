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

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
		
		ClaimListManager.initManager(this.getApplicationContext());
		ExpenseItemListManager.initManager(this.getApplicationContext());
		 
		ListView displayClaimsListView = (ListView) findViewById(R.id.ClaimsListView);
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
		
		displayClaimsListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Claim claim = list.get(position);
				//Toast.makeText(MainActivity.this, Integer.toString(list.indexOf(claim)), Toast.LENGTH_SHORT).show();
				AlertDialog.Builder builder = getAlertDialog(claimsOnHoldOptions, "Select an option",
						claim, MainActivity.this, list.indexOf(claim));
				builder.show();
				return false;
			}
		});
	}
	
	public AlertDialog.Builder getAlertDialog(final String strArray[], 
			String title, final Claim claim, final Activity activity, final int in) {
		
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
	        		startActivity(intent);
	        	}
	        	else if (which == 2) {
	        		// View expenseItems for this claim
	        		Intent intent = new Intent(MainActivity.this, ListExpenseItemsActivity.class);
	        		startActivity(intent);
	        	}
	        	else if (which == 3) {
	        		// Email claim
	        	}
	        	else if (which == 4) {
	        		// Mark as returned
	        		Toast.makeText(MainActivity.this, "Claim marked as returned", Toast.LENGTH_LONG).show();
	        	}
	        	else if (which == 5) {
	        		// Mark as approved
	        		Toast.makeText(MainActivity.this, "Claim marked as approved", Toast.LENGTH_LONG).show();
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
		Intent intent = new Intent(MainActivity.this, AddClaimActivity.class);
		startActivity(intent);
	}
	
	public void onClickCheckExpenseItemButton(View v) {
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
