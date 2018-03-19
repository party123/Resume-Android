package com.bluemor.reddotface.view;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class Album extends Fragment {
	// ����һ��DetailFragment����ʵ�������а���Ҫ���ݵ����ݰ�

	public static Album newInstance(int index) {
		Album f = new Album();

		// ��index��Ϊһ����������
		Bundle bundle = new Bundle(); // ʵ����һ��Bundle����
		bundle.putInt("index", index); // ������ֵ��ӵ�Bundle������
		f.setArguments(bundle); // ��bundle������ΪFragment�Ĳ�������
		return f;
	}


	public int getShownIndex() {
		return getArguments().getInt("index", 0); // ��ȡҪ��ʾ���б�������
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}

		ScrollView scroller = new ScrollView(getActivity()); // ����һ��������ͼ
		
		TextView text = new TextView(getActivity()); // ����һ���ı������

		text.setPadding(10, 10, 10, 10); // �����ڱ߾�
		text.setTextColor(Color.YELLOW);
		text.setTextSize(20f);
		scroller.addView(text); // ���ı��������ӵ�������ͼ��
//		scroller.setBackgroundColor(Color.rgb(89, 46, 106));
		scroller.setBackgroundColor(Color.rgb(86, 19, 81));
//		text.setText(Data.DETAIL[getShownIndex()]); // �����ı�����Ҫ��ʾ���ı�
		return scroller;
	}
}
