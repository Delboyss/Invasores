package development;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.imageio.ImageIO;

/**
 * Created by Daniel on 16/05/2015.
 */
public class InvasoresPanel extends JPanel implements Runnable, KeyListener {
    private static final int largura = 800;
    private static final int altura = 600;
    private Thread animator;
    private boolean isPaused = false;
    private HashSet<Invasor> invasores;
    private Atirador atirador;
    private Direcao dir;
    private Image img;
    Image background; 
    private int invasoresTotal = 10;
    private ArrayList ms;
     private final ExecutorService pool = Executors.newFixedThreadPool(3);
    
    public InvasoresPanel(String img) {
        this(new ImageIcon(img).getImage());
    }
    public InvasoresPanel(Image img) {     
        
        background = Toolkit.getDefaultToolkit().createImage(getClass().getResource("/development/img/space-bg.jpg"));
        this.ms = new ArrayList();
        this.img = img;
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(largura, altura));
        setFocusable(true);
        requestFocus();
        addKeyListener(this);
        invasores = new HashSet<Invasor>();
        
        for (int i = 0; i < invasoresTotal; i++) {
        
            invasores.add(new Invasor(this.getPreferredSize()));
            
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
            checkColisoes();
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    private void checkColisoes(){
        
         try{
            pool.execute(new Colisoes(invasores, atirador));

            repaint();


        }catch (Exception e){

            e.printStackTrace();
            pool.shutdown();
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
    
    
    
    public void atiradorAtirar(Atirador a) {
        
        if (!isPaused) {
            
            ArrayList ms = a.getMissil();
            // Executa quantos mísseis tiver
            for (int i = 0; i < ms.size(); i++) {
                Missil m = (Missil) ms.get(i);

                    m.move();
                        
                if (((Missil) ms.get(i)).visible == false)
                    ms.remove(i);
            }

        }
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.img, 0, 0, null);
        
        g.drawImage(background, 0, 0, null);
        g.setColor(Color.BLACK);
        
        
        g.drawRect(0, 0, getWidth(), getHeight() - 100);
        for (Invasor i : invasores) {
            i.draw(g);
        }
            
  
            ms = atirador.getMissil();
       
            
           // Desenha todos os mísseis que são adicionados na tela
          for (int i = 0; i < ms.size(); i++) {
                    Missil m = (Missil) ms.get(i);
                    m.move();
                    g.drawImage(m.getImage(), (int)m.getX(), (int)m.getY(), this);
         
          
          }

         // Desemja Player
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
        }else if (keyCode == KeyEvent.VK_F) {
                  atirador.atirar();
                 atiradorAtirar(atirador);
        }
    }

    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}