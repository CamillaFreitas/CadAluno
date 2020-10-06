package br.com.usjt.camilla.cadaluno.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.orhanobut.hawk.Hawk;

import br.com.usjt.camilla.cadaluno.R;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Hawk.init(this).build();
    }

    public void salvar(View view) {
        Hawk.put("tem_cadastro", true);
    }
}