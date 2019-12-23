module.exports = {
  chainWebpack: config => {
    // 对环境配置，不同的环境对应不同的BASE_URL，以便axios请求不同的地址
    config.plugin('define').tap(args => {
      args[0]['process.env'].BASE_URL = JSON.stringify(process.env.BASE_URL)
      return args
    })
  }
}
