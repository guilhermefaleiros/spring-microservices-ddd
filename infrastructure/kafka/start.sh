((sleep 15 && for i in $(cat topics.txt);
    do
      kafka-topics --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic $i
    done
)&) && /etc/confluent/docker/run