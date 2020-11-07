# 简介
通过Agentmain修改已加载的类

通过运行安装程序，枚举已经运行的JVM，逐个判断是不是目标进程，
如果是的话则attach到该JVM实例，加载设置Agent-Class的jar包，加载jar包则会自动调用Agent-Class中的agentmain方法，然后对类进行修改操作即可。

# 模块说明
## core
实现agentmain
```shell script
cd core
mvn assembly:assembly
```
得到`target/core- 1.0-SNAPSHOT-jar-with-dependencies.jar`
## demo
一个持续运行的JVM实例
## installer
attach `demo`进程，加载`core`中的`javaagent`

# 使用
1. 运行`demo`中的`Main`
2. 修改`installer`中`JVMTIThread.java`中的`core-1.0-SNAPSHOT-jar-with-dependencies.jar`为`code`模块生成的jar包路径
3. 运行`installer`中的`JVMTIThread`

## 结果
`Main`输出如下
```text
123
123
123
123
Agent Main called
agentArgs : args1
agentmain load Class  :Account
123
123
123
```
`JVMTIThread`输出如下
```text
Main
92726
ok
```
## 问题
如果多次加载一个jar包，那么class不会重新加载，但是agentmain会调用多次。

[Java VirtualMachine.loadAgent only load agent at once](https://stackoverflow.com/questions/26275503/java-virtualmachine-loadagent-only-load-agent-at-once)

# 参考
[探秘 Java 热部署三（Java agent agentmain](https://www.cnblogs.com/stateis0/p/9062201.html)