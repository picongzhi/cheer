<template>
  <div class="home">
    <markdown-editor
      v-model="content"
      height="300px"
    ></markdown-editor>
  </div>
</template>

<script>
import MarkdownEditor from '@/components/markdown'

export default {
  name: 'home',
  components: { MarkdownEditor },
  data () {
    return {
      content: ''
    }
  },
  mounted () {
  }
}
</script>
