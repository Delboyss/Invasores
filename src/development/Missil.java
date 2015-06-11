package development;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by User on 23/04/2015.
 */
public class Missil {
    private double x, y;
    private Image image;
    boolean visible;
    private int width, height;
    private double direcaoX, direcaoY;
    private int raio = 5;
    // lados 1 = direito e 0 igual a esquerdo
    private int lado;

    private final int BOARD_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private final int BOARD_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private final int MISSILE_SPEED = 5;

    public Missil(double x, double y) {
        ImageIcon ii =
                new ImageIcon(this.getClass().getResource("/development/img/missil.png"));

        image = ii.getImage();
        visible = true;
        width = image.getWidth(null);
        height = image.getHeight(null);
        this.direcaoX = direcaoX;
        this.direcaoY = direcaoY;
        this.x = x;
        this.y = y;

    }

    public double getDirecaoX() {
        return direcaoX;
    }

    public void setDirecaoX(double direcaoX) {
        this.direcaoX = direcaoX;
    }

    public double getDirecaoY() {
        return direcaoY;
    }

    public void setDirecaoY(double direcaoY) {
        this.direcaoY = direcaoY;
    }

    public Image getImage() {
        return image;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }

    public int getRaio(){
        return this.raio;
    }
    public void move() {

   
                x = x;
                // tiro vai para cima
                y--;
           
            
            if (x > BOARD_WIDTH)
                visible = false;
            if (y > BOARD_HEIGHT)
                visible = false;




    }
    

}
