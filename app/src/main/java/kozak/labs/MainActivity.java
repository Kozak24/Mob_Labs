package kozak.labs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button clearButton = (Button) findViewById(R.id.clearButton);
        final Button helloButton = (Button) findViewById(R.id.helloButton);
        final EditText namePlaceholder = (EditText) findViewById(R.id.name);
        final TextView textView = (TextView) findViewById(R.id.textView);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namePlaceholder.setText("");
            }
        });

        helloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("Hello " + namePlaceholder.getText().toString() + "!");
            }
        });
    }

}
