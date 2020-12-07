/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.test;

/**
 *
 * @author yeehos
 */
public class cl_AccountInfo {
 public  static int accountid=0;
 public   String accountinfo="";
 public  static int counter=0;
    public String fname="";
    public String lname="";
    public boolean activate_flag;
    public double balance=0;
    
    public cl_AccountInfo()
    {
        activate_flag=false;
        balance=0;
       
    }
    
    public String decrement()
   {
       accountid--;
       return Integer.toString(accountid);
   }
   public String increment()
   {
       accountid=accountid+1;
       
       String result="";
       String convertnum= Integer.toString(accountid); 
       int num=convertnum.length();
       num=13-num;
       for (int i=0;i<num;i++)
       {
           result=result+"0";
       }
       result=result+convertnum;
       accountinfo=result;
       return result;
   }
    
   public void set_fname(String fn)
   {
       fname=fn;
   }
   
   public void set_lname(String ln)
   {
       lname=ln;
   }
   
   public void set_bal(double ln)
   {
       balance=ln;
   }
   public void set_acflag(Boolean ln)
   {
       activate_flag=ln;
   }
   public String get_fname()
   {
       return fname;
   }
   
   public String get_lname()
   {
       return lname;
   }
   
   public double get_bal()
   {
       return balance;
   }
   
   public String get_accountid()
   {
       return accountinfo;
   }
   
   public boolean get_acflag()
   {
       return activate_flag;
   }
   
}
