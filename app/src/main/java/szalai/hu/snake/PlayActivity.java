package szalai.hu.snake;

import android.app.Activity;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;

public class PlayActivity extends Activity implements SensorEventListener{


    private SensorManager sensorManager;
    Sensor sensor;
    private float x;
    private float y;
    private ImageView snakeBody;
    int width;
    int heigt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        heigt = size.y;
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        snakeBody = (ImageView) findViewById(R.id.snakeimage);


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        this.x = event.values[0];
        this.y = event.values[1];
        Log.i("info", x + " " + y);
        if(x<0 && snakeBody.getX()<width-25){
            snakeBody.setX(snakeBody.getX()+25);
            Log.i("info","right");
        }
        if(x>0 && snakeBody.getX()>0){
            snakeBody.setX(snakeBody.getX()-25);
            Log.i("info","left");
        }
        if(y<0 && snakeBody.getY()<heigt-25){
            snakeBody.setY(snakeBody.getY()+25);
            Log.i("info","down");
        }
        if(y>0 && snakeBody.getY()>0){
            snakeBody.setY(snakeBody.getY()-25);
            Log.i("info","up");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
