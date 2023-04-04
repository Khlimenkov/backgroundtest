import 'dart:async';

import 'package:backgroundtest/src/app.dart';
import 'package:flutter/material.dart';

@pragma('vm:entry-point')
void main([List<String>? args]) => runZonedGuarded<Future<void>>(() async {
      WidgetsFlutterBinding.ensureInitialized();
      runApp(const App());
    }, (error, stackTrace) {
      print('$error\n$stackTrace');
    });
