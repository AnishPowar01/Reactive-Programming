#!/bin/bash

# Read and parse questions.json
questions=$(cat questions.json | jq -c '.[]')

# Loop through each question
for q in $questions; do
  curl -X POST "http://localhost:8080/api/questions/create" \
       -H "Content-Type: application/json" \
       -d "$q"

  echo "Posted: $q"
done
