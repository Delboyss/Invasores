package development;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by Daniel on 16/05/2015.
 */
public class Main {


    public static void main(String args[]) throws IOException {
        criarTela();
    }

    public static void criarTela() throws IOException {
        JFrame frame = new JFrame();
        InvasoresPanel sip = new InvasoresPanel();
        frame.getContentPane().add(sip);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}