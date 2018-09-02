package kozak.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Console;

import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        printIntoConsole();
    }


    private void printIntoConsole()
    {
        System.out.println("Hello world");
    }
}
