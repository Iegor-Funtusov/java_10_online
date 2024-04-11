
cd ./apache-tomcat-10.1.13/bin
sh ./shutdown.sh

cd ../../

mvn clean install

cp target/unit_25_servlet.war ./apache-tomcat-10.1.13/webapps

cd ./apache-tomcat-10.1.13/bin

sh ./startup.sh

cd ../../