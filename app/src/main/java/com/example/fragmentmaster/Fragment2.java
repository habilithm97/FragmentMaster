package com.example.fragmentmaster;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { // 인플레이션이 필요한 시점에 자동 호출됨
        return inflater.inflate(R.layout.fragment_1, container, false); // 인플레이션 과정이 끝나고 나면 프래그먼트가 하나의 뷰처럼 동작할 수 있는 상태가 됨
    }
}