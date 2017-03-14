package com.example.randomcall;

import java.util.ArrayList;
import java.util.Random;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.widget.Toast;

public class randomcaller extends BroadcastReceiver
{
	String number;
	ArrayList<String> al;
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		String phno;
		Random ran=new Random();
		al=new ArrayList<String>();
		Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null, null, null);
		if(cursor.getCount()>0)
		{
			cursor.moveToFirst();
			while(cursor.isAfterLast()==false)
			{
		      phno=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
		      al.add(phno); 
		      cursor.moveToNext();
			}
		}
		if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) 
		{
		      number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
		      al.remove(number);
		      int a=ran.nextInt(al.size());
		      String ph=al.get(a);
		      Toast.makeText(context,ph,Toast.LENGTH_LONG).show();
              setResultData(ph);
		}
	     al.add(number);
	}

}
