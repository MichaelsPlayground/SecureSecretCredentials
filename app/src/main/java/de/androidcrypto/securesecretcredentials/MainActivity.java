package de.androidcrypto.securesecretcredentials;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static String unscureApiKey = "1234-UNSECURE-###";
    private static String secureApiKey = BuildConfig.SECURE_API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView unsecure = findViewById(R.id.tvMainUnsecureCredential);
        TextView secure = findViewById(R.id.tvMainSecureCredential);

        unsecure.setText("This is the unsecured Api-key: " + unscureApiKey);

        secure.setText("This is the secured API-key: " + secureApiKey);

    }
}