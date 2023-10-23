#!/bin/zsh

# get all members
#curl https://api.github.com/orgs/codecentric/members > ./util/cc_members.json

rm ./util/data.sql
touch ./util/data.sql

# iterate over members, only using necessary data
while IFS= read -r LOGIN &&
      IFS= read -r URL &&
      IFS= read -r REPOS_URL; do
  # get employee data
  EMPLOYEE_NAME=$(curl --request GET --url ${URL} --header "Authorization: Bearer ${GH_TOKEN}" | jq ".name")

  if [[ "${EMPLOYEE_NAME}" == "null" ]];
  then
    FINAL_EMPLOYEE_NAME=""
  else
    FINAL_EMPLOYEE_NAME=$EMPLOYEE_NAME
  fi
  echo $FINAL_EMPLOYEE_NAME

  # create employee sql
  echo "INSERT INTO EMPLOYEE VALUES('${LOGIN}', ${FINAL_EMPLOYEE_NAME})" >> ./util/data.sql

  # get repos and respective languages
  EMPLOYEE_REPOS=$(curl --request GET --url ${REPOS_URL} --header "Authorization: Bearer ${GH_TOKEN}")
  echo $EMPLOYEE_REPOS

  # store repo with relation to employee

  # store language

  # store repo/language in relational table

done < <(jq -r '.[] | (.login, .url, .repos_url)' < ./util/cc_members.json)

# clean up
#rm ./util/cc_members.json