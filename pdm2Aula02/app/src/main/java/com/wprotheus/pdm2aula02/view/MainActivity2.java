package com.wprotheus.pdm2aula02.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.wprotheus.pdm2aula02.R;
import com.wprotheus.pdm2aula02.model.User;

import java.util.Collections;

public class MainActivity2 extends AppCompatActivity {
    private String dados = null;
    private ListView listView;
    private ArrayAdapter<User> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = findViewById(R.id.lvTeste);
        dados = getIntent().getStringExtra("dados");
        adapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1, Collections.singletonList(dados));
        listView.setAdapter(adapter);
    }
}