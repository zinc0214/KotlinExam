package ayhan.com.mykotlineexam

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by HanAYeon on 2018. 7. 13..
 */


// Chap 10 : 추상클래스와 인터페이스

class Chap10_AbstractAndInterface : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    // 추상클래스와 추상함수는 프로퍼티를 재정의 해줘야하며 ,open 키워드를 명시하지 않아도 된다.

    abstract class Super {
        val data1 : Int = 10
        abstract val data2 : Int


        fun myFun1() {

        }

        abstract fun myFun2()
    }


    class Sub : Super() {

        override val data2 : Int = 10
        override fun myFun2() {
        }
    }


    /*
    * 추상클래스 : is a / 무언가 객체를 정의할 목적.
    * 인터페이스 : has a / 객체를 정의할 목적이 아니라 어떤 객체가 되든 그런 행동을 가진다는 것을 명시*/


    interface MyInterface {
        val data1 : String
        fun myFun1() {

        }

        fun myFun2()
    }


    class MyClass : MyInterface {

        override val data1: String = "hi"
        override fun myFun2() {
            // ...
        }
    }


    // 인터페이스는 다른 인터페이스를 상속받을 수 있다.

    interface MyInte1 {
        fun myFun1()
    }

    interface MyInte2 {
        fun myFun2 ()
    }

    interface MyInte3: MyInte1, MyInte2 {
        fun myFun3()
    }

    class MyClass2 : MyInte3 {
        override fun myFun1() {
            //...
        }

        override fun myFun2() {
            //...
        }

        override fun myFun3() {
           // ...
        }

    }


    /*
    * 인터페이스에 프로퍼티를 정의할 때 규칙
    *
    *   - 추상형으로 선언하거나 get(), set() 함수를 정의해야 한다.
    *   - 추상 프로퍼티가 아니면 val 은 get() 함수를 꼭 선언해야 한다.
    *   - 추상 프로퍼티가 아니면 var 은 get, set 함수를 꼭 선언해야 한다.*/

    interface MyInterface3 {

        var prop1: Int

      //  val prop2 : String = "dkkd" // 에러

      //  val prop3 : String // 에러
      //  get() = field


        val prop3 : String
        get() = "hello"

        var prop4 : String
        get() = "hi"
        set(value) {

        }
    }

}