package com.company;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static javax.script.ScriptEngine.FILENAME;

public class Main {

    public static List<Word> frequentWordAnhVietList = new ArrayList<>();
    public static List<Word> frequentWordVietAnhList = new ArrayList<>();
    public static List<Word> listAnhViet;
    public static List<Word> listVietAnh;

    public static void main(String[] args) {

        listAnhViet = new ArrayList<>();
        ListWord listWord = new ListWord("Anh_Viet.xml");
        listAnhViet = listWord.getList();

        listVietAnh = new ArrayList<>();
        ListWord listWord2 = new ListWord("Viet_Anh.xml");
        listVietAnh = listWord2.getList();

        readFile("AnhVietFavourite.txt", AnhVietFavouriteLayout.favouriteStudentList);
        readFile("VietAnhFavourite.txt",VietAnhFavouriteLayout.favouriteStudentList);


        readFrequency("AnhVietFrequency.txt", frequentWordAnhVietList);
        readFrequency("VietAnhFrequency.txt",frequentWordVietAnhList);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AnhVietLayout layout = null;
                layout = new AnhVietLayout();
                layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                layout.setVisible(true);
                layout.setSize(400,350);
            }
        });







    }

    static void readFile(String fileName, Vector<String> list){
        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(fileName));

            while ((sCurrentLine = br.readLine()) != null) {
                list.add(sCurrentLine);
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
    }

    static void readFrequency(String fileName,List<Word> list){
        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(fileName));

            while ((sCurrentLine = br.readLine()) != null) {
                Word word = new Word();
                word.setWord(sCurrentLine);
                sCurrentLine = br.readLine();
                word.setFrequency(Integer.valueOf(sCurrentLine));
                sCurrentLine = br.readLine();
                int size = Integer.valueOf(sCurrentLine);
                for (int i = 0 ; i <size;i++){
                    sCurrentLine = br.readLine();
                    word.getListSearchedDate().add(sCurrentLine);
                }
                list.add(word);
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
    }

}
