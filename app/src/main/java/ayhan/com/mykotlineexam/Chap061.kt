package ayhan.com.mykotlineexam

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

/**
 * Created by HanAYeon on 2018. 6. 27..
 */

// 흐름 제어 구문과 연산자 - 6.1 조건문


class Chap061 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // if문
    fun ifFun() {
        val a = 5
        if ( a < 10) Log.d("if :", "$a < 10")

        if ( a > 0 && a <= 10) {
            Log.d("if2 :", "0 < $a < 10")
        } else if ( a > 10 && a <= 20) {
            Log.d("if3 :", "10 < $a <20")
        }

        val result = if ( a > 10) "hi" else "nope"  // if를 표현식으로 사용하는 경우. 항상 else 문도 있어야 한다. (else if 를 사용할 때도 마찬가지)
        Log.d("if4", result)

        val result2 = if( a < 10) {
            Log.d("if01", "hi")
            10 + 20
        } else {
            Log.d("if02", "hello")
            10 + 30
        }

        Log.d("if5", result2.toString())
    }


    // * 표현식 : 특정 문장을 수행하여 결과값을 만들 때, 그 문장을 표현식이라고 한대...

    // when : java 의 switch-case 문과 비슷하지만 기능이 더 많다.
    fun whenFun() {

        //정수로 분기
        val a2 = 1
        when (a2) {
            1 -> Log.d("when01", "a2 == 1")
            2 -> Log.d("when01", "a2 == 2")
            else -> Log.d("when01", "a2 is neither 1 nor 2")
        }

        val data2 = 10
        when (data2) {
            10, 20 -> Log.d("when02", "data is 10 or 20")
            30, 40 -> Log.d("when02", "data is 30 or 40")
            30 + 30 -> Log.d("when02", "data is 60")
            in 1..10 -> Log.d("when02", "1 < data <= 10")

        }
    }

    // when - 여러타입의 분기
    fun testWhen(data : Any) {
        when(data) {
            1 ->  Log.d("when03", "data == 1")
            "Hi" -> Log.d("when03", "data == Hi")
        }
    }

    // when 표현식 // 얘도 else 문이 필요하다
    fun whenExpression() {
        val data6 = 3
        val result3 = when(data6) {
            1 -> "1...."
            2 -> "2...."
            else -> {
                Log.d("when04", "else...")
                "Hello!"
            }
        }

        Log.d("when04", result3)
    }
}