#!/usr/bin/env bash
# deploy raspberry pi server

#***********************************************************************************************************************
param=$1
len=$(xset q | grep -Po 'LED mask:\s*\K\d+')
pac="/home/pi/server_ip"
#***********************************************************************************************************************
# DOWNLOAD
if [[  "$param" == "-d"  ]] || [[  "$param" == "-download"  ]]; then
    echo "server-ip download"
    cd $pac
    rm -rf server-ip
    git clone https://github.com/VlAvekin/server-ip.git
    cp /home/pi/server_ip/server-ip/script/server-ip.sh /home/pi/server_ip
fi
# DOWNLOAD END
#***********************************************************************************************************************

# Check installed software
#***********************************************************************************************************************
# JAVA
if [ "which java" ]; then
    if [[ "$param" == "-config" ]] &&  [[ "$2" != "0" ]]; then
    echo "Java exist..."
    java -version
    jdc -version
    fi
else
    sudo add-apt-repository ppa:webupd8team/java
    sudo apt-get update
    sudo apt-get install oracle-java11-installer

    export PATH="$PATH:$JAVA_HOME/bin:$JRE_HOME/bin"
    export JAVA_HOME=/usr/lib/jvm/java-11-oracle
    export JDK_HOME=/usr/lib/jvm/java-11-oracle
    export JRE_HOME=/usr/lib/jvm/java-11-oracle/jre
fi
# JAVA END

# MAVEN
maven=$(which mvn)
if [ $maven ]; then
    if [[ "$param" == "-config" ]] &&  [[ "$2" != "0" ]]; then
    echo "maven exist..."
    mvn -version
    fi
else
    echo "maven download..."
    sudo apt-get install maven
fi
# MAVEN END
#***********************************************************************************************************************

# PACKAGE
if [[ "$param" == "-package" ]]; then
    cd $pac/server-ip
    mvn clean package
    cp target/server-ip-0.0.1-SNAPSHOT.jar $pac
fi
# PACKAGE END

# START
if [[ "$param" == "-start" ]]; then
    echo "Start server..."
    #mvn clean package
    iptables -t nat -A POSTROUTING -o eth0 -j MASQUERADE
    iptables -A FORWARD -i eth0 -o wlan0 -m state --state RELATED,ESTABLISHED -j ACCEPT
    iptables -A FORWARD -i wlan0 -o eth0 -j ACCEPT
    nohup java -jar server-ip-0.0.1-SNAPSHOT.jar > log.txt &
fi
# START END

# STOP
if [[ "$param" == "-stop" ]]; then
    echo "Stop server..."
    pgrep java | xargs kill -9
fi
# STOP END

# RESTART
if [[ "$param" == "-restart" ]]; then
    echo "Restart server..."
    pgrep java | xargs kill -9
    nohup java -jar server-ip-0.0.1-SNAPSHOT.jar > log.txt &
fi
# RESTART END

# STATUS
if [[ "$param" == "-status" ]]; then
    tail -f log.txt
fi
# STATUS END
#***********************************************************************************************************************
# HELP
if [[ "$param" == "" ]] || [[  "$param" == "-h"  ]] || [[  "$param" == "-help"  ]]; then
    echo "HELPER";
    echo "Parameters:"
    echo
    echo "  ./$0 -d || -download    | download"
    echo "  ./$0 -start             | Start server"
    echo "  ./$0 -stop              | Stop server"
    echo "  ./$0 -restart           | Restart server"
    echo "  ./$0 -status            | Status server"; echo
    echo "  ./$0 -package           | Maven package project"
    echo "  ./$0 -update/-up        | Re build project"; echo
    echo "  ./$0 -config            | Check installed software"
    echo
fi
# HELP END
echo 'Bye'
exit 0;