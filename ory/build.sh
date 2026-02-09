if [ -f "openapi.jar" ]; then
    echo "✓ openapi.jar already exists, skipping download"
else
    echo "↓ Downloading openapi.jar..."
    wget https://repo1.maven.org/maven2/org/openapitools/openapi-generator-cli/7.19.0/openapi-generator-cli-7.19.0.jar -O openapi.jar
fi

# Generate code
echo "⚙ Generating OpenAPI client..."
java -jar openapi.jar generate \
  -g kotlin \
  -i ./openapi-1-3-8.yaml \
  -o ./shared/src/commonMain/kotlin \
  -p packageName=org.openapitools.client \
  -p dateLibrary=kotlinx-datetime \
  -p serializationLibrary=kotlinx_serialization \
  -p library=multiplatform \
  -p httpClient=ktor \
  -p generateModelDocumentation=false \
  -p generateApiDocumentation=false \
  -p ignoreUnknownProperties=false \
  -p useOneOfDiscriminatorLookup=false \
  --skip-validate-spec

echo "Fixing duplicate @Serializable annotations..."

find shared/src/commonMain/kotlin/src/commonMain/kotlin/org/openapitools/client/models/ -name "*.kt" -type f | while read file; do
    # Replace @Serializable@Serializable with single @Serializable
    sed -i 's/@Serializable@Serializable/@Serializable/g' "$file"
    sed -i 's/sealed classsealed class/sealed class/g' "$file"
    sed -i 's/abstract abstract/abstract/g' "$file"
done

echo "Fixed duplicate annotations in model files"
