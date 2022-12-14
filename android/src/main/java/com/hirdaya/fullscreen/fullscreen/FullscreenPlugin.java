package com.hirdaya.fullscreen.fullscreen;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

/**
 * FullscreenPlugin By Hirdaya Shrestha
 * For fullscreen in phones with notch
 * you can add these lines in styles
 * <item name="android:windowLayoutInDisplayCutoutMode">shortEdges</item>
 * <item name="android:windowTranslucentStatus">true</item>
 * <item name="android:windowTranslucentNavigation">true</item>
 */
public class FullscreenPlugin implements FlutterPlugin, MethodChannel.MethodCallHandler, ActivityAware {

    private Activity mainActivity;
    private MethodChannel channel;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding binding) {
        channel = new MethodChannel(binding.getBinaryMessenger(), "com.hirdaya.fullscreen/50 72 61 77 65 73 68 69 6B 61");
        channel.setMethodCallHandler(this);
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        switch (call.method) {
            case "enableFullscreen":
                hideSystemUi();
                result.success(null);
                break;
            case "disableFullscreen":
                showSystemUi();
                result.success(null);
                break;
            default:
                result.notImplemented();
        }
    }

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
        mainActivity = binding.getActivity();
    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
        mainActivity = binding.getActivity();
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {

    }

    @Override
    public void onDetachedFromActivity() {

    }

    private void hideSystemUi() {
        WindowCompat.setDecorFitsSystemWindows(mainActivity.getWindow(), false);
        WindowInsetsControllerCompat wICC = new WindowInsetsControllerCompat(mainActivity.getWindow(), mainActivity.getWindow().getDecorView());
        wICC.hide(WindowInsetsCompat.Type.systemBars());
        wICC.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
    }

    private void showSystemUi() {
        WindowCompat.setDecorFitsSystemWindows(mainActivity.getWindow(), true);
        new WindowInsetsControllerCompat(
                mainActivity.getWindow(),
                mainActivity.getWindow().getDecorView()
        ).show(WindowInsetsCompat.Type.systemBars());
    }
}
