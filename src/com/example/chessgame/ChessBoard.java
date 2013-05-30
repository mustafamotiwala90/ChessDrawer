package com.example.chessgame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.*;


import com.activeandroid.ActiveAndroid;

public class ChessBoard extends Activity{

	

	Map<Integer, String> boardPositions = new HashMap<Integer, String>();
	Map<Integer, String> boardPieces = new HashMap<Integer, String>();
	ArrayList<String> chessids = new ArrayList<String>();	
	public static String value;
	TextView pieceView;
	ImageView squareView;
	View squareContainerView;
	GridView grid;
	FrameLayout chessframe;
	RelativeLayout relativeframe;
	public ListView mDrawerList;
	public ArrayList<Integer> validmov;
	public ArrayAdapter<Object> arrayAdapter;
	public String[] movesArray ;
	
	 public CharSequence mDrawerTitle;
	 public CharSequence mTitle;
	 public DrawerLayout mDrawerLayout;
	 public ActionBarDrawerToggle mDrawerToggle;
	
	 public static int moveCount = 0;
	 int x;
 	 int y;
 	
	String WhiteKing = "&#9812";
	String WhiteQueen = "&#9813";
	String WhiteRook = "&#9814";
	String WhiteBishop = "&#9815";
	String WhiteKnight = "&#9816";
	String WhitePawn = "&#9817";
	String BlackKing = "&#9818";
	String BlackQueen = "&#9819";
	String BlackRook = "&#9820";
	String BlackBishop = "&#9821";
	String BlackKnight = "&#9822";
	String BlackPawn = "&#9823";
	
	int board[][] = new int[10][10];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chessboard);
		grid = (GridView)findViewById(R.id.chessboard);
		grid.setAdapter(new SquareAdapter(this));
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		
		mTitle = mDrawerTitle = getTitle();
		
		ActiveAndroid.initialize(this);
		
		movesArray = new String[1000];

        // Fill the songs array by using a for loop
        for(int i=0; i < movesArray.length; i++){
        	movesArray[i] = "Move " + i;
        }
		
        arrayAdapter = new ArrayAdapter<Object>(this, android.R.layout.simple_list_item_1, movesArray);
        mDrawerList.setAdapter(arrayAdapter);
        
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close   /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
 
	}


	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		 MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.main, menu);
	        
		return super.onCreateOptionsMenu(menu);
	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		 if (mDrawerToggle.onOptionsItemSelected(item)) {
	            return true;
	        }	
		 
		return super.onOptionsItemSelected(item);
	}



	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);

		return super.onPrepareOptionsMenu(menu);
	}


	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
	

	public class SquareAdapter extends BaseAdapter {

		Context mcontext;
		public SquareAdapter(Context conte)
		{
			mcontext = conte;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 64;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		
		public String SetPositionValue(int position) {
			chessids.add("A");
			chessids.add("B");
			chessids.add("C");
			chessids.add("D");
			chessids.add("E");
			chessids.add("F");
			chessids.add("G");
			chessids.add("H");

			value = chessids.get(position % 8) + (position / 8 + 1);
			boardPositions.put(position, value);
			
			board[(position%8)+1][(position/8)+1] = position;
		
		return value;
		
	}
	
		public void SetPieces(int position,TextView pieceView)
		{
			pieceView.setTextSize(30);
			
			switch(position)
			{
			case 0:case 7:
				pieceView.setText(Html.fromHtml(BlackRook));
				boardPieces.put(position, BlackRook);
				break;
			case 1:case 6:
				pieceView.setText(Html.fromHtml(BlackKnight));
				boardPieces.put(position, BlackKnight);
				break;
			case 2:case 5:
				pieceView.setText(Html.fromHtml(BlackBishop));
				boardPieces.put(position, BlackBishop);
				break;
			case 3:
				pieceView.setText(Html.fromHtml(BlackQueen));
				boardPieces.put(position, BlackQueen);
				break;
			case 4:
				pieceView.setText(Html.fromHtml(BlackKing));
				boardPieces.put(position, BlackKing);
				break;
			case 8:case 9:case 10:case 11:case 12:case 13:case 14:case 15:
				boardPieces.put(position, BlackPawn);
				pieceView.setText(Html.fromHtml(BlackPawn));
				break;
			
			case 56:case 63:
				pieceView.setText(Html.fromHtml(WhiteRook));
				boardPieces.put(position, WhiteRook);
				break;
			case 57:case 62:
				pieceView.setText(Html.fromHtml(WhiteKnight));
				boardPieces.put(position, WhiteKnight);
				break;
			case 58:case 61:
				pieceView.setText(Html.fromHtml(WhiteBishop));
				boardPieces.put(position, WhiteBishop);
				break;
			case 59:
				pieceView.setText(Html.fromHtml(WhiteQueen));
				boardPieces.put(position, WhiteQueen);
				break;
			case 60:
				pieceView.setText(Html.fromHtml(WhiteKing));
				boardPieces.put(position, WhiteKing);
				break;
			case 48:case 49:case 50:case 51:case 52:case 53:case 54:case 55:
				pieceView.setText(Html.fromHtml(WhitePawn));
				boardPieces.put(position, WhitePawn);
				break;
			}
			
		}
		
		
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			squareContainerView = convertView;
			if (convertView == null) {
				// Inflate the layout
				LayoutInflater inflater = getLayoutInflater();
				squareContainerView = inflater.inflate(R.layout.square, null);

				squareContainerView.setOnDragListener(new PieceOnDragListener(mcontext));
				
				
				// Background
				squareView = (ImageView) squareContainerView
						.findViewById(R.id.square_background);

				pieceView = (TextView) squareContainerView
						.findViewById(R.id.grid_inner_text);

				
				chessframe = (FrameLayout) squareContainerView.findViewById(R.id.square);
				
				relativeframe = (RelativeLayout) squareContainerView.findViewById(R.id.relativeframe);
				
				
				if (((position + position / 8) % 2) == 0) {
					squareView.setBackgroundColor(Color.parseColor("#00FF00"));
				} else {
					squareView.setBackgroundColor(Color.parseColor("#FFFF00"));
				}

				String val = SetPositionValue(position);		
				SetPieces(position,pieceView);
			}
			
			pieceView.setTag(Integer.valueOf(position));
			squareView.setTag(Integer.valueOf(position));
			chessframe.setTag(Integer.valueOf(position));
			relativeframe.setTag(Integer.valueOf(position));
			
			pieceView.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					final ClipData data = ClipData.newPlainText("position",
							position + "");
					final DragShadowBuilder pieceDragShadowBuilder = new DragShadowBuilder(
							pieceView);
					v.startDrag(data, pieceDragShadowBuilder, v, 0);
					return true;
				}

			});
			
			
			return squareContainerView;
		}
	
		
		public class PieceOnDragListener implements OnDragListener {
		    private Context context;
		    private boolean insideOfMe = false;
		  
		    public PieceOnDragListener(Context pContext) {
		      this.context = pContext;
		    }
		  
		    @Override
		    public boolean onDrag(View pV, DragEvent pEvent) {
		      final int dragAction = pEvent.getAction();
		      
		      View dragView = (View) pEvent.getLocalState();
		      final FrameLayout container = (FrameLayout) pV;
		      
				if (dragAction == DragEvent.ACTION_DRAG_STARTED)
				{
//					System.out.println("ACTION_DRAG_STARTED");
					//Adding the piece logic here to check which piece was touched 
					
				} 
				else if (dragAction == DragEvent.ACTION_DRAG_ENTERED)
				{
					insideOfMe = true;
//					System.out.println("ACTION_DRAG_ENTERED");
					findvalidmoves(dragView,container);
				}
				else if (dragAction == DragEvent.ACTION_DRAG_EXITED) 
				{
					insideOfMe = false;
//					System.out.println("ACTION_DRAG_EXITED");
				} 
				else if (dragAction == DragEvent.ACTION_DROP) 
				{	
						if(insideOfMe)
						{
							if(container.getChildCount()>1)
							{
								makeToastText("Invalid Move");
								removeColourFromValidMoves(validmov);
							}
							else
							{
								final ViewGroup owner = (ViewGroup) dragView.getParent();
								owner.removeView(dragView);
//								ImageView vi = (ImageView) container.findViewById(R.id.square_background);
//								vi.setBackgroundColor(Color.parseColor("#ffcc00"));
								container.addView(dragView);
								makeToastText(boardPositions.get(dragView.getTag()) + " To  "+ boardPositions.get(container.findViewById(R.id.grid_inner_text).getTag()));
								
								removeColourFromValidMoves(validmov);
								movesArray[moveCount] = boardPositions.get(dragView.getTag()) + " To "+ boardPositions.get(container.findViewById(R.id.grid_inner_text).getTag());
								arrayAdapter = new ArrayAdapter<Object>(this.context, android.R.layout.simple_list_item_1, movesArray);
						        mDrawerList.setAdapter(arrayAdapter);
						       
						        
//						        Intent intent = new Intent(ChessBoard.this, MyWidgetProvider.class);
//						        intent.putExtra("mydata",movesArray);
//						        intent.setAction(Intent.ACTION_SEND);
//						        ChessBoard.this.sendBroadcast(intent);
//						        widgetmovesupdated(movesArray);
						        
						        
						        Item item = new Item();
						        item.number = moveCount;
						        item.move = movesArray[moveCount];
						        item.save();
						        System.out.println("DATABASE SAVED WITH ENTRY AS : "+item.number+" AND : "+item.move);
						        moveCount++; 
						        
							}
						}
				}
				else if (dragAction == DragEvent.ACTION_DRAG_ENDED)
				{
					if (dropEventNotHandled(pEvent)) 
					{
//						System.out.println("ACTION_DRAG_ENDED");
					}
					
				}
				else
				{
					// Why is the else getting called
				}
		     
				return true;
		    }

		   @SuppressWarnings("deprecation")
		public void widgetmovesupdated(String[] movesArray)
		   {
			   
			   Context context = ChessBoard.this;
				AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
				
				ComponentName thisWidget = new ComponentName(context, MyWidgetProvider.class);
				int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
				
				
				
				RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
				
				Intent svcIntent=new Intent(context, StackWidgetService.class);
				
						svcIntent.putExtra("movesList",movesArray);
						svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));

						remoteViews.setRemoteAdapter(appWidgetIds[0], R.id.stackWidgetView,svcIntent);
				
				
						appWidgetManager.updateAppWidget(appWidgetIds[0], remoteViews);
			   
			   
		   }
		    
		    
		    public void findvalidmoves(View dragView,FrameLayout container)
		    {
		    	
		    	String currentpiece = boardPieces.get(dragView.getTag());
				int currentpositiontag = (Integer) dragView.getTag();
				if(currentpiece!=null)
				{
					validmov = validMoves(currentpiece,currentpositiontag);
					setColourToValidMoves(validmov);
				}
				else
					makeToastText("Blank Space");
				
			
		    }
		    
		    
		    public void setColourToValidMoves(ArrayList<Integer> validmove)
		    {
		    	for(int j=0;j<validmove.size();j++)
				{
		    		
		    		if(validmove.get(j)<=63 && validmove.get(j)>=0)
	    			{
		    			System.out.println(validmove.get(j));
		    			FrameLayout frm = (FrameLayout) grid.findViewWithTag(validmove.get(j));
		    			System.out.println(frm.getChildCount());
						ImageView tes = (ImageView) frm.findViewById(R.id.square_background);
						tes.setBackgroundColor(Color.parseColor("#ffcc00"));
	    			}
				}
		    }
		    
		    public void removeColourFromValidMoves(ArrayList<Integer> validmovee)
		    {
		    	for(int j=0;j<validmovee.size();j++)
					{
		    			if(validmovee.get(j)<=63 && validmovee.get(j)>=0)
		    			{
		    				FrameLayout frm = (FrameLayout) grid.findViewWithTag(validmovee.get(j));
		    				ImageView tes = (ImageView) frm.findViewById(R.id.square_background);
		    				int positiontemp = validmovee.get(j);
						
		    				if (((positiontemp + positiontemp / 8) % 2) == 0) {
		    					tes.setBackgroundColor(Color.parseColor("#00FF00"));
		    				}
		    				else 
		    				{
		    					tes.setBackgroundColor(Color.parseColor("#FFFF00"));
						}
		    			}	
					}
		    }
		    
		    
		    
		    public ArrayList<Integer> validMoves(String currentpiece,int currentpositiontag)
		    {
		    	
		    	ArrayList<Integer> moveslist = new ArrayList<Integer>();
		    	
		    	for (int i = 0; i < board.length; i++) {
				    for (int j = 0; j < board.length; j++) {
				        if (board[i][j]==currentpositiontag)
				        {
				            System.out.println("Board : "+ i + " , "+ j );
				            x = i;
				            y = j;
				        }
				    }
				}
		    			    	
		    	int pawnmaxmoves = 2;		    	
		    	
		    	if(currentpiece.equalsIgnoreCase(WhitePawn))
				{
//					System.out.println("A white pawn was moved from : " + currentpositiontag);
					for(int i=1;i<=pawnmaxmoves;i++)
					{
						moveslist.add(currentpositiontag-(8*i));
					}
				
				}
				else if(currentpiece.equalsIgnoreCase(WhiteRook))
				{
//					System.out.println("A white rook was moved");
					for(int m=x+1;m<=8;m++)
					{
						moveslist.add(board[m][y]);
					}
					
					for(int n=y+1;n<=8;n++)
					{
						moveslist.add(board[x][n]);
					}
					for(int m=x-1;m>=1;m--)
					{
						moveslist.add(board[m][y]);
					}
					
					for(int o=y-1;o>=1;o--)
					{
						System.out.println(board[x][o]);
						moveslist.add(board[x][o]);
					}
					
					

				}
				else if(currentpiece.equalsIgnoreCase(WhiteKnight))
				{
					System.out.println("A white knight was moved from : "+currentpositiontag);

				}
				else if(currentpiece.equalsIgnoreCase(WhiteBishop))
				{
					System.out.println("A white bishop was moved");
					
					for (int i=x-1,j=y-1;i>=0 && j>=0;i--,j--) 
						 moveslist.add(board[i][j]);
					 for (int a=x+1,b=y+1;a<=8 && b<=8;a++,b++) 
						 moveslist.add(board[a][b]);
					 for (int c=x-1,d=y+1;c>=0 && d<=8;c--,d++) 
						 moveslist.add(board[c][d]);
					 for (int e=x+1,f=y-1;e<=8 && f>=0;e++,f--) 
						 moveslist.add(board[e][f]);
					
				}
				else if(currentpiece.equalsIgnoreCase(WhiteKing))
				{
					System.out.println("A white king was moved");
				}
				else if(currentpiece.equalsIgnoreCase(WhiteQueen))
				{
					System.out.println("A white queen was moved");
					
					for(int m=x+1;m<=8;m++)
					{
						moveslist.add(board[m][y]);
					}
					
					for(int n=y+1;n<=8;n++)
					{
						moveslist.add(board[x][n]);
					}
					for(int m=x-1;m>=1;m--)
					{
						moveslist.add(board[m][y]);
					}
					
					for(int o=y-1;o>=1;o--)
					{
						System.out.println(board[x][o]);
						moveslist.add(board[x][o]);
					}
					
					for (int i=x-1,j=y-1;i>=0 && j>=0;i--,j--) 
						 moveslist.add(board[i][j]);
					 for (int a=x+1,b=y+1;a<=8 && b<=8;a++,b++) 
						 moveslist.add(board[a][b]);
					 for (int c=x-1,d=y+1;c>=0 && d<=8;c--,d++) 
						 moveslist.add(board[c][d]);
					 for (int e=x+1,f=y-1;e<=8 && f>=0;e++,f--) 
						 moveslist.add(board[e][f]);
					
					
					
				}
				else if(currentpiece.equalsIgnoreCase(BlackPawn))
				{
					System.out.println("A black pawn was moved");
					
					for(int i=1;i<=pawnmaxmoves;i++)
					{
						moveslist.add(currentpositiontag+(8*i));
					}
					
				}
				else if(currentpiece.equalsIgnoreCase(BlackRook))
				{
					System.out.println("A black rook was moved");
					
					
					for(int m=x+1;m<=8;m++)
					{
						moveslist.add(board[m][y]);
					}
					
					for(int n=y+1;n<=8;n++)
					{
						moveslist.add(board[x][n]);
					}
					for(int m=x-1;m>=1;m--)
					{
						moveslist.add(board[m][y]);
					}
					
					for(int o=y-1;o>=1;o--)
					{
						System.out.println(board[x][o]);
						moveslist.add(board[x][o]);
					}
					
					
				}
				else if(currentpiece.equalsIgnoreCase(BlackKnight))
				{
					System.out.println("A black knight was moved");
				}
				else if(currentpiece.equalsIgnoreCase(BlackBishop))
				{
					System.out.println("A black bishop was moved");
					
					for (int i=x-1,j=y-1;i>=0 && j>=0;i--,j--) 
						 moveslist.add(board[i][j]);
					 for (int a=x+1,b=y+1;a<=8 && b<=8;a++,b++) 
						 moveslist.add(board[a][b]);
					 for (int c=x-1,d=y+1;c>=0 && d<=8;c--,d++) 
						 moveslist.add(board[c][d]);
					 for (int e=x+1,f=y-1;e<=8 && f>=0;e++,f--) 
						 moveslist.add(board[e][f]);
					
					
				}
				else if(currentpiece.equalsIgnoreCase(BlackQueen))
				{
					System.out.println("A black queen was moved");
				
					for(int m=x+1;m<=8;m++)
					{
						moveslist.add(board[m][y]);
					}
					
					for(int n=y+1;n<=8;n++)
					{
						moveslist.add(board[x][n]);
					}
					for(int m=x-1;m>=1;m--)
					{
						moveslist.add(board[m][y]);
					}
					
					for(int o=y-1;o>=1;o--)
					{
						moveslist.add(board[x][o]);
					}

					 for (int i=x-1,j=y-1;i>=0 && j>=0;i--,j--) 
						 moveslist.add(board[i][j]);
					 for (int a=x+1,b=y+1;a<=8 && b<=8;a++,b++) 
						 moveslist.add(board[a][b]);
					 for (int c=x-1,d=y+1;c>=0 && d<=8;c--,d++) 
						 moveslist.add(board[c][d]);
					 for (int e=x+1,f=y-1;e<=8 && f>=0;e++,f--) 
						 moveslist.add(board[e][f]);
						 
				}
				else if(currentpiece.equalsIgnoreCase(BlackKing))
				{
					System.out.println("A black king was moved");
				}
				else
				{
					makeToastText("Invalid");
				}
		    	
		  
		    	return moveslist;
		    }
	    
		    
		    public void makeToastText(String toastText)
		    {
		    	final Toast toast = Toast.makeText(this.context, toastText,
				        Toast.LENGTH_SHORT);
				toast.show();

				new Handler().postDelayed(new Runnable() {

				    @Override
				    public void run() {
				        toast.cancel();
				    }
				}, 1500);
		    }
		        
		    
		    private boolean dropEventNotHandled(DragEvent dragEvent) {
		    	        return !dragEvent.getResult();
		    	    }

			
		  }
		
		
		
	}
	
	
	
}
