package com.example.chessgame;

import java.util.ArrayList;
import java.util.List;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;


public class StackWidgetService extends RemoteViewsService{

	
	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		// TODO Auto-generated method stub
		 return new StackRemoteViewsFactory(this.getApplicationContext(), intent);
		 }

	public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory
	{

		private Context ctxt=null;
		  private int appWidgetId;
		  public  ArrayList<String> movesl=new ArrayList<String>();
		  public  ArrayList<String> moveslistfinal = new ArrayList<String>();
		  public String[] items={"lorem", "ipsum", "dolor",
              "sit", "amet", "consectetuer",
              "adipiscing", "elit", "morbi",
              "vel", "ligula", "vitae",
              "arcu", "aliquet", "mollis",
              "etiam", "vel", "erat",
              "placerat", "ante",
              "porttitor", "sodales",
              "pellentesque", "augue",
              "purus"};
		  
		  
		
		public StackRemoteViewsFactory(Context applicationContext, Intent intent) {
		
		this.ctxt = applicationContext;
//		 appWidgetId=intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
//                 AppWidgetManager.INVALID_APPWIDGET_ID);
		
//		 movesl = intent.getStringArrayExtra("movesList");
		 movesl = intent.getStringArrayListExtra("stock_list");
		 
		
		 for(int m=0;m<movesl.size();m++)
		 {
			 	if(!movesl.contains(movesl.get(m)))
			 		moveslistfinal.add(movesl.get(m));
		
		 }
		 
		 
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return movesl.size();
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public RemoteViews getLoadingView() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public RemoteViews getViewAt(int position) {

			
			
			RemoteViews row=new RemoteViews(ctxt.getPackageName(),R.layout.row);

			row.setTextViewText(R.id.country_name, movesl.get(position));
			row.setImageViewResource(R.id.country_icon, R.drawable.chess_icon);
			
			
			Intent i=new Intent();
			Bundle extras=new Bundle();

			extras.putString(MyWidgetProvider.EXTRA_WORD, movesl.get(position));
			i.putExtras(extras);
			row.setOnClickFillInIntent(android.R.id.text1, i);

			return(row);		
		}

		@Override
		public int getViewTypeCount() {
			// TODO Auto-generated method stub
			return 1;
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public void onCreate() {
			// no operation
		}

		@Override
		public void onDataSetChanged() {
			// no operation
			
		}

		@Override
		public void onDestroy() {
			// TODO Auto-generated method stub
			
		}
		
		
	}
}
