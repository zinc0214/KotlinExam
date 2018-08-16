package ayhan.com.mykotlineexam

import android.support.v7.app.AppCompatActivity

/**
 * Created by HanAYeon on 2018. 7. 26..
 */


/*
* 함수형 언어
*   - 일급 객체로서의 함수 : 함수가 최상위 구성요소임. 클래스를 선언하지 않고도 함수를 정의할 수 있어여 하며, 함수 내에 다른함수나 클래스 등의 다양한 구성요소를 포함할 수 있어야 한다.
*     또한 함수를 변수처럼 사용할 수 있어야 한다.
*   - 순수함수 : 부수효과가 발생하지 않는 함수 = 같은 인수를 전달해서 호출하면 항상 같은 결과값을 반환함.
*   순수함수가 되려면 함수 내에서 함수 밖의 데이터를 변경하는 작업이 발생하지 않아야 하고, 별도의 압출력이 발생하지 않아야 한다.
*
*
*
*  함수형 프로그래밍에서의 데이터
*       - 데이터는 변경되지 않으며 프로그램의 상태만 표현.
*       - 함수에서 데이터는 변경하지 않고 새로운 데이터를 만들어 반환.
*       -> 즉, 변경할 수 없는 상수 데이터만을 이용하며 함수의 흐름에 따라 프로그래밍해야한다. */


class Chap12_FuntionalProgramming : AppCompatActivity() {

    // 함수에 변수를 대입하는 방법
    val funVal1 = { x1 : Int ->     // 람다함수를 대입하는 방식.
        println("hello $x1")
        x1 * 10
    }

    fun callFun() {
        funVal1(10)

        val funVal2 = :: funVal1    // 과 같이 함수참조를 통해 변수에 함수를 대입할 수 있다.
    }


    /*
    * 람다함수
    *       - 항상 { } 으로 감싸서 표현해야 한다
    *       - {} 안에 '->' 표시가 있으며 그 왼쪽은 매개변수, 오른쪽은 함수 내용이다.
    *       매개변수 타입을 선언해야 하며 추론할 수 있으면 생략할 수 있다.
    *       함수의 반환값은 함수 내용의 마지막 표현식이다*/


  //  typealias MyType = (Int) -> Boolean // 반복되서 사용하는 함수 타입을 지정할 수 있다.....
  //  val myFun2: MyType= {it > 10}


    // 매개변수 타입을 추론할 수 있을 땐 생략가능하다.
    val lamdafun: (Int) -> Int = {x -> x + 10}
    // 원래는
    val lamdafun2: (Int) -> Int = {x :Int -> x + 10} //이런식..?


    // 매개변수가 하나일 떄에는 it 로 매개변수를 지칭할 수 있다.
    val lamdafun3 : (Int) -> Int = { x -> x + 10 }
    val lamdafun4 : (Int) -> Int = { it + 10}    // it는 함수 타입이 정의되어 있는 경우에만 사용할 수 있다.

    // 멤버 참조 이용 ' :: ' 을 이용해서 참조한다. = 리플렉션 기법 이라고 한다.

    fun memberReference1() {

        class User(val name: String, val age: Int)
        val lamda1 : (User) -> Int = { user: User -> user.age}
        println("lambda return : ${lamda1(User("H", 10))}")             // lambda return :10

        val lamda2: (User) -> Int = {it.age}
        println("lambda return : ${lamda2(User("J", 20))}")             // lambda return : 20

        val lamda3: (User) -> Int = User::age
        println("lambda return : ${lamda3(User("K", 30))}")             // lambda return : 30
    }
}