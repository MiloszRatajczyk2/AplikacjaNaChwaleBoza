package miloszratajczyk.aplikacjanachwaleboza

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //Main function DONE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    //Operates Buttons DONE
    fun btnClick(view: View) {
        val intent = when(view.id) {
            mainActBtn01.id -> Intent(this@MainActivity, ListActivity::class.java)
            mainActBtn02.id -> Intent(this@MainActivity, BibleActivity::class.java)
            mainActBtn03.id -> Intent(this@MainActivity, BooksActivity::class.java)
            mainActBtn04.id -> Intent(this@MainActivity, SermonActivity::class.java)
            else -> Intent(this, SActivity::class.java)
        }
        startActivity(intent)
    }
    //Creates OptionsMenu DONE
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_act_menu, menu)
        return true
    }
    //Operates OptionsMenu DONE
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        startActivity(Intent(this@MainActivity, SetActivity::class.java))
        return true
    }
}
