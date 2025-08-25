# Read the JSON file and parse it into objects
$questions = Get-Content -Raw -Path "questions.json" | ConvertFrom-Json

foreach ($q in $questions) {
    # Convert object back to JSON string (PowerShell keeps it as an object)
    $json = $q | ConvertTo-Json -Compress

    # Send POST request with JSON body
    curl -Method Post "http://localhost:8080/api/questions/create" `
           -Headers @{ "Content-Type" = "application/json" } `
           -Body $json


    Write-Host "Posted: $json"
}
