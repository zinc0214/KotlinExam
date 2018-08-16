package ayhan.com.mykotlineexam

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

/**
 * Created by HanAYeon on 2018. 6. 15..
 */

// 변수와 함수

class Chap04_ValAndFun : AppCompatActivity() {

    val data1: Int = 10
    val data2 = 20  // value
    var data3 = 30 // variable

  //  val nullData1: String = null // no
    val nullData2: String? = null // yes
    var nullData3: String? = null // yes

  //  const val myConst: Int = 10
  //  const var myConst2: Int = 20 // var 변수, 클래스 및 함수 내부에서는 const 함수를 사용할 수 없다. 근데 val인데 왜 안되는건지 모르겠네...

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


   //     data2 = 30 // no
        data3 = 40 // yes

   //     nullData2 = "hi" // no
        nullData3 = "hi" // yes


        val obj = FunClass()
        obj.infixFun(10)
        obj infixFun 10

        Log.d("MyFun", (10 myFun 10).toString())
        Log.d("MyFun2", "a" + 10.myFun(10))


    }


    //매개변수 부분에는 var, val 의 선언을 할 수 없다. 함수의 매개변수는 무조건 val 로 적용된다.
    //함수 안애 함수를 중첩으로 정의할 수 있다.
    fun sum(a: Int, b: Int): Int {
        var sum = 0
        fun calSum() {
            for(i in a..b) {
                sum += i
            }
        }

        calSum()

        return sum
    }

    //반환형이 없는 경우네느 Unit 이 명시된다. 생략가능하며 반환타입이 없다면 Unit 이 기본으로 적용된다. (void..느낌?)
    fun sum2(a: Int, b: Int): Unit {

    }


    //아래의 3개지 함수는 모두 동일한 의미를 가지고 있다.
    fun some1(a: Int, b: Int): Int {
        return a+b
    }

    fun some2(a: Int, b: Int) : Int = a + b

    fun some3(a: Int, b: Int) = a + b


    // 함수 오버로딩. 같은 이름의 함수에 매개변수를 다르게 하여 여러 개를 정의한다.
    fun over(a: String) {
        Log.d("over", "call String$a");
    }
    fun over(a: Int){
        Log.d("over", "call Int$a");
    }
    fun over(a: Int, b: String) {
        Log.d("over", "call 2$a,$b")
    }



    // 매개변수 쓰는 방식
    fun callHello() {
        sayHello(null)
        sayHello("HyunSoo")
        sayHello2()
        sayHello2("HyunSoo")
    }

    fun sayHello(name: String?) {
        if(name==null) {
            Log.d("sayHello", "Hello, A?")
        } else {
            Log.d("sayHello", "Hello, $name")
        }
    }

    fun sayHello2(name: String = "JaeHo") {
        Log.d("sayHello", "Hello, $name");
    }



    // 중위표현식
    // infix 은 다음과 같은 경우에서만 사용이 가능하다 .
    //   - 클래으스이 멤버 함수로 선언하거나 클래스의 확장 함수일 떄
    //  - 매개변수가 하나인 함수일 때
    infix fun Int.myFun(x:Int): Int {
        return x * x
    }

    class FunClass {
        infix fun infixFun(a: Int) {
            Log.d("funClass", "call..");
        }
    }




    // 가변인수
    fun callVarags() {
        varargsFun(1, "Hello", "World", "BB")
        varargsFun(6, 20, false, "he")
    }
    fun <T> varargsFun(a1: Int, vararg array: T) {
        for(a in array) {
            Log.d("varagsFun", "$a")
        }
    }


    // 재귀함수
    // 위 아래의 코드가 비슷해보이긴 하지만 tailrec 예약어를 사용하면 소스코드가 자바로 변경될 때 재귀함수가 아닌 일반적인 반복문으로 변경된다.\
    // 즉 stackOverFlowError 의 위험에서 벗어날 수 있게된다.
    // 유의해야 하는 점은 tailrec 예약어를 쓰는 경우 재귀함수에서 자신을 다시 호출하는 구문은 함수의 맨 마지막에 작성해 주어야 한다는 점.
    fun recPrint(no: Int = 1, count: Int = 1) {
        Log.d("recPrint", "");
        return if(no==count) return else recPrint(no-1, count)
    }

    tailrec fun tailrecPrint(no: Int = 1, count: Int = 1) {
        Log.d("tailRecPrint", "")
        return if(no==count) return else tailrecPrint(no-1, count)
    }
}
