package szalai.hu.snake;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import szalai.hu.snake.entitiy.Enemy;

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
    private Enemy enemy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        container = (RelativeLayout) findViewById(R.id.container);
        snakeBody = new ImageView(this);
        snakeBody.setX(0);
        snakeBody.setY(0);
        snakeBody.setImageResource(R.drawable.snakeimage);
        container.addView(snakeBody);
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
        enemy = new Enemy(this, 2, 2);
        container.addView(enemy.getImageView());


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
        Log.i("info", "snake " + snakeBody.getX() + " " + snakeBody.getY());
        Log.i("info", "enemy " + enemy.getImageView().getX() + " " + enemy.getImageView().getY());
        Log.i("info", "width " + width + " height " + height);
        if (x < 1 && snakeBody.getX() < width - 75) {
            snakeBody.setX(snakeBody.getX() + 25);
            //Log.i("info", "right");
        }
        if (x > -1 && snakeBody.getX() > 0) {
            snakeBody.setX(snakeBody.getX() - 25);
            //Log.i("info", "left");
        }
        if (y > 1 && snakeBody.getY() < height - 75) {
            snakeBody.setY(snakeBody.getY() + 25);
            //Log.i("info", "down");
        }
        if (y < -1 && snakeBody.getY() > 0) {
            snakeBody.setY(snakeBody.getY() - 25);
            //Log.i("info", "up");
        }

        if (enemy.getImageView().getX() == snakeBody.getX() && enemy.getImageView().getY() == snakeBody.getY()) {
            container.removeView(enemy.getImageView());
            enemy = new Enemy(this, 2, 2);
            container.addView(enemy.getImageView());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this, sensor);
    }


}
