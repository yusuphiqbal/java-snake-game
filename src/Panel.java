import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Panel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 500;
    static final int SCREEN_HEIGHT = 500;
    static final int UNIT_SIZE = 20;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 50;
    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    int bodyParts = 6;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    Panel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.decode("#073b4c"));
        this.setFocusable(true);
        this.addKeyListener(new GameKeyAdapter());
        startGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
        }
        repaint();
    }

    private void startGame() {
        createFood();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw(graphics);
    }

    public void draw(Graphics graphics) {
        graphics.setColor(Color.decode("#ef476f"));
        graphics.fillRect(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

        for (int i = 0; i < bodyParts; i++) {
            graphics.setColor(Color.decode("#06d6a0"));
            graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
        }
    }

    private void createFood() {
        appleX = random.nextInt(SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
        appleY = random.nextInt(SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;
    }

    private void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U' -> y[0] -= UNIT_SIZE;
            case 'D' -> y[0] += UNIT_SIZE;
            case 'L' -> x[0] -= UNIT_SIZE;
            default -> x[0] += UNIT_SIZE;
        }
    }

    private static class GameKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent keyEvent) {

        }
    }
}
