//package com.niko.flutterhybirddemo;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.view.ViewGroup;
//
//import io.flutter.embedding.android.FlutterActivity;
//import io.flutter.embedding.android.FlutterFragment;
//import io.flutter.embedding.engine.FlutterEngine;
//import io.flutter.embedding.engine.FlutterEngineCache;
//import io.flutter.embedding.engine.dart.DartExecutor;
//import io.flutter.view.FlutterRunArguments;
//import io.flutter.view.FlutterView;
//
//@SuppressWarnings("ALL")
//public class SingleFlutterViewActivity extends AppCompatActivity {
//
//
//    private FlutterEngine flutterEngine;
//    private static final String TAG_FLUTTER_FRAGMENT = "flutter_fragment";
//
//    FlutterFragment flutterFragment;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_single_flutter_view);
//        init();
//    }
//
//    private void init() {
//
//        //方式一
//        findViewById(R.id.startFlutterActivity).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(FlutterActivity.createDefaultIntent(
//                        SingleFlutterViewActivity.this));
//            }
//        });
//
//        //方式二
//        findViewById(R.id.startFlutterActivity2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent myapp2 = FlutterActivity.withNewEngine()
//                        .initialRoute("myapp2")
//                        .build(SingleFlutterViewActivity.this);
//                startActivity(myapp2);
//            }
//        });
//
//        //方式三
//        flutterEngine = new FlutterEngine(this);
//        flutterEngine.getDartExecutor().executeDartEntrypoint(
//                DartExecutor.DartEntrypoint.createDefault()
//        );
//        //设置跳转的路由
//        flutterEngine.getNavigationChannel().setInitialRoute("myapp2");
//        FlutterEngineCache.getInstance().put("my_engine", flutterEngine);
//
//        findViewById(R.id.startFlutterActivity3).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent engineIntent = FlutterActivity
//                        .withCachedEngine("my_engine")
//                        .build(SingleFlutterViewActivity.this);
//                startActivity(engineIntent);
//            }
//        });
//
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        flutterFragment = (FlutterFragment) fragmentManager.findFragmentByTag(TAG_FLUTTER_FRAGMENT);
//        flutterFragment = FlutterFragment.createDefault();
//        fragmentManager.beginTransaction()
//                .add(R.id.flutter_view, flutterFragment, TAG_FLUTTER_FRAGMENT)
//                .commit();
//    }
//
//
//}
//
//
