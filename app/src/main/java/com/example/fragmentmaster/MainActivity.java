package com.example.fragmentmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*

*프래그먼트의 목적은 분할된 화면들을 독립적으로 구성하고 상태를 관리하기 위해 사용함

-액티비티 화면을 각각 부분 화면의 프래그먼트로 분할해서 만들고 그 프래그먼트를 독립적으로 관리해야하기 때문에 프래그먼트는 항상
액티비티 위에 올라가 있어야함 -> 따라서 프래그먼트가 액티비티에 올라가는 시점에 제대로 동작함

-프래그먼트가 동작하는 방식은 액티비티의 동작 방식을 본 떠 만들었음
 ->액티비티 : 시스템의 액티비티 매니저가 관리, 데이터 전달 시 인텐트 사용
 ->프래그먼트 : 액티비티의 프래그먼트 매니저가 관리, 데이터 전달 시 메소드 호출

-프래그먼트 화면 전환은 리소스를 적게 사용하는 가벼운 화면 전환임

-프래그먼트는 뷰는 아니고 부분 화면을 담기 위한 틀임

-프래그먼트에서 해당 액티비티를 참조하고 싶으면 onAttach()로 전달되는 파라미터를 참조하거나 getActivity() 호출하여 반환되는 객체를 참조할 수  있음
 -> 참조한 객체를 변수에 할당하면 프래그먼트 클래스 안에서 자유롭게 액티비티 객체를 참고할 수 있음



**프래그먼트의 수명주기
-프래그먼트가 화면에 보이기 전까지 호출될 수 있는 수명주기 메소드
 -> onAttach() : 프래그먼트가 액티비티에 올라갈 때 호출됨
 -> onCreate() : 프래그먼트가 초기화될 때 호출됨
 -> onCreateView() : 프래그먼트와 관련되는 뷰 계층을 만들어서 리턴함
 -> onActivityCreated() : 프래그먼트와 연결된 액티비티가 onCreate() 메소드의 작업을 완료했을 때 호출됨
 -> onStart() : 사용자에게 프래그먼트가 보일 때 호출됨
 -> onResume() : 사용자와 상호작용할 수 있을 때 호출됨

-프래그먼트가 화면에서 보이지 않게 되면서 호출되는 상태 메소드
 -> onPause() : 사용자와 상호작용을 중지할 때 호출됨
 -> onStop() : 화면에서 더 이상 보이지 않을 때 호출됨
 -> onDestroyView() : 프래그먼트와 관련된 뷰 리소스를 해제할 수 있도록 호출됨
 -> onDestroy() : 프래그먼트의 상태를 마지막으로 정리할 수 있도록 호출됨
 -> onDetach() : 프래그먼트가 액티비티와 연결을 끊기 바로 전에 호출됨

 */

public class MainActivity extends AppCompatActivity implements ButtonFragment.ImageSelectionCallback{

    ButtonFragment listFragment;
    ImageFragment viewerFragment;

    int[] images = {R.drawable.luck12, R.drawable.gitprofile, R.drawable.dingsung};

    /*
    Fragment1 fragment1;
    Fragment2 fragment2;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        listFragment = (ButtonFragment)manager.findFragmentById(R.id.listFragment);
        viewerFragment = (ImageFragment)manager.findFragmentById(R.id.viewerFragment);

        /*
        fragment1 = (Fragment1)getSupportFragmentManager().findFragmentById(R.id.fragment); // 메인 액티비티에 있는 프래그먼트는 부분 화면들을 담기 위한 틀임
        fragment2 = new Fragment2();
         */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_test, menu); // 인플레이션하여 메모리에 로딩

        View view = menu.findItem(R.id.search2).getActionView(); // 메뉴 아이템 중에서 검색을 위해 정의한 아이템템을 뷰 객체로 참조함
        if(view != null) {
            EditText edt = view.findViewById(R.id.edt); // 검색 메뉴 아이템 안에 정의한 EditText 객체 참조

            if(edt != null) {
                edt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                        Toast.makeText(getApplicationContext(), "검색어를 입력했습니다. ", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int selectedId = item.getItemId(); // 메뉴 아이템들을 가져옴
        switch (selectedId) {
            case R.id.f5:
                Toast.makeText(getApplicationContext(), "새로고침 메뉴를 선택하였습니다. ", Toast.LENGTH_SHORT).show();
                break;

            /*case R.id.search:
                Toast.makeText(getApplicationContext(), "검색 메뉴를 선택하였습니다. ", Toast.LENGTH_SHORT).show();
                break; */

            case R.id.setting:
                Toast.makeText(getApplicationContext(), "환경 설정 메뉴를 선택하였습니다. ", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    } //

    @Override
    public void onImageSelected(int position) {
        viewerFragment.setImage(images[position]);
    }

    /*
    public void onFragmentChanged(int index) { // beginTransaction() : 프래그먼트를 변경하기 위한 트랜잭션을 시작함, replace() : 프래그먼트 화면 전환, commit() : 실행
        if(index == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit(); // 프래그먼트 매니저 객체를 사용할 때에는 트랜잭션이 사용됨(화면 전환 오류 발생 시 다시 원래 상태로 돌릴 수 있어야하므로)
        } else if(index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
        }
    } */
}