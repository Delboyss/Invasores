package development;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Daniel on 16/05/2015.
 */
public class Atirador {
    private int x, y;
    private int dx, dy;
    private int iw, ih;
    private Image icon;
    private Dimension area;
    private ArrayList missils;

    public Atirador(Dimension a) {
        area = a;
        icon =
                new ImageIcon(getClass().getResource("/development/img/Nave.png")).getImage();
        iw = icon.getWidth(null);
        ih = icon.getHeight(null);
        x = (int) (iw / 2 + (a.width - iw) / 2);
        y = (int) (a.height - 100 + ih / 2);
                
        missils = new ArrayList();        
                
    }

    public void move(Direcao dir) {
        if (dir == null) return;

        switch (dir) {
            case LEFT: {
                x--;
                if (x < iw / 2) x = iw / 2;
                break;
            }
            case RIGHT: {
                x++;
                if (x > area.width - iw / 2) x = area.width - iw / 2;
                break;
            }
            case UP: {
                y--;
                if (y < area.height - 100 + ih / 2) y = area.height - 100 + ih / 2;
                break;
            }
            case DOWN: {
                y++;
                if (y > area.height - ih / 2) y = area.height - ih / 2;
                break;
            }
            
        
        }
    }
    
    
    public void atirar(){
                
        missils.add(new Missil(x, y));
        
    }
    
    
     public ArrayList getMissil() {
        return missils;
    }
    
    
  
    public void draw(Graphics g) {
        g.drawImage(icon, x - iw / 2, y - ih / 2, null);
    }
}
