run-dist:
	./app/build/install/app/bin/app

test:
	./gradlew test

build: test checkstyle
	./gradlew clean installDist

report:
	./gradlew jacocoTestReport --stacktrace

checkstyle:
	./gradlew checkstyleMain

say-hello:
	echo "Hello, World!"

.PHONY: build, test
