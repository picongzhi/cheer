# Cheer

## 环境

### 后端

- cheer-server
- JDK1.8
- Maven 管理依赖
- Spring Boot
- 使用 IDEA 开发

### 前端

- cheer-vue
- nvm 管理 node 版本
- npm 管理依赖，使用 taobao 源加速
- webpack 构建工具
- vue-cli3 脚手架工具
- 使用 VSCode 开发

### 版本管理
 - npm install -g commitizen
 - 使用git cz提交
  
### 依赖存储
 - MySQL 
 - Redis 安装: docker run -d -p 6379:6379 redis redis-server --appendonly yes

## 运行

### 后端

使用 IDEA 打开 cheer-server 安装好依赖之后找到 cheer/cheer-server/cheer-server-api/src/main/java/com/pcz/cheer 下面的 Application.java，运行即可

### 前端

- cd cheer-vue
- npm install
- npm run serve
