// for Run AccountApplication
-Dspring.profiles.active=dev
-Dspring.flyway.enabled=true

//OR

-Dspring.profiles.active=prod
-Dspring.flyway.enabled=false


// for Run tests
-Dspring.profiles.active=test
-Dspring.flyway.enabled=false