package club.scorpio.univ.scorpio

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.content_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setSupportActionBar(toolbar_register)

        toolbar_register.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        if(false) // todo: condition
            showAlert()
        else {
            super.onBackPressed()
            finish()
        }
    }

    override fun onStart() {
        super.onStart()

        button_register.setOnClickListener {v ->
            if(false){
                // todo: condition
            }else
                Snackbar.make(v, "Please fix all wrong inputs !", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null)
                        .show()

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

    // TODO: implement register button
}
