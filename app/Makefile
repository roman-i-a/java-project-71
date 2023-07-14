.DEFAULT_GOAL := build-run

run-dist:
	./build/install/app/bin/app build/resources/test/fixtures/file1.json build/resources/test/fixtures/file2.json

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean install

run:
	./gradlew run

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain checkstyleTest

update-deps:
	./gradlew useLatestVersions


build-run: build run

.PHONY: build, run-dist