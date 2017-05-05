package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by MyPC on 3/18/2017.
 */
public class AnhVietLayout extends JFrame {

    JPanel borderLayout;
    JLabel label;
    JButton btnChange;
    JButton btnFavourite;
    JButton btnStatistic;
    public AnhVietLayout() {

        borderLayout = new JPanel();
        borderLayout.setLayout(null);

        ImageIcon iconDic = new ImageIcon("iconDictionary.png");
        Image image = iconDic.getImage();
        Image newImage = image.getScaledInstance(60,60,Image.SCALE_SMOOTH);
        ImageIcon IconDic = new ImageIcon(newImage);

        label = new JLabel();
        label.setIcon(IconDic);
        label.setBounds(170,0, 60, 80);

        JLabel labelTitle = new JLabel("WELCOME TO DICTIONARY");
        labelTitle.setBounds(120,50,200,40);
        labelTitle.setForeground(new Color(198, 40, 20));

        ImageIcon iconSearch = new ImageIcon("icon.png");
        image = iconSearch.getImage();
        newImage = image.getScaledInstance(35,35,Image.SCALE_SMOOTH);
        iconSearch = new ImageIcon(newImage);


        JButton btnSearch = new JButton(iconSearch);
        btnSearch.setBounds(290,100,30,30);

        borderLayout.add(btnSearch);
        borderLayout.add(label);
        borderLayout.add(labelTitle);


        JEditorPane jEditorPane = new JEditorPane();
        jEditorPane.setBounds(40,100,250,30);
        //jEditorPane.hi("từ điển Anh- Việt")

        borderLayout.add(jEditorPane);

        btnChange = new JButton("Từ điển Việt - Anh");
        btnChange.setBounds(40,160,280,30);

        borderLayout.add(btnChange);


        btnFavourite = new JButton("Từ yêu thích");
        btnFavourite.setBounds(40,200,280,30);

        borderLayout.add(btnFavourite);

        btnStatistic = new JButton("Thống kê");
        btnStatistic.setBounds(40,240,280,30);

        borderLayout.add(btnStatistic);

        btnStatistic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AnhVietStatistic layout = null;
                layout = new AnhVietStatistic();
                layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                layout.setVisible(true);
                layout.setSize(350,550);
                AnhVietLayout.this.setVisible(false);
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ResultAnhVietLayout.FLAG = 1;
                ResultAnhVietLayout.key = jEditorPane.getText();
                ResultAnhVietLayout layout = null;
                layout = new ResultAnhVietLayout();
                layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                layout.setVisible(true);
                layout.setSize(400,550);
                AnhVietLayout.this.setVisible(false);
            }
        });

        btnFavourite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AnhVietFavouriteLayout layout = null;
                layout = new AnhVietFavouriteLayout();
                layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                layout.setVisible(true);
                layout.setSize(400,500);
                AnhVietLayout.this.setVisible(false);
            }
        });

        btnChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VietAnhLayout layout = null;
                layout = new VietAnhLayout();
                layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                layout.setVisible(true);
                layout.setSize(400,350);
                AnhVietLayout.this.setVisible(false);
            }
        });

        this.add(borderLayout);

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

}
