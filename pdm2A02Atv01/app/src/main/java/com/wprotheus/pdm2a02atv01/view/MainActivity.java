package com.wprotheus.pdm2a02atv01.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wprotheus.pdm2a02atv01.R;
import com.wprotheus.pdm2a02atv01.model.User;
import com.wprotheus.pdm2a02atv01.util.Auxilia;
import com.wprotheus.pdm2a02atv01.util.Conexao;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private final String URL = "https://jsonplaceholder.typicode.com/posts";
    private List<User> dadosBaixados = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        executarTarefa();
    }

    private void executarTarefa() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        service.execute(() -> {
            try {
                Conexao conexao = new Conexao();
                InputStream inputStream = conexao.obterRespostaHTTP(URL);
                Auxilia auxilia = new Auxilia();
                String textoFromJson = auxilia.converter(inputStream);
                Gson gson = new Gson();
                if (!textoFromJson.isEmpty()) {
                    Type type = new TypeToken<List<User>>() {
                    }.getType();
                    dadosBaixados = gson.fromJson(textoFromJson, type);
                } else
                    handler.post(() -> Toast.makeText(getApplicationContext(),
                            "Não foi possível obter os dados.", Toast.LENGTH_SHORT).show());
                handler.post(() -> {
                    Intent intent = new Intent(getApplicationContext(), PresentationActivity.class);
                    intent.putExtra("dados", String.valueOf(dadosBaixados));
                    startActivity(intent);
                });
            } catch (Exception e) {
                e.printStackTrace();
                handler.post(() -> Toast.makeText(getApplicationContext(),
                        "URL inválida.", Toast.LENGTH_LONG).show());
            }
        });
    }
}