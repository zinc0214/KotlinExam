package ayhan.com.mykotlineexam

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import java.util.*

/**
 * Created by HanAYeon on 2018. 6. 28..
 */


// 흐름 제어 구문과 연산자 - 6.2 반복문

class Chap062 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // for문
    fun forFun() {
        var sum: Int = 0
        for (i in 1..10) {
            sum += i
        }

        Log.d("for01", "$sum")


        //for (i in 1 until 11 step 2) // 1부터 11 미만까지 2씩 증가.

    }

    // while문
    fun whileFun() {
        var x = 0
        var sum1 = 0
        while (x < 10) {
            sum1 += ++x
        }

        Log.d("while01", "$sum1")
    }


    // break, continue, 라벨
    fun breakFun() {
        for (i in 1..3) {
            for (j in 1..3) {
                if (j > 1) break
                Log.d("break01", "i : $i, j : $j")
            }
        }
    }

    fun labelFun() {
        aaa@ for (i in 1..3) {
            for (j in 1..3) {
                if (j > 1) break@aaa
                Log.d("label01", "i : $i, j : $j")
            }
        }
    }


}