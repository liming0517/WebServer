package main.tankgame.main;

import main.tankgame.paint.Mypanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyTankGame extends JFrame {
    private JMenuBar jMenuBar = null;
    private JMenu jMenu = null;
    private JMenuItem jMenuItem = null;
    private Mypanel mypanel = null;

    public MyTankGame() {

        jMenuBar = new JMenuBar();
        jMenu = new JMenu("设置");
        jMenuItem = new JMenuItem("开始游戏");
        mypanel =new Mypanel();
        jMenu.add(jMenuItem);
        jMenuBar.add(jMenu);
        this.add(jMenuBar, BorderLayout.NORTH);
        this.add(mypanel,BorderLayout.CENTER);
        this.addKeyListener(mypanel);
        this.setBackground(Color.BLACK);
        this.setSize(500, 500);
        this.setLocation(300, 10);
        this.setTitle("坦克大战");
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        MyTankGame myTankGame = new MyTankGame();

    }
}


