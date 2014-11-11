package com.example.mediaplayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Caretaker {
	ArrayList <Memento> myMementos = new ArrayList<Memento>();
	
	@SuppressLint("SdCardPath")
	public Caretaker() {
			try {
				String currentLine;
				
				FileInputStream myReader = new FileInputStream("/sdcard/download/data.txt");
				BufferedReader bfr = new BufferedReader(new InputStreamReader(myReader));
				int count = 0;
				String path = "";
				String state = "";
				while((currentLine = bfr.readLine()) != null) {
					if (count%2 == 0){
						path = currentLine;
					} else {
						state = currentLine;
						if (count != 1) {
							Memento m = new Memento(path, state);
							myMementos.add(m);
						}
					}
					count++;
				}
				Memento m = new Memento(path, state);
				myMementos.add(m);
				String testString = "";
				for (int i = 0; i < myMementos.size(); i++) {
					testString = testString + myMementos.get(i).getPath();
					testString = testString + myMementos.get(i).getState() + "\n";
				}
				bfr.close();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void addMemento(Memento m) {
		myMementos.add(m);
	}
	
	public Memento getMomento(int pos) {
		return myMementos.get(pos);
	}
	
	@SuppressLint("SdCardPath")
	public void writeFile() {
		try {
			FileWriter writer = new FileWriter("/sdcard/download/test.txt"); 
			// Writes the content to the file
			for (int w = 0; w < myMementos.size(); w++) {
				writer.write(myMementos.get(w).getPath() + "\n");
				writer.write(myMementos.get(w).getState() + "\n");
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
