# About compilation #

## Level 1 (without libs) ##

### go to /java/level_1/simple ###
`javac Hello.java`\
`java Hello`
### go to /java/level_1/package ###
`javac ua/com/alevel/Hello.java`\
`java ua.com.alevel.Hello`
### go to /java/level_1/union ###
`javac ua/com/alevel/Hello.java`\
`java ua.com.alevel.Hello`
### go to /java/level_1/separate_packages ###
`javac ua/com/alevel/Hello.java`\
`java ua.com.alevel.Hello`

## Level 2 (with libs) ##
## Compilation contains external libraries ##

### go to /java/level_2/minimal_proj ###
`javac -sourcepath ./src -d build/classes ./src/ua/com/alevel/Hello.java`\
`java -cp build/classes ua.com.alevel.Hello`
### go to /java/level_2/minimal_jar ###
`javac -sourcepath ./src -d build/classes ./src/ua/com/alevel/Hello.java`\
`jar cvfm build/jar/hello.jar resources/MANIFEST.MF -C build/classes .`\
`java -jar build/jar/hello.jar`

### go to /java/level_2/depends ###
`javac -sourcepath ./src -d build/classes/ -cp ./libs/commons-lang3-3.13.0.jar src/ua/com/alevel/Hello.java`\
`java -cp build/classes/:./libs/commons-lang3-3.13.0.jar ua.com.alevel.Hello`

### go to /java/level_2/final_1 ###
### 1 variant ###
### Run compile ###
`$ javac -sourcepath ./src -d build/classes/ -cp ./libs/commons-lang3-3.13.0.jar src/ua/com/alevel/Hello.java`
### copy lib jar ###
`$ cp -r libs/*.jar build/jar`
### Create jar ###
`$ jar cvfm build/jar/hello.jar resources/MANIFEST.MF -C build/classes .`
### Run jar ###
`$ java -jar build/jar/hello.jar`

### go to /java/level_2/final_2 ###
### 2 variant ###
### Run compile ###
`$ javac -sourcepath ./src -d build/classes/ -cp ./libs/commons-lang3-3.13.0.jar src/ua/com/alevel/Hello.java`
### Unzip all jars ###
`$ cd libs`
`$ jar xf commons-lang3-3.13.0.jar`
`$ cp -rf ./org ../build/classes`
`$ cd ..`
### Create jar ###
`$ jar cvfm build/jar/hello.jar resources/MANIFEST.MF -C build/classes .`
### Show jar ###
`$ jar tf build/jar/hello.jar`
### Run jar ###
`$ java -jar build/jar/hello.jar`
