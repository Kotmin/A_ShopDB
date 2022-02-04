package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class FileMenager {
    private static ArrayList<String> commands= new ArrayList<>();
    private static ArrayList<String> temp = new ArrayList<>();
    private static ArrayList<String> login = new ArrayList<>();
    private static ArrayList<String> password = new ArrayList<>();
    private static ArrayList<String> email = new ArrayList<>();
    private static ArrayList<String> lastname = new ArrayList<>();
    private static ArrayList<String> nouns = new ArrayList<>();
    private static ArrayList<String> comp = new ArrayList<>();
    private static ArrayList<String> models = new ArrayList<>();


    public static void simplewrite(){
        File file = new File("pastelist_short.txt");
        try {
            FileWriter fileWriter = new FileWriter(file,true);
            for (int i = 0; i < commands.size(); i++) {
                fileWriter.append(""+ commands.get(i)+'\n');
                 // dobrze by bylo to dac w finally ale zdaje sie byc to zupelnie inna przestrzenia nazw

            }
            fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getCause());
        }
    }
    public static void randomPerson(Integer od_ilu_index,Integer ile_rekordow) //od ktorego indekstu zaczynamy
    {
        Integer hook=od_ilu_index;
        Random rand = new Random();
        try {
            BufferedReader bufferedReader= new BufferedReader(new FileReader("compoundwords.txt"));
            String linia = null;
            while ((linia = bufferedReader.readLine() ) != null){
                comp.add(linia);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Pliku compoundwords.txt nie odnaleziono!");
            System.err.println(e.getCause());
        } catch (IOException e) {
            System.out.println("Blad spowodowany :");
            System.err.println(e.getCause());
        }

        //nouns
        try {
            BufferedReader bufferedReader= new BufferedReader(new FileReader("nouns.txt"));
            String linia = null;
            while ((linia = bufferedReader.readLine() ) != null){
                nouns.add(linia);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Pliku nouns.txt nie odnaleziono!");
            System.err.println(e.getCause());
        } catch (IOException e) {
            System.out.println("Blad spowodowany :");
            System.err.println(e.getCause());
        }
        //names

        try {
            BufferedReader bufferedReader= new BufferedReader(new FileReader("imiona.txt"));
            String linia = null;
            while ((linia = bufferedReader.readLine() ) != null){
                temp.add(linia);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Pliku imiona.txt nie odnaleziono!");
            System.err.println(e.getCause());
        } catch (IOException e) {
            System.out.println("Blad spowodowany :");
            System.err.println(e.getCause());
        }

        //surnames

        try {
            BufferedReader bufferedReader= new BufferedReader(new FileReader("nazwiska.txt"));
            String linia = null;
            while ((linia = bufferedReader.readLine() ) != null){
                lastname.add(linia);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Pliku imiona.txt nie odnaleziono!");
            System.err.println(e.getCause());
        } catch (IOException e) {
            System.out.println("Blad spowodowany :");
            System.err.println(e.getCause());
        }

        //temp.get(rand.nextInt(temp.size()))+" "+temp.get(rand.nextInt(temp.size()))// DLA LOSOWANIA IMION I NAZWISK
        for (int i = 0; i < ile_rekordow; i++) {
            commands.add("INSERT INTO User(ID_user,Login,Password,Email) VALUES("+od_ilu_index.toString()+",'"+comp.get(rand.nextInt(comp.size()))+nouns.get(rand.nextInt(nouns.size()))+od_ilu_index.toString()+"','#HASHEDPASS"+od_ilu_index.toString()+"','"+comp.get(rand.nextInt(comp.size()))+nouns.get(rand.nextInt(nouns.size()))+od_ilu_index.toString()+"@gmail.com');" );
            commands.add("INSERT INTO UserRank(ID_user,ID_Rank) VALUES("+od_ilu_index.toString()+","+"1);");
            if(rand.nextBoolean()){
                commands.add("INSERT INTO RetailCustomer(ID_user,FirstName,LastName) VALUES("+od_ilu_index.toString()+",'"+temp.get(rand.nextInt(temp.size()))+"','"+lastname.get(rand.nextInt(lastname.size()))+"');");
            }
            else
            {
                commands.add("INSERT INTO CorporateCustomer(ID_user,NIP,REGON,TIN,Company_Name) VALUES("+od_ilu_index.toString()+","+Integer.toString(rand.nextInt(899999998)+100000000)+","+Integer.toString(rand.nextInt(89999998)+10000000)+",'"+Integer.toString(rand.nextInt(89999998)+10000000)+"','"+nouns.get(rand.nextInt(nouns.size()))+".INC');");
            }
            commands.add("INSERT INTO Address(ID_Address,Number,Street,City,Province,Postalcode,ID_user,ID_Country) VALUES("+od_ilu_index.toString()+",'"+Integer.toString(rand.nextInt(9998))+"','"+comp.get(rand.nextInt(comp.size()))+" Street','"+nouns.get(rand.nextInt(nouns.size()))+" Town','"+comp.get(rand.nextInt(comp.size()))+"','"+Integer.toString(rand.nextInt(98))+"-"+Integer.toString(rand.nextInt(998))+"',"+od_ilu_index.toString()+","+Integer.toString(rand.nextInt(10)+1)+");");
            od_ilu_index+=1;
        }

///////////////////////////

        //tu zaczynamy obsluge produktow
//        od_ilu_index=hook;
//        for (int i = 0; i < ile_rekordow; i++) {
//            commands.add("INSERT INTO UserRank(ID_product) VALUES("+od_ilu_index.toString()+
//        }


        //tusobiesprytnie zakomentujemy



        Integer zzz=0;
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 20; j++) {
                commands.add("INSERT INTO Model(ID_model,Name_model,ID_product,Price,Quantity) VALUES("+zzz.toString()+",'"+zzz.toString()+"',"+Integer.valueOf(i)+","+Integer.valueOf(rand.nextInt(5000)+900)+",1);");
                commands.add("INSERT INTO Attribute(Name_attribute,Value,ID_model) VALUES("+"'RAM','"+Integer.toString(rand.nextInt(128))+"',"+zzz.toString()+");");
                zzz++;
                if(i>=14 && i<=18)
                    if (rand.nextBoolean())
                        commands.add("INSERT INTO Attribute(Name_attribute,Value,ID_model) VALUES("+"'Strap','leather',"+zzz.toString()+");");
                    else
                        commands.add("INSERT INTO Attribute(Name_attribute,Value,ID_model) VALUES("+"'Strap','plastic',"+zzz.toString()+");");

            }
        }

        for (int i = 0; i < 30; i++) {
            commands.add("INSERT INTO ShoppingCart(ID_user,ID_model,Quantity) VALUES("+Integer.toString(rand.nextInt(100)+1)+","+Integer.toString(rand.nextInt(399)+1)+","+Integer.toString(rand.nextInt(3)+1)+");");
        }
        for (int i = 0; i < 50; i++) {
            commands.add("INSERT INTO OrderHeader(ID_Order,OrderDate,DeliveryCost,ID_user,ID_delivery,ID_payment,ID_status,ID_Address) VALUES("+ Integer.toString(i)+",sysdate,0,"+Integer.toString(rand.nextInt(100)+1)+","+Integer.toString(rand.nextInt(4))+","+Integer.toString(rand.nextInt(6))+","+Integer.toString(rand.nextInt(7))+","+Integer.toString(rand.nextInt(100))+");");
            commands.add("INSERT INTO OrderDetail(ID_Order,ID_model,Quantity,DiscountAmout,TransationPrice,DeliveryDiscount) VALUES("+ Integer.toString(i)+","+Integer.toString(rand.nextInt(400))+",1,0,"+Integer.toString(rand.nextInt(10000))+",0);");
        }
        for (int i = 0; i < 20; i++) {
            commands.add("INSERT INTO OrderDetail(ID_Order,ID_model,Quantity,DiscountAmout,TransationPrice,DeliveryDiscount) VALUES("+ Integer.toString(rand.nextInt(10))+","+Integer.toString(rand.nextInt(400))+",1,0,"+Integer.toString(rand.nextInt(10000))+",0);");
        }
    }


}
