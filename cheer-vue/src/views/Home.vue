<template>
  <div class="home">
    <markdown-editor v-model="content" height="300px"></markdown-editor>
  </div>
</template>

<script>
import { Message } from 'element-ui'
import MarkdownEditor from '@/components/markdown'
import { getUserInfo } from '@/api/user'

export default {
  name: 'home',
  components: { MarkdownEditor },
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
