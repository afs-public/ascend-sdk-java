#!/bin/bash

set -e

# defaults
MAVEN_CENTRAL_BASE_URL="https://central.sonatype.com/api/v1"
# env vars
AUTH_TOKEN=$SONATYPE_MAVEN_CENTRAL_USER_TOKEN
# inputs
BUNDLE_DIR=$1

if [ -z "$AUTH_TOKEN" ]; then
  echo "Error: Authentication token (SONATYPE_MAVEN_CENTRAL_USER_TOKEN) is not set in the environment."
  exit 1
fi

if [ -z "$BUNDLE_DIR" ]; then
  echo "Error: Path to the bundle staging directory is not provided."
  exit 1
fi

# zip the bundle directory
echo "Zipping the bundle directory: $BUNDLE_DIR"
BUNDLE_FILE_LOCATION=$(mktemp -u).zip

cd $BUNDLE_DIR
zip -r $BUNDLE_FILE_LOCATION com

# upload the bundle
echo "Uploading bundle: $BUNDLE_FILE_LOCATION to $MAVEN_CENTRAL_BASE_URL"

UPLOAD_RESPONSE=$(curl -s -o - -w "HTTPSTATUS:%{http_code}" \
  -X POST \
  --header "Authorization: Bearer $AUTH_TOKEN" \
  --form "bundle=@$BUNDLE_FILE_LOCATION" \
  $MAVEN_CENTRAL_BASE_URL/publisher/upload?publishingType=AUTOMATIC)

HTTP_STATUS=$(echo "$UPLOAD_RESPONSE" | awk -F'HTTPSTATUS:' '{print $2}')
HTTP_BODY=$(echo "$UPLOAD_RESPONSE" | awk -F'HTTPSTATUS:' '{print $1}')

if [ "$HTTP_STATUS" -ne 201 ] || echo "$HTTP_BODY" | jq -e '.error' > /dev/null 2>&1; then
  echo "Error encountered!"
  echo "HTTP Status: $HTTP_STATUS"
  echo "Response Body: $HTTP_BODY"
  exit 1
fi

DEPLOYMENT_ID=$HTTP_BODY
echo "Checking deployment status for ID: $DEPLOYMENT_ID"

# Function to check deployment status
check_deployment_status() {
    local status_response
    status_response=$(curl -s --request POST \
        --header "Authorization: Bearer $AUTH_TOKEN" \
        "$MAVEN_CENTRAL_BASE_URL/publisher/status?id=$DEPLOYMENT_ID")

    local deployment_state
    deployment_state=$(echo "$status_response" | jq -r '.deploymentState')

    echo "Current deployment state: $deployment_state"

    if [ "$deployment_state" = "FAILED" ]; then
        echo "Deployment failed!"
        echo "Full status response:"
        echo "$status_response" | jq '.'
        return 1
    elif [ "$deployment_state" = "PUBLISHING" ]; then
        echo "Deployment in progress..."
        return 0
    elif [ "$deployment_state" = "PUBLISHED" ]; then
        echo "Deployment completed successfully!"
        return 0
    else
        return 2
    fi
}

MAX_ATTEMPTS=10
ATTEMPT=1
SLEEP_TIME=10

while [ $ATTEMPT -le $MAX_ATTEMPTS ]; do
    echo "Checking deployment status (attempt $ATTEMPT of $MAX_ATTEMPTS)..."

    if check_deployment_status; then
        echo "Deployment successful!"
        exit 0
    elif [ $? -eq 1 ]; then
        echo "Deployment failed!"
        exit 1
    fi

    ATTEMPT=$((ATTEMPT + 1))
    echo "Waiting $SLEEP_TIME seconds before next check..."
    sleep $SLEEP_TIME
done

echo "Timeout waiting for deployment to complete"
exit 1
