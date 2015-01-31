package com.example.expensetracker;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListExpenseItemsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_expense_items);
		
		ListView displayExpenseItemsListView = (ListView) findViewById(R.id.expenseItemsListView);
		Collection<ExpenseItem> expenseItems = ExpenseItemListController.getExpenseItemList().getExpenseItems();
		final ArrayList<ExpenseItem> list = new ArrayList<ExpenseItem>(expenseItems);
		final ArrayAdapter<ExpenseItem> expenseItemAdapter = new ArrayAdapter<ExpenseItem>(this, 
				android.R.layout.simple_list_item_1, list);
		displayExpenseItemsListView.setAdapter(expenseItemAdapter);
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
