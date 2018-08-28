# Spring Boot JDBC CRUD Demo

This is a Spring Boot application to show a simple jdbc CRUD example.
This application uses Spring's JDBCTemplate to execute sql statements.

This application has 'default' profile settings. 
These are used when running the application locally, standalone. 

When running this application on Cloud Foundry, the application uses settings from 'cloud' profile.


### CI/CD pipleine

The ci/cd pipeline is in the `ci` folder.

If for some reason the scripts do not have execute permissions set, 
please run the following in `ci/` folder.

``` 
find . -name '*.sh' | xargs git update-index --chmod=+x
``` 