GCS source

## Introduction

`gcs-source` polls files from the gcs bucket ("enfuse-telemetry-data"). Each file has "HistoryList" inside, that contains array of JSONs. 
Each JSON is converting to POJO, then POJO is converting to avro-message and pushes to kafka-topic.

#### Build

`./gradlew clean build`

#### Running

`./gradlew bootRun`

#### Authentication

- needed access to GCS bucket 
`https://console.cloud.google.com/storage/browser/enfuse-telemetry-data?project=enfuse-gke&authuser=1&supportedpurview=project`

- generate/download a service account key for `dev-791` 
service account `https://console.cloud.google.com/iam-admin/serviceaccounts?authuser=2&orgonly=true&project=enfuse-gke&supportedpurview=organizationId`

- export GOOGLE_CLOUD_KEYFILE_JSON="[PATH]/[FILE_NAME].json"
- export GOOGLE_APPLICATION_CREDENTIALS="[PATH]/[FILE_NAME].json"

For the detailed information, go [here](https://cloud.google.com/docs/authentication/production).


