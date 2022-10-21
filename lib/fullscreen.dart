import 'dart:io';

import 'package:flutter/services.dart';

class Fullscreen {
  //made with <3 by Hirdaya
  MethodChannel channel = const MethodChannel(
      "com.hirdaya.fullscreen/50 72 61 77 65 73 68 69 6B 61");

  Future<void> enableFullscreen() async {
    if (Platform.isAndroid) {
      channel.invokeMethod("enableFullscreen");
    }
  }

  Future<void> disableFullscreen() async {
    if (Platform.isAndroid) {
      channel.invokeMethod("disableFullscreen");
    }
  }
}
