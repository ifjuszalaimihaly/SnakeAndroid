package szalai.hu.snake.entitiy;

import java.util.Random;

/**
 * Created by Mihaly Szalai on 2017. 07. 04..
 */

public class Enemy {

    private int[] enemyXpos;
    private int[] enemyYpos;
    private int xlenght;
    private int ylenght;
    private Random rnd;

    public Enemy(int xlenght, int ylenght){
        this.xlenght = xlenght;
        this.ylenght = ylenght;
        enemyXpos = new int[xlenght];
        enemyYpos = new int[ylenght];
        rnd = new Random();
        for(int i=0; i<xlenght; i++){
            enemyXpos[i] = i*25;
        }
        for(int i=0; i<ylenght; i++){
            enemyYpos[i] = i*25;
        }
    }

    public int getXPos(){
        return enemyXpos[rnd.nextInt(xlenght)];
    }

    public int getYPos(){
        return enemyYpos[rnd.nextInt(ylenght)];
    }


}
