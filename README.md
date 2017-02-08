# blue-green

Webapp used for testing and demo purposes.

This is a "Hello world" style app that can be used as a sample application for testing and demoing. It simply displays a
 page tha can be either "blue" or "green".

Possible use cases is testing/demoing:

* Deployments on various platforms
* Blue-green/canary deployment
* CI/CD pipelines
* and anything you else you might think of

Default mode is "blue". To start in "green" mode you can pass in a command line argument `color=green`.

I.e. on linux/OSX

    $> blue-green-0.0.1-SNAPSHOT.jar --color=green
    
with `java -jar`:

    $> java -jar blue-green-0.0.1-SNAPSHOT.jar --color=green

