package Sprite;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Power extends Sprite {
    private String bossName;

    public Power(int x, String bossName) {
        this.x = x;
        this.y = 50;
        this.h = 45;
        this.w = MAX_HEALTH;
        this.health = START_HEALTH; // Thiết lập sức khỏe bắt đầu
        this.bossName = bossName;
    }

    // Giảm sức khỏe của Boss
    public void decreaseHealth(int amount) {
        health = Math.max(0, health - amount); // Không cho sức khỏe âm
    }

    public void setHealth() {
        health = health - (int) (MAX_HEALTH * 0.03);
    }

    @Override
    public BufferedImage defaultImage(int frameIndex) {
        // Cài đặt cho việc trả về ảnh mặc định dựa trên chỉ số frame
        // Ví dụ:
        return new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public BufferedImage defaultImage(Graphics g) {
        // Cài đặt cho việc vẽ với Graphics
        // Ví dụ vẽ một hình chữ nhật màu đỏ
        BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics g2 = img.getGraphics();
        g2.setColor(Color.RED);
        g2.fillRect(0, 0, 100, 100); // Vẽ nền đỏ
        g2.dispose();
        return img;
    }

    // Vẽ thanh máu và tên của Boss
    @Override
    public void print(Graphics pen, int frameIndex) {
        super.print(pen, frameIndex); // Gọi phương thức print của lớp cha nếu cần thiết
        // Vẽ thanh máu
        pen.setColor(Color.RED);
        pen.fillRect(x, y, w, h);  // Vẽ thanh máu nền đỏ (thanh tổng)
        pen.setColor(Color.GREEN);
        pen.fillRect(x, y, health, h);  // Vẽ thanh máu hiện tại (màu xanh lá)
        pen.setColor(Color.WHITE);
        pen.setFont(new Font("times", Font.BOLD, 30));
        pen.drawString(bossName, x, y + h + 30);  // Vẽ tên của boss phía dưới thanh máu
    }
    public void printBox(Graphics pen) {
        pen.setColor(Color.RED);
        pen.fillRect(x, y, w, h);  // Vẽ thanh máu nền đỏ (thanh tổng)
        pen.setColor(Color.GREEN);
        pen.fillRect(x, y, health, h);  // Vẽ thanh máu hiện tại (màu xanh lá)
        pen.setColor(Color.WHITE);
        pen.setFont(new Font("times", Font.BOLD, 30));
        pen.drawString(bossName, x, y + h + 30);  // Vẽ tên của boss phía dưới thanh máu
    }
}
