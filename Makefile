run-dist:
	./app/build/install/app/bin/app

build:
	./gradlew clean installDist

checkstyle:
	./gradlew checkstyleMain

say-hello:
	echo "Hello, World!"

.PHONY: build
