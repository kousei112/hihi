package Test;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Sprite.Naruto;


public class TestStrongAttack extends JPanel {
    private Naruto naruto;

    public TestStrongAttack() {
        naruto = new Naruto(50, 300); // Tạo nhân vật Naruto tại vị trí (50, 300)
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Vẽ trạng thái hiện tại của Naruto
        BufferedImage img = naruto.defaultImage(g); 
        g.drawImage(img, naruto.x, naruto.y, null); 
    }

    public void startStrongAttack() {
        naruto.performAction(5); // 8 là ATTACKING2 đại diện cho strongAttacking trong định nghĩa của bạn

        Timer timer = new Timer(50, e -> {
            naruto.defaultImage(0);
            repaint(); // Cập nhật và vẽ lại màn hình sau mỗi khung hình

            if (naruto.currentMove == 4) { // Nếu Naruto quay về trạng thái STANCING
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test Strong Attack");
        TestStrongAttack panel = new TestStrongAttack();

        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Khởi động strong attack sau một khoảng thời gian ngắn
        Timer startTimer = new Timer(500, e -> {
            panel.startStrongAttack();
            ((Timer) e.getSource()).stop();
        });
        startTimer.start();
    }
}

