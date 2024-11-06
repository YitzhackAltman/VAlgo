package com.view.Algo;

import com.view.Algo.FrontAlgorithm.AlgoWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List <Integer>generatedList = generateList();
        AlgoWindow window = AlgoWindow.createWindow(generatedList);
    }

    public static List<Integer> generateList() {
        List <Integer> list = new ArrayList<>(200);
        Random random = new Random();
        final int MAX_LIST_SIZE  = 100;
        for (int i = 0; i < MAX_LIST_SIZE; ++i) {
            list.add(random.nextInt(0, 200));
        }

        return list;
    }
}
