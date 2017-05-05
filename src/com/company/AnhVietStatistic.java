package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

/**
 * Created by MyPC on 3/23/2017.
 */
public class AnhVietStatistic extends JFrame {

    JScrollPane panel;
    JPanel titlePanel;
    Container contentPane;
    JPanel backPanel;
    JList list;
    DefaultListModel frequencyList = new DefaultListModel();

    JComboBox<Integer> day = new JComboBox<>();
    JComboBox<Integer> month = new JComboBox<>();
    JComboBox<Integer> year = new JComboBox<>();
    JComboBox<Integer> dayTo = new JComboBox<>();
    JComboBox<Integer> monthTo = new JComboBox<>();
    JComboBox<Integer> yearTo = new JComboBox<>();

    public AnhVietStatistic(){
        contentPane = this.getContentPane();
        contentPane.setLayout(null);
        titlePanel = new JPanel();
        backPanel = new JPanel();
        ImageIcon iconBack = new ImageIcon("iconBack.png");
        Image image = iconBack.getImage();
        Image newImage = image.getScaledInstance(24,24, Image.SCALE_SMOOTH);
        iconBack = new ImageIcon(newImage);

        JButton btnBack = new JButton(iconBack);
        btnBack.setBounds(10,10,30,30);
        backPanel.setBounds(10,10,40,40);
        backPanel.add(btnBack);

        titlePanel.setBounds(4,50,250,90);



        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AnhVietLayout layout = null;
                layout = new AnhVietLayout();
                layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                layout.setVisible(true);
                layout.setSize(400, 350);
                AnhVietStatistic.this.setVisible(false);
            }
        });

        JLabel label = new JLabel("Favourite words List");
        label.setBounds(0,40,400,30);
        label.setForeground(new Color(198, 40, 20));
        label.setFont(new Font("Papyrus", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.TOP);
        backPanel.add(label);

        JLabel labelFrom = new JLabel("Từ ngày: ");
        labelFrom.setBounds(10,60,60,20);

        titlePanel.add(labelFrom);

        day.setBounds(80,60,80,50);

        day.addItem(1);day.addItem(2);day.addItem(3);day.addItem(4);day.addItem(5);day.addItem(6);day.addItem(7);
        day.addItem(8);day.addItem(9);day.addItem(10);day.addItem(11);day.addItem(12);day.addItem(13);day.addItem(14);
        day.addItem(15);day.addItem(16);day.addItem(17);day.addItem(18);day.addItem(19);day.addItem(20);day.addItem(21);
        day.addItem(22);day.addItem(23);day.addItem(24);day.addItem(25);day.addItem(26);day.addItem(27);day.addItem(28);
        day.addItem(29);day.addItem(30);day.addItem(31);

        titlePanel.add(day);

        month.setBounds(170,60,80,50);

        month.addItem(1);month.addItem(2);month.addItem(3);month.addItem(4);month.addItem(5);month.addItem(6);
        month.addItem(7);month.addItem(8);month.addItem(9);month.addItem(10);month.addItem(11);month.addItem(12);

        titlePanel.add(month);


        day.setBounds(260,60,80,50);

        year.addItem(2000);year.addItem(2001);year.addItem(2002);year.addItem(2003);year.addItem(2004);year.addItem(2005);year.addItem(2006);
        year.addItem(2007);year.addItem(2008);year.addItem(2009);year.addItem(2010);year.addItem(2011);year.addItem(2012);
        year.addItem(2013);year.addItem(2014);year.addItem(2015);year.addItem(2016);year.addItem(2017);

        titlePanel.add(year);


              JLabel labelTo = new JLabel("Đến ngày: ");
        labelTo.setBounds(10,100,60,20);

        titlePanel.add(labelTo);

        day.setBounds(80,100,80,50);

        dayTo.addItem(1);dayTo.addItem(2);dayTo.addItem(3);dayTo.addItem(4);dayTo.addItem(5);dayTo.addItem(6);dayTo.addItem(7);
        dayTo.addItem(8);dayTo.addItem(9);dayTo.addItem(10);dayTo.addItem(11);dayTo.addItem(12);dayTo.addItem(13);dayTo.addItem(14);
        dayTo.addItem(15);dayTo.addItem(16);dayTo.addItem(17);dayTo.addItem(18);dayTo.addItem(19);dayTo.addItem(20);dayTo.addItem(21);
        dayTo.addItem(22);dayTo.addItem(23);dayTo.addItem(24);dayTo.addItem(25);dayTo.addItem(26);dayTo.addItem(27);dayTo.addItem(28);
        dayTo.addItem(29);dayTo.addItem(30);dayTo.addItem(31);

        titlePanel.add(dayTo);


        month.setBounds(170,100,80,50);

        monthTo.addItem(1);monthTo.addItem(2);monthTo.addItem(3);monthTo.addItem(4);monthTo.addItem(5);monthTo.addItem(6);
        monthTo.addItem(7);monthTo.addItem(8);monthTo.addItem(9);monthTo.addItem(10);monthTo.addItem(11);monthTo.addItem(12);


        titlePanel.add(monthTo);


        day.setBounds(260,100,80,50);

        yearTo.addItem(2000);yearTo.addItem(2001);yearTo.addItem(2002);yearTo.addItem(2003);yearTo.addItem(2004);yearTo.addItem(2005);yearTo.addItem(2006);
        yearTo.addItem(2007);yearTo.addItem(2008);yearTo.addItem(2009);yearTo.addItem(2010);yearTo.addItem(2011);yearTo.addItem(2012);
        yearTo.addItem(2013);yearTo.addItem(2014);yearTo.addItem(2015);yearTo.addItem(2016);yearTo.addItem(2017);

        titlePanel.add(yearTo);



        JButton btnStatistic = new JButton("Thống kê");
        btnStatistic.setBounds(350,150,60,30);
        titlePanel.add(btnStatistic);
        list = new JList(frequencyList);
        btnStatistic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frequencyList.clear();
                process();
                list.setModel(frequencyList);
            }
        });


        list.setVisibleRowCount(20);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //list.setBounds(30,30,100,100);
        panel = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.setBounds(10,160,300,300);
        contentPane.add(panel);

        //panel.setLayout(null);

        contentPane.add(backPanel);
        contentPane.add(titlePanel);
    }

    void process(){

        String fromDate = String.valueOf(year.getSelectedItem()) + "-";
        if (Integer.valueOf((Integer) month.getSelectedItem())>9){
            fromDate+=String.valueOf(month.getSelectedItem());
        }
        else {
            fromDate+="0"+ String.valueOf(month.getSelectedItem());
        }
         fromDate +="-";
        if (Integer.valueOf((Integer) day.getSelectedItem())>9){
            fromDate+=String.valueOf(day.getSelectedItem());
        }
        else {
            fromDate+="0"+ String.valueOf(day.getSelectedItem());
        }


        String toDate = String.valueOf(yearTo.getSelectedItem()) + "-";
        if (Integer.valueOf((Integer) monthTo.getSelectedItem())>9){
            toDate+=String.valueOf(monthTo.getSelectedItem());
        }
        else {
            toDate+="0"+ String.valueOf(monthTo.getSelectedItem());
        }
        toDate +="-";
        if (Integer.valueOf((Integer) dayTo.getSelectedItem())>9){
            toDate+=String.valueOf(dayTo.getSelectedItem());
        }
        else {
            toDate+="0"+ String.valueOf(dayTo.getSelectedItem());
        }

        if (isLegalDate(fromDate) && isLegalDate(toDate)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0 ; i < Main.frequentWordAnhVietList.size();i++){
                Word temp = Main.frequentWordAnhVietList.get(i);
                for (int j = 0 ; j <temp.getListSearchedDate().size();j++){
                    try {
                        System.out.println(!sdf.parse(fromDate).after(sdf.parse(temp.getListSearchedDate().get(j))));

                        System.out.println(!sdf.parse(toDate).before(sdf.parse(temp.getListSearchedDate().get(j))));

                        if (!sdf.parse(fromDate).after(sdf.parse(temp.getListSearchedDate().get(j))) && !sdf.parse(toDate).before(sdf.parse(temp.getListSearchedDate().get(j)))){
                            frequencyList.addElement(temp.getWord() + "---Frequency: "+String.valueOf(temp.getFrequency()));
                            break;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }


        }
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {

                    PrintWriter writer = new PrintWriter("VietAnhFrequency.txt");
                    BufferedWriter out = new BufferedWriter(writer);
                    for (int i = 0; i < Main.frequentWordVietAnhList.size();i++){
                        out.write(Main.frequentWordVietAnhList.get(i).getWord());
                        out.newLine();
                        out.write(String.valueOf(Main.frequentWordVietAnhList.get(i).getFrequency()));
                        out.newLine();
                        out.write(String.valueOf(Main.frequentWordVietAnhList.get(i).getListSearchedDate().size()));
                        out.newLine();

                        for (int  j = 0 ; j < Main.frequentWordVietAnhList.get(i).getListSearchedDate().size();j++){
                            out.write(Main.frequentWordVietAnhList.get(i).getListSearchedDate().get(j));
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

                    PrintWriter writer2 = new PrintWriter("VietAnhFavourite.txt");
                    BufferedWriter out2 = new BufferedWriter(writer2);
                    for (int i = 0; i < VietAnhFavouriteLayout.favouriteStudentList.size();i++){
                        out2.write(VietAnhFavouriteLayout.favouriteStudentList.get(i));
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

    boolean isLegalDate(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        return sdf.parse(s, new ParsePosition(0)) != null;
    }
}

