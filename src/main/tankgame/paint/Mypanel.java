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

    //���õ���̹������
    private int size = 3;

    //���캯��
    public Mypanel() {
        this.setBackground(Color.white);
        enemyTanks = new Vector<>();
        //��ʼ���ҷ�̹��
        mytank = new MyTank(250, 330, 0);
        //��ʼ������̹��
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
        //�����ҷ�̹��
        this.drawTank(mytank.getX(), mytank.getY(), mytank.getDirect(), g);
        g.setColor(Color.red);
        //�����з�̹��
        for (int i = 0; i < enemyTanks.size(); i++) {
            //ȡ������̹��
            EnemyTank enemyTank = enemyTanks.get(i);
            //System.out.println("��ʼ���Ƶ���̹��"+"enemyTank.getX()="+enemyTank.getX()+"    enemyTank.getY()="+enemyTank.getY());
            this.drawTank(enemyTank.getX(), enemyTank.getY(), enemyTank.getDirect(), g);
        }

    }


    //����̹��
    public void drawTank(int x, int y, int direct, Graphics g) {
        switch (direct) {
            case 0:
                //���� 0
                //1��������ߵľ���
                g.fill3DRect(x, y, 5, 30, false);
                //2�������ұߵľ���
                g.fill3DRect(x + 15, y, 5, 30, false);
                //3:�����м����
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                //4:����Բ��
                g.fillOval(x + 5, y + 10, 10, 10);
                //5:������Ͳ
                g.drawLine(x + 10, y + 15, x + 10, y);
                break;
            case 1:
                //����  1
                //1��������ߵľ���
                g.fill3DRect(x, y, 5, 30, false);
                //2�������ұߵľ���
                g.fill3DRect(x + 15, y, 5, 30, false);
                //3:�����м����
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                //4:����Բ��
                g.fillOval(x + 5, y + 10, 10, 10);
                //5:������Ͳ
                g.drawLine(x + 10, y + 15, x + 10, y + 30);
                break;
            case 2:
                //���� 2
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
                //����
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
