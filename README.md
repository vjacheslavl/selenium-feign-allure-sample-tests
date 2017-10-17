Selenium / Feign (REST) / JUnit5 sample project
=======================

### What should I know?

- To run tests

        mvn clean verify allure:report
        
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