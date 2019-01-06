package miloszratajczyk.aplikacjanachwaleboza

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        //Setting ActionBar
        val myActionBar = supportActionBar
        myActionBar?.title = "Aplikacja Na Chwałę Bożą"
        myActionBar?.subtitle = "Spiewniki"
    }

    fun btnClick(view: View) {
        val intent = Intent(this, SActivity::class.java)
            when(view.id) {
            listActBtn01.id -> intent.putExtra("songbook", 1) //pp
            listActBtn02.id -> intent.putExtra("songbook", 2) //smp
            listActBtn03.id -> intent.putExtra("songbook", 3) //pna
            listActBtn04.id -> intent.putExtra("songbook", 4) //bs
            listActBtn05.id -> intent.putExtra("songbook", 5) //sp
            listActBtn06.id -> intent.putExtra("songbook", 6) //w
            listActBtn07.id -> intent.putExtra("songbook", 7) //pnz
            else -> intent.putExtra("songbook", 1)
        }
        startActivity(intent)
    }

}
