package co.edu.unal.krunko.sitespins.presentation;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import co.edu.unal.krunko.sitespins.R;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		Intent goLogin = new Intent(this, LoginActivity.class);
		//startActivity(goLogin);
	}
}
