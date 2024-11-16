package Test;

import Sprite.Naruto;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DotonTest extends JPanel{
    private Naruto naruto;
    private boolean testingDotonNoJutsu = true;

    public DotonTest() {
        naruto = new Naruto(100, 300);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        

        BufferedImage narutoImage = naruto.dotonNoJutsuImage();
            g.drawImage(narutoImage, naruto.getX(), naruto.getY(), null);
    }

    private void updateAction() {
        if (testingDotonNoJutsu && naruto.isDotonFinished()) {
            testingDotonNoJutsu = false;
            System.out.println("Test is finished");
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Doton No Jutsu Test");
        DotonTest dotonTest = new DotonTest();
        frame.add(dotonTest);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        new Timer(30, e -> {
            dotonTest.updateAction();
            dotonTest.repaint();
        }).start();
    }
}
