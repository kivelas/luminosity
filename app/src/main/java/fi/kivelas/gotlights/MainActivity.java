package fi.kivelas.gotlights;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    SensorManager smLight;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.image);
        textView = (TextView) findViewById(R.id.text);

        smLight = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        final Sensor lightSensor = smLight.getDefaultSensor(Sensor.TYPE_LIGHT);

        SensorEventListener listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                //get light measure
                float lightValue = sensorEvent.values[0];
                if(lightValue > 200){
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.sunglasses));
                    textView.setText(getResources().getString(R.string.bright));
                } else if(lightValue > 10 && lightValue <= 200){
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.happy));
                    textView.setText(getResources().getString(R.string.nice));
                } else if(lightValue > 2 && lightValue <= 10){
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.whatever));
                    textView.setText(getResources().getString(R.string.normal));
                } else if(lightValue > 0 && lightValue <= 2){
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.cryingnotears));
                    textView.setText(getResources().getString(R.string.shady));
                } else if(lightValue <= 0){
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.horror));
                    textView.setText(getResources().getString(R.string.dark));
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        smLight.registerListener(listener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
}