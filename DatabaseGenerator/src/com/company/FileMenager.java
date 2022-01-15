package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class FileMenager {
    private static ArrayList<String> commands= new ArrayList<>();
    private static ArrayList<String> temp = new ArrayList<>();

    public static void simplewrite(){
        File file = new File("pastelist.txt");
        try {
            FileWriter fileWriter = new FileWriter(file,true);
            for (int i = 0; i < commands.size(); i++) {
                fileWriter.append("INSERT HEHE "+ commands.get(i)+'\n');
                 // dobrze by bylo to dac w finally ale zdaje sie byc to zupelnie inna przestrzenia nazw

            }
            fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getCause());
        }
    }
    public static void randomPerson()
    {
        Random rand = new Random();
        try {
            BufferedReader bufferedReader= new BufferedReader(new FileReader("imiona.txt"));
            String linia = null;
            while ((linia = bufferedReader.readLine() ) != null){
                temp.add(linia);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Pliku nie odnaleziono!");
            System.err.println(e.getCause());
        } catch (IOException e) {
            System.out.println("Blad spowodowany :");
            System.err.println(e.getCause());
        }

        for (int i = 0; i < 10; i++) {
            commands.add(temp.get(rand.nextInt(temp.size()))+" "+temp.get(rand.nextInt(temp.size())));
        }

    }


}
