# Pure Kotlin/Native iOS App

This example app is 100% written in Kotlin and does not contain any code in Objective-C or Swift. Furthermore, the app binary itself is build by Gradle and does not depend on Xcode to generate the app.

In fact an iOS-App is just a folder with some special files. Namely the binary itself (build by Gradle) and a `Info.plist` file, that describes the app and where to find the binary, app icon, supported features, requirements, etc.

To build the app the included `Makefile` can be used by executing:
```shell
make simulator.app
```
This will create the app/directory `build/bin/iosSimulatorArm64/simulator.app`

This app can then be installed on a simulator. To simplify this, the `Makefile` includes other targets, that require a running simulator. It is easiest to manually start the simulator from the GUI, but afterward not further manual action required. Simply execute the following command to run the app:
```shell
make run
```

Alternatively the app can also just be installed using:
```shell
make install
```

## Caveats

* Currently signing the app is not included in this example. This means, it can only be installed in simulators for now. Technically it would be possible to support signing though, this is no limitation of the chosen method to create the app.
* Building some UI is only shown via manual creation of UIKit components. Different solutions could be used, e.g. Compose UI, CashApp's Reedwood, ...
