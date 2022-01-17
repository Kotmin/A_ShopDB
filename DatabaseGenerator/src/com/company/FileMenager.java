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

    public static void simplewrite(){
        File file = new File("pastelist.txt");
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
        //temp.get(rand.nextInt(temp.size()))+" "+temp.get(rand.nextInt(temp.size()))// DLA LOSOWANIA IMION I NAZWISK
        for (int i = 0; i < ile_rekordow; i++) {
            commands.add("INSERT INTO User(ID_user,Login,Password,Email) VALUES("+od_ilu_index.toString()+",'"+comp.get(rand.nextInt(comp.size()))+nouns.get(rand.nextInt(nouns.size()))+od_ilu_index.toString()+"','#HASHEDPASS"+od_ilu_index.toString()+"','"+comp.get(rand.nextInt(comp.size()))+nouns.get(rand.nextInt(nouns.size()))+od_ilu_index.toString()+"@gmail.com');" );
            commands.add("INSERT INTO UserRank(ID_user,ID_Rank) VALUES("+od_ilu_index.toString()+","+"1);f");
            od_ilu_index+=1;
        }

    }


}
