# Pure Kotlin/Native iOS App

This example app is 100% written in Kotlin and does not contain any code in Objective-C or Swift. Furthermore, the app binary itself is build by Gradle and does not depend on Xcode to generate the app.

In fact an iOS-App is just a folder with some special files. Namely the binary itself (build by Gradle) and a `Info.plist` file, that describes the app and where to find the binary, app icon, supported features, requirements, etc.

## Usage

To build the one of the apps ([direct-uikit](#example-direct-uikit)) the included `Makefile` can be used by executing:
```shell
make simulator.app
```
This will create the app/directory `direct-uikit/build/bin/iosSimulatorArm64/simulator.app`

This app can then be installed on a simulator. To simplify this, the `Makefile` includes other targets, that require a running simulator. It is easiest to start the simulator either from the GUI or using the `Makefile`:
```shell
make sim
```

Afterward not further manual action required. Simply execute the following command to run the app:
```shell
make run
```

Alternatively the app can also just be installed using:
```shell
make install
```

## UI Building Examples

This repo contains a few examples of how UI could be build in an app, that's 100% written in Kotlin.

### Example: `direct-uikit`

The first and easiest example, is using [UIKit](https://developer.apple.com/documentation/uikit) directly. It can be build using the following `make` command:
```shell
make simulator.app PROJ=direct-uikit
```

### Example: `compose-jb`

This example utilizes the [compose-multiplatform](https://github.com/JetBrains/compose-multiplatform) implementation by JetBrains to build UI. Additionally it relies on the implementation of UI-components using [Skiko](https://github.com/JetBrains/skiko), which draws on a canvas. It can be build using the following `make` command:
```shell
make simulator.app PROJ=compose-jb
```

### Example: `compose-uikit` (via `compose-ui-uikit`)

This example replaces the implementation of UI-components with a custom implementation that relies on UIKit. Therefore no canvas is needed and all native features would be theoretically usable. The example app can be build using the following `make` command:
```shell
make simulator.app PROJ=compose-uikit
```

## Caveats

* Currently signing the app is not included in this example. This means, it can only be installed in simulators for now. Technically it would be possible to support signing though, this is no limitation of the chosen method to create the app.
* Different solutions for building UI could be used that are currently not shown, e.g. CashApp's Reedwood, Swift UI ...
