package com.example.mediaplayer;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
	
	protected Memento myMemento;
	protected static Caretaker myCaretaker;
	private Button filesButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filesButton = (Button) findViewById(R.id.filesButton);
                     
        myCaretaker = new Caretaker();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public static Caretaker getCaretaker() {
    	return myCaretaker;
    }
    
    public static void writeCaretaker() {
    	
    	myCaretaker.writeFile();
    }
    
    @Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

    public void playlists(View v){
    	Intent intent = new Intent(MainActivity.this, MediaOptionsActivity.class);
		startActivity(intent);
    }
	
	public void exitApp(View v){
		finish();
	}
}
