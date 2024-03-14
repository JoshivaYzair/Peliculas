package ruiz.joshiva.peliculas2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class Login : AppCompatActivity() {
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var btn_login : Button
    lateinit var btn_singUp: Button
    lateinit var recuperar: TextView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        email = findViewById(R.id.Login_Email)
        password = findViewById(R.id.Login_Password)
        btn_login = findViewById(R.id.Login_btn_Ingresar)
        btn_singUp = findViewById(R.id.Login_btn_Registrar)
        recuperar = findViewById(R.id.Login_lb_Recuperar)

        btn_singUp.setOnClickListener{
            var intent = Intent(this, SingUp::class.java)
            startActivity(intent)
        }
        btn_login.setOnClickListener{
            var email = email.text.toString()
            var password = password.text.toString()

            var intent = Intent(this, MainActivity::class.java)

            if (!email.isNullOrEmpty() && !password.isNullOrEmpty()){
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Exito", "signInWithEmail:success")
                            //val user = auth.currentUser
                            //updateUI(user)
                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Error", "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()
                            //updateUI(null)
                        }
                    }
            }else{
                Toast.makeText(this,"Email o contraseña no puede estar vacio", Toast.LENGTH_SHORT).show()
            }

        }

        recuperar.setOnClickListener{
            var intent = Intent(this, Recovery::class.java)
            startActivity(intent)
        }
    }
}