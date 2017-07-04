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

public class PlayActivity extends Activity implements SensorEventListener{


    private SensorManager sensorManager;
    Sensor sensor;
    private float x;
    private float y;
    private ImageView snakeBody;
    RelativeLayout container;
    int width;
    int height;

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
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        this.x =  event.values[0];
        this.y =  event.values[1];

        if(x<0 && snakeBody.getX()<width-75){
            snakeBody.setX(snakeBody.getX()+25);
            Log.i("info","right");
        }
        if(x>0 && snakeBody.getX()>25){
            snakeBody.setX(snakeBody.getX()-25);
            Log.i("info","left");
        }
        if(y>0 && snakeBody.getY()<height-75){
            snakeBody.setY(snakeBody.getY()+25);
            Log.i("info","down");
        }
        if(y<0 && snakeBody.getY()>25){
            snakeBody.setY(snakeBody.getY()-25);
            Log.i("info","up");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private int getRelativeLeft(View myView) {
        if (myView.getParent() == myView.getRootView())
            return myView.getLeft();
        else
            return myView.getLeft() + getRelativeLeft((View) myView.getParent());
    }

    private int getRelativeTop(View myView) {
        if (myView.getParent() == myView.getRootView())
            return myView.getTop();
        else
            return myView.getTop() + getRelativeTop((View) myView.getParent());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this,sensor);
    }

    class MyGlobalListenerClass implements ViewTreeObserver.OnGlobalLayoutListener{
        @Override
        public void onGlobalLayout() {
            View v = (View) findViewById(R.id.container);
            width = v.getWidth();
            height = v.getHeight();
        }
    }
}
