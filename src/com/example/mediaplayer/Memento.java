package com.example.mediaplayer;

public class Memento {
	   private String state;
	   private String path;

	   public Memento(String path, String state){
		  this.path = path;
	      this.state = state;
	   }

	   public String getState(){
	      return state;
	   }	
	   
	   public String getPath(){
		   return path;
	   }
}
