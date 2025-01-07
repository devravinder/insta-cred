1. We are using outbox pattern to publish the event message to the message queue.
   - this can be enabled or disabled 
     - based on the configuration in application.properties (out-box-pattern-enabled)
   - as we are using outbox pattern, code become more complex.
   - let's understand the code.
     - When a users request for a loan, we'll create LoanRequests ( Entity in table )
     - these LoanRequests should be sent to LoanProcessService through MessageQueue ( as a Event )
     - Creating an Event is handled by LoanRequestEventCreator
         - Creating an Event = creating a message & publishing to queue
     - LoanRequestEventCreator internally handles in two ways
         - directly publishing to queue ( directLoanRequestEventCreatorImpl )
         - using outbox pattern ( abstractPatternLoanRequestEventCreatorImpl )
             - when using outbox pattern, 
                - we'll create LoanRequestEvents ( Entity in table )
                - on regular intervals, we'll publish LoanRequestEvents to MessageQueue 
                   - using cron job : LoanRequestEventPublishJob
               
            

     