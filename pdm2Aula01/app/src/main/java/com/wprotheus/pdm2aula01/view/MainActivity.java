package com.wprotheus.pdm2aula01.view;

import static com.google.android.material.internal.ViewUtils.hideKeyboard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wprotheus.pdm2aula01.R;
import com.wprotheus.pdm2aula01.model.Estudante;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editTextNome;
    private EditText editTextDisciplina;
    private EditText editTextNota;
    private List<Estudante> lista;
    private TextView textViewResultado;
    private String retorno;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextNome = findViewById(R.id.editTextNome);
        editTextDisciplina = findViewById(R.id.editTextDisciplina);
        editTextNota = findViewById(R.id.editTextNota);
        textViewResultado = findViewById(R.id.textViewResultado);
        editTextNome.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) hideKeyboard(v);
        });
        editTextDisciplina.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) hideKeyboard(v);
        });
        editTextNota.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) hideKeyboard(v);
        });
        lista = new ArrayList<>();
    }

    public void criarLista(View view) {
        if (!editTextNome.getText().toString().isEmpty()
                && !editTextDisciplina.getText().toString().isEmpty() && !editTextNota.getText().toString().isEmpty()) {
            lista.add(new Estudante(editTextNome.getText().toString(),
                    editTextDisciplina.getText().toString(),
                    Integer.parseInt(editTextNota.getText().toString())));
            Toast.makeText(MainActivity.this, "Item inserido", Toast.LENGTH_SHORT).show();
            editTextNome.setText(editTextNome.getHint().toString());
            editTextDisciplina.setText(editTextDisciplina.getHint().toString());
            editTextNota.setText(editTextNota.getHint().toString());
        } else
            Toast.makeText(this.getApplicationContext(), "Preencher todos os campos", Toast.LENGTH_SHORT).show();
    }

    public String criarJSON(List<Estudante> dados) {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < dados.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("nome", dados.get(i).getNome());
                jsonObject.put("disciplina", dados.get(i).getDisciplina());
                jsonObject.put("nota", dados.get(i).getNota());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return "{ Estudantes: " + String.valueOf(jsonArray) + "}";
    }

    public void gerarJSON(View view) {
        retorno = criarJSON(lista);
        textViewResultado.setText(retorno);
    }

    public void abrirTela(View view) {
        Intent intent = new Intent(this.getApplicationContext(), ActivitySegunda.class);
        intent.putExtra("dados", retorno);
        startActivity(intent);
    }
}