package com.example.expensetracker;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemLongClickListener;

public class ListExpenseItemsActivity extends Activity {
	
	 String expenseItemsOnHoldOptions[] = {
				"Delete",
				"Edit",
				"Cancel"
		};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_expense_items);
		
		ClaimListManager.initManager(this.getApplicationContext());
		ExpenseItemListManager.initManager(this.getApplicationContext());
		
		ListView displayExpenseItemsListView = (ListView) findViewById(R.id.expenseItemsListView);
		Collection<ExpenseItem> expenseItems = ExpenseItemListController.getExpenseItemList().getExpenseItems();
		final ArrayList<ExpenseItem> list = new ArrayList<ExpenseItem>(expenseItems);
		final ArrayAdapter<ExpenseItem> expenseItemAdapter = new ArrayAdapter<ExpenseItem>(this, 
				android.R.layout.simple_list_item_1, list);
		displayExpenseItemsListView.setAdapter(expenseItemAdapter);
		
		// Added an observer pattern here
		ExpenseItemListController.getExpenseItemList().addListener(new Listener() {
			@Override
			public void update() {
				list.clear();
				Collection<ExpenseItem> expenseItems = ExpenseItemListController.getExpenseItemList().getExpenseItems();
				list.addAll(expenseItems);
				expenseItemAdapter.notifyDataSetChanged();
			}
		});
		
		displayExpenseItemsListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				ExpenseItem expenseItem = list.get(position);
				AlertDialog.Builder builder = getAlertDialog(expenseItemsOnHoldOptions, "Select an option",
						expenseItem, ListExpenseItemsActivity.this, list.indexOf(expenseItem), expenseItem);
				builder.show();
				return false;
			}
		});
	}
	
	public void onClickAddExpenseItemButton(View v) {
		Intent intent = new Intent(ListExpenseItemsActivity.this, AddExpenseItemsActivity.class);
		startActivity(intent);
	}
	
	public void onClickHomeButton(View v) {
		Intent intent = new Intent(ListExpenseItemsActivity.this, MainActivity.class);
		startActivity(intent);
	}
	
	public AlertDialog.Builder getAlertDialog(final String strArray[], 
			String title, final ExpenseItem expenseItem, final Activity activity, final int in, final ExpenseItem eI) {
		
	    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
	    alertDialogBuilder.setTitle(title);

	    alertDialogBuilder.setItems(strArray, new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	        	if (which == 0) {
	        		// Delete ExpenseItem
	        		ExpenseItemListController.getExpenseItemList().removeExpenseItem(expenseItem);
	        	}
	        	else if (which == 1) {
	        		// Edit ExpenseItem
	        		Intent intent = new Intent(ListExpenseItemsActivity.this, EditExpenseItemsActivity.class);
	        		intent.putExtra("index", in);
	        		intent.putExtra("expenseItem", eI);
	        		startActivity(intent);
	        	}
	        	else if (which == 2) {
	        		// Dismiss view
	        		dialog.dismiss();
	        	}
	        }
	    });
	   return alertDialogBuilder;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_expense_items, menu);
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
