package ayhan.com.mykotlineexam

import android.support.v7.app.AppCompatActivity

/**
 * Created by HanAYeon on 2018. 7. 27..
 */


class Chap13_HighOrderAndInlineFuntion : AppCompatActivity() {

    /*
    *   고차함수 (High-Order Function) : 매개변수로 함수를 전달받거나 함수를 반환하는 함수.
    *   고차함수는 매개변수와 반환에 함수를 이용할 수 있다.
   */

    fun hignFun(x1: Int, argFun: (Int) -> Int) {

        val result = argFun(10)
        println("x1 : $x1, argFun : $result")
    }

    fun highFun2(x: Int, argFun1: (Int) -> Int, argFun2: (Int) -> Boolean) {

    }

    // 고차함수 함수반환.
    fun hignFun3(str: String): (x1: Int, x2: Int) -> Int {

        when (str) {
            "-" -> return { x1, x2 -> x1 - x2 }
            "*" -> return { x1, x2 -> x1 * x2 }
            else -> return { x1, x2 -> x1 + x2 }
        }
    }

    fun hignFun4(argFun: (x: Int) -> Int) {
        println("${argFun(10)}")
    }

    fun nameFun(x: Int): Int {
        return x * 5
    }

    fun callFun() {

        hignFun(20, { x -> x * x })
        hignFun(20) { x -> x * x }        // 이런식으로 괄호 ( ) 를 생략할 수도 있다.

        highFun2(1, { it * it }, { it > 10 })
        highFun2(1, { it * it }) { it > 10 }    // 괄호의 생략은 가장 마지막 매개변수만 가능.

        val result = hignFun3("*")
        println("result * : ${result(1, 2)}")

        hignFun4(::nameFun) // 이런식으로 참조가능.
    }

    // 람다함수 에서는 return 문을 사용할 수 없다.


    /////////////////////////////////////////


    /*
    *  run() 함수
    *   - 람다함수를 실행하고 그 결과값을 얻는 목적
    *   - 객체의 멤버에 접근하기 위해 사용*/

    val result = run {
        println("Lamda!!")
        10 + 20
    }


    fun callFun2() {
        println(result)
    }

    class User() {
        var name: String? = null
        var age: Int? = null

        fun sayHello() {
            println("hello, $name")
        }

        fun sayInfo() {
            println("i am $name, $age years old")
        }
    }

    /*
    * apply() 함수 : run() 함수와 사용 목적은 같으나 반환하는 값에 차이가 있다.*/

    val user = User()
    val user2 = user.apply {
        name = "ay"
        sayHello()      // hello, ay
        sayInfo()
    }

    fun callFun3() {
        println("user name : ${user.name}, user2 name : ${user2.name}")     // user name : ay, user2 name : ay
        user.name = "hehe"
        user2.name = "keke"
        println("user name : ${user.name}, user2 name : ${user2.name}") // user name : keke, user2 name : keke
        // 객체는 동일하기 때문.
    }


    /*
    * let()함수 : 자신이 호출한 객체를 매개변수로 전달받은 람다 함수에 매개변수로 전달. */
    class User2() {
        var name: String? = null
        var age: Int? = null

        constructor(name: String, age: Int) : this() {
            this.name = name
            this.age = age
        }
    }

    fun letFunTedt(user: User2) {
        print("letTest() : ${user.name} ,,, ${user.age}")
    }

    fun callFun4() {
        val user2 = User2("K", 30)
        letFunTedt(user2)

        // 위 코드를

        User2("K", 30).let { user2 ->
            letFunTedt(user2)  // 이러케쓸수있다.
        }

        User2("J", 1).let {
            letFunTedt(it)
        }
    }


    /*
    * with() 함수 : run 과 유사. run은 자신을 호출한 객체를 이용하지만, with 는 매개변수로 지정한 객체를 이용한다...*/

    fun callFun5() {
        val user3 = User()

        user3.run {
            name = "HE"
            sayHello()
        }

        with(user3) {
            name = " H"
            sayHello()
        }
    }


    /////////////////////////////////////////////////////


    /*
    * 인라인 함수 : 고차함수로 선언하고, 매개변수로 함수 타입을 선언하는 경우
    * 자바로 변형했을 시 코드가 복잡해진다.
    * 이렇게 고차함수 호출이 빈번하여 성능에 영향이 미칠 때 인라인 함수를 사용해주는 것이 대안이 될 수 있다.
    * 인라인 함수를 선언하면 해당 함수는 컴파일 단계에서 정적으로 포함되므로 함수 호출이 발생하지 않는다.*/


    inline fun funTest(argFun: (x1 : Int, x2 : Int) -> Int) {
        argFun(10, 20)
    }

    fun callFun6() {
        funTest{x1, x2 -> x1 + x2}
    }

    /*
    * 인라인으로 함수를 선언하면 호출될 떄 정적으로 포함되므로 런타임 떄 호출이 그만큼 줄 수 있따.
    * *** inline 함수는 람다 함수를 매개 변수로 사용할 때에만 효과적이다.*/



    ///////////////////////////////////////////////////

    /*
    * 노인라인 함수 : 고차함수의 함수 타임 매개변수가 여러개 일 때 noinline 을 통해 inline에
    * 적용되는 람다함수와 아닌 람다함수를 구분할 수 있다. */


    // 아래의 함수는 inline 으로 선언되었으므로 함수의 모든 내용이 정적으로 포함된다.
    inline fun inlineTest1(argFun1: (x : Int) -> Int, argFun2: (x : Int) -> Int) {
        argFun1(10)
        argFun2(10)
    }

    // 어떠한 매개변수는 Inline 으로 하고 싶지 않으면 다음과 같이 noinline 을 쓴다.
    inline fun inlineTest2(argFun1: (x: Int) -> Int, noinline argFun2: (x: Int) -> Int) {
        argFun1(10)
        argFun2(20)
    }

    fun callFun7() {
        inlineTest1({it * 10}, {it * 20})
        inlineTest2({it * 10}, {it * 20})
    }



    /////////////////////////////////////////////////////////

    /*
    * 논로컬 반환 : 람다 함수 내에서 람다 함수를 포함하는 함수를 벗어나게 하는 기법.
    * 이름이 정의된 일반함수와 익명함수에서만 사용가능 하며, 람다 함수에서는 사용할 수 없다.*/

    fun nonLocalTest1(argFun: (x : Int, y : Int) -> Int) : Int{
        return argFun(0, 0)
    }


    // inline 함수로 선언하면 return 을 사용할 수 있다.
    inline fun nonLocalTest2(argFun: (x1: Int, x2: Int) -> Int) : Int {
        return argFun(0, 0)
    }


    fun callFun8() {
        println("callFun top...")
        val result = nonLocalTest1{ x, y ->
          //  if(y <= 0)  return // 이런식으로 사용할 수 없다는 것....
            x/ y
        }

        val result2 = nonLocalTest2{ x, y ->
            if(y <=0) return    // inline 함수로 선언해주면 return 가능!
            x/ y
        }
    }

    /*
    * 크로스 인라인 : 다른 객체에 대입해 이용하는 매개변수를 선언할 떄 crossline 예약어를 사용한다.
    * - crossline 는 함수를 inline으로 선언했더라도 람다 함수에 return 을 사용하지 못하게 하는 예약어이다.
    * - inline 고차함수라고 하더라도 , 다른 객체에 대입하면 return 사용에 제약이 있다.*/

    open class TestClass {
        open fun some() {}
    }

    inline fun inlineTest(argFun: () -> Unit) {
        val obj = object : TestClass() {

            /* 에러. return을 명시할 수 있는 람다함수를 inline 함수 내에서만 사용하지 않고 다른 객체에 대입하면
            * 그 객체의 코드가 inline 되지 않아서 발생하는 에러*/
            //override fun some() = argFun
        }
    }

    inline fun inlineTest2(crossinline argFun: () -> Unit) {
        val obj = object : TestClass() {

           // override fun some() = argFun
        }
    }


    /*
    * 라벨로 변환 : 람다함수 앞에 라벨을 지정하고 return에 해당 라벨을 명시해 함수를 종료 할 수 있다.
    * 리턴 시 아래의 코드가 수행이 안되는데, 만약에 아래의 내용이 리턴 될때도 실행되어야할 떄 필요.
    * */

    val array = arrayOf(1, -1, 2)
    fun arrayLoop() {
        println("Start")
        array.forEach aaa@{
            if(it < 0) return@aaa       // @가 쓰인 부분만 벗어난다.
            println(it)
        }

        println("End")
    }


    /*
    * 클로저 : 프로그래밍 언어에서 흔히 함수의 스코프에 사용된 변수를 바인딩하기 위한 기법으로 사용됨.
    * 함수가 호출될 때 발생하는 데이터를 함수 호출 후에도 그대로 유지해 이용하는 기법.
    */
}