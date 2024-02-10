package com.wprotheus.pdm2a02atv01.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.wprotheus.pdm2a02atv01.R;
import com.wprotheus.pdm2a02atv01.model.User;

import java.util.Collections;

public class PresentationActivity extends AppCompatActivity {
    private String dados = null;
    private ListView lvDados;
    private ArrayAdapter<User> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);
        lvDados = findViewById(R.id.lvDados);

        dados = getIntent().getStringExtra("dados");
        adapter = new ArrayAdapter(getApplicationContext(),
                            android.R.layout.simple_list_item_1, Collections.singletonList(dados));
        lvDados.setAdapter(adapter);
    }
}