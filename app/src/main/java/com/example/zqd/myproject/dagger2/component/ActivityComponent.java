package com.example.zqd.myproject.dagger2.component;

import com.example.zqd.myproject.dagger2.scope.ActivityScope;
import com.example.zqd.myproject.ui.view.activity.HistoryActivity;
import com.example.zqd.myproject.ui.view.activity.MainActivity;
import com.example.zqd.myproject.ui.view.activity.TabActivity;

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
@ActivityScope
@Component(dependencies = AppComponent.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(HistoryActivity activity);

}
