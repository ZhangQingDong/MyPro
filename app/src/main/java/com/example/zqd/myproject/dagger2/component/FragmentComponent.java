package com.example.zqd.myproject.dagger2.component;

import com.example.zqd.myproject.dagger2.scope.FragmentScope;
import com.example.zqd.myproject.ui.view.fragment.FragmentOne;
import com.example.zqd.myproject.ui.view.fragment.FragmentThree;
import com.example.zqd.myproject.ui.view.fragment.FragmentTwo;

import dagger.Component;

/**
 * <p>Title: com.example.zqd.myproject.component</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * @author zhangqingdong
 * @version 4.0
 * @date 2018/4/19 16:15
 */
@FragmentScope
@Component(dependencies = AppComponent.class)
public interface FragmentComponent {

}
