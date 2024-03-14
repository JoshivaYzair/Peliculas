package ruiz.joshiva.peliculas2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class Recovery : AppCompatActivity() {

    lateinit var email: EditText
    lateinit var btn_recuperar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recovery)

        email = findViewById(R.id.Recovery_Email)
        btn_recuperar = findViewById(R.id.Recovery_btn_Recuperar)

        btn_recuperar.setOnClickListener{
            var email =  email.text.toString()

            if (!email.isNullOrEmpty()){
                Firebase.auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("Exito", "Email sent.")
                        }
                    }
                finish()
            }else{
                Toast.makeText(this,"Ingresa correo",Toast.LENGTH_SHORT).show()
            }
        }
    }
}