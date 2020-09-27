package rirozizo.avss.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import rirozizo.avss.R;

public class QualityPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quality_picker);
    }

    public void HighQuality(View v)
    {
        Intent intent = new Intent(rirozizo.avss.activity.QualityPickerActivity.this, HighQualityActivity.class);
        startActivity(intent);
    }

    public void LowQuality(View v)
    {
        Intent intent = new Intent(rirozizo.avss.activity.QualityPickerActivity.this, LowQualityActivity.class);;
        startActivity(intent);
    }
}
