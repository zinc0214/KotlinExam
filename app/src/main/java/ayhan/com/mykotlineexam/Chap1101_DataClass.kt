package ayhan.com.mykotlineexam

import android.support.v7.app.AppCompatActivity

/**
 * Created by HanAYeon on 2018. 7. 13..
 */

// Chap1101_DataClass : 다양한 코틀린 클래스 - 데이터클래스

class Chap1101_DataClass : AppCompatActivity() {


    /*
    * 데이터 클래스 : 내부에 특별한 로직의 함수 없이 데이터만 포함한 클래스를
    * VO (value-object) 클래스 라고 부르는데, 이러한 클래스를 조금 더 편하게 이용할 수 있도록 만든 클래스
    *
    * 데이터클래스는 다음의 조건에 맞게 선언해야한다.
    *   - 주 생성자를 선언해야하며 주 생성자의 매개변수는 최소 하나 이상이어야 한다
    *   - 모든 주 생성자의 매개변수는 val 또는 var 로 선언해야 한다
    *   - abstract, open, select, inner 등의 예약어를 추가할 수 없다
    *   */

    class Product(val name: String, val price: Int)

    data class User(val name: String, val age: Int)

    fun test(){

        var product = Product("Item", 100)
        var user1 = User("Han", 24)
        var user2 = User("Han", 24)

        print(user1.equals(user2)) // 클래스 내의 객체의 데이터가 같은지 비교

        print(product.toString()) // 주소가 출력됨
        print(user1.toString()) // User(name = han, age = 24) 와 같이 출력됨

        print(user1.component1()) // Han 이 출력됨
        print(user1.component2()) // 24 가 출력됨

        val (name, age) = user1
        println("name : $name, age : $age")


        var user3 = User(age = 10, name= "Yeon")
        println(user3.toString())  // User(name = Yeon, age = 10) 이 출력됨
        var user4 = user3.copy(age = 20)
        println(user4.toString()) //User(name = Yeon, age = 20) 이

     }
}