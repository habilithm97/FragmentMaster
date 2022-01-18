package com.example.fragmentmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/* 프래그먼트의 목적은 분할된 화면들을 독립적으로 구성하고 상태를 관리하기 위해 사용함

-액티비티 화면을 각각 부분 화면의 프래그먼트로 분할해서 만들고 그 프래그먼트를 독립적으로 관리해야하기 때문에 프래그먼트는 항상
액티비티 위에 올라가 있어야함 -> 따라서 프래그먼트가 액티비티에 올라가는 시점에 제대로 동작함

-프래그먼트가 동작하는 방식은 액티비티의 동작 방식을 본 떠 만들었음
 ->액티비티 : 시스템의 액티비티 매니저가 관리, 데이터 전달 시 인텐트 사용
 ->프래그먼트 : 액티비티의 프래그먼트 매니저가 관리, 데이터 전달 시 메소드 호출

프래그먼트 화면 전환은 리소스를 적게 사용하는 가벼운 화면 전환임

프래그먼트는 뷰는 아니고 부분 화면을 담기 위한 틀임
 */

public class MainActivity extends AppCompatActivity {

    Fragment1 fragment1;
    Fragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = (Fragment1)getSupportFragmentManager().findFragmentById(R.id.fragment); // 메인 액티비티에 있는 프래그먼트는 부분 화면들을 담기 위한 틀임
        fragment2 = new Fragment2();
    }

    public void onFragmentChanged(int index) { // beginTransaction() : 프래그먼트를 변경하기 위한 트랜잭션을 시작함, replace() : 프래그먼트 화면 전환, commit() : 실행
        if(index == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit(); // 프래그먼트 매니저 객체를 사용할 때에는 트랜잭션이 사용됨(화면 전환 오류 발생 시 다시 원래 상태로 돌릴 수 있어야하므로)
        } else if(index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
        }
    }
}