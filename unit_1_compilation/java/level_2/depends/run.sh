javac -sourcepath ./src -d build/classes -cp ./libs/commons-lang3-3.13.0.jar ./src/ua/com/alevel/Hello.java
java -cp build/classes/:./libs/commons-lang3-3.13.0.jar ua.com.alevel.Hello
