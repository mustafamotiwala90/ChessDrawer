package com.example.chessgame;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;
import com.example.chessgame.ChessBoard;


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

	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
		ChessBoard ch = new ChessBoard();
		movesListWidget = ch.gettingMovesForWidgetUpdate();	
		
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		
		
		for (int i = 0; i < appWidgetIds.length; ++i) {
			
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
			
			Intent svcIntent=new Intent(context, StackWidgetService.class);
			Bundle b=new Bundle(); 
//		      svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
				
			ChessBoard ch = new ChessBoard();
			movesListWidget = ch.gettingMovesForWidgetUpdate();	
			
			  b.putStringArray("movesList", movesListWidget);
			  
			  for(int j=0;j<movesListWidget.length;j++)
			  {
				  System.out.println("List values are as : " + movesListWidget[j]);
			  }
			  
			  
		      svcIntent.putExtras(b);
		      svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
	
		      remoteViews.setRemoteAdapter(appWidgetIds[i], R.id.stackWidgetView,svcIntent);

			
		      Intent clickIntent=new Intent(context, ChessActivity.class);
		      PendingIntent clickPI=PendingIntent
		                              .getActivity(context, 0,
		                                            clickIntent,
		                                            PendingIntent.FLAG_UPDATE_CURRENT);
		      
		      remoteViews.setPendingIntentTemplate(R.id.stackWidgetView, clickPI);

		      appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
		}
		
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
	
	
	
	
	
	
}
