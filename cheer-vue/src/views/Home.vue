<template>
  <div class="home">
    <el-container>
      <el-header>Header</el-header>
      <el-container>
        <el-aside width="300px">Aside</el-aside>
        <el-main>
          <!-- <markdown-editor
            v-model="content"
            height="300px"
          ></markdown-editor> -->
        </el-main>
      </el-container>
    </el-container>

  </div>
</template>

<script>
import { Message } from 'element-ui'
// import MarkdownEditor from '@/components/markdown'
import { getUserInfo } from '@/api/user'

export default {
  name: 'home',
  // components: { MarkdownEditor },
  data () {
    return {
      content: ''
    }
  },
  mounted () {
    getUserInfo().then(res => {
      console.log(res)
      Message.success('hello, ' + res.data.username)
    }).catch(err => {
      Message.error(err.message)
      this.$router.push('/login')
    })
  }
}
</script>

<style lang="stylus" scoped>
.home {
  height: 100%;

  .el-container {
    height: 100%;

    .el-header, .el-footer {
      background-color: #409EFF;
      color: #333;
      text-align: center;
      line-height: 60px;
    }

    .el-container {
      .el-main {
        background-color: #FDE2E2;
        color: #333;
        text-align: center;
        line-height: 200px;
      }

      .el-aside {
        background-color: #E1F3D8;
        color: #333;
        text-align: center;
        line-height: 200px;
      }
    }
  }
}
</style>
