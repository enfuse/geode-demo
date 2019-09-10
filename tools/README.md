```shell script
# Generate file with test data
./generateTelemetrySampleData.sh

# Put file in directory for source applications to consume
./deploy-file-to-source.sh

# Read file with test data and produce messages to Kafka topic
./publishSampleDataToKafka.sh

# Read file multiple times & produce messages to Kafka topic
./publishMultipleDataFilesToKafka.sh
```
