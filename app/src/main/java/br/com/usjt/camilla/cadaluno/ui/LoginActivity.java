package br.com.usjt.camilla.cadaluno.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import br.com.usjt.camilla.cadaluno.R;
import br.com.usjt.camilla.cadaluno.model.Usuario;
import br.com.usjt.camilla.cadaluno.model.UsuarioViewModel;

public class LoginActivity extends AppCompatActivity {

    private Button buttonLogin;
    private TextView textViewNovoCadastro;
    private UsuarioViewModel usuarioViewModel;
    private Usuario usuarioCorrente;
    private EditText editTextEmail;
    private EditText editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Hawk.init(this).build();

        editTextEmail = findViewById(R.id.editTextUsuario);
        editTextSenha = findViewById(R.id.editTextSenha);

        usuarioViewModel = new ViewModelProvider(this).get(UsuarioViewModel.class);

        usuarioViewModel.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(@Nullable final Usuario usuario) {
                updateUsuario(usuario);
            }
        });

        buttonLogin = findViewById(R.id.buttonLogin);
        textViewNovoCadastro = findViewById(R.id.textViewNovoCadastro);

        if(Hawk.contains("tem_cadastro")){
            if(Hawk.get("tem_cadastro")){
                //desbloquear frase loguin e esconder frase de cadastro
                desbloquear();
            }
            else{
                //bloquear o loguin e mostrar frase de cadastro
                bloquear();
            }
        }
        else{
            //bloquear loguin e mostrar frase
            bloquear();
        }
    }

    private void updateUsuario(Usuario usuario){
        usuarioCorrente = usuario;
    }

    private void bloquear(){
        buttonLogin.setEnabled(false);
        buttonLogin.setBackgroundColor(getResources().getColor(R.color.cor_cinza));
        textViewNovoCadastro.setVisibility(View.VISIBLE);
    }

    private void desbloquear(){
        buttonLogin.setEnabled(true);
        buttonLogin.setBackgroundColor(getResources().getColor(R.color.cor_vermelha));
        textViewNovoCadastro.setVisibility(View.GONE);
    }

    public void novoCadastro(View view) {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        if(usuarioCorrente != null){
            if(usuarioCorrente.getEmail().equalsIgnoreCase(editTextEmail.getText().toString())
            && usuarioCorrente.getSenha().equals(editTextSenha.getText().toString())){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Usuário e/ou senha inválidos", Toast.LENGTH_SHORT).show();
            }
        }
    }
}