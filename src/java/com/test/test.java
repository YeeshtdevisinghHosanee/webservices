/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.*;
import org.json.simple.JSONArray;    
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
/**
 *
 * @author yeehos
 */
@Path("test")
public class test {
   static List<cl_AccountInfo> object_list = new ArrayList<cl_AccountInfo>(); // Ok
    
   public String func_rdate()
   {
            LocalDateTime myDateObj = LocalDateTime.now();
            System.out.println("Before formatting: " + myDateObj);
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = myDateObj.format(myFormatObj);
            
            return formattedDate;
   }
   
   
   
    @GET
    @Path("/getdata")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDataINJSON() 
    {
        
        //return message;
       // return "{\"ResponseStatus\":{\"ReturnCode\":\"0\",\"ErrorMsg\":\"\"},\"ResponseData\":{\"ValidationError\":{\"ErrorCode\":\"\",\"ErrorMsg\":\"\"},\"Get_FX_IndRates_Resp\":{\"FX_IndRates\":{\"DATE_RATES\":{\"CCY_Rate_List\":[{\"CCY\":\"\",\"CCY_NAME\":\"\",\"UNITS\":\"1\",\"TT_Buy\":\"3.971\",\"TT_Sell\":\"4.09\",\"DD_Buy\":\"3.961\",\"DD_Sell\":\"4.09\",\"NT_Buy\":\"3.933\",\"NT_Sell\":\"4.09\",\"Reval_Rate\":\"4.0305\"}]}}}}}";
    return "\"firstName\": \"Jane\",\"lastName\": \"Doe\",\"companyName\": \"ACME\"";
     

    
    
    }
    
    @POST
    @Path("/createaccount")
    @Produces(MediaType.APPLICATION_JSON)
    public String func_createaccount(String message) 
    {
       /** cl_AccountInfo a1=new cl_AccountInfo();
        
        JSONObject obj = new JSONObject();
        obj.put("AccountID", String.valueOf(a1.increment()));
        obj.put("Name", "App Shah");
        return obj.toJSONString();**/
        
  
        String ad="";
        cl_AccountInfo  a1=new cl_AccountInfo();
        
        
        String fresult="";
        JSONObject obj = new JSONObject();
        
        try
        {
        if (!message.equals(""))
        {
        String conca="";
        String [] newarr=message.split("\"");
        
        List<String> list_fields=new ArrayList<String>();  
         List<String> list_values=new ArrayList<String>(); 
         int chk1=0;
         String prevval="";
         String fieldval="";
         String fielddata="";
        for (int i=0;i<newarr.length;i++)
        {
           
           if (newarr[i].contains(":") )
           {
               //conca=conca+newarr[i];
               fieldval=prevval;
               chk1=1;
            
           }
            
           if (chk1==0)
           {
           if (!newarr[i].contains("{") &  !newarr[i].contains("}") &  !newarr[i].contains("}") )
           {
               
               prevval=newarr[i];
            
           }
           }
           else
           {
               if (!newarr[i].contains("{") &  !newarr[i].contains("}") &  !newarr[i].contains("}") &  !newarr[i].contains(":"))
                {
                 //conca=conca+newarr[i];
                  fielddata=newarr[i];
                  chk1=0;
                  conca=conca+fieldval+":"+fielddata+";";
                }
           }
           
        }
     
        
        ad=String.valueOf(a1.increment());
        obj.put("AccountID", ad);
        String [] arr_val=conca.split(";");
        for (int i=0;i<arr_val.length;i++)
        {
            String [] arr_val2=arr_val[i].split(":");
            obj.put(arr_val2[0], arr_val2[1]);
            if (arr_val2[0].equals("fname"))
            {
                a1.set_fname(arr_val2[1]);
            }
            if (arr_val2[0].equals("lname"))
            {
                a1.set_lname(arr_val2[1]);
            }
        }
        fresult=obj.toJSONString();
        
        object_list.add(a1);
        
        }
        else
        {
             obj.put("ErrorCode", "-1");
              obj.put("ErrorMsg", "Unsuccessful creation as request is blank.");
              fresult=obj.toJSONString();
        }
        }
        catch (Exception e)
        {
            obj.put("ErrorCode", "-1");
              obj.put("ErrorMsg", "Unsuccessful creation as request format not good.");
              obj.remove("AccountID");
              a1.decrement();
              fresult=obj.toJSONString();
        }
        
        for (cl_AccountInfo temp : object_list) 
            {
                    
                  //if (temp.get_accountid().equals(req_id))
                //  {
                      
                    //   fresult=fresult+temp.get_accountid();
                 // }
            }
        System.gc();
       return fresult;
        
        
       // return conca;
    }
    
    
    @POST
    @Path("/getbalance")
    @Produces(MediaType.APPLICATION_JSON)
    public String func_getbalance(String message) 
    {
       /** cl_AccountInfo a1=new cl_AccountInfo();
        
        JSONObject obj = new JSONObject();
        obj.put("AccountID", String.valueOf(a1.increment()));
        obj.put("Name", "App Shah");
        return obj.toJSONString();**/
        
  
    int chk1=0;
    cl_AccountInfo  a1=new cl_AccountInfo();
        
        
        String fresult="";
        JSONObject obj1 = new JSONObject();
        
        try
        {
             Object obj=JSONValue.parse(message);   
            JSONObject jsonObject = (JSONObject) obj;    
            //getting values form the JSONObject and casting that values into corresponding types  
            String req_id = (String) jsonObject.get("accountid"); 
         //   fresult=Integer.toString(object_list.size());
            
        /**    for (cl_AccountInfo temp : object_list) 
            {
                    
                  if (temp.get_accountid().equals(req_id))
                  {
                      
                      if (temp.get_acflag()==false)
                      {
                          chk1=2;
                      }
                      else
                      {
                        obj1.put("accountid", temp.get_accountid());
                        obj1.put("First Name", temp.get_fname());
                        obj1.put("Last Name", temp.get_lname());
                        obj1.put("Balance", temp.get_bal());
                         obj1.put("acflag", temp.get_acflag());
                        fresult=fresult+obj1.toJSONString();
                        chk1=1;
                      }
                  }
            }**/
            
            
            Comparator<cl_AccountInfo> c = new Comparator<cl_AccountInfo>()  
            {  
                    public int compare(cl_AccountInfo u1, cl_AccountInfo u2)  
                    {  
                        return u1.get_accountid().compareTo(u2.get_accountid());  
                    }  
             };  
            
            cl_AccountInfo c2=new cl_AccountInfo();
            c2.accountinfo=req_id;
            int index = Collections.binarySearch(object_list,c2, c);  
            
             if (index >=0)
                  {
                      
                      if (object_list.get(index).get_acflag()==false)
                      {
                          chk1=2;
                      }
                      else
                      {
                        obj1.put("accountid", object_list.get(index).get_accountid());
                        obj1.put("First Name", object_list.get(index).get_fname());
                        obj1.put("Last Name", object_list.get(index).get_lname());
                        obj1.put("Balance", object_list.get(index).get_bal());
                         obj1.put("acflag", object_list.get(index).get_acflag());
                         obj1.put("timestamp", func_rdate());
                        fresult=fresult+obj1.toJSONString();
                        chk1=1;
                      }
                  } 
            
        }
        catch (Exception e)
        {
            obj1.put("ErrorCode", "-1");
              obj1.put("ErrorMsg", "Unsuccessful creation as request format not good.");
              
              fresult=obj1.toJSONString();
        }
        
        if (chk1==0)
        {
             obj1.put("ErrorCode", "-1");
              obj1.put("ErrorMsg", "Account not found/disactivated.");
                 fresult=obj1.toJSONString();
        }
      
        
        if (chk1==2)
        {
             obj1.put("ErrorCode", "-1");
             obj1.put("ErrorMsg", "Account is disactivated.");
             fresult=obj1.toJSONString();
        }
        if (chk1==2)
        {
             obj1.put("ErrorCode", "-1");
             obj1.put("ErrorMsg", "Account is disactivated.");
             fresult=obj1.toJSONString();
        }
        System.gc();
       return fresult;
        
        
       // return conca;
    }
    
    
    
    @POST
    @Path("/debitaccount")
    @Produces(MediaType.APPLICATION_JSON)
    public String func_debitaccount(String message) 
    {
       /** cl_AccountInfo a1=new cl_AccountInfo();
        
        JSONObject obj = new JSONObject();
        obj.put("AccountID", String.valueOf(a1.increment()));
        obj.put("Name", "App Shah");
        return obj.toJSONString();**/
        
  
    int chk1=0;
    cl_AccountInfo  a1=new cl_AccountInfo();
        
        
        String fresult="";
        JSONObject obj1 = new JSONObject();
        
        try
        {
             Object obj=JSONValue.parse(message);   
            JSONObject jsonObject = (JSONObject) obj;    
            //getting values form the JSONObject and casting that values into corresponding types  
            String req_id = (String) jsonObject.get("accountid"); 
            // String dbamount = (String) jsonObject.get("debit_amount"); 
             double dbamount = (double) jsonObject.get("debit_amount"); 
         //   fresult=Integer.toString(object_list.size());
            
          /**  for (cl_AccountInfo temp : object_list) 
            {
                    
                  if (temp.get_accountid().equals(req_id))
                  {
                      if (temp.get_acflag()==false)
                      {
                          chk1=2;
                      }
                      else
                      {
                       temp.set_bal(temp.get_bal()-dbamount);
                       obj1.put("accountid", temp.get_accountid());
                       obj1.put("First Name", temp.get_fname());
                       obj1.put("Last Name", temp.get_lname());
                       obj1.put("Balance", temp.get_bal());
                       obj1.put("acflag", temp.get_acflag());
                       fresult=fresult+obj1.toJSONString();
                       chk1=1;
                      }
                  }
            }**/
             
             
             Comparator<cl_AccountInfo> c = new Comparator<cl_AccountInfo>()  
            {  
                    public int compare(cl_AccountInfo u1, cl_AccountInfo u2)  
                    {  
                        return u1.get_accountid().compareTo(u2.get_accountid());  
                    }  
             };  
            
            cl_AccountInfo c2=new cl_AccountInfo();
            c2.accountinfo=req_id;
            int index = Collections.binarySearch(object_list,c2, c);  
            
             if (index >=0)
             {
                 if (object_list.get(index).get_acflag()==false)
                      {
                          chk1=2;
                      }
                      else
                      {
                       object_list.get(index).set_bal(object_list.get(index).get_bal()-dbamount);
                       obj1.put("accountid", object_list.get(index).get_accountid());
                       obj1.put("First Name", object_list.get(index).get_fname());
                       obj1.put("Last Name", object_list.get(index).get_lname());
                       obj1.put("Balance", object_list.get(index).get_bal());
                       obj1.put("acflag", object_list.get(index).get_acflag());
                       obj1.put("timestamp", func_rdate());
                       fresult=fresult+obj1.toJSONString();
                       chk1=1;
                      }
             }
            
        }
        catch (Exception e)
        {
            obj1.put("ErrorCode", "-1");
              obj1.put("ErrorMsg", "Unsuccessful creation as request format not good.");
              
              fresult=obj1.toJSONString();
        }
        
        if (chk1==0)
        {
             obj1.put("ErrorCode", "-1");
              obj1.put("ErrorMsg", "Account not found.");
                 fresult=obj1.toJSONString();
        }
   
           if (chk1==2)
        {
             obj1.put("ErrorCode", "-1");
             obj1.put("ErrorMsg", "Account is disactivated.");
             fresult=obj1.toJSONString();
        }
           System.gc();
       return fresult;
        
        
       // return conca;
    }
    
    
    
     @POST
    @Path("/creditaccount")
    @Produces(MediaType.APPLICATION_JSON)
    public String func_creditaccount(String message) 
    {
       /** cl_AccountInfo a1=new cl_AccountInfo();
        
        JSONObject obj = new JSONObject();
        obj.put("AccountID", String.valueOf(a1.increment()));
        obj.put("Name", "App Shah");
        return obj.toJSONString();**/
        
  
    int chk1=0;
    cl_AccountInfo  a1=new cl_AccountInfo();
        
        
        String fresult="";
        JSONObject obj1 = new JSONObject();
        
        try
        {
             Object obj=JSONValue.parse(message);   
            JSONObject jsonObject = (JSONObject) obj;    
            //getting values form the JSONObject and casting that values into corresponding types  
            String req_id = (String) jsonObject.get("accountid"); 
            // String dbamount = (String) jsonObject.get("debit_amount"); 
             double cramount = (double) jsonObject.get("credit_amount"); 
         //   fresult=Integer.toString(object_list.size());
            
          /**  for (cl_AccountInfo temp : object_list) 
            {
                    
                  if (temp.get_accountid().equals(req_id))
                  {     if (temp.get_acflag()==false)
                      {
                          chk1=2;
                      }
                      else
                        {
                       temp.set_bal(temp.get_bal()+cramount);
                       obj1.put("accountid", temp.get_accountid());
                       obj1.put("First Name", temp.get_fname());
                       obj1.put("Last Name", temp.get_lname());
                       obj1.put("Balance", temp.get_bal());
                       obj1.put("acflag", temp.get_acflag());
                       fresult=fresult+obj1.toJSONString();
                       chk1=1;
                       }
                  }
            }**/
            
             Comparator<cl_AccountInfo> c = new Comparator<cl_AccountInfo>()  
            {  
                    public int compare(cl_AccountInfo u1, cl_AccountInfo u2)  
                    {  
                        return u1.get_accountid().compareTo(u2.get_accountid());  
                    }  
             };  
            
            cl_AccountInfo c2=new cl_AccountInfo();
            c2.accountinfo=req_id;
            int index = Collections.binarySearch(object_list,c2, c);  
            
             if (index >=0)
             {
                  if (object_list.get(index).get_acflag()==false)
                      {
                          chk1=2;
                      }
                      else
                        {
                       object_list.get(index).set_bal(object_list.get(index).get_bal()+cramount);
                       obj1.put("accountid", object_list.get(index).get_accountid());
                       obj1.put("First Name", object_list.get(index).get_fname());
                       obj1.put("Last Name", object_list.get(index).get_lname());
                       obj1.put("Balance", object_list.get(index).get_bal());
                       obj1.put("acflag", object_list.get(index).get_acflag());
                       obj1.put("timestamp", func_rdate());
                       fresult=fresult+obj1.toJSONString();
                       chk1=1;
                       }
             }
            
            
            
            
        }
        catch (Exception e)
        {
            obj1.put("ErrorCode", "-1");
              obj1.put("ErrorMsg", "Unsuccessful creation as request format not good.");
              
              fresult=obj1.toJSONString();
        }
        
        if (chk1==0)
        {
             obj1.put("ErrorCode", "-1");
              obj1.put("ErrorMsg", "Account not found.");
                 fresult=obj1.toJSONString();
        }
        if (chk1==2)
        {
             obj1.put("ErrorCode", "-1");
             obj1.put("ErrorMsg", "Account is disactivated.");
             fresult=obj1.toJSONString();
        }
        System.gc();
       return fresult;
        
        
       // return conca;
    }
    
    
    
    @POST
    @Path("/activateaccount")
    @Produces(MediaType.APPLICATION_JSON)
    public String func_activateaccount(String message) 
    {
       /** cl_AccountInfo a1=new cl_AccountInfo();
        
        JSONObject obj = new JSONObject();
        obj.put("AccountID", String.valueOf(a1.increment()));
        obj.put("Name", "App Shah");
        return obj.toJSONString();**/
        
  
    int chk1=0;
    cl_AccountInfo  a1=new cl_AccountInfo();
        
        
        String fresult="";
        JSONObject obj1 = new JSONObject();
        
        try
        {
             Object obj=JSONValue.parse(message);   
            JSONObject jsonObject = (JSONObject) obj;    
            //getting values form the JSONObject and casting that values into corresponding types  
            String req_id = (String) jsonObject.get("accountid"); 
            // String dbamount = (String) jsonObject.get("debit_amount"); 
            
         //   fresult=Integer.toString(object_list.size());
            
          /**  for (cl_AccountInfo temp : object_list) 
            {
                    
                  if (temp.get_accountid().equals(req_id))
                  {
                       temp.set_acflag(true);
                       obj1.put("accountid", temp.get_accountid());
                       obj1.put("First Name", temp.get_fname());
                       obj1.put("Last Name", temp.get_lname());
                       obj1.put("Balance", temp.get_bal());
                       obj1.put("acflag", temp.get_acflag());
                       fresult=fresult+obj1.toJSONString();
                       chk1=1;
                  }
            }**/
            
            
            Comparator<cl_AccountInfo> c = new Comparator<cl_AccountInfo>()  
            {  
                    public int compare(cl_AccountInfo u1, cl_AccountInfo u2)  
                    {  
                        return u1.get_accountid().compareTo(u2.get_accountid());  
                    }  
             };  
            
            cl_AccountInfo c2=new cl_AccountInfo();
            c2.accountinfo=req_id;
            int index = Collections.binarySearch(object_list,c2, c);  
            
             if (index >=0)
             {
                  object_list.get(index).set_acflag(true);
                       obj1.put("accountid", object_list.get(index).get_accountid());
                       obj1.put("First Name", object_list.get(index).get_fname());
                       obj1.put("Last Name", object_list.get(index).get_lname());
                       obj1.put("Balance", object_list.get(index).get_bal());
                       obj1.put("acflag", object_list.get(index).get_acflag());
                       obj1.put("timestamp", func_rdate());
                       fresult=fresult+obj1.toJSONString();
                       chk1=1;
                  
             }
            
        }
        catch (Exception e)
        {
            obj1.put("ErrorCode", "-1");
              obj1.put("ErrorMsg", "Unsuccessful creation as request format not good.");
              
              fresult=obj1.toJSONString();
        }
        
        if (chk1==0)
        {
             obj1.put("ErrorCode", "-1");
              obj1.put("ErrorMsg", "Account not found.");
                 fresult=obj1.toJSONString();
        }
      System.gc();
       return fresult;
        
        
       // return conca;
    }
    
        
    @POST
    @Path("/dactivateaccount")
    @Produces(MediaType.APPLICATION_JSON)
    public String func_dactivateaccount(String message) 
    {
       /** cl_AccountInfo a1=new cl_AccountInfo();
        
        JSONObject obj = new JSONObject();
        obj.put("AccountID", String.valueOf(a1.increment()));
        obj.put("Name", "App Shah");
        return obj.toJSONString();**/
        
  
    int chk1=0;
    cl_AccountInfo  a1=new cl_AccountInfo();
        
        
        String fresult="";
        JSONObject obj1 = new JSONObject();
        
        try
        {
             Object obj=JSONValue.parse(message);   
            JSONObject jsonObject = (JSONObject) obj;    
            //getting values form the JSONObject and casting that values into corresponding types  
            String req_id = (String) jsonObject.get("accountid"); 
            // String dbamount = (String) jsonObject.get("debit_amount"); 
            
         //   fresult=Integer.toString(object_list.size());
            
           /** for (cl_AccountInfo temp : object_list) 
            {
                    
                  if (temp.get_accountid().equals(req_id))
                  {
                       temp.set_acflag(false);
                       obj1.put("accountid", temp.get_accountid());
                       obj1.put("First Name", temp.get_fname());
                       obj1.put("Last Name", temp.get_lname());
                       obj1.put("Balance", temp.get_bal());
                       obj1.put("acflag", temp.get_acflag());
                       fresult=fresult+obj1.toJSONString();
                       chk1=1;
                  }
            }**/
            
            
             Comparator<cl_AccountInfo> c = new Comparator<cl_AccountInfo>()  
            {  
                    public int compare(cl_AccountInfo u1, cl_AccountInfo u2)  
                    {  
                        return u1.get_accountid().compareTo(u2.get_accountid());  
                    }  
             };  
            
            cl_AccountInfo c2=new cl_AccountInfo();
            c2.accountinfo=req_id;
            int index = Collections.binarySearch(object_list,c2, c);  
            
             if (index >=0)
             {
                  if (object_list.get(index).get_accountid().equals(req_id))
                  {
                       object_list.get(index).set_acflag(false);
                       obj1.put("accountid", object_list.get(index).get_accountid());
                       obj1.put("First Name", object_list.get(index).get_fname());
                       obj1.put("Last Name", object_list.get(index).get_lname());
                       obj1.put("Balance", object_list.get(index).get_bal());
                       obj1.put("acflag", object_list.get(index).get_acflag());
                       obj1.put("timestamp", func_rdate());
                       fresult=fresult+obj1.toJSONString();
                       chk1=1;
                  }
             }
            
        }
        catch (Exception e)
        {
            obj1.put("ErrorCode", "-1");
              obj1.put("ErrorMsg", "Unsuccessful creation as request format not good.");
              
              fresult=obj1.toJSONString();
        }
        
        if (chk1==0)
        {
             obj1.put("ErrorCode", "-1");
              obj1.put("ErrorMsg", "Account not found.");
                 fresult=obj1.toJSONString();
        }
      System.gc();
       return fresult;
        
        
       // return conca;
    }
    
    
    
     @POST
    @Path("/event")
    @Produces(MediaType.APPLICATION_JSON)
    public String func_event(String message) 
    {
         String a;
         a="";
        String path_in_events="C://Users//yeehos//Desktop//anousha//middleware//moneyware//data//events_input.txt";
        String path_out_events="C://Users//yeehos//Desktop//anousha//middleware//moneyware//data//events_output.txt";
        
        String path_out_format="C://Users//yeehos//Desktop//anousha//middleware//moneyware//data//events_out_format.txt";
        
        
        
        List<String> list_input = new ArrayList<>();
        List<String> list_output = new ArrayList<>();
        String conca,word;
      word="";
        conca="";
        
        for (int i=0; i < message.length(  ); i++)
        {
             System.out.println("Char " + i + " is " + message.charAt(i));
             
             char c=message.charAt(i);  
               String s=String.valueOf(c);  
             if ((s).equals(":"))
             {
                 c=message.charAt(i+1);  
                  s=String.valueOf(c);  
                  
                  
                  if ((s).equals("\""))
                  {
                   
                      c=message.charAt(i+2);  
                      s=String.valueOf(c); 
                  
                      
                      
                      int count=2;
                      c=message.charAt(i+2);  
                      s=String.valueOf(c); 
                       
                      while(!((s).equals("\"")))
                      {
                          c=message.charAt(i+count);  
                          s=String.valueOf(c);
                       //   s=s.replace("\"", "");
                          
                        if ((s).equals("\""))
                        {
                        list_input.add(word);
                        word="";
                        }
                        else
                        {
                          //conca=conca+"-"+s; 
                          count=count+1;
                          word=word+s;
                          
                        }
                          
                          
                      }
                      
                      
                  }
             }
        }
        
        for (int i = 0; i < list_input.size(); i++) {
            System.out.println(list_input.get(i));
          //  conca=conca+"-mina"+list_input.get(i); 
            
        }
        
        int linenum,countline;
        linenum=0;
        countline=0;
       
                try{
                    
                    //input

                    File file = new File(path_in_events); 

                    BufferedReader br = new BufferedReader(new FileReader(file)); 

                    String st; 

                    while ((st = br.readLine()) != null) 
                    {
                        countline=countline+1;
                      conca=conca+st; 
                      System.out.println(conca);
                      String [] arr1=st.split(",");
                      
                      int ck1;
                      ck1=0;
                     
                      if (arr1[0].equals(list_input.get(0)))
                      {
                              ck1=1;  
                               a=a+list_input.get(0)+arr1[0]+"HH";
                              
                      }
                      if (arr1[1].equals(list_input.get(1)))
                      {
                              ck1=ck1+1;  
                              a=a+list_input.get(1)+arr1[1]+"HH2";
                      }
                      
                      if (arr1[2].equals(list_input.get(2)))
                      {
                              ck1=ck1+1;     
                              a=a+list_input.get(2)+arr1[2]+"HH3";
                      }
                      
                      if (arr1[3].equals(list_input.get(3)))
                      {
                            ck1=ck1+1;  
                             // linenum=countline;
                              a=a+list_input.get(3)+arr1[3]+"HH4";
                      }
                      
                      if (ck1==4)
                      {
                          linenum=countline;
                          a=a+countline+"HH8";
                         ck1=0;
                      }
                      
                      for (int i = 0; i < list_input.size(); i++) {
                            System.out.println(list_input.get(i));
                            

                        }
                    }
                    
                    
                    //output
                    
                    
                countline=0;
                 file = new File(path_out_events); 

               br = new BufferedReader(new FileReader(file)); 

                  conca="";

                    while ((st = br.readLine()) != null) 
                    {
                        countline=countline+1;
                        if (countline==linenum)
                        {
                                conca=conca+st; 
                                System.out.println(conca);
                                String [] arr1=st.split(",");


                                for (int i = 0; i < arr1.length; i++) {
                                     

                                     // conca=conca+"uu"+arr1[i];
                                      list_output.add(arr1[i]);
                                  }
                        }
                    }
                    
                    
                    
                    
                    //response format
                    
                     countline=0;
                 file = new File(path_out_format); 

               br = new BufferedReader(new FileReader(file)); 

                  conca="";
                  
                
               int g;
              String sprevious="";
               while ((g=br.read())!=-1)
               {
                   char c=(char)g;
                   String s=String.valueOf(c);  
                   
                   
                    
                    if ((s).equals("\""))
                    {
                        
                        if ((sprevious).equals("\""))
                        {
                            if (countline<list_output.size())
                            {
                                conca=conca+list_output.get(countline);
                                countline=countline+1;
                            }


                        }
                                      
                    }
                    
      

                     conca=conca+s;
                     sprevious=s; 
                   
               }

                   /** while ((st = br.readLine()) != null) 
                    {
                       
                        for (int i=0; i < st.length(  ); i++)
                        {
                           
                            
                             char c=st.charAt(i);  
                               String s=String.valueOf(c);  
                               
                             if ((s).equals(":"))
                             {
                                 c=message.charAt(i+1);  
                                  s=String.valueOf(c);  

conca=conca+s;
                                  if ((s).equals("\""))
                                  {
                                        
                                        conca=conca+list_output.get(countline);
                                        countline=countline+1;
                                  }
                             }
                        }
                    
                    
                    
                    
                    
                    }**/
                    
                    
                    
                    
                    
                    
                    
                    
                    


                }

                catch (IOException ie)
                {

                }
                
                
                
                
                
                
       
     /**  String[] arrOfStr = message.split(":"); 
       
       for (String a : arrOfStr) 
       {
            System.out.println(a); 
            if (a.contains(","))
            {
                conca=conca+"-"+a;
            }
       
       }
       **/
       
       
       
       
       
        //return message;
       // return "{\"ResponseStatus\":{\"ReturnCode\":\"0\",\"ErrorMsg\":\"\"},\"ResponseData\":{\"ValidationError\":{\"ErrorCode\":\"\",\"ErrorMsg\":\"\"},\"Get_FX_IndRates_Resp\":{\"FX_IndRates\":{\"DATE_RATES\":{\"CCY_Rate_List\":[{\"CCY\":\"\",\"CCY_NAME\":\"\",\"UNITS\":\"1\",\"TT_Buy\":\"3.971\",\"TT_Sell\":\"4.09\",\"DD_Buy\":\"3.961\",\"DD_Sell\":\"4.09\",\"NT_Buy\":\"3.933\",\"NT_Sell\":\"4.09\",\"Reval_Rate\":\"4.0305\"}]}}}}}";
    // return "\"firstName\": \"Jane\",\"lastName\": \"Doe\",\"companyName\": \"ACME\"";
     
    // return "aaa";
  /**    String conca;
       conca="";
    try{
    
        File file = new File("C://Users//yeehos//Desktop//anousha//middleware//moneyware//New folder//a.txt"); 
  
        BufferedReader br = new BufferedReader(new FileReader(file)); 
      
        String st; 
       
        while ((st = br.readLine()) != null) 
        {
          conca=conca+st; 
          System.out.println(conca);
        }
       
         

    }
    
    catch (IOException ie)
    {
        
    }**/
     //return conca+Integer.toString(linenum);
                return conca;
    
   // return linenum;
    }
    
    
    
    
}


//http://localhost:8080/WebApplication1/webresources/test/getdata