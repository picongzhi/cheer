<template>
  <div class="register">
    <el-form class="register-form" ref="registerForm" :model="registerForm" :rules="rules" label="top">
      <div class="title-container">
        <h3 class="title">用户注册</h3>
      </div>
      <el-form-item class="username" prop="username" label="用户名">
        <el-input v-model="registerForm.username" placeholder="请输入用户名" name="username" type="text"></el-input>
      </el-form-item>
      <el-form-item class="email" prop="email" label="邮箱">
        <el-input v-model="registerForm.email" placeholder="请输入邮箱" name="email" type="text"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="registerForm.password" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPass">
        <el-input type="password" v-model="registerForm.checkPass" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('registerForm')">提交</el-button>
        <el-button @click="resetForm('registerForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { userRegister } from '@/api/user'
import { Message } from 'element-ui'

export default {
  name: 'Register',
  data () {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.registerForm.checkPass !== '') {
          this.$refs.registerForm.validateField('checkPass')
        }
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.registerForm.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      registerForm: {
        username: '',
        email: '',
        password: '',
        checkPass: ''
      },
      rules: {
        username: [
          { required: true, message: '用户名不能为空', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '邮箱不能为空', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' },
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          userRegister({
            username: this.registerForm.username,
            email: this.registerForm.email,
            password: this.registerForm.password
          })
            .then(res => {
              const { data } = res
              Message.info('注册成功')
              this.$router.push('/login')
            })
            .catch(err => {
              Message.error(err)
            })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
      this.$router.push('/register')
    }
  }
}
</script>
import { userRegister } from '@/api/user'
import { Message } from 'element-ui'

<style lang='stylus' scoped>
.register {
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #FFFFFF;

  .register-form {
    width: 300px;
    margin: 10px;

    .title-container {
      .title {
        font-size: 26px;
        text-align: center;
        color: #5F6368;
      }
    }

    .username .email .pass .checkPass {
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
