run-dist:
	./app/build/install/app/bin/app

build:
	./gradlew clean installDist

checkstyle:
	./gradlew checkstyleMain

.PHONY: build
