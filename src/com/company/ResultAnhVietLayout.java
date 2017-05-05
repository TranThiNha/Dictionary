package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;

/**
 * Created by MyPC on 3/19/2017.
 */
public class ResultAnhVietLayout extends JFrame {

    public static int FLAG = 1; //1: AnhVietLayout, 2:AnhVietFavouriteLayout

    JPanel panel;
    JButton btnBack;
    JButton btnFavourite;
    JScrollPane scrollPane;
    Container container;
    public static String key;
    int indexOfSelectedWord = -1;


    public ResultAnhVietLayout(){
        container = this.getContentPane();
        container.setLayout(null);
        panel = new JPanel();
        panel.setLayout(null);

        panel.setBounds(0,0,400,40);
        JTextArea content = new JTextArea();
        content.setBounds(10,45,400,400);
        content.setWrapStyleWord(true);
        content.setLineWrap(true);
        //content.setVerticalAlignment(SwingConstants.TOP);


        if (key!=null){

            boolean check = false;
            for (int i =0;i < Main.listAnhViet.size() ;i++){
                if (key.equalsIgnoreCase(Main.listAnhViet.get(i).getWord())){
                    content.setText(Main.listAnhViet.get(i).getMeanWord());
                    int index = isExist(key);
                    Date d = new Date();
                    String dateWithoutTime =  d.toString().substring(24,28) + "-"+convertToMonthNumber(d.toString().substring(4,7))+"-"+ d.toString().substring(8, 10);
                    Word temp;
                    if (index > -1){
                        temp = Main.frequentWordAnhVietList.get(index);
                        Main.frequentWordAnhVietList.remove(index);
                    }
                    else {
                        temp = Main.listAnhViet.get(i);
                    }
                    temp.getListSearchedDate().add(dateWithoutTime);
                    temp.addFrequency();
                    Main.frequentWordAnhVietList.add(temp);

                    indexOfSelectedWord = i;
                    check = true;
                    break;
                }
            }
            if (!check){
                content.setText("Not found");
            }
        }


        ImageIcon iconBack = new ImageIcon("iconBack.png");
        Image image = iconBack.getImage();
        Image newImage = image.getScaledInstance(24,24, Image.SCALE_SMOOTH);
        iconBack = new ImageIcon(newImage);

        ImageIcon iconFavourite = new ImageIcon("iconFavourite.png");
        image = iconFavourite.getImage();
        newImage = image.getScaledInstance(24,24, Image.SCALE_SMOOTH);
        iconFavourite = new ImageIcon(newImage);

        ImageIcon iconNotFavourite = new ImageIcon("iconNotFavourite.png");
        image = iconNotFavourite.getImage();
        newImage = image.getScaledInstance(24,24, Image.SCALE_SMOOTH);
        iconNotFavourite = new ImageIcon(newImage);

        btnBack = new JButton(iconBack);
        btnBack.setBounds(10,8,30,30);

        panel.add(btnBack);

        btnFavourite = new JButton(iconNotFavourite);
        btnFavourite.setBounds(300,8,30,30);

        panel.add(btnFavourite);

        container.add(panel);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (FLAG == 1) {

                    AnhVietLayout layout = null;
                    layout = new AnhVietLayout();
                    layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    layout.setVisible(true);
                    layout.setSize(400, 350);
                    ResultAnhVietLayout.this.setVisible(false);
                }
                else if (FLAG == 2){
                    AnhVietFavouriteLayout layout = null;
                    layout = new AnhVietFavouriteLayout();
                    layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    layout.setVisible(true);
                    layout.setSize(400,550);
                    ResultAnhVietLayout.this.setVisible(false);
                }
            }
        });


        if (indexOfSelectedWord > -1 && isFavourited(Main.listAnhViet.get(indexOfSelectedWord).getWord())){
            btnFavourite.setIcon(iconFavourite);
        }
        else {
            btnFavourite.setIcon(iconNotFavourite);
        }

        ImageIcon finalIconNotFavourite = iconNotFavourite;
        ImageIcon finalIconFavourite = iconFavourite;
        btnFavourite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (indexOfSelectedWord > -1 && isFavourited(Main.listAnhViet.get(indexOfSelectedWord).getWord())) {
                    Main.listAnhViet.get(indexOfSelectedWord).setFavourited(false);
                    AnhVietFavouriteLayout.favouriteStudentList.remove(getIndexOfSelectedElenmentInFavouriteList(Main.listAnhViet.get(indexOfSelectedWord).getWord()));
                    btnFavourite.setIcon(finalIconNotFavourite);
                }
                else if (indexOfSelectedWord > - 1){
                    AnhVietFavouriteLayout.favouriteStudentList.add(Main.listAnhViet.get(indexOfSelectedWord).getWord());
                    Main.listAnhViet.get(indexOfSelectedWord).setFavourited(true);
                    btnFavourite.setIcon(finalIconFavourite);
                }
            }
        });

        scrollPane = new JScrollPane(content,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10,70,370,420);
        container.add(scrollPane);


        this.add(panel);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    try {

                        PrintWriter writer = new PrintWriter("AnhVietFrequency.txt");
                        BufferedWriter out = new BufferedWriter(writer);
                        for (int i = 0; i < Main.frequentWordAnhVietList.size();i++){
                            out.write(Main.frequentWordAnhVietList.get(i).getWord());
                            out.newLine();
                            out.write(String.valueOf(Main.frequentWordAnhVietList.get(i).getFrequency()));
                            out.newLine();
                            out.write(String.valueOf(Main.frequentWordAnhVietList.get(i).getListSearchedDate().size()));
                            out.newLine();

                            for (int  j = 0 ; j < Main.frequentWordAnhVietList.get(i).getListSearchedDate().size();j++){
                                out.write(Main.frequentWordAnhVietList.get(i).getListSearchedDate().get(j));
                                out.newLine();
                            }
                        }
                        out.flush();
                        out.close();

                        PrintWriter writer1 = new PrintWriter("VietAnhFrequency.txt");
                        BufferedWriter out1 = new BufferedWriter(writer1);
                        for (int i = 0; i < Main.frequentWordVietAnhList.size();i++){
                            out1.write(Main.frequentWordVietAnhList.get(i).getWord());
                            out1.newLine();
                            out1.write(String.valueOf(Main.frequentWordVietAnhList.get(i).getFrequency()));
                            out1.newLine();
                            out1.write(String.valueOf(Main.frequentWordVietAnhList.get(i).getListSearchedDate().size()));
                            out1.newLine();

                            for (int  j = 0 ; j < Main.frequentWordVietAnhList.get(i).getListSearchedDate().size();j++){
                                out1.write(Main.frequentWordVietAnhList.get(i).getListSearchedDate().get(j));
                                out1.newLine();
                            }
                        }
                        out1.flush();
                        out1.close();

                        PrintWriter writer2 = new PrintWriter("AnhVietFavourite.txt");
                        BufferedWriter out2 = new BufferedWriter(writer2);
                        for (int i = 0; i < AnhVietFavouriteLayout.favouriteStudentList.size();i++){
                            out2.write(AnhVietFavouriteLayout.favouriteStudentList.get(i));
                            out2.newLine();
                        }
                        out2.flush();
                        out2.close();

                        PrintWriter writer3 = new PrintWriter("VietAnhFavourite.txt");
                        BufferedWriter out3 = new BufferedWriter(writer3);
                        for (int i = 0; i < VietAnhFavouriteLayout.favouriteStudentList.size();i++){
                            out3.write(VietAnhFavouriteLayout.favouriteStudentList.get(i));
                            out3.newLine();
                        }
                        out3.flush();
                        out3.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                }

        });

    }

    static int getIndexOfSelectedElenmentInFavouriteList(String name){
        for (int i = 0; i < AnhVietFavouriteLayout.favouriteStudentList.size();i++){
           if ( AnhVietFavouriteLayout.favouriteStudentList.get(i).equals(name)){
               return i;
           }
        }
        return -1;
    }

    int isExist(String word){
        if (Main.frequentWordAnhVietList.size()> 0)
        {for (int  i =0 ; i < Main.frequentWordAnhVietList.size();i++){
            if (Main.frequentWordAnhVietList.get(i).getWord().equals(word)){
                return i;
            }
        }}
        return -1;
    }

    boolean isFavourited(String word){
        for (int i = 0 ; i < AnhVietFavouriteLayout.favouriteStudentList.size();i++){
            if (AnhVietFavouriteLayout.favouriteStudentList.get(i).equals(word)){
                return true;
            }
        }
        return false;
    }

    String convertToMonthNumber(String month){
        if (month.equals("Jan"))
            return "01";
        else  if (month.equals("Feb"))
            return "02";
        else  if (month.equals("Mar"))
            return "03";
        else  if (month.equals("Apr"))
            return "04";
        else  if (month.equals("May"))
            return "05";
        else  if (month.equals("Jun"))
            return "06";
        else  if (month.equals("Jul"))
            return "07";
        else  if (month.equals("Agu"))
            return "08";
        else  if (month.equals("Sep"))
            return "09";
        else  if (month.equals("Oct"))
            return "10";
        else  if (month.equals("Nov"))
            return "11";
        else  if(month.equals("Dec"))
            return "12";
        else return "13";
    }

}
