package szalai.hu.snake;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import szalai.hu.snake.entitiy.Enemy;

import static szalai.hu.snake.R.drawable.enemy;

public class PlayActivity extends Activity implements SensorEventListener {


    private SensorManager sensorManager;
    Sensor sensor;
    private float x;
    private float y;
    private ImageView snakeBody;
    private RelativeLayout container;
    private int width;
    private int height;
    private boolean up;
    private boolean down;
    private boolean right;
    private boolean left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        container = (RelativeLayout) findViewById(R.id.container);
        snakeBody = (ImageView) findViewById(R.id.snakeimage);
        ViewTreeObserver vto = container.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                container.getViewTreeObserver().removeOnPreDrawListener(this);
                width = container.getWidth();
                height = container.getHeight();
                return true;
            }
        });
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        ImageView enemyView = new ImageView(this);
        enemyView.setImageResource(enemy);
        Enemy enemy = new Enemy(39, 65);
        enemyView.setX(250);
        enemyView.setY(250);
        container.addView(enemyView);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        this.x = event.values[0];
        this.y = event.values[1];
        stearing();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void stearing() {
        Log.i("info", snakeBody.getX() + " " + snakeBody.getY());
        if (x < 2 && snakeBody.getX() < width - 75) {
            snakeBody.setX(snakeBody.getX() + 25);
            Log.i("info", "right");
        }
        if (x > -2 && snakeBody.getX() > 25) {
            snakeBody.setX(snakeBody.getX() - 25);
            Log.i("info", "left");
        }
        if (y > 2 && snakeBody.getY() < height - 75) {
            snakeBody.setY(snakeBody.getY() + 25);
            Log.i("info", "down");
        }
        if (y < -2 && snakeBody.getY() > 25) {
            snakeBody.setY(snakeBody.getY() - 25);
            Log.i("info", "up");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this, sensor);
    }


}
