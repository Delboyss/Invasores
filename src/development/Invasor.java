package development;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Daniel on 16/05/2015.
 */
public class Invasor {

    private int x, y;
    private int dx, dy;
    private int iw, ih;
    private Image icon;
    private Dimension area;

    public Invasor(Dimension a) {

        area = a;
        icon = new ImageIcon(getClass().getResource("Img/Invasor.png")).getImage();
        iw = icon.getWidth(null);
        ih = icon.getHeight(null);
        x = (int) (iw / 2 + Math.random() * (a.width - iw));
        y = (int) (ih / 2 + Math.random() * (a.height - 100 - ih));

        
        while (dx == 0 || dy == 0) {
            dx = 3 - (int) (Math.random() * 6);
            dy = 2 - (int) (Math.random() * 4);
        }

    }

    public void move() {
        x += dx;
        y += dy;

        if (x < iw / 2) {
            dx = -dx;
            x += dx;
        }

        if (y < ih / 2) {
            dy = -dy;
            y += dy;
        }

        if (x > area.width - iw / 2) {
            dx = -dx;
            x += dx;
        }

        if (y > area.height - 100 - ih / 2) {
            dy = -dy;
            y += dy;
        }
    }

    public void draw(Graphics g) {
        g.drawImage(icon, x - iw / 2, y - ih / 2, null);

    }

}
