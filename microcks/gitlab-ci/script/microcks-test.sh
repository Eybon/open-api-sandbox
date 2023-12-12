#!/bin/bash

echo "microcks-cli test '${MICROCKS_TEST_API_NAME}' ${MICROCKS_TEST_API_ENDPOINT} OPEN_API_SCHEMA --microcksURL=${MICROCKS_API} --waitFor=5sec --keycloakClientId=${MICROCKS_KEYCLOAK_CLIENT_ID} --keycloakClientSecret=${MICROCKS_KEYCLOAK_CLIENT_SECRET} ${MICROCKS_ADDITIONAL_PARAM}"

microcks-cli test 'API Formation:1.0.0' ${MICROCKS_TEST_API_ENDPOINT} OPEN_API_SCHEMA --microcksURL=${MICROCKS_API} --waitFor=5sec --keycloakClientId=${MICROCKS_KEYCLOAK_CLIENT_ID} --keycloakClientSecret=${MICROCKS_KEYCLOAK_CLIENT_SECRET} ${MICROCKS_ADDITIONAL_PARAM} > RESULT.txt;

cat RESULT.txt

if grep -q "success: true" RESULT.txt;
then
  echo -e "\e[32mLes Tests via Microcks sont OKs\e[0m";
  exit 0;
else
  echo -e "\e[31mLes Tests via Microcks sont KOs\e[0m";
  exit 1;  
fi