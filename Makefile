all: build

build:
	javac -d ./out *.java
run: build
	cd out/;java Backtrack