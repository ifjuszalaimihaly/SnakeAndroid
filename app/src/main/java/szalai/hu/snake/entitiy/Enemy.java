package szalai.hu.snake.entitiy;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import java.util.Random;

import szalai.hu.snake.R;

/**
 * Created by Mihaly Szalai on 2017. 07. 04..
 */

public class Enemy {

    private int[] enemyXpos;
    private int[] enemyYpos;
    private int xlenght;
    private int ylenght;
    private Random rnd;
    private ImageView imageView;

    public Enemy(Context context, int xlenght, int ylenght) {
        this.xlenght = xlenght;
        this.ylenght = ylenght;
        enemyXpos = new int[xlenght];
        enemyYpos = new int[ylenght];
        rnd = new Random();
        for(int i=0; i<xlenght; i++){
            enemyXpos[i] = i * 50;
        }
        for(int i=0; i<ylenght; i++){
            enemyYpos[i] = i * 50;
        }
        for (int i = 0; i < xlenght; i++) {
            Log.i("info", "enemy x " + enemyYpos[i]);
        }
        Log.i("info", xlenght + " " + ylenght);
        imageView = new ImageView(context);
        imageView.setImageResource(R.drawable.enemy);
        imageView.setX(enemyXpos[rnd.nextInt(xlenght)]);
        imageView.setY(enemyYpos[rnd.nextInt(ylenght)]);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
