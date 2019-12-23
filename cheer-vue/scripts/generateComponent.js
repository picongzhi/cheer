const chalk = require('chalk')
const path = require('path')
const fs = require('fs')

const resolve = (...file) => path.resolve(__dirname, ...file)
const log = message => console.log(chalk.green(`${message}`))
const successLog = message => console.log(chalk.blue(`${message}`))
const errorLog = error => console.log(chalk.red(`${error}`))

const {
  vueTemplate,
  entryTemplate
} = require('./template')

const generateFile = (path, data) => {
  if (fs.existsSync(path)) {
    errorLog(`${path}文件已存在`)
    return
  }

  return new Promise((resolve, reject) => {
    fs.writeFile(path, data, 'utf8', err => {
      if (err) {
        errorLog(err.message)
        reject(err)
      } else {
        resolve(true)
      }
    })
  })
}

const dotExistDirectoryCreate = (directory) => {
  return new Promise((resolve) => {
    mkdirs(directory, () => {
      resolve(true)
    })
  })
}

const mkdirs = (directory, callback) => {
  var exists = fs.existsSync(directory)
  if (exists) {
    callback()
  } else {
    mkdirs(path.dirname(directory), () => {
      fs.mkdirSync(directory)
      callback()
    })
  }
}

log('请输入要生成的组件名称，如需生成全局组件，请加global/前缀')
let componentName = ''
process.stdin.on('data', async chunk => {
  const inputName = String(chunk).trim().toString()
  const componentDirectory = resolve('../src/components', inputName)
  const componentVueName = resolve(componentDirectory, 'main.vue')
  const entryComponentName = resolve(componentDirectory, 'index.js')

  const hasComponentDirectory = fs.existsSync(componentDirectory)
  if (hasComponentDirectory) {
    errorLog(`${inputName}组件目录已经存在，请重新输入`)
    return
  } else {
    log(`正在生成component目录 ${componentDirectory}`)
    await dotExistDirectoryCreate(componentDirectory)
  }

  try {
    if (inputName.includes('/')) {
      const inputArr = inputName.split('/')
      componentName = inputArr[inputArr.length - 1]
    } else {
      componentName = inputName
    }

    log(`正在生成vue文件 ${componentVueName}`)
    await generateFile(componentVueName, vueTemplate(componentName))

    log(`正在生成entry文件 ${entryComponentName}`)
    await generateFile(entryComponentName, entryTemplate)
    successLog('生成成功')
  } catch (e) {
    errorLog(e.message)
  }

  process.stdin.emit('end')
})

process.stdin.on('end', () => {
  log('exit')
  process.exit()
})
