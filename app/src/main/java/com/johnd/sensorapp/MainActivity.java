package com.johnd.sensorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.hardware.SensorEvent;
import android.hardware.Sensor;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SensorManager sensorManager = null;
    TextView Readings = null;
    TextView Subtitle;
    List list;

    SensorEventListener sensorEventListener = new SensorEventListener(){
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            Readings.setText("x: "+values[0]+"\ny: "+values[1]+"\nz: "+values[2]);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instance of sensor manager
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        Readings = (TextView)findViewById(R.id.tvReadings);

        Subtitle = (TextView)findViewById(R.id.tvSubTitle);
        
        

        list = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if(list.size()>0){
            sensorManager.registerListener(sensorEventListener, (Sensor) list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            Toast.makeText(getBaseContext(), "Error, No Accelerometer Available.", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    protected void onStop() {
        if(list.size()>0){
            sensorManager.unregisterListener(sensorEventListener);
        }
        super.onStop();
    }

}
