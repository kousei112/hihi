package Test;

import Sprite.Naruto;

import javax.swing.*;
import java.awt.*;

public class IT extends JPanel {
    private Naruto naruto;
    private int direction = 1; // 1: di chuyển sang phải, -1: di chuyển sang trái
    private int frameWidth = 800; // Chiều rộng khung
    private int frameHeight = 600; // Chiều cao khung

    public IT() {
        naruto = new Naruto(0, 400); // Khởi tạo Naruto ở vị trí ban đầu

        // Đặt hướng ban đầu
        naruto.setFacingRight(true);

        Timer timer = new Timer(16, e -> {
            update();
            repaint();
        });
        timer.start();
    }

    private void update() {
        // Chỉ di chuyển nếu không ở trạng thái Stancing
        if (naruto.getCurrentMove() != 4) { // 4 là trạng thái Stancing
            // Nếu gặp biên phải thì đổi hướng sang trái
            if (naruto.getX() + 100 >= frameWidth) {
                direction = -1;
                naruto.setFacingRight(false); // Đổi hướng sang trái
            }
            // Nếu gặp biên trái thì đổi hướng sang phải
            else if (naruto.getX() <= 0) {
                direction = 1;
                naruto.setFacingRight(true); // Đổi hướng sang phải
            }
    
            // Di chuyển nhân vật
            naruto.setX(naruto.getX() + direction * naruto.getSpeed());
        }
    
        // Chọn hành động ngẫu nhiên khi di chuyển
        if (Math.random() < 0.01) { // Xác suất thấp để thay đổi hành động
            int randomAction = (int) (Math.random() * 9); // 0 đến 8
            naruto.performAction(randomAction);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, frameWidth, frameHeight); // Tô nền trắng

        // Vẽ nhân vật Naruto
        g.drawImage(naruto.defaultImage(0), naruto.getX(), naruto.getY(), null);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Naruto Animation Test");
        IT panel = new IT();
        frame.add(panel);
        frame.setSize(800, 600); // Kích thước khung
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Hiển thị ở giữa màn hình
        frame.setVisible(true);
    }
}
