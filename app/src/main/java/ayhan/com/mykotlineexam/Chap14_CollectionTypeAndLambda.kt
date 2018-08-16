package ayhan.com.mykotlineexam

import android.support.v7.app.AppCompatActivity

/**
 * Created by HanAYeon on 2018. 7. 29..
 */

  class Chap14_CollectionTypeAndLambda : AppCompatActivity() {

    /*
    * forEach() : 단순이 컬렉션 티입의 데이터 개수만큼 특정 구문을 반복할 때 유용.
    * forEachIndexed() : forEach 함수와 동일하나 람다 함수에 인덱스 값까지 전달해준다.
    * all() : 컬렉션 타입의 데이터가 특정 조건에 모두 만족하는지 판단할 때 사용 .
    * any() : 특정 조건에 만족하는 데이터가 있는 지 판단할 떄 사용
    * count() :람다식으로 대입한 조건에 만족하는 데이터 개수를 반환
    * find() : 조건에 만족하는 가장 첫 번째 데이터를 반환
    * reduce() , fold() : 함수에 람다함수를 대입하면 컬렉션 타입의 제이터를 람다 함수에 차례로 전달하며 결과값을 반환해 준다.
    *   reduce 는 함수 초기값 지정 불가 / fold는 지정가능
    *    foldRight, reduceRight 는 순서가 반대.
    * maxBy() : max 함수와 목적은 같지만 람다함수를 매개변수로 지정하여 로직에 의한 계산 결과 중 가장 큰 수 반환
    * none() : 람다 함수에서 지정한 조건에 맞는 데이터가 없으면 true, 있으면 false 반환
    * sumBy() : 람다함수를 거쳐 반환한 모든 값을 더하는 함수
    * */


    fun testFun() {

        class User(val name: String, val age: Int) {
            var list = listOf(User("He", 40), User("JJ", 20))

        }

        val user = User("H", 1)

        print("all : ${user.list.all { it.age > 30 }}")     // false
        print("any : ${user.list.all { it.age > 30 }}")    // true
        print("count : ${user.list.count { it.age > 30 }}") // 1

        val find = user.list.find { it.age > 30 }
        print("find : ${find?.name}, ${find?.age}") // He, 40

        var result = listOf(1, 2).fold(10, { total, next ->
            print("$total ... $next")
            total + next
        })

        print("fold : $result") // 10 ... 1 \n 11 ... 2\n fold : 13

        var result2 = listOf(1, 11, 4).maxBy { it % 10 }!!
        print("max by : $result2") // 4


    }

    /*
    * 필터링 함수
    * filter() : 컬렉션 타입의 데이터 중 특정 조건에 맞는 데이터만 추출할 떄 사용
    * filterNot() : 조건에 맞지 않는 데이터만 추출
    * filterNotNull() : null 이 아닌 데이터만 추출
    * drop() : 매개변수로 숫자를 대입하며 앞부분부터 대입한 숫자 개수만큼을 제외하고 나머지를 추출
    * dropWhile() : 매개변수로 람다 함수를 지정하며 조건에 만족하지 않는 데이터가 나올 때 부터 나머지를 추출
    * slice, take, takeLast, takeWhile() : 모두 특정 위치에 있는 데이터를 추출
    *   slice() : 범위나 숫자 값을 여러개 대입하고 그 위치에 있는 데이터만을 추출
    *   take() : 전체 데이터의 앞에서부터 숫자에 해당하는 개수만큼의 데이터 추출
    *   takeLast() : 마지막 데이터부터 계산
    *   takeWhile() : 앞에서부터 조건이 맞는지 확인, 조건이 맞지 않는 데이터가 나오기 전까지를 추출
    * */

    fun filterTest() {

        val list1 = listOf<Int>(12, 8, 9, 20)

        val resultList = ArrayList<Int>()
        for (i in list1) {
            if (i > 10) resultList.add(i)
        }
        for (i in resultList) {
            println(i)  // 12, 20
        }

        //위의 코드를 filter 를 써서 아래처럼 바꿀 수 있다.
        val resultList2 = list1.filter { it > 10 }
        for (i in resultList) {
            print(i)
        }

        //map 에서 사용하는 경우
        val map1 = mapOf<String, Int>("one" to 15, "two" to 5)
        val resultMap1 = map1.filter { entry -> entry.value > 10 }
        for (i in resultMap1) {
            print(i) // one=15
        }

        //drop
        listOf<Int>(1, 2, 3, 4).drop(2).forEach { print(it) }  // 3 4

        //dropWhile
        listOf<Int>(2, 1, 12, 5, 23).dropWhile { it < 10 }.forEach { print(it) }  // 12  5  23

        //dropLastWhile
        listOf<Int>(1, 12, 5, 23, 5, 4).dropLastWhile { it < 10 }.forEach { print(it) }  // 1 12 5 23

        //slice
        listOf(1, 12, 5, 23, 5, 4).slice(1..3).forEach { print(it) } // 12, 5, 23

        //take
        listOf(1, 12, 5, 23, 5, 4).take(3).forEach { print(it) } // 1, 12, 5

        //takeLast
        listOf(1, 12, 5, 23, 5, 4).takeLast(3).forEach { print(it) } //23, 5, 4

        //takeWhile
        listOf(11, 12, 5, 23, 5, 4).takeWhile { it > 10 }.forEach { print(it) } // 11, 12
    }


    /*
    * 매핑함수
    * map() : forEach() 함수와 비슷. 집합 객체의 데이터 만큼 반복실행한다는 것은 동일하나 map은 반복실행 후 결과를 다시 반환한다.
    * groupBy() : 컬레션 타입의 데이터를 특정 데이터로 묶을 때 사용*/

    fun mapTest() {

        // map
        val list1 = listOf<Int>(12, 8, 9, 20)
        list1.filter { it > 10 }
                .map { it * 10 }
                .forEach { print(it) } // 120, 200

        // mapIndexed
        listOf(1, 2, 3).mapIndexed { index, data -> index * data }
                .forEach { println(it) } // 0, 2, 6

        // groupBy
        class User(val name: String, val age: Int)

        val list = listOf<User>(User("Kang", 10), User("Han", 24), User("Kim", 24))
        list.groupBy { it.age }
                .forEach {
                    println("Key : ${it.key} ... count : ${it.value.count()}")
                    it.value.forEach { println("${it.name} ... ${it.age}") }
                }

        // -> 결과 /*
        // key : 10  ... count : 1*/
        // Kang ... 10
        // key : 24 ... count : 2
        // Han ... 24
        // Kim ... 24
    }


    /*
    * 요소함수
    * contains() : 컬렉션 타입의 데이터 중 특저 데이터가 있는지를 판단하는 함수. 데이터가 있으면 true, 없으면 false 를 반환한다.
    * elementAt() : 특정 위치의 데이터 추출 , 데이터 범위를 넘어난 인덱스 값을 주게 되면 IndexOutOfBoundsException 이 발생한다.
    * elementAtOrElse() : elementAt() 함수처럼 특정 인덱스의 데이터를 추출하나, 데이터 범위를 넘어가도 Exception 이 발생하진 않는다. 대신 지정된 람다 함수가 실행되고 그 람다 함수에서 반환한 값을 보낸다.
    * first() : 람다 함수로 조건을 주고 그 조건에 맞는 가장 첫 번째 데이터를 추출하되 없으면 NoSuchElementException 이 발생한다.
    * firstOrNull() : first() 와 동일하나 조건에 맞는 데이터가 없으면 null 반환한다.
    * last() : first 와 반대. 가장 마지막 데이터를 추출
    * lastOrNull()
    * indexOf() : 매개변수로 받은 데이터가 위치하는 첫 번째 인덱스 값 반환, 데이터가 없으면 -1 반환한다.
    * indexOfFirst() : indexOf() 와 동일하나 매개변수를 람다함수로 전달받는다. <-> indexOfLast()
    *
    * */

    fun elementTest() {

        // contains()
        val result = listOf(2, 5, 10, 8).contains(7) // false

        // elementAt()
        val result2 = listOf(2, 5, 10, 8).elementAt(2) // 10

        // elementAtOrElse()
        val result3 = listOf(2, 5, 10, 8).elementAtOrElse(5, { 0 })  // 0

        // first()
        val result4 = listOf(3, 5, 10, 8).first { it % 5 == 0 }  // 5

        // indexOf()
        val result5 = listOf(2, 5, 10, 2).indexOf(2) // 0

        // indexOfFirst()
        val result6 = listOf(2, 5, 10, 2).indexOfFirst { it % 2 == 0 } // 0

        // indexOfLast()
        val result7 = listOf(2, 5, 10, 2).indexOfLast { it % 2 == 0 } // 3

    }


    /*
    * 정렬 함수
    * */

    fun sortTest() {

        listOf(1, 5, 2).reversed().forEach { print("$it") } // 2 5 1

        listOf(1, 5, 2).sorted().forEach { print(it) } // 1 2 5

        listOf("banana" , "jaeho", "elo").sorted().forEach { print(it) } // banana elo jaeho

        listOf("banana", "jaeho", "elo").sortedDescending().forEach { print(it) } // jaeho elo banana
    }
}