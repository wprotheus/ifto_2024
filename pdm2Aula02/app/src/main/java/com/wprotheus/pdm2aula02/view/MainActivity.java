package com.wprotheus.pdm2aula02.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wprotheus.pdm2aula02.R;
import com.wprotheus.pdm2aula02.model.User;

import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String URL = "https://jsonplaceholder.typicode.com/posts";
    private TextView textViewID;
    private ListView listView;
    private StringBuilder builder = null;
    private List<User> dadosBaixados = null;
    private ArrayAdapter<User> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewID = findViewById(R.id.dadosID);
        listView = findViewById(R.id.listaViewDados);
        new ObterDados().execute();
    }

    private class ObterDados extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            Conexao conexao = new Conexao();
            InputStream inputStream = null;
            try {
                inputStream = conexao.obterRespostaHTTP(URL);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Auxilia auxilia = new Auxilia();
            String textoJSON = auxilia.converter(inputStream);
            Log.i("JSON", "doInBackground: " + textoJSON);
            Gson gson = new Gson();
            builder = new StringBuilder();

            if (textoJSON != null) {
                Type type = new TypeToken<List<User>>() {
                }.getType();
                dadosBaixados = gson.fromJson(textoJSON, type);
//                dadosBaixados.forEach(user -> {builder.append(user).append("\n");});
                for (int i = 0; i < dadosBaixados.size(); i++) {
                    builder.append(dadosBaixados.get(i).getUserId()).append("\n");
                }
            } else {
                runOnUiThread(() -> Toast.makeText(getApplicationContext(),
                        "Não foi possível obter JSON", Toast.LENGTH_SHORT).show());
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Download começando...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
//            Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
//            intent.putExtra("dados", String.valueOf(dadosBaixados));
//            startActivity(intent);
            adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, dadosBaixados);
            listView.setAdapter(adapter);
//            textViewID.setText(builder.toString());
        }
    }
}