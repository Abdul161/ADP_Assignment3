/*
 * Abdul Aleem Chilwan
 * 220108447
 * ReadFile.java
 * Reading SER file and manipulating the data to show correct output
 * whilst displaying it on a txt file
 * 9th June 2021
 */
package za.ac.cput.assignment3;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;


public class ReadFile {
    int allowedCarRent;
    int notAllowedCarRent;
    
ObjectInputStream Input;
FileWriter customerWriter,supplierWriter;

BufferedWriter bfr,bfr2;
ArrayList<Customer> customer= new ArrayList<>();
ArrayList<Supplier> supplier= new ArrayList<>();
    
    public void openFile(){
    try{
    Input = new ObjectInputStream( new FileInputStream("stakeholder.ser"));
        System.out.println("File: Client.ser successfully created");
    }
    catch(IOException ioe){
        System.out.println("Error Opening File" + ioe.getMessage());
    }
    }
   
    
    public void readFromFile(){
        try{
           while(true){
               Object object = Input.readObject();
               String cust ="Customer";
               String sup = "Supplier";
               String name = object.getClass().getSimpleName();
               if ( name.equals(cust)){
                   customer.add((Customer)object);
               } else if ( name.equals(sup)){
                   supplier.add((Supplier)object);
               } 
               
               else {
                   System.out.println("Does not work");
               }
           }
        }
        catch (EOFException eofe) {
            System.out.println("End of the ser file reached");
        }
        catch (ClassNotFoundException ioe) {
            System.out.println("Error reading ser file: "+ ioe);
        }
        catch (IOException ioe) {
            System.out.println("Error reading ser file: "+ ioe);
        }
        
        sortBothMethods();
        
    }
    
    public void sortBothMethods(){
        String[] sortingCust = new String[customer.size()];
        String[] sortingSupplier= new String[supplier.size()];
        ArrayList<Customer> sortingCustomer= new ArrayList<>();
        ArrayList<Supplier> sortSupplierArray= new ArrayList<>();
        int count = customer.size();
        int icount = supplier.size();
        for (int i = 0; i < count; i++) {
            sortingCust[i] = customer.get(i).getStHolderId();
        }
        Arrays.sort(sortingCust);
        
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                if (sortingCust[i].equals(customer.get(j).getStHolderId())){
                    sortingCustomer.add(customer.get(j));
                }
            }
        }
       
        customer = sortingCustomer;
        
        for(int i= 0; i<icount;i++){
            sortingSupplier[i]= supplier.get(i).getName();
        }
        Arrays.sort(sortingSupplier);
        
        for (int i=0; i<icount;i++){
            for (int j=0; j< icount;j++){
                if(sortingSupplier[i].equals(supplier.get(j).getName())){
                    sortSupplierArray.add(supplier.get(j));
                }
            }
        }
       supplier= sortSupplierArray;
    }
    
    public void allowedToRent(){
          // String[] sortingCust = new String[customer.size()];
          boolean canRent;
        //ArrayList<Customer> sortingCustomer= new ArrayList<>();
        int count = customer.size();
         for (int i = 0; i < count; i++) {
            canRent = customer.get(i).getCanRent();
            if (canRent == true){
            allowedCarRent++;}
            if (canRent == false){
            notAllowedCarRent++;}
        }
    
    }
    
    public void writeCustomer(){
        try{
            customerWriter = new FileWriter("customerOutFile.txt");
          
            bfr = new BufferedWriter(customerWriter);
            
            bfr.write(String.format("%-10s \n", "=================== CUSTOMERS ========================"));
            
            bfr.write(String.format("%-5s %-10s %-12s %-15s %-15s\n", "ID","Name","Surname","Date of Birth","Age"));
             
            bfr.write(String.format("%-10s \n", "======================================================"));
            
            for (int i = 0; i < customer.size(); i++) {
                bfr.write(String.format("%-5s %-10s %-12s \n", customer.get(i).getStHolderId(), customer.get(i).getFirstName(), customer.get(i).getSurName()));
            }
         
            
            
            bfr.write(String.format("%-10s \n", "\nNumber of customers allowed to rent:       " +allowedCarRent));
            bfr.write(String.format("%-10s \n", "Number of customers not allowed to rent:   " +notAllowedCarRent));
           
          
        }
        catch(IOException fnfe )
        {
            System.out.println(fnfe);
            System.exit(1);
        }
        try{
            bfr.close( ); 
        }
        catch (IOException ioe){            
            System.out.println("error closing text file: " + ioe.getMessage());
            System.exit(1);
        }
    }
    
     public void writeSupplier(){
        try{
            
            supplierWriter= new FileWriter("supplierOutFile.txt");
           
            bfr2 = new BufferedWriter(supplierWriter);
           
            bfr2.write(String.format("%-10s \n", "========================== SUPPLIERS ============================="));
            
            bfr2.write(String.format("%-5s %-20s %-15s %-15s\n", "ID","Product Name","Product Type","Product Description"));
            
            bfr2.write(String.format("%-10s \n", "=================================================================="));
            
            for (int i = 0; i < supplier.size(); i++) {
                bfr2.write(String.format("%-5s %-20s %-15s %-15s \n", supplier.get(i).getStHolderId(), supplier.get(i).getName(), supplier.get(i).getProductType(),supplier.get(i).getProductDescription()));
            }
          
        }
        catch(IOException exc )
        {
            System.out.println(exc);
            System.exit(1);
        }
        try{
            bfr2.close( ); 
        }
        catch (IOException ioe){            
            System.out.println("error closing text file: " + ioe.getMessage());
            System.exit(1);
        }
    }
     
       public void closeFile(){
   try{
   Input.close();
   }
   catch(IOException ioe){
       System.out.println("Error closing the file " + ioe.getMessage());
   }
    }
    
}
