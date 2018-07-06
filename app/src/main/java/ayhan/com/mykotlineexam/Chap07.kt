package ayhan.com.mykotlineexam

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log


/**
 * Created by HanAYeon on 2018. 6. 29..
 */

// 07. 클래스

class Chap07 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    /*
    * 주 생성자 :
    *   하나의 클래스에 하나만 정의할 수 있다.
    *   클래스 선언 부분에 작성
    *   꼭 작성해야 하는 건 아니지만 보조 생성자가 있다면 작성하지 않아도 된다.
    *   매개변수가 있을 수 있으며, 객체를 생성할 때는 매개변수에 맞는 인수를 전달해야 한다.
    *   */


    class User1 constructor(name: String, age: Int) { }

    // 생성자 매개변수 기본값 명시
    class User2(name: String = "hunsoo", age: Int = 0) { }


    class User4(name: String, age: Int) {

        init {      // 주생성자는 실행 영역을 { } 으로 할 수 없다. 그래서 init() 예약어를 사용해야 한다...
            Log.d("User1", "It's init.")
        }
    }

    val user1 = User4("Jaeho", 40)


    // 생성자 매개변수를 프로퍼티에 대응하기 위한 방법1
    class User5(name: String, age: Int) {
        val name = name

        init {
            Log.d("User5","constructor argument : name = $name, age = $age")
        }

        fun sayHello() {
            Log.d("User5-1", "hello $name")
        }
    }


    // 생성자 매개변수를 프로퍼티에 대응하기 위한 방법2
    class User6(val name: String, val age: Int) {
        val Myname = name
        init {
            Log.d("User6","constructor argument : name = $name, age = $age")
        }
        fun sayHello() {
            Log.d("User6-1", "hello $name")
        }
    }




    /*
    * 보조생성자 :
    *   주 생성자는 클래스 헤더에 선언하고, 보조 생성자는 클래스 몸체에 constructor 예약어로 선언한다.
    *   클래스를 선언할 때는 최소한 하나 이상의 생성자를 정의해야 한다.*/

    class User10{ } // 컴파일러가 매개변수 없는 주 생성자를 자동추가
    class User11(name: String) { } // 주 생성자만 선언
    class User12 {                  // 보조 생성자만 선언
        constructor(name: String) { }
    }


    // 생성자 오버로딩
    class User13 {
        constructor(){}
        constructor(name: String){}
        constructor(name: String, age: Int){}
    }


    // 보조생성자의 매개변수에는 val, var 이 들어갈 수 없다!

    // 주셍성자와 보조생성자를 함께 선언하는 경우
    class User14(name: String) {
        init {
            println("init Hello, $name")
        }

        // 보조생성자에서 주생성사를 호출할 때는 this 를 사용한다.
        constructor(name: String, age: Int): this(name){
            println(" constructor Hello, $name ... $age")
        }
    }

    // 보조생성자가 여러개인 경우
    class User15(name: String){
        constructor(name: String, age: Int): this(name) {

        }
        constructor(name: String, age: Int, email: String):this(name, age) {

        }

        // 이렇게고 된다..
    }

    class CallClass() {
        val user1 = User1("Hello",  10)
        val user5 = User5("HyunSoo", 32)

        val user131 = User13()
        val user132 = User13("Byungab")
        val user133 = User13("Byungab", 32)

        val user141 = User14("Joowon")
        val user142 = User14("Joowon", 35)

        /*
        * user141 결과 = "init Hello, Joowon"
        * user142 결괴 = "init Hello, Joowon" \n "constructor Hello, Joowon ... 35"*/
    }

}