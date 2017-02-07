package com.example.kartheek_sample.models;

public class SectionItem implements Item {
	 
	 private final String title;
	  
	 public SectionItem(String title) {
	  this.title = title;
	 }
	  
	 public String getTitle(){
	  return title;
	 }
	  
	 @Override
	 public boolean isHeader() {
	  return true;
	 }
	 
	}
