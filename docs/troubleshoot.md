### redis issues
1. connection issue
   - keep connections details under spring.data.redis
2. Serialization issue
   - if we are using redis as cache, then the catchable object should be marked as Serializable
      - the return type of methods ( eg: PagedResult, LoanType)
   - if we are using redis as data storage...then marks the entity as Serializable
3. Caused by: io.lettuce.core.RedisCommandExecutionException: ERR Unknown subcommand or wrong number of arguments for 'SETINFO'. Try CLIENT HELP.
    - this occurs when 'SETINFO' redis command s executed & client information is missing.
          - the redis server may not be stable version... so no version information is available.
          - try to use different redis version
          - as of now ```redis/redis-stack-server:7.2.0-v13-x86_64``` docker image is working fine
    - or from java side skip the setting of client information
        - ref: [GitHub Issue](https://github.com/redis/lettuce/issues/2817#issuecomment-2085523946)
       

### Jpa issues
1. @EnableJpaRepositories & @EntityScan 
  - if we are not setting explicitly then only the @SpringBootApplication packages will be scanned for entities & repositories
  - but if we set only the mentioned packages will be scanned & SpringBootApplication package will be ignored
  - so if we are using @EnableJpaRepositories & @EntityScan in child modules & also use them in SpringBootApplication package
    - then only both packages will be scanned for entities & repositories

