package development;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Daniel on 16/05/2015.
 */
public class InvasoresPanel extends JPanel implements Runnable, KeyListener {
    private static final int largura = 800;
    private static final int altura = 600;
    private Thread animator;
    private boolean isPaused = false;
    private Invasor[] invasores;
    private Atirador atirador;
    private Direcao dir;

    public InvasoresPanel() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(largura, altura));
        setFocusable(true);
        requestFocus();
        addKeyListener(this);
        invasores = new Invasor[20];
        for (int i = 0; i < invasores.length; i++) {
            invasores[i] = new Invasor(this.getPreferredSize());
            atirador = new Atirador(this.getPreferredSize());
        }
    }

    public void addNotify() {
        super.addNotify();
        startGame();
    }

    private void startGame() {
        if (animator == null) {
            animator = new Thread(this);
            animator.start();
        }
    }

    public void run() {
        while (true) {
            gameUpdate();
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void gameUpdate() {
        if (!isPaused) {
            for (Invasor i : invasores) {
                i.move();
            }
            atirador.move(dir);
        }
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth(), getHeight() - 100);
        for (Invasor i : invasores) {
            i.draw(g);
        }
        atirador.draw(g);
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_P) {
            isPaused = !isPaused;
        }
        if (isPaused) {
            return;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            dir = Direcao.LEFT;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            dir = Direcao.RIGHT;
        } else if (keyCode == KeyEvent.VK_UP) {
            dir = Direcao.UP;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            dir = Direcao.DOWN;
        }
    }

    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}