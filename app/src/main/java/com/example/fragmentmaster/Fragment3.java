package com.example.fragmentmaster;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment3 extends Fragment {

    public static interface ImageSelectionCallback {
        public void onImageSelected(int position);
    }

    public ImageSelectionCallback callback;

    @Override
    public void onAttach(@NonNull Context context) { // 프래그먼트가 액티비티에 올라옴
        super.onAttach(context);

        if(context instanceof ImageSelectionCallback) {
            callback = (ImageSelectionCallback)context;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { // 인플레이션이 필요한 시점에 자동 호출됨
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_3, container, false); // rootView는 최상위 레이아웃, 인플레이션을 통해 참조한 객체임
        // -> 인플레이션 과정이 끝나고나면 프래그먼트가 하나의 뷰처럼 동작할 수 있는 상태가 됨

        Button btn1 = rootView.findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callback != null) {
                    callback.onImageSelected(0);
                }
            }
        });

        Button btn2 = rootView.findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callback != null) {
                    callback.onImageSelected(1);
                }
            }
        });

        Button btn3 = rootView.findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callback != null) {
                    callback.onImageSelected(2);
                }
            }
        });

        return rootView; // 인플레이션한 rootView를 리턴 -> 프래그먼트 화면으로 보여짐
    }
}