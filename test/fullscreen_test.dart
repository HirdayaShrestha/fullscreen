import 'package:flutter_test/flutter_test.dart';
import 'package:fullscreen/fullscreen.dart';
import 'package:fullscreen/fullscreen_platform_interface.dart';
import 'package:fullscreen/fullscreen_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockFullscreenPlatform
    with MockPlatformInterfaceMixin
    implements FullscreenPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final FullscreenPlatform initialPlatform = FullscreenPlatform.instance;

  test('$MethodChannelFullscreen is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelFullscreen>());
  });

  test('getPlatformVersion', () async {
    Fullscreen fullscreenPlugin = Fullscreen();
    MockFullscreenPlatform fakePlatform = MockFullscreenPlatform();
    FullscreenPlatform.instance = fakePlatform;

    expect(await fullscreenPlugin.getPlatformVersion(), '42');
  });
}
