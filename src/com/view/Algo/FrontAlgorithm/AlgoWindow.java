package com.view.Algo.FrontAlgorithm;

import javax.swing.*;
import java.util.List;

public class AlgoWindow {
    private static AlgoWindow window;
    private final AlgoPanel panel;
    private JFrame frame;
    private AlgoWindow(List<Integer> generatedList) {
        panel = new AlgoPanel(generatedList);
        creteFrame();
    }

    public static AlgoWindow createWindow(List <Integer> generatedList) {
        if(AlgoWindow.window == null) {
            AlgoWindow.window = new AlgoWindow(generatedList);
        }
        return AlgoWindow.window;
    }


    private void creteFrame() {
        frame = new JFrame();
        frame.setSize(1080, 720);
        frame.setTitle("AlgoWindow");
        frame.setResizable(false);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }
}
