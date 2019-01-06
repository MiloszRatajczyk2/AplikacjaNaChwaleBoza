package miloszratajczyk.aplikacjanachwaleboza

import android.os.Bundle
import com.google.gson.Gson
import ListedSong
import android.annotation.SuppressLint
import android.view.ViewGroup
import android.widget.Button
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_s.*
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class SActivity : AppCompatActivity() {

    private val btnLayoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_s)

        //Reading from JSON
        val arrayOfSongbooks =
            arrayOf("pp.json", "smp.json", "pna.json", "bs.json", "sp.json", "w.json", "pnz.json")
        val jsonString =
            application.assets.open(arrayOfSongbooks[intent.extras.getInt("songbook",1) - 1])
            .bufferedReader().use { it.readText() }
        val listOfListedSongs =
            Gson().fromJson<List<ListedSong>>(jsonString, object : TypeToken<List<ListedSong>>(){}.type)

        //Creating Buttons
        for (i in 0 until listOfListedSongs.size) {
            val btn = Button(this)
            btn.layoutParams = btnLayoutParams
            btn.text = (i + 1).toString() + ". " + listOfListedSongs[i].title
            btn.setOnClickListener {
                startActivity(Intent(this, SongActivity::class.java)
                    .putExtra("number", i + 1)
                    .putExtra("git", false))
            }
            ll02.addView(btn)

            if ((i + 1) % 50 == 0) {
                val btn2 = Button(this)
                btn2.layoutParams = btnLayoutParams
                btn2.text = (i + 1).toString()
                btn2.setOnClickListener { sv02.smoothScrollTo(0, btn.y.toInt()) }
                ll01.addView(btn2)
            }
        }
        btnSearch.setOnClickListener {
            val num = etSearch.text.toString().toIntOrNull()
            if (num != null && num <= listOfListedSongs.size) {
                val intent = Intent(this, SongActivity::class.java)
                intent.putExtra("number", num)
                startActivity(intent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.s_act_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        startActivity(Intent(this@SActivity, SetActivity::class.java))
        return true
    }
}
