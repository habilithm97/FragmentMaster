package com.example.fragmentmaster;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class ImageFragment extends Fragment {

    ImageView img1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { // 인플레이션이 필요한 시점에 자동 호출됨
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_viewer, container, false); // rootView는 최상위 레이아웃, 인플레이션을 통해 참조한 객체임
        // -> 인플레이션 과정이 끝나고나면 프래그먼트가 하나의 뷰처럼 동작할 수 있는 상태가 됨

        img1 = rootView.findViewById(R.id.img1);
        return rootView; // 인플레이션한 rootView를 리턴 -> 프래그먼트 화면으로 보여짐
    }

    public void setImage(int resId) {
        img1.setImageResource(resId); // 액티비티에서 이 프래그먼트에 있는 이미지뷰에 이미지를 설정할 수 있도록 함
    }
}