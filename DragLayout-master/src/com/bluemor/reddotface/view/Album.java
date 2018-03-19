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
	// 创建一个DetailFragment的新实例，其中包括要传递的数据包

	public static Album newInstance(int index) {
		Album f = new Album();

		// 将index作为一个参数传递
		Bundle bundle = new Bundle(); // 实例化一个Bundle对象
		bundle.putInt("index", index); // 将索引值添加到Bundle对象中
		f.setArguments(bundle); // 将bundle对象作为Fragment的参数保存
		return f;
	}


	public int getShownIndex() {
		return getArguments().getInt("index", 0); // 获取要显示的列表项索引
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}

		ScrollView scroller = new ScrollView(getActivity()); // 创建一个滚动视图
		
		TextView text = new TextView(getActivity()); // 创建一个文本框对象

		text.setPadding(10, 10, 10, 10); // 设置内边距
		text.setTextColor(Color.YELLOW);
		text.setTextSize(20f);
		scroller.addView(text); // 将文本框对象添加到滚动视图中
//		scroller.setBackgroundColor(Color.rgb(89, 46, 106));
		scroller.setBackgroundColor(Color.rgb(86, 19, 81));
//		text.setText(Data.DETAIL[getShownIndex()]); // 设置文本框中要显示的文本
		return scroller;
	}
}
