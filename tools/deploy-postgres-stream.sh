
curl -H "Accept: application/json" -X POST "http://localhost:9393/streams/definitions" -i \
    -d "name=geode-demo-pipeline-postgres" \
    -d "definition=http --server.port=20001|postgres-processor --spring.datasource.driver-class-name=org.postgresql.Driver --spring.datasource.password=app --spring.datasource.url=jdbc:postgresql://postgres.localhost:5432/app --spring.datasource.username=app|log"

curl -H "Content-type: application/json" -X POST "http://localhost:9393/streams/deployments/geode-demo-pipeline-postgres" -i#
