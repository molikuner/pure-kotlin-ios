SIM?=$(shell xcrun simctl list -j 'devices' 'booted' | jq --raw-output '.devices | .[] | .[] | .udid' | head -n 1)

.PHONY: default
default: simulator.app

.PHONY: clean
clean:
	./gradlew clean

.PHONY: sim
sim:
	open -a "Simulator.app"

.PHONY: simulator.app
simulator.app:
	./gradlew --quiet linkDebugExecutableIosSimulatorArm64
	mkdir -p build/bin/iosSimulatorArm64/simulator.app
	cp build/bin/iosSimulatorArm64/{debugExecutable,simulator.app}/pure-kotlin-ios.kexe
	cp src/iosMain/resources/Info.plist build/bin/iosSimulatorArm64/simulator.app/

.PHONY: install
install: simulator.app
	@sh -c '[ "X${SIM}" != "X" ] || { echo "SIM variable not set and simulator not found" && exit 1; }'
	xcrun simctl install ${SIM} build/bin/iosSimulatorArm64/simulator.app

.PHONY: run
run: install
	@sh -c '[ "X${SIM}" != "X" ] || { echo "SIM variable not set and simulator not found" && exit 1; }'
	xcrun simctl launch --console ${SIM} com.molikuner.kn.ios.pure-kotlin-ios
