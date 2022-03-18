package com.example.fragmentmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(3); // 미리 로딩해 놓을 아이템의 개수를 3개로 늘림

        ViewPagerTest adapter = new ViewPagerTest(getSupportFragmentManager());

        TabFragment1 tabFragment1 = new TabFragment1();
        adapter.addItem(tabFragment1);

        TabFragment2 tabFragment2 = new TabFragment2();
        adapter.addItem(tabFragment2);

        TabFragment3 tabFragment3 = new TabFragment3();
        adapter.addItem(tabFragment3);

        viewPager.setAdapter(adapter); // 어댑터 객체 설정 -> 이 때부터 뷰페이저는 어댑터에 있는 프래그먼트들을 화면에 보여줌
    }

    class ViewPagerTest extends FragmentStatePagerAdapter { // 어댑터는 뷰페이저에서 보여줄 각 프래그먼트를 관리함

        ArrayList<Fragment> items = new ArrayList<Fragment>(); // 프래그먼트 객체를 담음

        public ViewPagerTest(@NonNull FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item) {
            items.add(item);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }
}