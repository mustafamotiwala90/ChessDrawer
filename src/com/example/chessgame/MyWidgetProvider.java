package com.example.chessgame;

import java.util.ArrayList;
import java.util.List;

import com.activeandroid.query.Select;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;

public class MyWidgetProvider extends AppWidgetProvider{

	public static String EXTRA_WORD=
		    "com.commonsware.android.appwidget.lorem.WORD";
	public String[] movesListWidget;
	
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled(Context context) {
		// TODO Auto-generated method stub
		super.onDisabled(context);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
		
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
		ComponentName thisAppWidget = new ComponentName(context.getPackageName(), MyWidgetProvider.class.getName());
		int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);
		
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
		
		Intent svcIntent=new Intent(context, StackWidgetService.class);
		
				svcIntent.putExtra("movesList",movesListWidget);
				svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));

				remoteViews.setRemoteAdapter(appWidgetIds[0], R.id.stackWidgetView,svcIntent);
		
		
	}

	@Override
	public void onReceive(Context context, Intent intent) {

		super.onReceive(context, intent);

		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
		ComponentName thisAppWidget = new ComponentName(context.getPackageName(), MyWidgetProvider.class.getName());
		int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);
			
			if (intent == null) {
				System.out.println("NULL INTENT");
			}
			else if(intent.getStringArrayExtra("mydata")!=null)
			{
			movesListWidget = intent.getStringArrayExtra("mydata");
			
				if(movesListWidget!=null)
					{
						for(int m=0;m<1;m++)
							System.out.println("moves 1 : "+movesListWidget[m]);
					}
				else
						System.out.println("I was called : ");
				onUpdate(context,appWidgetManager,appWidgetIds);		
	
			}
		}

	@SuppressWarnings("deprecation")
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		
		
		for (int i = 0; i < appWidgetIds.length; ++i)
			{
			
				RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
			
				Intent svcIntent=new Intent(context, StackWidgetService.class);
				
						
				ArrayList<Item> movesItems = new ArrayList<Item>();
				ArrayList<String> movesListF = new ArrayList<String>();
				movesItems = getAll();
				
					for(int r=0;r<movesItems.size();r++)
						{
							System.out.println("MOVES : "+movesItems.get(r).number + " AND : "+movesItems.get(r).move);
							movesListF.add(movesItems.get(r).move);
					
						}
						
					
						svcIntent.putStringArrayListExtra("stock_list", movesListF);
						svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
						
						remoteViews.setRemoteAdapter(appWidgetIds[i], R.id.stackWidgetView,svcIntent);

			
						Intent clickIntent=new Intent(context, ChessActivity.class);
						PendingIntent clickPI=PendingIntent.getActivity(context, 0,clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		      
						remoteViews.setPendingIntentTemplate(R.id.stackWidgetView, clickPI);
	
						appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
			}
		
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
	
	
	public static ArrayList<Item> getAll() {
		return new Select()
			.from(Item.class)
			.orderBy("RANDOM()")
			.execute();
	}
	
	
}
