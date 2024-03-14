package ruiz.joshiva.peliculas2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class SingUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var email : EditText
    lateinit var password : EditText
    lateinit var confirmPassword : EditText
    lateinit var nombre :EditText
    lateinit var genero : EditText
    lateinit var edad : EditText
    lateinit var telefono : EditText
    lateinit var btn_Registrar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

        email = findViewById(R.id.SignUp_Email)
        password = findViewById(R.id.SignUp_Password)
        confirmPassword = findViewById(R.id.SignUp_ConfirmPassword)
        btn_Registrar = findViewById(R.id.SignUp_btn_Registrar)
        nombre = findViewById(R.id.SignUp_Nombre)
        genero = findViewById(R.id.SignUp_Genero)
        edad = findViewById(R.id.SignUp_Edad)
        telefono = findViewById(R.id.SignUp_Telefono)

        auth = Firebase.auth

        btn_Registrar.setOnClickListener{
            var correo:String = email.text.toString()
            var password:String = password.text.toString()
            var confirmPassword:String = confirmPassword.text.toString()
            if (!correo.isNullOrEmpty() && !password.isNullOrEmpty() && !confirmPassword.isNullOrEmpty()){
                if (password == confirmPassword && password.length>6){
                    auth.createUserWithEmailAndPassword(correo, password)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("Exito", "createUserWithEmail:success")
                                //updateUI(user)
                                finish()
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("Error", "createUserWithEmail:failure", task.exception)
                                Toast.makeText(
                                    baseContext,
                                    "Authentication failed.",
                                    Toast.LENGTH_SHORT,
                                ).show()
                                //updateUI(null)
                            }
                        }
                }else{
                    Toast.makeText(this,"Las contraseñas no son iguales o tiene menos de 6 caracteres",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Ingrese correo y contraseña",Toast.LENGTH_SHORT).show()
            }
        }
    }
}