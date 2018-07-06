package ayhan.com.mykotlineexam

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlin.properties.Delegates

/**
 * Created by HanAYeon on 2018. 7. 6..
 */

// Chap08 : 프로퍼티

class Chap08 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /*
    * 프로퍼티 : 클래스의 변수, val, var 로 선언되는 변수들.
     * 프로퍼티는 변수처럼 보이지만 함수가 내장된 변수이다. 즉 getter , setter 함수가 내장되어 있다는 것.*/

    class User {
        var name: String = "hyunsoo" // name , age = 프로퍼티
        var age: Int = 32
    }


    // 사용자 정의 프로퍼티
    class User2 {
        var greeting: String = "Hello"
            set(value) {
                field = "Hello " + value
            }
            get() = field.toUpperCase()


        var age: Int = 0
            set(value) {
                if (value > 0) {
                    field = value
                } else {
                    field = 0
                }
            }

        /*
        * 생성자 프로퍼티를 만들 때 필요한 규칙들
        *   - get(), set() 함수 내부에서는 프로퍼티 값을 field 로 접근한다.
        *   - var로 선언한 프로퍼티는 set, get 함수를 모두 정의할 수 있으나  val로 선언한 프로퍼티는 set 함수를 정의할 수 없다.
        *   - val 로 선언한 프로퍼티는 get 함수를 정의하면 초기값을 명시하지 않아도 된다.
        *   - var 로 선언한 프로퍼티는 get 함수를 정의하더라도 초기값을 명시해야 한다.  */
    }


    // 주 생성자와 프로퍼티
    // 지역변수는 프로퍼티가 아니므로  get set 함수를 제공하지 않는다.
    // 프로퍼티로 이용할 수 있는 변수는 어떤 블록 { } 에도 포함되지 않는 최상위 레벨 변수와 클래스 내부에 선언한 멤버 변수다.

    // 최상위 레벨 변수
    var myVal: String = "hello"
        get() = field.toUpperCase()
        set(value) {
            field = "hello" + value
        }

    class User3(val name: String) {
        var age: Int = 0
        set(value) {
            if(value > 0) field = value
            else field = 0
        }

        fun myFun() {
            var no = 0
            //get  -> 지역변수를 프로퍼티가 아니므로 에러..!
        }
    }


    // 프로퍼티 초기화 : * 프로퍼티는 선언과 동시에 초기값을 대입해 초기화해야 한다.
    // 1. 초기화 블록에서 초기화
    class User4 {
        var data: String
        val data2: Int

        init {
            data = "Kkang"
            data2 = 10
        }
    }

    //2. null 허용으로 선언
    class User5 {
        val name1: String = "zinc"
        var name2: String? = null
        val name3: String? = null
        var age: Int? = null

        constructor(name2: String, name3: String, age: Int) {
            this.name2 = name2
            //this.name3 = name3 // var 이 아니므로 에러.
            this.age = age
        }
    }


    // 늦은 초기화 : 프로퍼티를 null 허용으로 선언하지 않고 프로퍼티 초기화를 미루는 방법 = lateinit 을 사용한다.
    /*
    * 늦은 초기화를 사용할 시 주의할 점
    *   - lateinit 는 var 로 선언한 프로퍼티에만 사용할 수 있다.
    *   - latainit 는 클래스 몸채에 선언한 프로퍼티에서만 사용할 수 있다. 주 생성사에서는 사용 불가
    *   - lateinit 는 사용자 정의 getter, setter 를 사용하지 않은 프로퍼티에서만 사용할 수 있다.
    *   - null 허용 프로퍼티에는 사용할 수 없다.
    *   - 기초타입 프로퍼티에는 사용할 수 없다.
    *   */
    class User6 {
        lateinit var lateData: String
    }


    // 초기화 미루기 : 프로퍼티의 초기화를 이용 시점으로 미루는 방법. 실제 프로퍼티를 이용하는 시점에서 초기화를 하는것 = lazy 를 사용한다.
    /*
    * lazy 사용 규칙
    *   - 호출 시점에 초기화 진행
    *   - val로 선언한 프로퍼티에만 사용 가능
    *   - 클래스 몸체 이외에 최상위 레벨에서 사용 가능
    *   - 기초 타입에도 사용 가능*/

    val someData: String by lazy {
        print("i am somedata lazy")
        "hello"
    }

    class User7 {
        val name: String by lazy {
            print("i am name lazy")
            "zinc"
        }

        val age: Int by lazy {
            print("i am age lazy")
            10
        }

        init {
            print("i am init")
        }

        constructor(){
            print("i am constructor")
        }
    }

    // 프로퍼티 값 변경 감지 : 프로퍼티 값이 변경되는 순간, 개발자의 코드를 실행할 수 있다.
    class User8 {
        var name: String by Delegates.observable("nonValue") {
            props, old, new -> println("old: $old ... new: $new")
        }
    }

    fun callClass() {
        val user = User()

        user.name = "jaeho"


        val user2 = User2()
        user2.greeting = "ayhan"
        println(user2.greeting)
        user2.age = -4
        println(user2.age)
        user2.age = 30
        println(user2.age)


        val user4 = User4()
        print(user4.data)   // init 에 초기화 되 값이 있기 때문에 에러가 나지않는다.
        print(user4.data2)


        val user6 = User6()
        user6.lateData = "HiHi"
        println(user6.lateData)

        val user8 = User8()
        println(user8.name)
        user.name = "zinc"
        user.name = "han"
        println(user.name)
    }
}