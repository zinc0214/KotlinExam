package ayhan.com.mykotlineexam

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by HanAYeon on 2018. 7. 6..
 */


// Chap09_Extends : 상속

class Chap09_Extends : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    /*
    * Any Class : equals(), toString() 이외의 다른 멤버들을 제공하징 않는다.
    * */
    class Shape {
        var x: Int = 0
        var y: Int = 0
        var name: String = "Rect"


        fun draw() {
            println("draw shape : $name, location ($x, $y)")
        }
    }


    // open 을 클래스 선언 시 시작할 때 쓰게되면 상속을 허용한단느 뜻이 되다.
    // 개발자가 open을 명시하지 않으면 기본으로 final 이 적용되기 때문에 상속이 허용되지 않는다.
    open class Shape2 {
        var x:Int = 0
        set(value) {
            if(value < 0) field = 0
            else field = value
        }

        var y:Int = 0
        set(value) {
            if(value < 0) field = 0
            else field = value
        }

        lateinit var name: String

        fun print() {
            println("$name : location : $x, $y")
        }
    }

    class Rect: Shape2() {
        var width: Int = 0
        set(value) {
            if(value < 0) field = 0
            else field = value
        }

        var height: Int = 0
        set(value) {
            if(value < 0) field = 0
            else field = height
        }

    }

    class Circle: Shape2() {
        var r: Int = 0
        set(value) {
            if(value < 0) field = 0
            else field = value
        }
    }

    // 오버라이드
    open class Shape3 {

        // 오버라이드를 하기 위해서는 함수에도 open 을 붙여야 한다.
        open fun print() {
            print("print....shape...")
        }
    }


    class Rect2 : Shape3() {

        override fun print() {
            print("print....rect....")
        }
    }


    // override 예약어 / override 예약어를 추가한 함수는 open 을 추가하지 않아도 다시 하위클래스에서 정의할 수 있다.
    // 반대로 A 를 상속받은 B 가 자신이 오버라이드 한 A의 함수가 오버라이드 되는 것을 막고싶다면 'final override... ' 와 같이 쓰면 된다.

    // 프로퍼티 오버라이드 : 상위클래스에 선언된 프로퍼티는 하위 클래스에서 자기 것처럼 이용할 수 있다.
    /*
    * 프로퍼티를 오버라이드 할 때는 다음과 같은 규칙이 있다.
    *   - 상위클래스의 프로퍼티와 이름 , 타입이 모두 같아야 한다.
    *   - 상위 클래스에 val 로 선언된 프로퍼티를 하위클래스에서 val, var 로 재정의할 수 있다.
    *   - 상위 클래스에 var 로 선언된 프로퍼티는 하위클래스에서 var 로만 재정의 할 수 있다. (val 는 불가)
    *   - 상위에서 null 허용으로 선언된 경우 하위에서 null 불허용으로 재정의 할 수 있다.
    *   - 상위에서 null 불허용으로 선언된 경우 하위에서 null 허용으로 재정의 할 수 없다,
    *   */
    open class Super {
        open val name: String = "ayhan"
    }

    open class Sub : Super() {
        final override var name: String = "zinc"
    }


    // 상위 클래스에서 멤버 접근
    open class Super2 {
        open var x : Int = 10
        open fun someFun() {
            println("Super.... someFun()")
        }
    }

    class Sub2: Super2() {
        override var x : Int = 20
        override fun someFun() {
            super.someFun()     // 상위클래스를 호출 .
            println("Sub .... ${super.x}.... $x")
        }
    }


    // 스마트 캐스팅

    // is 연산자를 사용
    fun smartCast(data: Any): Int {
        if(data is Int) return data * data  // 스마트캐스팅이 되어 int 타입의 데이터인 경우 해당 타입으로 자동적으로 형변환이 된다.
        else return 0
    }

    class MyClass1 {
        fun fun1() {
            println("Fun1()...")
        }
    }
    class MyClass2 {
        fun fun2() {
            println("Fun2()...")
        }
    }
    fun smartCase2(obj: Any) {
        if(obj is MyClass1) obj.fun1()
        if(obj is MyClass2) obj.fun2()
    }


    //as를 이용한 캐스팅 ( 형식 : 객체 as 타입) : 이건 상위타입 -> 하위타입 -> 상위타입 으로의 캐스팅 할 때만 사용가능하다.
    open class Super3 {
        fun superFun() {
            println("superFun()...")
        }
    }

    class Sub13: Super3() {
        fun subFun1() {
            println("subFun1()...")
        }
    }

    class Sub23: Super3() {
        fun subFun2() {
            println("subFun2()...")
        }
    }


    // null 허용객체를 as를 통해서 캐스팅 하기 위해서는
    val obj7: Super3? = Sub23()
    val obj8:Sub23 = obj7 as Sub23

    val obj9: Super3? = null
    val obj10: Sub23 = obj9 as Sub23 // 런타임에러 발생 : null을 캐스팅할 수 없기 때문
    val obj11: Sub23? = obj9 as? Sub23 // null 대입 시 null 이 반환된다.



    /*
    * 접근제한자 :
    *   - public
    *   - private
    *   - internal : 같은 모듈 내에서만 접근 가능
    *           IntelliJ / maven / gradle ...
    *   - protected
    *   */


    /*
    *
    * 접근제한자를 get(), set() 에서 다르게 사용 하고자 하는 경우
    *   - get() 함수의 접근 제한자는  프로퍼티의 접근 제한자와 항상 같다.
    *   - set() 함수의 접근 제한자는 프로퍼티의 접근 제한자와 다르게 설정할 수는 있으나, 접근 범위를 넓혀서 설정할 수는 없다.*/

    fun callClass() {

        val shape1 = Shape()
        val shape2 = Shape()
        val shape3 = shape1

        println("shape1.equals(shape2) is ${shape1.equals(shape2)} ")   // false
        println("shape1.equals(shape3) is ${shape1.equals(shape3)} ")   // true

        val rect = Rect()
        rect.name = "Rect"
        rect.x = 10
        rect.y = 10
        rect.width = 20
        rect.height = 20
        rect.print()

        val circle = Circle()
        circle.x = 30
        circle.y = 30
        circle.r = 5
        circle.print()

        val obj3: Super3 = Sub13()
        val obj4: Sub13 = obj3 as Sub13
        obj4.superFun()
        obj4.subFun1()

    }
}