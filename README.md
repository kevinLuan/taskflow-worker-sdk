### Taskflow worker SDK 
taskflow-worker-sdk
============
<div align="left">
  <a href="javascript:void(0);"><img src="https://img.shields.io/badge/build-passing-brightgreen" /></a>
  <a href="javascript:void(0);" target="_blank"><img src="https://img.shields.io/badge/docs-latest-brightgreen" /></a>
  <a href="https://www.apache.org/licenses/LICENSE-2.0"><img src="https://img.shields.io/badge/License-Apache%202.0-blue.svg" alt="License"></a>
  <a href="https://javadoc.io/doc/cn.taskflow/taskflow-worker-sdk/latest/index.html" target="_blank"><img src="https://javadoc.io/badge/cn.taskflow/taskflow-worker-sdk/0.0.1.svg" /></a>
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


### 代码生成

#### JSON 数据
```json
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

### 官网：https://www.taskflow.cn

### 商户批量付款前须知
    BOSS 开工端通过商户号+商户订单号标识唯一订单。
    付款限制：单笔最低 10 元，单笔最高 9.8 万元，每人/月不超过 9.8 万元。
    微信付款每人/天限 10 笔，每人单笔限制 2w，单日 2w 限额。
    建议采用同批次多笔订单下发方式，尽量少使用一批次一笔订单方式下发。
    测试环境为了方便测试，付款状态下发金额分那一位 0 为处理中，奇数失败，偶数成功（例：100 处理中 ，101 失败， 102 成功）。
    同步返回结果仅代表系统已接收到通信请求，不能作为交易结果处理；交易结果请以“异步通知批量付款结果”或“商户批量付款查询”接口的交易状态为准。
    为保障交易结果及时安全，异步通知由于网络等各种原因可能延时或异常，请务必对接付款查询接口并以查询的最终交易状态为准。如有退票，退票状态一般是 T+1 或者 T+2 返回，特殊情况可能会在 T+0 返回。我方将第一时间联系您更新最新状态。
    注意备注中不能包含的敏感词：工资、薪酬、提现、薪、补贴、分红、奖金、返现、劳务费、分润、备用金、咨询等。
    注意 resCode6000 错误，6000 错误可能是因为超时引起的
    付款手机号需与签约时一致