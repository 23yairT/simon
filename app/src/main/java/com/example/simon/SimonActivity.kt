package com.example.simon

import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import java.util.*
import kotlin.collections.ArrayList

class SimonActivity : AppCompatActivity(), View.OnClickListener {

    var soundPool: SoundPool? = null

    val simonImageView = arrayOfNulls<SimonCellType>(4)

    private var simonSoundPoolLoaded = false
    private var simonLevel = 1
    private var simonCellOnList: ArrayList<Int>? = null
    private var simonCount = 0
    private var simonCurrentCellIndex = 0
    private var simonTimer: Timer? = null
    private var simonTimerTask: TimerTask? = null
    private val simonSoundID = IntArray(4)
    private var simonSoundVolume = 0f
    private var simonPrevSoundStreamID = 0
    private var simonScore = 0
    private var simonTimerForClick: CountDownTimer? = null
    private var simonSoundOn = true
    private var simonCountdownTimer: CountDownTimer? = null
    private var simonTimerTaskCompleted = true
    private var simonInstanceSaved = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simon)

        simonImageView[0] = findViewById<View>(R.id.greenCell) as SimonCellType
        simonImageView[1] = findViewById<View>(R.id.redCell) as SimonCellType
        simonImageView[2] = findViewById<View>(R.id.yellowCell) as SimonCellType
        simonImageView[3] = findViewById<View>(R.id.blueCell) as SimonCellType
        simonImageView[0]!!.setCellType(SimonCellType.CELL_TYPE_GREEN)
        simonImageView[1]!!.setCellType(SimonCellType.CELL_TYPE_RED)
        simonImageView[2]!!.setCellType(SimonCellType.CELL_TYPE_YELLOW)
        simonImageView[3]!!.setCellType(SimonCellType.CELL_TYPE_BLUE)

        for (i in 0..3) {
            simonImageView[i]!!.setOnClickListener(this)
            simonImageView[i]!!.setOff()
        }

        if (savedInstanceState != null) {
            simonInstanceSaved = true
            val simonArray = savedInstanceState.getIntArray(CELL)
            simonCellOnList = ArrayList()

            for (i in simonArray!!.indices) {
                simonCellOnList!!.add(simonArray[i])
            }

            simonScore = savedInstanceState.getInt(SCORE)
            simonLevel = savedInstanceState.getInt(LEVEL)

            simonTimerTaskCompleted = savedInstanceState.getBoolean(TIMER)

            gameSounds()

        } else {

            gameSounds()

            startGame()
        }
    }

    private fun startGame() {

        val round = Random(Calendar.getInstance().timeInMillis)

        if (simonCellOnList == null) {
            simonCellOnList = ArrayList()
        }

        val number = round.nextInt(4)
        simonCellOnList!!.add(number)

        if(simonSoundPoolLoaded) {
            litCells()
        }
    }

    private fun litCells() {
        simonInstanceSaved = false
        simonCurrentCellIndex = 0

        if (simonTimer != null) {
            simonTimer!!.cancel()
        }

        simonTimer = Timer()
        simonTimerTask = object : TimerTask() {
            override fun run() {
                TODO("Not yet implemented")
            }

        }
    }


    private fun gameSounds() {

    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val LEVEL_UP = 1
        private const val GAME_OVER = 2
        private const val CELL = "SimonCell"
        private const val SCORE = "Score"
        private const val LEVEL = "Level"
        private const val TIMER = "GameDisplayTimerCompleted"
    }
}