package com.example.expensetracker;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

public class ExpenseItemListManager {
	static final String prefFile = "ExpenseItemList";
	static final String eilKey = "expenseItemList";
	
	Context context;
	
	static private ExpenseItemListManager expenseItemListManager = null;
	
	public ExpenseItemListManager(Context context) {
		this.context = context;
	}
	
	public static void initManager(Context context) {
		if (expenseItemListManager == null) {
			if (context == null) {
				throw new RuntimeException("Missing context for expenseItemListManager");
			}
			 expenseItemListManager = new ExpenseItemListManager(context);
		}
	}

	public static ExpenseItemListManager getManager() {
		if (expenseItemListManager == null) {
			throw new RuntimeException("Did not initialize Manager");
		}
		return expenseItemListManager;
	}
	
	public ExpenseItemList loadExpenseItemList() throws ClassNotFoundException, IOException {
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		String expenseItemListData = settings.getString(eilKey, "");
		if (expenseItemListData.equals("")) {
			return new ExpenseItemList();
		} else {
			return expenseItemListFromString(expenseItemListData);
		}
	}
	
	private ExpenseItemList expenseItemListFromString(String expenseItemListData) throws ClassNotFoundException, IOException {
		ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(expenseItemListData, Base64.DEFAULT));
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (ExpenseItemList) oi.readObject();
	}
	
	private String expenseItemListToString(ExpenseItemList eil) throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(eil);
		oo.close(); 
		byte bytes[] = bo.toByteArray();
		return Base64.encodeToString(bytes, Base64.DEFAULT);
	}
 
	public void saveExpenseItemList(ExpenseItemList eil) throws IOException {
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(eilKey, expenseItemListToString(eil));
		editor.commit();
	}
}