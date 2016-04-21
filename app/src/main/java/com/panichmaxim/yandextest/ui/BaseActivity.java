package com.panichmaxim.yandextest.ui;

import android.os.Bundle;
import com.trello.navi.component.support.NaviAppCompatActivity;
import com.trello.rxlifecycle.ActivityLifecycleProvider;
import com.trello.rxlifecycle.navi.NaviLifecycle;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseActivity extends NaviAppCompatActivity {

    protected final ActivityLifecycleProvider provider = NaviLifecycle.createActivityLifecycleProvider(this);
    protected CompositeSubscription mCompositeSubscription  = new CompositeSubscription();

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeSubscription.unsubscribe();
    }

}
