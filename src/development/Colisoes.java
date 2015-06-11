package development;


import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by romulolink on 06/05/15.
 */
public class Colisoes implements Runnable {

    private HashSet<Invasor> invasores;
    private HashSet<Missil> missils;
    private Atirador t_player;
    private Boolean houveColisao;

    public Colisoes(HashSet<Invasor> invasores, Atirador t_player) {
        this.invasores = invasores;
        //this.missils = missils;
        this.t_player = t_player;

    }

    public Boolean getHouveColisao() {
        return houveColisao;
    }

    @Override
    public void run() {

        double distance;

        ArrayList ms = t_player.getMissil();

        // Verifica colisão com qualquer dos tanques na arena
        for (Invasor inv : invasores) {


            // Seleciona apenas inimigos
            if (true) {

                // Para cada míssil enviado pelo player checa a colisão
                for (int i = 0; i < ms.size(); i++) {
                    Missil m = (Missil) ms.get(i);

                    // Sound.BULLET_SHOT.play();
                    // if (((Missil) ms.get(i)).visible == true)

                    // Calcula Hipotenusa
                    distance = Math.pow(m.getY() - inv.getY(), 2) + Math.pow(m.getX() - inv.getX(), 2);
                    distance = Math.sqrt(distance);

                    // Para cada missil de player
                    //for (int j=0;j<t_player.getMissil().size();j--){
                    //}
                    // verifica distancia entre os raios do player com os robos
                    if (distance <= m.getRaio() + inv.getRaio()) {

                     //   Sound.EXPLOSION.play();

                     //   Sound.FIRST_BLOOD.play();

                        inv.setDestruido(true);
                       
                        ms.remove(m);
                        houveColisao = true;

                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        invasores.remove(inv);
                    }



                }





            }
        }

        houveColisao = false;

    }




}
