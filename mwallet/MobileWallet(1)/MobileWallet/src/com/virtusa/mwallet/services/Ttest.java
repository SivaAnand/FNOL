package com.virtusa.mwallet.services;


import java.text.*; 
import java.util.*; 

public class Ttest 
{ 
  public Ttest() throws ParseException 
  { 
    String dateString = "2001/03/09"; 
     
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd"); 
    Date convertedDate = dateFormat.parse(dateString); 

    System.out.println("Converted string to date : " + convertedDate); 
  } 

  public static void main(String[] argv) throws ParseException 
  { 
    new Ttest(); 
  } 
} 



