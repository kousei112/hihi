package Test;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Sprite.Naruto;
import Sprite.Enemy;

public class GameTest extends JPanel {
    private Naruto naruto;
    private Enemy enemy;
    private static final int ATTACK_DISTANCE = 50;  // Khoảng cách Naruto tấn công khi đến gần enemy

    public GameTest() {
        // Khởi tạo Naruto và Enemy
        naruto = new Naruto(100, 300);
        enemy = new Enemy(300, 300, 50, 400, 100);

        // Thiết lập panel game
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Vẽ Naruto và Enemy
        naruto.defaultImage(g);  // Vẽ hình ảnh của Naruto dựa trên trạng thái hiện tại
        enemy.render(g);         // Vẽ Enemy

        // Di chuyển Enemy
        enemy.move();

        // Cập nhật vị trí của Naruto, làm cho Naruto di chuyển về phía Enemy
        moveNarutoTowardsEnemy();

        // Kiểm tra khi Naruto đến gần đủ và tung đòn
        if (distanceToEnemy() <= ATTACK_DISTANCE) {
            performAttack();
        }

        // Repaint để tiếp tục cập nhật trạng thái
        repaint();
    }

    // Hàm tính khoảng cách giữa Naruto và Enemy
    private int distanceToEnemy() {
        return Math.abs(naruto.getX() - enemy.getX());
    }

    // Hàm làm Naruto di chuyển hướng về phía Enemy
    private void moveNarutoTowardsEnemy() {
        if (naruto.getX() < enemy.getX()) {
            naruto.setX(naruto.getX() + 5);  // Di chuyển phải
        } else if (naruto.getX() > enemy.getX()) {
            naruto.setX(naruto.getX() - 5);  // Di chuyển trái
        }

        // Làm cho Naruto chạy
        naruto.performAction(Naruto.RUNNING);
    }

    // Hàm thực hiện tấn công khi Naruto tới gần Enemy
    private void performAttack() {
        // Căn cứ vào khoảng cách, chọn chiêu thức tấn công
        if (distanceToEnemy() <= 10) {
            naruto.startAttack();  // Bắt đầu tấn công
            naruto.performAction(Naruto.ATTACKING1);  // Chiêu thức Default Attack
            enemy.setHealth();  // Giảm máu Enemy
        } else if (distanceToEnemy() <= 30) {
            naruto.startAttack();  // Bắt đầu tấn công
            naruto.performAction(Naruto.ATTACKING2);  // Chiêu thức Strong Attack
            enemy.setHealth();  // Giảm máu Enemy
        } else {
            naruto.startAttack();  // Bắt đầu tấn công
            naruto.performAction(Naruto.ATTACKING3);  // Chiêu thức Doton No Jutsu
            enemy.setHealth();  // Giảm máu Enemy
        }
    }

    public static void main(String[] args) {
        // Tạo cửa sổ game và thêm panel GameTest vào
        JFrame frame = new JFrame("Naruto vs Enemy");
        GameTest game = new GameTest();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(game);
        frame.setVisible(true);
    }
}
