package com.wprotheus.pdm2aula01.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wprotheus.pdm2aula01.R;
import com.wprotheus.pdm2aula01.model.Estudante;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActivitySegunda extends AppCompatActivity {
    private String dadosJSON;
    private ListView listView;
    private List<Estudante> lista;
    private ArrayAdapter<Estudante> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        dadosJSON = getIntent().getStringExtra("dados");
        listView = findViewById(R.id.listaViewDados);
        Toast.makeText(ActivitySegunda.this, dadosJSON, Toast.LENGTH_SHORT).show();
        lista = consumirJson();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        listView.setAdapter(adapter);
    }

    protected List<Estudante> consumirJson() {
        List<Estudante> listaEstudantes = null;
        if (!dadosJSON.isEmpty()) {
            System.out.println("linha 41 -> " + dadosJSON);
            listaEstudantes = new ArrayList<>();
            try {
                JSONObject jsonObject = new JSONObject(dadosJSON);
                JSONArray jsonArray = jsonObject.getJSONArray("Estudantes");
                System.out.println("linha 46 -> Chegou aqui");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    Estudante estudante = new Estudante();
                    estudante.setNome(object.getString("nome"));
                    estudante.setDisciplina(object.getString("disciplina"));
                    estudante.setNota(object.getInt("nota"));
                    listaEstudantes.add(estudante);
                }
                System.out.println("linha 55 -> " + listaEstudantes.get(0).toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return listaEstudantes;
    }
}