(cd SCDF/source
./gradlew clean build install)

(cd SCDF/transform
./gradlew clean build install)


./tools/destroy-stream.sh

./tools/register-apps.sh

./tools/deploy-stream.sh
