#!/usr/bin/env sh
HOST_PORT=27017
DOCKER_IMAGE_TAG=mongo:4.4.6
CONTAINER_NAME=petclinic-mongo

if ! docker start "$CONTAINER_NAME" > /dev/null 2> /dev/null;
then
  docker run -d -p $HOST_PORT:27017 --name "$CONTAINER_NAME" "$DOCKER_IMAGE_TAG"
fi
