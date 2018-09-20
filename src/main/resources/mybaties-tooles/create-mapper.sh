#生成mapper.xml、mapper.java、table.java 的命令
#前提1：修改finance-config.xml中的目录为自己代码库的目录
#前提2：进入到目录 finance-admin/src/main/resources/mybaties-tooles 下再执行命令

java -jar mybatis-generator-core-1.3.2.jar -configfile  finance-config.xml -overwrite