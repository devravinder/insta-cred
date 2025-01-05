1. 'common-*' projects are common libraries used by other projects
    - this is to avoid code duplication
  
2. AutoConfiguration need to be configured if we are building custom package that requires component scan / configuration
   - see: common-web
           - common-web/src/main/java/com/paravar/instacred/common/web/WebAutoConfiguration
           - resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
3. We are handling Global Exceptions in common-web
