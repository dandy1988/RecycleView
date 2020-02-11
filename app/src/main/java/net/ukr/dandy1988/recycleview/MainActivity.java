package net.ukr.dandy1988.recycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.TaskStackBuilder;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private Button btnEmail;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "FAB pressed", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(new AdapterRecycleView.TaskAdapter(generateEvents()));

        btnEmail = findViewById(R.id.btnEmail);

    }

    // инфлейт меню (вверху приложения справа)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //создание списка меню и действия по выбору из списка
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast.makeText(this, "Good bye!", Toast.LENGTH_LONG).show();
//            finishAffinity();
            this.finish();
            return true;
        }
        if (id == R.id.settings_email) {
            btnEmailPressed(new View(this));
            return true;

        }
        return super.onOptionsItemSelected(item);
    }


    //создание списка ивентов
    public List<SwimEvent> generateEvents() {
        List<SwimEvent> events = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            events.add(new SwimEvent("2020.1." + i, "event#" + i, 0));
        }
        return events;
    }

    //кнопка для отправки e-mail
    public void btnEmailPressed(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        Uri uri = Uri.parse("mailto: dandy1988@gmail.com");
        intent.setData(uri);
        intent.putExtra("subject", "my subject");
        intent.putExtra("body", "my message");
        startActivity(intent);
    }


}
