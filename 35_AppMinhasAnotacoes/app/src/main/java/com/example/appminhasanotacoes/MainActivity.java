package com.example.appminhasanotacoes;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

     private FloatingActionButton fab;
     private EditText editAnotacao;
     private AnotacoesPreferencias preferencias;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);

          editAnotacao =  findViewById(R.id.editTextTextMultiLine);
          preferencias = new AnotacoesPreferencias(getApplicationContext());//Context do main

          fab = findViewById(R.id.fab);
          fab.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                    String txtRec = "";
                    try {
                         txtRec = editAnotacao.getText().toString();
                    }catch (Exception e){
                         e.printStackTrace();
                    }

                    //Validar se foi digitado algo
                    if (txtRec.equals("")){
                         Snackbar.make(view, "Preencha a anotação", Snackbar.LENGTH_LONG).show();
                    } else {
                         preferencias.salvarAnotacao(txtRec);
                         Snackbar.make(view, "Anotação salva com sucesso.", Snackbar.LENGTH_LONG).show();
                    }
               }
          });

          //Recuperando Anotacoes
          String anotacao = preferencias.recuperarAnotacao();
          if (!anotacao.equals("")){
               editAnotacao.setText(anotacao);
          }
     }
}