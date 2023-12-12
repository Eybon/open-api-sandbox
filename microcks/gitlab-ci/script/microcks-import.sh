#!/bin/bash

echo "microcks-cli import ${MICROCKS_IMPORT_API_FILE} --microcksURL=${MICROCKS_API} --keycloakClientId=${MICROCKS_KEYCLOAK_CLIENT_ID} --keycloakClientSecret=${MICROCKS_KEYCLOAK_CLIENT_SECRET} ${MICROCKS_ADDITIONAL_PARAM}"

microcks-cli import ${MICROCKS_IMPORT_API_FILE} --microcksURL=${MICROCKS_API} --keycloakClientId=${MICROCKS_KEYCLOAK_CLIENT_ID} --keycloakClientSecret=${MICROCKS_KEYCLOAK_CLIENT_SECRET} ${MICROCKS_ADDITIONAL_PARAM} > RESULT.txt;

cat RESULT.txt

if grep -q "Microcks has discovered" RESULT.txt;
then
  echo -e "\e[32mDéploiement de l'API dans Microcks OK\e[0m";
  exit 0;
else
  echo -e "\e[31mDéploiement de l'API dans Microcks KO\e[0m";
  exit 1;  
fi