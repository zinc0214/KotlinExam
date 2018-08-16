package ayhan.com.mykotlineexam

import android.support.v7.app.AppCompatActivity
import java.util.*

/**
 * Created by HanAYeon on 2018. 7. 23..
 */

// Chap1102_OtherClass : 다양한 코틀린 클래스 - Enum 클래스, 익명 클래스


class Chap1102_OtherClass : AppCompatActivity()  {

    /*
* Enum 클래스 : 열거형타입. 상수 여러개를 대입해 선언하고 이 값 중 하나를 지정해서 사용한다.
* 프로퍼티 - name, original
*   name : 열거 상수 문자열  / ordinal : 인덱스 번호
* */

    enum class Direction {
        NORTH, SOUTH, WEST, EAST
    }

    enum class Direction2(var no: Int, val str: String) {
        NORTH(0, "notrh"),
        SOUTH(1, "south"),
        WEST(2, " west"),
        EAST(3, "east")
    }

    fun EnumClassExam() {

        val direction : Direction = Direction.NORTH

        println("${direction.name} .... ${direction.ordinal}")      // NORTH .... 0

        val directions : Array<Direction> = Direction.values()
        directions.forEach { t -> println(t.name) }                 // NORTH \n SOUTH \n WEST \n EAST

        val direction1 = Direction.valueOf("WEST")
        println("${direction1.name} ... ${direction1.ordinal}")     // WEST ... 2


        val direction2 : Direction2 = Direction2.NORTH
        println("no : ${direction2.no}, ${direction2.str}")     // no : 0, north

        direction2.no  = 10
        println("no : ${direction2.no}, ${direction2.str}")     // no : 10, north

    }

///////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
    * 익명 클래스 : */
    enum class Direction3 {

        // NORTH, SOUTH = 각 열거상수


        // 열거형 클래스는 상수를 , 으로 구분하기 때문에 프로퍼티와 함수의 구분이 필요하다. 그래서 ';' 를 사용해서 구분해주는 것.
        NORTH { // 익명클래스 작성
            override val data1 : Int  = 10
            override fun myFun() {
                print("north myFun")
            }
        },

        SOUTH {
            override val data1 : Int  = 20
            override fun myFun() {
                print("south myFun")
            }
        };      // 열거 상수 이외에 다른함수나 프로퍼티를 선언할 때는 세미콜론을 사용한다.

        abstract val data1 : Int
        abstract fun myFun()
    }


    fun AnonymousClassExam() {
        val direction3 : Direction3 = Direction3.NORTH
        println(direction3.data1)   // 10
        direction3.myFun()          // north myFun
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////////


    /*
    * Sealed 클래스 : 몇몇 타입을 묶이 위한 용도. sealed 예약어로 선언.
    * 기본적으로 abstract 를 내장하고있기 때문에 객체 생성이 불가능하다.*/
    sealed class Shape {
        class Circle(val radius: Double) : Shape()
        class Rect(val width: Int, val height: Int) : Shape()       // enum class 와 달리 매개변수를 다르게 주고싶을 때 sealed 클래스를 사용한다.
    }

    //class Triangle(val bottom: Int, val height: Int) : Shape()

    fun SealedClassExam() {
        val shape1 : Shape = Shape.Circle(10.0)
        //val shape2 : Shape = Triangle(10,10)
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////////


    /*
    * Nested Class : 특정 클래스에 선언된 클래스. */

    class Outer {

        var no : Int = 10

        class Nested {
            val name: String = "KK"
            fun myFun() {
                println("hehe,,,")
                // no = 20 //  Nested 클래스는 상위의 멤버변수에 접근할 수 없다.
            }
        }


        inner class Nested2 {   // 상위의 멤버에  접근하고 싶으면 inner 를 써줘야한다.
            val name: String = "KK"
            fun myFun() {
                println("hehe,,,")
                 no = 20
            }
        }
    }

    fun NestedClassExam() {
        val obj : Outer.Nested = Outer.Nested()
        val obj2 : Outer.Nested2 = Outer().Nested2()
        println(obj.name)
        obj.myFun()
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////////


    /*
    * Object 클래스 : 익명 클래스를 정의할 때 사용. class 예약어를 작성하는 대신 object {} 와 같이 선언한다.
    * 생성자를 추가할 수 없다. */

    val obj1 = object { // 최상위에 익명클래스를 작성할 수도 있다.
        val no1 : Int = 10
        fun myFun() {

        }
    }

    class Outer2 {
        val obj2 = object  {        // 익명클래스는 특정클래스 안에 작성할 수도있다.
            var  no2 : Int = 0
            fun myFun() {

            }
        }
    }


    class Outer3 {

        private var no: Int = 0

        // private 를 붙이지 않은 상태의 object 클래스는, 외부에서 object 로 인식하지 못하고 Any 타입으로 인식되어 프로퍼티에 접근할 수 없게 된다.
        val myInner = object  {
            val name : String = "Hel"
            fun innerFun() {
                println("inner fun")
                no++
            }
        }


        // private 를 붙이게 되면 내부에서만 사용하므로 object 타입으로 인식은 되지만, 외부에서는 여전히 사용할 수 없다.
        private val myInner2 = object  {        // 외부에서 object 클래스의 멤버를 사용하기 위해서는 private 키워드를 추가해 주어야 한다.
            val name : String = "Hel"
            fun innerFun() {
                println("inner fun")
                no++
            }
        }

        fun outerFun() {
            //myInner.name // 이런 것 못함.
            myInner2.name
            myInner2.innerFun()
        }

    }


    fun ObjectClassExam() {
        val obj = Outer3()
        //obj.myInner.innerFun()  // 이런거 못함.
        //obj.myInner2.name // 못함. -> myInner2 는 private 이므로 외부에서는 접근할 수 없으니까.
    }


    interface  SomeInterface {
        fun interfaceFun()
    }

    open class SomeClass {
        fun someClassFun() {
            println("SomeClass...")
        }
    }

    class Outer4 {
        // object 타입으로 선언되었기 때문에 외뿌에서 멤버를 이용할 수 있게 된다.
        val myInner : SomeClass = object : SomeClass(), SomeInterface {
            override  fun interfaceFun() {
                println("interfaceFun")
            }
        }
    }

    fun ObjectClassExam2() {
        val obj = Outer4()
        obj.myInner.someClassFun()
    }


    class NormalClass {
        fun myFun() {

        }
    }

    object ObjectClass {
        fun myFun() {

        }
    }

    fun ObjectClassExam3() {

        val obj1 : NormalClass = NormalClass()
        val obj2 : NormalClass = NormalClass()
        obj1.myFun()

        // 에러! ->object 클래스는 클래스 명이 있더라도 클래스 선언과 동시에 객체가 생성되기 때문에, 객체를 또 생성하면 에러가 난다.
     //   val obj3 : ObjectClass = ObjectClass()

        ObjectClass.myFun() // 이렇게 바로 불러줘야함.
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////////



    /*
    * companion 예약어 : Object 와 함께 사용되며 object 클래스의 멤버를 object 클래스명 없이 사용할 수 있게 해준다.
    * 즉 companion object = 자바의 static.
    * */

    class Outer5 {
        companion object NestedClass {

            val no : Int = 0
            fun myFun() {

            }
        }

        fun myFun() {
            no
            myFun()
        }
    }

    fun CompanionExam() {
        Outer5.NestedClass.no
        Outer5.NestedClass.myFun()

        Outer5.no
        Outer5.myFun()
    }
}