package com.company;

/**
 * Created by MyPC on 3/22/2017.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

/**
 * Created by MyPC on 3/20/2017.
 */
public class VietAnhFavouriteLayout extends JFrame {

    JScrollPane panel;
    JPanel titlePanel;
    Container contentPane;
    JPanel backPanel;

    public static Vector<String> favouriteStudentList = new Vector<>();
    public VietAnhFavouriteLayout(){
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
        backPanel.setBounds(150,10,40,40);
        backPanel.add(btnBack);

        titlePanel.setBounds(20,50,300,50);



        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VietAnhLayout layout = null;
                layout = new VietAnhLayout();
                layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                layout.setVisible(true);
                layout.setSize(400, 350);
                VietAnhFavouriteLayout.this.setVisible(false);
            }
        });

        JLabel label = new JLabel("Favourite words List");
        label.setBounds(0,40,400,30);
        label.setForeground(new Color(198, 40, 20));
        label.setFont(new Font("Papyrus", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.TOP);
        titlePanel.add(label);

        JList list = new JList(favouriteStudentList);
        list.setVisibleRowCount(20);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //list.setBounds(30,30,100,100);

        panel = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.setBounds(10,100,370,300);
        //panel.setLayout(null);

        contentPane.add(backPanel);
        contentPane.add(titlePanel);
        contentPane.add(panel);

        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    ResultVietAnhLayout.FLAG = 2;
                    ResultVietAnhLayout.key = list.getSelectedValue().toString();
                    ResultVietAnhLayout layout = null;
                    layout = new ResultVietAnhLayout();
                    layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    layout.setVisible(true);
                    layout.setSize(400,550);
                    VietAnhFavouriteLayout.this.setVisible(false);
                    // Double-click detected
                    //int index = list.locationToIndex(evt.getPoint());
                } else if (evt.getClickCount() == 3) {

                    // Triple-click detected
                    int index = list.locationToIndex(evt.getPoint());
                }
            }
        });




        //this.add(pa);
        // this.add(panel);

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

