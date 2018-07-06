package ayhan.com.mykotlineexam

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import java.util.*

/**
 * Created by HanAYeon on 2018. 6. 27..
 */

//데이터타입 - 컬렉션타입

class Chap052 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    // 5.2.1 배열
    fun array() {

        var array = arrayOf(1, "kkang", true)       // 타입을 명시하지 않았기 때문에 다양한 타입의 데이터를 넣을 수 있다.
        array[0] = 10
        array[2]="world"

        Log.d("ArrayLog", "${array[0]} , ${array[1]} , ${array[2]}")


        var array2 = arrayOf<Int>(10, 20) // 타입을 명시한 경우
        var array3 = intArrayOf(10, 20)

        //다음과 같이 Array 클래스를 사용할 수도 있다.
        var array4 = Array(3,{ i -> i * 10})
        Log.d("ArrayLog2", "${array4[0]}, ${array4[1]}, ${array4[2]}")

        //빈 배열을 만드는 방식
        var nullArray = arrayOfNulls<Any>(5)
        nullArray[0] = 1
        nullArray[1] = "he..."

        var nullArray2 = Array<String>(3,{""})
        nullArray2[0] = "HyunSoo"
        nullArray2[1] = "JaeHo"
    }


    // 5.2.2 List , Set, Map
    // 객체가 List type 이면 불변, MutableList 타입이면 가변이다... 각각 listOf() , mutableListOf() 를 통해서 만들 수 있다.
    fun lsm() {

       // var immutableList: List<String> = listOf("Hello", "World") // 근데 외않돼

        val arrayList: ArrayList<String> = ArrayList();
        arrayList.add("Hello")

        val mapList: HashMap<String, String> = HashMap();
        mapList.put("one", "jaeho")
    }



    // 5.2.3 Iterator
    // 컬렉션 타입의 데이터를 hasNext() , next() 함수를 이용해 차례로 얻어서 사용하기 위한 인터페이스
    fun IteratorExam() {
        val list : ArrayList<String> = ArrayList();
        val iterator1 = list.iterator()
        while (iterator1.hasNext()) {
            Log.d("iteratorExam1", iterator1.next())
        }
    }
}


