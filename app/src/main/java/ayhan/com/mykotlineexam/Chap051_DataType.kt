package ayhan.com.mykotlineexam

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

@SuppressLint("Registered")
/**
 * Created by HanAYeon on 2018. 6. 15..
 */


// 데이터타입 - 기초데이터타입
class Chap051_DataType : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("getLength1", getLength("Hello").toString())
        Log.d("getLength2", getLength(10).toString())
        Log.d("casesTest1", cases(1))
        Log.d("casesTest2", cases("Hello"))
        Log.d("casesTest3", cases(100))

    }


    // Any : 어떠한 타입의 데이터도 대입할 수 있다는 것. 특정 변수에 대입되는 타입을 예측할 수 없을 때 사용할 수 있다.
    fun getLength(obj: Any): Int {
        if(obj is String) {
            return obj.length
        }

        return 0
    }

    // when 에서 사용하는 방법
    fun cases(obj: Any): String {
        when(obj){
            1 -> return "one"
            "Hello" -> return "Greeting"
            is Long -> return "long"
            !is String -> return "Not a String."
            else -> return "unknown"
        }
    }

    // nullable 을 위해서는 항상 ? 를 포함시켜줘야 한다.
    val a : Int? = null

    fun pasreInt(str:String): Int? {
        return str.length
    }

    // any타입에 null을 허용시킬 때도 ? 를 포함시켜준다.
    val myVal1: Any = 10
    val myVal2: Any? = myVal1 // 이렇게는 가능함

    val myVal3: Any? = 10
    //val myVal4: Any = myVal3 // 이렇게는 못함
    // 그러니까 any? > any 인 거..

    //데이터와 관계 없이 특수상황을 표현하는 Unit, Nothing 타입
    // 1. Unit : 함수의 반환 구문이 없다는 것을 표현하기 위해 사용  = java void
    fun myFin1 () { }
    fun myFin2(): Unit {} // myFin1 == myFin2

    //2. Nothing : 의미있는 데이터가 없음을 명시적으로 선언하기 위해 사용되는 타입
    fun myNotingFun(arg: Nothing?): Nothing {
        throw Exception()   // 의미있는 데이터가 없음
    }


    // 특정타입의 데이터인지 판단히기 위해서는 is 연산자를 사용한다
    // * is 연산자를 이용해 타입을 확인할 때 특별한 타입으로 확인되면 명시적으로 타입 캐스팅을 하지 않아도 자동으로 캐스팅된다. = 스마트 캐스트
    fun getStringLength(obj: Any): Int? {
        if(obj is String) {     // obj 가 문자열 타입이면 String 으로 자동형변환을 해준다.
            return obj.length
        }

        return null
    }

    // 기초타입의 캐스팅은 지원해 주지 않기 때문에 다음과 같이 사용해야한다.
    var a1: Int = 10
    var a2: Double = a1.toDouble() // toByte, toShort....

















}