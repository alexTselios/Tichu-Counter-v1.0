package alexandrostselios.tichucounter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import java.io.IOException;

public class Load extends Activity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a FileManager object to use files
        try {
            FileManager fileManager = new FileManager(getIntent(),this);
            fileManager.readData();
            EditText text = findViewById(R.id.score1EditText);
            text.setText("100");
        } catch (IOException e) {
            e.printStackTrace();
        }
        finish();
    }
}