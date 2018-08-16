package ayhan.com.mykotlineexam

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import java.util.*
import java.util.Arrays.asList
import java.util.Collections.*

/**
 * Created by HanAYeon on 2018. 6. 29..
 */


// 흐름 제어 구문과 연산자 - 6.3 연산자

class Chap063_Operator : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    // 전개연산자
    fun spreadFun() {
        val array1 = arrayOf(10, 20, 30)

        val list1: ArrayList<Any> = ArrayList();
        list1.add(1)

        val list2 = asList(1, 2, array1[0], array1[1], array1[2], 100, 200)
        list2.forEach { Log.d("spread :", "$it") }

        // 위의 코드를 전개연산자로 쓰는 방법
        var list3 = asList(1, 2, *array1, 100, 200)
        list3.forEach { Log.d("spread :", "$it") }
    }


    fun callSomeFun() {
        val array3 = arrayOf<String>("J")
        someFun(*array3)
    }

    //전개 연산자를 사용자 정의 함수에 사용
    fun someFun(vararg a: String) {
        val iterator = a.iterator()
        while (iterator.hasNext()) {
            Log.d("someFun :", iterator.next())
        }
    }


    // 일치연산 A === B (A와 B가 같은 객체이면 true) ..


    // 연산자 재정의 : 연산자에 대응하는 함수를 재정의하는 것 operator 라는 예약어를 추가해야 한다.
    data class MyClass(val no: Int) {
        operator fun plus(arg: Int): Int {
            return no - arg
        }
    }

    operator fun MyClass.minus(arg: Int): Int {
        return no + arg
    }

    class Test(val no: Int) {
        operator fun plus(arg: Int): Int {
            return no - arg
        }
    }


    fun callMain() {
        val obj: MyClass = MyClass(10)

        val result1 = obj + 5   // 결과 = 5
        val result2 = obj - 5   // 결과 = 15
    }


}