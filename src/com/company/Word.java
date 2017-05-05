package com.company;

import java.util.Date;
import java.util.Vector;

/**
 * Created by MyPC on 3/19/2017.
 */
public class Word {

    String word;
    String meanWord;
    Integer frequency = 0;
    Boolean isFavourited = false;
    Vector<String> listSearchedDate = new Vector<>();

    public void setFrequency(int frequency){
        this.frequency = frequency;
    }

    public Vector<String> getListSearchedDate() {
        return listSearchedDate;
    }

    public void setListSearchedDate(Vector<String> listSearchedDate) {
        this.listSearchedDate = listSearchedDate;
    }

    public Boolean getFavourited() {
        return isFavourited;
    }

    public void setFavourited(Boolean favourited) {
        isFavourited = favourited;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void addFrequency() {
        this.frequency ++;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeanWord() {
        return meanWord;
    }

    public void setMeanWord(String meanWord) {
        this.meanWord = meanWord;
    }
}
