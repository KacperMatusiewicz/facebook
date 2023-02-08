if [ -d "docker/prod" ]
then
  cd docker/prod || exit
  echo "Compose down:"
  docker compose down
  cd ..
  cd ..
fi

echo "Clean:"
mvn clean -Pdocker-image
echo "Package:"
mvn package -Pdocker-image

if [ -d "docker/prod" ]
then
  cd docker/prod || exit
  echo "Compose up:"
  docker compose up
else
    echo "docker/prod not found, cannot compose up "
fi

echo "Done, press any key to continue"
while [ true ] ; do
read -t 3 -n 1
if [ $? = 0 ] ; then
exit ;
fi
done