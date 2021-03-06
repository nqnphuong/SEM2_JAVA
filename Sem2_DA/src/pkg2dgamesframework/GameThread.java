/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgamesframework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author phamn
 */
public class GameThread extends JPanel implements Runnable {
    //extends exception: xử lí ngoại lệ - extend: kế thừa class
    //implements Runnable - implemetns: kế thừa interface
    //làm vòng lặp game
    //(1)lắng nghe được các phím - THREAD - implements từ interface Runnable.
    private GameScreen context;

    private Thread thread;  //THREAD

    private Graphics ThisGraphics;

    public static int FPS = 70;

    private BufferedImage buffImage;

    private int MasterWidth, MasterHeight;
    public static float scaleX_ = 1, scaleY_ = 1;

    public GameThread(GameScreen context) {
        this.context = context;

        MasterWidth = context.CUSTOM_WIDTH;
        MasterHeight = context.CUSTOM_HEIGHT;

        this.thread = new Thread(this); //gọi new Theard - tạo đối tượng

    }

    public void StartThread() {
        thread.start(); //gọi phương thức start để luồng có thể hoạt động
    }

    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, context.CUSTOM_WIDTH, context.CUSTOM_HEIGHT);
        Graphics2D g2 = (Graphics2D) g;
        if (buffImage != null) {
            g2.scale(scaleX_, scaleY_);
            g2.drawImage(buffImage, 0, 0, this);
        }
    }

    private void UpdateSize() {
        if (this.getWidth() <= 0) {
            return;
        }

        context.CUSTOM_WIDTH = this.getWidth();
        context.CUSTOM_HEIGHT = this.getHeight();

        scaleX_ = (float) context.CUSTOM_WIDTH / (float) MasterWidth;
        scaleY_ = (float) context.CUSTOM_HEIGHT / (float) MasterHeight;
    }

    @Override
    public void run() { //(2)

        long T = 1000 / FPS;
        long TimeBuffer = T / 2;

        long BeginTime = System.currentTimeMillis();
        long EndTime;
        long sleepTime;

        while (true) {

            UpdateSize();

            context.GAME_UPDATE(System.currentTimeMillis());
            try {

                buffImage = new BufferedImage(MasterWidth, MasterHeight, BufferedImage.TYPE_INT_ARGB);
                if (buffImage == null) {
                    return;
                }
                Graphics2D g2 = (Graphics2D) buffImage.getGraphics();

                if (g2 != null) {
                    context.GAME_PAINT(g2);
                }

            } catch (Exception myException) {
                myException.printStackTrace();
            }

            repaint();

            EndTime = System.currentTimeMillis();
            sleepTime = T - (EndTime - BeginTime);
            if (sleepTime < 0) {
                sleepTime = TimeBuffer;
            }

            try {
                Thread.sleep(sleepTime);
                // cho phép tiến trình nghỉ
            } catch (InterruptedException ex) {
            }

            BeginTime = System.currentTimeMillis();
        }
    }
}
