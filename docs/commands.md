1. Splotless
   1. to check  
      1. $ ``` ../gradlew spotlessCheck```                         // from submodule  
      2. $ ```./gradlew :apps:loan-hub-service:spotlessCheck```   // from root project  
      3. $ ```./gradlew spotlessCheckAll```                       // from root project
      
   2. to apply
      1. $ ```../gradlew spotlessApply```                         // from submodule
      2. $ ```./gradlew :apps:loan-hub-service:spotlessApply```      // from root project
      3. $ ```./gradlew spotlessApplyAll```                          // from root project

2. Tests
   1. $ ```../../gradlew test```                                  // from submodule
   2. $ ```./gradlew :apps:loan-hub-service:test```                // from root

3. Build
   1. $ ```../gradlew build```                                 // from submodule
   2. $ ```./gradlew :apps:loan-hub-service:build```

4. Build Docker image
   1. $ ```../gradlew :apps:loan-hub-service:bootBuildImage```  // from submodule

5. to run docker compose files
   1. to start infra  
      1. $ ```docker compose -f ./infra.yaml  up -d```
      2. $ ```docker compose --profile ui -f ./infra.yaml  ps```  // run ui profile  
         also we can run multiple profiles  ```--profile ui --profile db```

   2. to stop infra  
      1. $ ```docker compose -f ./infra.yaml  down```
      2. $ ```docker compose --profile ui -f ./infra.yaml  ps```