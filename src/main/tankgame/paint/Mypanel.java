package main.tankgame.paint;

import main.tankgame.element.EnemyTank;
import main.tankgame.element.MyTank;
import main.tankgame.element.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Vector;

public class Mypanel extends JPanel implements KeyListener {
    private MyTank mytank = null;
    private EnemyTank enemyTank = null;
    private Vector<EnemyTank> enemyTanks = null;

    //设置敌人坦克数量
    private int size = 3;

    //构造函数
    public Mypanel() {
        this.setBackground(Color.white);
        enemyTanks = new Vector<>();
        //初始化我方坦克
        mytank = new MyTank(250, 330, 0);
        //初始化敌人坦克
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            enemyTank = new EnemyTank(i * 50, i, random.nextInt(4));
            enemyTanks.add(enemyTank);
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.gray);
        g.fillRect(0, 0, 350, 350);
        g.setColor(Color.orange);
        //画出我方坦克
        this.drawTank(mytank.getX(), mytank.getY(), mytank.getDirect(), g);
        g.setColor(Color.red);
        //画出敌方坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出敌人坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            //System.out.println("开始绘制敌人坦克"+"enemyTank.getX()="+enemyTank.getX()+"    enemyTank.getY()="+enemyTank.getY());
            this.drawTank(enemyTank.getX(), enemyTank.getY(), enemyTank.getDirect(), g);
        }

    }


    //画出坦克
    public void drawTank(int x, int y, int direct, Graphics g) {
        switch (direct) {
            case 0:
                //朝上 0
                //1：绘制左边的矩形
                g.fill3DRect(x, y, 5, 30, false);
                //2：绘制右边的矩形
                g.fill3DRect(x + 15, y, 5, 30, false);
                //3:绘制中间矩形
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                //4:画出圆形
                g.fillOval(x + 5, y + 10, 10, 10);
                //5:绘制炮筒
                g.drawLine(x + 10, y + 15, x + 10, y);
                break;
            case 1:
                //朝下  1
                //1：绘制左边的矩形
                g.fill3DRect(x, y, 5, 30, false);
                //2：绘制右边的矩形
                g.fill3DRect(x + 15, y, 5, 30, false);
                //3:绘制中间矩形
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                //4:画出圆形
                g.fillOval(x + 5, y + 10, 10, 10);
                //5:绘制炮筒
                g.drawLine(x + 10, y + 15, x + 10, y + 30);
                break;
            case 2:
                //朝左 2
                g.fill3DRect(x, y, 30, 5, false);
                //
                g.fill3DRect(x, y + 15, 30, 5, false);
                //
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                //
                g.fillOval(x + 10, y + 5, 10, 10);
                //
                g.drawLine(x + 15, y + 10, x - 3, y + 10);
                break;
            case 3:
                //朝右
                g.fill3DRect(x, y, 30, 5, false);
                //
                g.fill3DRect(x, y + 15, 30, 5, false);
                //
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                //
                g.fillOval(x + 10, y + 5, 10, 10);
                //
                g.drawLine(x + 15, y + 10, x + 30, y + 10);
                break;
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
            this.mytank.setDirect(0);
            if (mytank.getY() > 0 ) {
                this.mytank.moveUP();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.mytank.setDirect(1);
            if ( mytank.getY() < 320) {
                this.mytank.moveDown();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.mytank.setDirect(2);
            if (mytank.getX() > 0 ) {
                this.mytank.moveLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.mytank.setDirect(3);
            if (mytank.getX() < 320) {
                this.mytank.moveRight();
            }
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
