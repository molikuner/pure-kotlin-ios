SIM?=$(shell xcrun simctl list -j 'devices' 'booted' | jq --raw-output '.devices | .[] | .[] | .udid' | head -n 1)
PROJ?=direct-uikit

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
	./gradlew --quiet :${PROJ}:linkDebugExecutableIosSimulatorArm64
	mkdir -p ${PROJ}/build/bin/iosSimulatorArm64/simulator.app
	cp ${PROJ}/build/bin/iosSimulatorArm64/{debugExecutable,simulator.app}/${PROJ}.kexe
	cp ${PROJ}/src/iosMain/resources/Info.plist ${PROJ}/build/bin/iosSimulatorArm64/simulator.app/

.PHONY: install
install: simulator.app
	@sh -c '[ "X${SIM}" != "X" ] || { echo "SIM variable not set and simulator not found" && exit 1; }'
	xcrun simctl install ${SIM} ${PROJ}/build/bin/iosSimulatorArm64/simulator.app

.PHONY: run
run: install
	@sh -c '[ "X${SIM}" != "X" ] || { echo "SIM variable not set and simulator not found" && exit 1; }'
	xcrun simctl launch --console ${SIM} com.molikuner.kn.ios.pure-kotlin-ios.${PROJ}
