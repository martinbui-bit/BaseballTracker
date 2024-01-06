package com.example.baseballtracker2023

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import java.io.IOException
import com.google.gson.Gson
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getInfo(view: View){

        val request: Request = Request.Builder()
            .url("https://api-baseball.p.rapidapi.com/games?league=1&season=2022")
            .get()
            .addHeader("X-RapidAPI-Key", "059016c823msh0fd3b21bdfb5497p1811adjsna0adf8fac25b")
            .addHeader("X-RapidAPI-Host", "api-baseball.p.rapidapi.com")
            .build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle the error
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body()
                val body = responseBody?.string()
                val gameResponse = Gson().fromJson(body, GameResponse::class.java)
                val games = gameResponse.response
                //            println(games)
                // games starting at 193 have scores
                // Do something with the list of games
                val index = Random.nextInt(200, 2963)
                var gameText = findViewById<TextView>(R.id.gamesList)
                gameText.append("\nHome Team: " + games[index].teams.home.name +
                        "\nAway Team: " + games[index].teams.away.name +
                        "\nHome team score:" + games[index].scores.home.total +
                        "\nAway team score:" + games[index].scores.away.total + "\n")



            }
        })
    }

    fun removeItem(view: View){
        var currTextView = findViewById<TextView>(R.id.gamesList)
        var currText = currTextView.text
        var lst = ArrayList<Int>() // hold all the new lines
        for ( i in currText.indices){
            if(currText[i] == '\n')
                lst.add(i)
        }
        try {
            // get the index of the last teams first newline character
            val index = lst[lst.size - 5]
            currText = currText.substring(0, index)
            currTextView.text = currText
        }catch (e: IndexOutOfBoundsException){

        }
    }
    fun changeView(view: View)
    {
        val intent = Intent(this, VideoPlayerActivity::class.java)
        startActivity(intent)
    }

    //commented out code below was used to test and display listView
//    private class MyAdapter(context: Context): BaseAdapter()
//    {
//        private val mContext: Context
//
//        init {
//            mContext = context
//        }
//        override fun getCount(): Int {
//            return 10
//        }
//
//        override fun getItemId(position: Int): Long {
//            return position.toLong()
//        }
//        override fun getItem(position: Int): Any {
//
//            return "TEST STRING"
//        }
//
//        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//            val textView = TextView(mContext)
//            textView.text = "Testing"
//
////            textView.text =
//            return textView
//        }
//    }
}



data class GameResponse(
    val response: List<Game>,
    var homeTeam: String,//Martin added this
    var awayTeam: String//Martin added this
)
data class Game(
    val game_id: Int,
    val league: League,
    val teams: Team,
    val scores: Score,
    val date: String,
    val time: String
)

data class League(
    val id: Int,
    val name: String
)

data class Team(
    val home: Home,
    val away: Away
)

data class Home(
    val id: Int,
    val name: String
)

data class Away(
    val id: Int,
    val name: String
)

data class Score(
    val home: HomeScore,
    val away: AwayScore
)

data class HomeScore(
    val hits: Int,
    val errors: Int,
    val total: Int
)

data class AwayScore(
    val hits: Int,
    val errors: Int,
    val total: Int
)




