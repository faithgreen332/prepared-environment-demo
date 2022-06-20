* redis,rabbitmq,nacos,mysql,seata-server 本地都是单实例，因为太耗内存了，只是测试，正式是集群
* 疫情期间可能在家里办公，配置文件，比如 application-dev1/dev2.yaml，dev1的是家里，dev2是办公室测试环境

---

|master|node1|node2|node3|
|---|---|---|---|
|redis-6.2.6|nacos-2.0.4|rabbitmq|docker:mysql-8.0.28,seata-server|

*其中nacos2.0.3的版本单实例只能用内置数据库，阿里自己说的打包打错了*
