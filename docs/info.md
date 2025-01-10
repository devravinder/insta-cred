1. 'common-*' projects are common libraries used by other projects
    - this is to avoid code duplication
  
2. AutoConfiguration need to be configured if we are building custom package that requires component scan / configuration
   - see: common-web
           - common-web/src/main/java/com/paravar/instacred/common/web/WebAutoConfiguration
           - resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
3. We are handling Global Exceptions in common-web

### Imps
1. Custom JPA queries & converting to specific object see: ProcessedEventRepository ( common-jpa )
2. Rabbit MQ
   - to send message to multiple queues
      - use pattern with topic exchange while binding queues to exchange
         - Note: patten ( wild cards ) is used only for queue listeners ( consumers )
         - Note: if we add more than one consumer to same queue, only one consumer will receive the message
               - to receive message in multiple consumers, use different queues for each consumer 
               - which are bind to same exchange with wild card binding key
               - in our app, approved.loan.requests.queue.1, approved.loan.requests.queue.2 are queues
        
      - while sending message to exchange use routing key which matches the pattern
         - Note: patten ( wild cards ) should not be used while sending/publishing message to exchange
         - wild cards are meant for queue listeners
      
      - i.e 
         - queue-listeners, binding key: 'approved.loan.requests.#'
         - queue-publisher, routing key: 'approved.loan.requests.key'
      - in our app: ( for approved loans )
         - queue-listeners are : loan-hub-service & cred-score-service
         - queue-publisher is: loan-process-service
   - we can also use Fan-out exchange to send message to multiple queues
