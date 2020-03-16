package com.niko.flutterhybirddemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.android.FlutterFragment;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FlutterEngine flutterEngine;
    private static final String TAG_FLUTTER_FRAGMENT = "flutter_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化FlutterEngine
        flutterEngine = new FlutterEngine(this);
        flutterEngine.getDartExecutor().executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault());

//        flutterEngine.getNavigationChannel().setInitialRoute("route");
        //添加FlutterEngine
        FlutterEngineCache.getInstance().put("my_engine",flutterEngine);

        findViewById(R.id.start_flutter_activity).setOnClickListener(this::onClick);
        findViewById(R.id.start_flutter_activity2).setOnClickListener(this::onClick);
        findViewById(R.id.start_flutter_activity3).setOnClickListener(this::onClick);

        loadFlutterView();
    }

    private void loadFlutterView() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        //这里会报错，不让我强转，不理会他
        FlutterFragment  flutterFragment = (FlutterFragment)fragmentManager.findFragmentByTag(TAG_FLUTTER_FRAGMENT);
        flutterFragment = FlutterFragment.createDefault();
        fragmentManager.beginTransaction()
                .add(R.id.load_flutter_view,flutterFragment,TAG_FLUTTER_FRAGMENT)
                .commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_flutter_activity:
                //方式一：跳转到默认的页面
                startActivity(FlutterActivity.createDefaultIntent(
                        MainActivity.this
                ));
                break;
            case R.id.start_flutter_activity2:
                //方式二：跳转到指定路由
                startActivity(FlutterActivity.withNewEngine()
                .initialRoute("route")
                .build(MainActivity.this));
                break;
            case R.id.start_flutter_activity3:
                //方式三： 先初始化FlutterEngine 在跳转界面明显比前面两种方式快很多
                Intent intent = FlutterActivity
                        .withCachedEngine("my_engine")
                        .build(MainActivity.this);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;

        }
    }
}
