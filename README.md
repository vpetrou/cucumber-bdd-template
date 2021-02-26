<h1>TEMPLATE E2E Tests</h1>

<h4>1. Preparation</h4>

<b>&emsp;Prerequisite Software:</b>

- [JAVA JDK 1.8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)
- [Apache MAVEN](https://maven.apache.org/download.cgi)

<b>&emsp;Frameworks/Tools that are used:</b>
- Selenium WebDriver
- Cucumber BDD
- REST Assured API
- Maven
- Zalenium
                    
<h4>3. Configuration of E2E Tests</h4>

Prior the execution of the E2E Tests, a configuration file must be updated accordingly. 
Navigate to <b>/src/main/resources/</b> and update the content of the file "env.properties": 

    # Environment URLs Configuration
    WebUI.URL=https://www.test.com
    WebUI.API.URL=https://www.test.com/rest/v1
    
    # Users
    Admin.USERNAME=admin
    Admin.PASSWORD=admin
    # Timeout and Interval in seconds
    TIMEOUT=180
    INTERVAL=5
    
    # WebDriver Configuration
    BROWSER=chrome
    DRIVER.REMOTE.URL=http://localhost:4444/wd/hub
    HEADLESS.MODE=false

Variable DRIVER.REMOTE.URL refers to Zalenium or Selenium Grid hub URL. If empty then execution is done locally. 
E.g. setting value for the variable DRIVER.REMOTE.URL to http://localhost:4444/wd/hub the tests are executed remotely.
For Zalenium there is a monitoring console to see the tests running: http://localhost:4444/grid/admin/live

For local execution, webdriver aligned with the installed Chrome version must be placed in /src/test/resources/drivers/
        
<h4>4. Execution of E2E Tests<h4>

The execution of E2E Tests can be done via local machine or via Jenkins and Zalenium. For the execution of the tests below command is required:

      mvn clean install

There are 2 arguments that could be used

<b>-Denv.properties</b> to define a different configuration file.<br>Default is "src/main/resources/env.properties"

<b>-Dcucumber.filter.tags</b> to define specific tagged scenarios or features for execution.<br>If empty, then a full execution is done.<br>Example:
-Dcucumber.filter.tags="@TAG-1234"

<h4>4. Reporting of E2E Tests<h4>

After the execution of the tests, a target folder is generated.
<br>Sub-folder cucumber-html-reports contains the test execution results.
<br>File "feature-overview.html" must be opened to view the results.

