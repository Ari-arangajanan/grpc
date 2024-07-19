# Sample Saga project

 # (curl for API call)

 Orchestration pattern 

    curl --location 'http://localhost:9301/author' \
    --header 'Content-Type: application/json' \
    --data '{
    "id": 1,
    "name": "teyteyt"
    }'

Choreography test

    curl --location 'http://localhost:9301/user/saveChore' \
    --header 'Content-Type: application/json' \
    --data '{
    "id": 1,
    "name": "gdfgd"
    }'



# Rocket mq configs
download RocketMq: https://github.com/apache/rocketmq?tab=readme-ov-file  

see the chat : https://chatgpt.com/share/4dbcf4f7-8205-439e-9f39-a9796565655d

# run RocketMQ server locally on your Windows PC, follow these steps
1. Install Java Development Kit (JDK):
    RocketMQ requires Java. Download and install JDK from the Oracle website or use an OpenJDK version.
2. Download RocketMQ:
Download the latest RocketMQ release from the Apache RocketMQ GitHub releases page.

3. Extract the RocketMQ Files:
Extract the downloaded RocketMQ zip or tar file to a directory on your system.

4. Set Up RocketMQ:
Navigate to the bin directory in the RocketMQ installation folder.

5. Start Name Server:
Open a command prompt and navigate to the RocketMQ bin directory. Run the following command to start the RocketMQ Name Server:

# Example Commands:
Starting Name Server:

    cd path\to\rocketmq\bin
    mqnamesrv.cmd

Starting Broker:

    cd path\to\rocketmq\bin
    mqbroker.cmd -n 127.0.0.1:9876


# RocketMQ Dashboard
For a graphical interface, you can use RocketMQ Dashboard. Follow these steps:

1. Download RocketMQ Dashboard:
Clone the RocketMQ Dashboard from GitHub:


        git clone https://github.com/apache/rocketmq-dashboard.git  

2. Build and Run Dashboard:
Navigate to the dashboard directory and build the project using Maven:


    cd rocketmq-dashboard
    mvn clean package -Dmaven.test.skip=true
    java -jar target/rocketmq-dashboard-<version>.jar

3. Access the Dashboard:
Open your web browser and go to http://localhost:8080. You should see the RocketMQ Dashboard.


