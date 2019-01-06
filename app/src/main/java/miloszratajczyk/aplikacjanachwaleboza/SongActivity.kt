package miloszratajczyk.aplikacjanachwaleboza

import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ListedSong
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_song.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SongActivity : AppCompatActivity() {

    private var songNumber = 0
    private var title = ""
    private var capo = ""
    private var bCh = ""
    private var ch = ""
    private var aCh = ""
    private var link = ""
    private var chords = false
    private var enters = false
    private var playing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)

        //Reading JSON
        val jsonString = application.assets.open("sp.json").bufferedReader().use{ it.readText() }
        val listOfListedSongs = Gson().fromJson<List<ListedSong>>(jsonString, object : TypeToken<List<ListedSong>>(){}.type)

        //Setting song values
        songNumber = intent.extras.getInt("number",1)
        title = listOfListedSongs[songNumber-1].title
        capo = listOfListedSongs[songNumber-1].capo
        bCh = listOfListedSongs[songNumber-1].b_ch
        ch = listOfListedSongs[songNumber-1].ch
        aCh = listOfListedSongs[songNumber-1].a_ch
        link = listOfListedSongs[songNumber-1].link
        chords = intent.extras.getBoolean("git",false)
        enters = false

        //Setting ActionBar
        val myActionBar = supportActionBar
        if(chords)
            myActionBar?.title = songNumber.toString() + if(capo != "") " - Capo: $capo"
        else
            myActionBar?.title = songNumber.toString()
        myActionBar?.subtitle = listOfListedSongs[songNumber-1].title

        //Setting TextViews
        set(tvBCh, bCh)
        set(tvCh, ch)
        set(tvACh, aCh + aCh)
    }

    private fun String.setChords(): String {
        if(!chords) {
            var new = ""
            var add = true

            for(i in 0 until this.length-1) {
                if (this[i] == '~') {
                    add = false
                }
                else if(this[i] == '&' || this[i] == '*') {
                    add = true
                    new += this[i]
                }
                else if(add) {
                    new += this[i]
                }
            }

            return new.replace("~","")
        }
        else {
            return this.replace("~","")
        }
    }

    private fun setVal(songbook: Int, num: Int) {

    }
    //Setting a TextView DONE
    private fun set(tv: TextView, text: String) {
        tv.visibility =  View.VISIBLE
        if(text == "")
            tv.visibility = View.GONE
        else
            tv.text = text.setChords().setEnters()
    }
    //Setting enters in string DONE
    private fun String.setEnters(): String {
        return if (enters || chords)
            this.replace("&", "\n").replace("*", "\n\n")
        else
            this.replace("&", "").replace("*", "\n\n")
    }
    //Operates playing
    private fun onMenuPlay() {
        /*
        if(playing) {
            llYT.visibility = View.VISIBLE
            Toast.makeText(this, "Wczytywanie...", Toast.LENGTH_LONG).show()

        }
        else {
            llYT.visibility = View.GONE
        }
        playing = !playing
        */
    }
    //Changes view to Chords DONE
    private fun onMenuChords() {
        val intent = Intent(this, SongActivity::class.java)
        intent.putExtra("number", songNumber)
        intent.putExtra("git", !chords)
        startActivity(intent)
        finish()
    }
    //Creates OptionsMenu DONE
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.song_act_menu, menu)

        return true
    }
    //Operates OptionsMenu DONE
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menuPlay -> onMenuPlay()
            R.id.menuChords -> onMenuChords()
        }
        return true
    }
    //Stops player onDestroy
    override fun onDestroy() {
        super.onDestroy()
    }
}
