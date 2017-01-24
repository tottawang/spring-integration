# spring-integration

### Description
This sample is to use Spring integration features. Based on a number lets say 150, it will transform to a list contains the itself and next 5 numbers (total 5 numbers), Splitter will split the message payload to 5 separate messages and finally filter will try to filer out those odd numbers. The pipeline is not very practical just try to make transform, split and filter work together.

### Run and build
Step-1: 
./gradlew clean build

Step-2:
* java -jar build/libs/sample-0.0.1-SNAPSHOT.jar