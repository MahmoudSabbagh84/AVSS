echo "subscribed to avss_mqtt"
echo "ID: avss_pi"
mosquitto_sub -h 192.168.0.101 -t avss_mqtt -u steve -P password -i avss_pi >> /home/pi/Desktop/mosquitto_logs.txt
