Selenium / Feign (REST) / JUnit5 sample project
=======================

### What should I know?

- To run any unit tests that test your Selenium framework you just need to ensure that all unit test file names end, or start with "test" and they will be run as part of the build.
- The maven failsafe plugin has been used to create a profile with the id "selenium-tests".  This is active by default, but if you want to perform a build without running your selenium tests you can disable it using:

        mvn clean verify -P-selenium-tests
        
- The maven-failsafe-plugin will pick up any files that end in IT by default.  You can customise this is you would prefer to use a custom identifier for your Selenium tests.

### Anything else?

Configuration defined in ApplicationProperties.java

You can specify environment to run
-Dapplication.env=local
-Dapplication.env=dev

Yes you can specify which browser to use by using one of the following switches:

- -Dbrowser=firefox
- -Dbrowser=chrome
- -Dbrowser=ie
- -Dbrowser=edge
- -Dbrowser=safari
- -Dbrowser=phantomjs
- -Dbrowser=opera

You can specify a grid to connect to where you can choose your browser, browser version and platform:

- -Dapplication.remotDriver=true 
- -DseleniumGridURL=http://{username}:{accessKey}@ondemand.saucelabs.com:80/wd/hub 
- -Dplatform=xp 
- -Dbrowser=firefox 
- -DbrowserVersion=44

You can even specify multiple threads (you can do it on a grid as well!):

- -Dthreads=2

You can also specify a proxy to use

- -Dapplication.proxyEnabled=true
- -Dapplication.proxyHost=localhost
- -Dapplication.proxyPost=8080

If the tests fail screenshots will be saved in ${project.basedir}/target/screenshots (TBD)