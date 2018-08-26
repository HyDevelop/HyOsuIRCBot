mvn package appassembler:assemble -Ptest

rm -rf ./target/app/webRoot/*.properties

cp ./target/classes/*.properties ./target/app/webRoot/

chmod +x ./target/app/bin/*

cd ./target/app/bin/

nohup ./jboot &

tail -f ./target/app/bin/nohup.out