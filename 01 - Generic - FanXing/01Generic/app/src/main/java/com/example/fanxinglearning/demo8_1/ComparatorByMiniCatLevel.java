package com.example.fanxinglearning.demo8_1;

import com.example.fanxinglearning.demo8.MiniCat;

import java.util.Comparator;

public class ComparatorByMiniCatLevel implements Comparator<MiniCat> {
    @Override
    public int compare(MiniCat miniCat, MiniCat t1) {
        return miniCat.level - t1.level;
    }
}
