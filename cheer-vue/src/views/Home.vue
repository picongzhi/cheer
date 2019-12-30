<template>
  <div class="home">
  </div>
</template>

<script>
import { getUserInfo } from '@/api/user'

export default {
  name: 'home',
  mounted () {
    getUserInfo(1)
  }
}
</script>
