package club.scorpio.univ.scorpio

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_login.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar_login)

        toolbar_login.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar_login.setNavigationOnClickListener {
            onBackPressed()
        }
    }



    override fun onStart() {
        super.onStart()


        login_button.setOnClickListener{ v ->

            if(checkEmail() && checkPass()){
                // TODO: if everything is ok
            }else {
                Snackbar.make(v, "E-mail or Password incorrect", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null)
                        .show()
            }
        }

        register_button.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            Toast.makeText(this, "You will have to agree to the university intern rules",
                    Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        if(!TextUtils.isEmpty(password.text) || !TextUtils.isEmpty(email.text))
            showAlert()
        else {
            super.onBackPressed()
            finish()
        }
    }

    private fun showAlert(){
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Alert")
                .setPositiveButton("OK"){
                    dialog, which -> super.onBackPressed()
                }
                .setNegativeButton("Cancel"){
                    dialog, which -> dialog.dismiss()
                }
                .setCancelable(true)
                .setMessage("Your changes will be lost !")
                .setIcon(R.drawable.ic_warning_yellow_24dp)
                .show()
    }

    private fun checkPass(): Boolean{
        return if (TextUtils.isEmpty(password.text) || password.text.length < 5) {
            password.error = "Invalid Password"
            false
        } else true
    }

    private fun checkEmail(): Boolean{
        return if (TextUtils.isEmpty(email.text) || !Patterns.EMAIL_ADDRESS.matcher(email.text).matches()){
            email.error = "Invalid Email Address"
            false
        } else true
    }

    // TODO: implement login button and connect to firebase
}
