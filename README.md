# 简易 ATM 系统

一个基于 Java 和 Swing 的简易 ATM 客户端与服务端示例，包含登录、存款、取款、转账与查询等功能。UI 采用统一样式与布局，支持独立启动的本地服务端。

## 功能
- 登录/退出
- 存款、取款、转账
- 操作完成后账户查询与操作日志展示
- 统一 UI 风格（Nimbus + 自定义样式）

## 目录结构
- `src/` 源代码
  - `com.simple.atm.server` 服务端入口与处理策略（`BankCenterServer`）
  - `com.simple.atm.ui` 客户端 Swing 界面（`LoginFrame`、`MainFrame`、`DepositFrame`、`WithdrawFrame`、`TransferFrame`、`QueryFrame`）
  - `com.simple.atm.client` 客户端 Socket 通信
  - `com.simple.atm.service` 业务服务
  - `com.simple.atm.dao` 内存数据存储
  - `com.simple.atm.entity` 实体定义
  - `com.simple.atm.util.Config` 服务端地址与端口

## 环境与配置
- JDK 8+（任意更高版本均可）
- 端口与地址：见 `src/com/simple/atm/util/Config.java`，默认 `HOST=127.0.0.1`，`PORT=9090`

## 编译与运行
可直接在 IDE 中运行，也可使用命令行编译。

### 方式一：IDE 运行
1. 运行服务端：`com.simple.atm.server.BankCenterServer` 的 `main`（文件位置：`src/com/simple/atm/server/BankCenterServer.java:82`）
2. 运行客户端：`com.simple.atm.ui.LoginFrame` 的 `main`（文件位置：`src/com/simple/atm/ui/LoginFrame.java:76`）

### 方式二：命令行编译与运行（Windows PowerShell）
```powershell
# 在项目根目录执行
mkdir bin
javac -encoding UTF-8 -d bin (Get-ChildItem -Recurse -Filter *.java src | % FullName)

# 启动服务端
java -cp bin com.simple.atm.server.BankCenterServer
# 另开一个终端启动客户端（UI）
java -cp bin com.simple.atm.ui.LoginFrame
```
## 备注
- 服务端与客户端均为示例实现，数据存储使用内存；如需接入数据库。
- 若端口被占用，可修改 `Config.PORT` 或关闭占用端口的进程。

