## Weather

### Run application

  * Install Maven from [here](http://maven.apache.org)
  * `git checkout git@github.com:autoschool/weather.git` - checkout application
  * `cd weather` - go to application directory
  * `mvn jetty:run` - run server
  * `http://localhost:8080` - open in browser


### Run unit and integration tests

  * `mvn clean test`

### Run system tests

  *  Install PhantomJS from [here](http://phantomjs.org)
  *  `mvn jetty:run` - run server
  *  run WebTest from IDE

### TODO

 * System test infrastructure