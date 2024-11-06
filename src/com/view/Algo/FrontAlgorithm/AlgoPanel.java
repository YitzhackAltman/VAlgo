package com.view.Algo.FrontAlgorithm;

import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.util.List;


public class AlgoPanel extends JPanel {
    private BufferedImage buffer;
    private final List<Integer> toSort;
    private int IndexHighlight1;
    private int IndexHighLight2;
    boolean isSorted;
    public AlgoPanel(List<Integer> toSort) {
        setDoubleBuffered(true);
        this.toSort = toSort;
        Thread t = new Thread(() -> babbleSort(toSort));
        t.start();

    }

    private void highlight(int index1, int index2) {
        this.IndexHighlight1 = index1;
        this.IndexHighLight2 = index2;
    }

    private void swap(int i, int j) {
        int temp = this.toSort.get(i);
        this.toSort.set(i, this.toSort.get(j));
        this.toSort.set(j, temp);
    }
    public synchronized void babbleSort(List<Integer> s) {
        for (int i =0; i < s.size(); ++i) {
            for(int j = i + 1; j < s.size(); ++j) {
                if (s.get(i) > s.get(j)) {
                    highlight(i, j);
                    swap(i, j);
                    repaint();
                    delay(10);
                }
            }
        }
        isSorted = true;
    }


    private void delay(int ms) {
        try {
            Thread.sleep(ms);
        }catch (InterruptedException e) { e.printStackTrace(); }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (buffer == null) {
            buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        }

        Graphics graphics = buffer.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());

        final int TotalScreenWidth = getWidth();
        final int TotalScreenHeight = getHeight();

        final int HalfScreenWidth = TotalScreenWidth / 2;
        final int HalfScreenHeight = TotalScreenHeight / 2;
        final int SQUARE_WIDTH = 30;
        final int SQUARE_HEIGHT = 10;
        final int PADDING = 2;
        final int HalfSquareWidth = SQUARE_WIDTH / 2;
        final int TotalSquarePadding = SQUARE_WIDTH + PADDING;
        final int TotalNumberPadding = HalfSquareWidth / 2;
        final Point CameraPosition = new Point(HalfScreenWidth, HalfScreenHeight);

        final int CAMERA_PADDING = 100 * 2;

        Point SquarePosition;

        for (int SortIndex = 0; SortIndex < toSort.size(); ++SortIndex) {

            final int Value = toSort.get(SortIndex);
            int noneNValue = GetNoneNegativeValue(Value);
            int height = (noneNValue * SQUARE_HEIGHT) / 4;
            SquarePosition = new Point((SortIndex * TotalSquarePadding) - CAMERA_PADDING,
                                    (2 * HalfScreenHeight) - height);
            int characterPositionX = (2*HalfScreenHeight) - height;
            int characterPositionY = (SortIndex * TotalSquarePadding);
            //SquarePosition = new Point((characterPositionX - CameraPosition.x), ((characterPositionY - CameraPosition.y)));



            if (SortIndex == IndexHighlight1 || SortIndex == IndexHighLight2) {
                graphics.setColor(Color.red);
            }else {
                graphics.setColor(Color.GREEN);
            }

            if ((IndexHighLight2 * SQUARE_WIDTH) >= TotalScreenWidth) {
                CameraPosition.x += (IndexHighLight2 * SQUARE_WIDTH) % TotalScreenWidth;

                /*public static Position drawCharacterRelativeToCamera(Position cameraPosition, Position characterPosition) {
                    return new Position((characterPosition.x - (cameraPosition.x - CAMERA_PADDING)), (characterPosition.y - (cameraPosition.y - CAMERA_PADDING)));
                }*/
            }

            graphics.fillRect(SquarePosition.x, SquarePosition.y, SQUARE_WIDTH, height);

            // Drawing Numbers
            g.setColor(Color.BLACK);

            graphics.drawString(String.valueOf(Value), SquarePosition.x + TotalNumberPadding, SquarePosition.y);

        }

        g.drawImage(buffer, 0, 0, null);
        graphics.dispose();
    }

    public int GetNoneNegativeValue(int value) {
        if (value < 0) {
            return value * -1;
        }
        return value;
    }
}