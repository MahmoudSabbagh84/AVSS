package rirozizo.avss.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import rirozizo.avss.LoginActivity;
import rirozizo.avss.R;
import rirozizo.avss.helper.SQLiteHandler;
import rirozizo.avss.helper.SessionManager;

public class MainActivity extends Activity {
    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();
    }

    public void Logs(View v) {
        Intent intent = new Intent(rirozizo.avss.activity.MainActivity.this, LoggerActivity.class);
        startActivity(intent);
    }

    public void Led(View v) {
        final String TAG = "MQTT";
        Log.d(TAG, "pressed");
        String clientId = MqttClient.generateClientId();
        final MqttAndroidClient client =
                new MqttAndroidClient(this.getApplicationContext(), "tcp://192.168.0.101:1883",
                        clientId);

        try {
            Log.d(TAG, "try");
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName("steve");
            options.setPassword("password".toCharArray());
            Log.d(TAG, "connecting to MQTT broker");
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Log.d(TAG, "connected!");
                    String topic = "avss_mqtt";
                    String payload = "TURN LED ON PLZ";
                    byte[] encodedPayload = new byte[0];
                    try {
                        encodedPayload = payload.getBytes("UTF-8");
                        MqttMessage message = new MqttMessage(encodedPayload);
                        client.publish(topic, message);
                    } catch (UnsupportedEncodingException | MqttException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Log.d(TAG, "onFailure");

                }
            });
            Log.d(TAG, "END");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void Selfie(View v) {
        final String TAG = "MQTT";
        Log.d(TAG, "pressed");
        String clientId = MqttClient.generateClientId();
        final MqttAndroidClient client =
                new MqttAndroidClient(this.getApplicationContext(), "tcp://192.168.0.101:1883",
                        clientId);

        try {
            Log.d(TAG, "try");
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName("steve");
            options.setPassword("password".toCharArray());
            Log.d(TAG, "connecting");
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Log.d(TAG, "connected!");
                    String topic = "avss_mqtt";
                    String payload = "TAKE A SELFIE";
                    byte[] encodedPayload = new byte[0];
                    try {
                        encodedPayload = payload.getBytes("UTF-8");
                        MqttMessage message = new MqttMessage(encodedPayload);
                        client.publish(topic, message);
                    } catch (UnsupportedEncodingException | MqttException e) {
                        e.printStackTrace();
                    }
                    //SystemClock.sleep(2000);
                    Intent intent = new Intent(rirozizo.avss.activity.MainActivity.this, QualityPickerActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Log.d(TAG, "onFailure");

                }
            });
            Log.d(TAG, "END");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(rirozizo.avss.activity.MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}