package it.units.sdm.project.gui;

import it.units.sdm.project.gui.menucomponents.MenuBar;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JFrame {

    private static final int WINDOW_WIDTH = 750;
    private static final int WINDOW_HEIGHT = 750;

    public GameScreen(){
        super();
        setProperties();
        add(new MenuBar(), BorderLayout.NORTH);
        add(new OuterGamePanel(), BorderLayout.CENTER);
    }

    public static int getWindowWidth(){
        return WINDOW_WIDTH;
    }

    private void setProperties(){
        setTitle("Order and Chaos Game");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }



}