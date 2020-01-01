<template>
  <div class="login">
    <el-form class="login-form" ref="loginForm" :model="loginForm" :rules="rules" label="top">
      <div class="title-container">
        <h3 class="title">用户登录</h3>
      </div>
      <el-form-item class="username" prop="username" label="用户名">
        <el-input v-model="loginForm.username" placeholder="请输入用户名或邮箱" name="username" type="text"></el-input>
      </el-form-item>
      <el-form-item class="password" prop="password" label="密码">
        <el-input v-model="loginForm.password" placeholder="请输入密码" name="password" type="password"></el-input>
      </el-form-item>
      <el-form-item label="记住我" prop="rememberMe">
        <el-switch v-model="loginForm.rememberMe" active-color="#13ce66" inactive-color="#EAECF0"></el-switch>
      </el-form-item>
      <el-form-item class="btn-container">
        <el-button class="btn" type="primary" size="small" @click="handleLogin('loginForm')">登录</el-button>
        <router-link to="/register">
          <el-button class="btn" type="primary" size="small">注册</el-button>
        </router-link>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { userLogin } from '@/api/user'
import { Message } from 'element-ui'

export default {
  name: 'Login',
  data () {
    return {
      loginForm: {
        username: '',
        password: '',
        rememberMe: false
      },
      rules: {
        username: [
          { required: true, message: '用户名或邮箱不能为空', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    handleLogin (loginForm) {
      this.$refs[loginForm].validate(valid => {
        if (valid) {
          userLogin(this.loginForm)
            .then(res => {
              const { data } = res
              localStorage.setItem('token', data['token'])

              this.$router.push('/')
            })
            .catch(err => {
              Message.error(err)
            })
        }
      })
    }
  }
}
</script>

<style lang='stylus' scoped>
.login {
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #FFFFFF;

  .login-form {
    width: 300px;
    margin: 10px;

    .title-container {
      .title {
        font-size: 26px;
        text-align: center;
        color: #5F6368;
      }
    }

    .username .password {
      margin: 10px;
    }

    .btn-container {
      display: flex;
      justify-content: center;
      align-items: center;

      .btn {
        width: 100px;
        margin: 0 20px;
      }
    }
  }
}
</style>
