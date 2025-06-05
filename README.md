### Taskflow worker SDK 
taskflow-worker-sdk
============
<div align="left">
  <a href="javascript:void(0);"><img src="https://img.shields.io/badge/build-passing-brightgreen" /></a>
  <a href="javascript:void(0);" target="_blank"><img src="https://img.shields.io/badge/docs-latest-brightgreen" /></a>
  <a href="https://www.apache.org/licenses/LICENSE-2.0"><img src="https://img.shields.io/badge/License-Apache%202.0-blue.svg" alt="License"></a>
  <a href="https://javadoc.io/doc/cn.taskflow/taskflow-worker-sdk/latest/index.html" target="_blank"><img src="https://javadoc.io/badge/cn.taskflow/taskflow-worker-sdk/0.0.6.svg" /></a>
</div>

## 简介
taskflow-worker 集成 SDK

### 快速开始

#### 添加依赖
### Maven dependency
```xml
<dependency>
    <groupId>cn.taskflow</groupId>
    <artifactId>taskflow-worker-sdk</artifactId>
    <version>latest</version>
</dependency>
```


### 示例代码

```java
/*开发者自有的私钥*/
String devPrivateKey = "...";
/*平台公钥*/
String platformPublicKey = "...";
String basePath = "http://localhost:8083/api";
String keyId = "197203879aa";
String keySecret = "916f3ba4d7d044cdaf4a596fa882b533";
TaskflowClient taskflowClient = new TaskflowClient(basePath, keyId, keySecret, devPrivateKey, platformPublicKey);
```


## 许可证

[Apache 2.0 许可证](https://www.apache.org/licenses/LICENSE-2.0)

### 文档
[飞流云官方文档](https://docs.taskflow.cn/) <br/>
[身边云文档](https://docs.taskflow.cn/guide/integration/sby/) <br/>
[youjiayun文档](https://docs.taskflow.cn/guide/integration/yjy/)
