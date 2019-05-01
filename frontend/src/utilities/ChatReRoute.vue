<template>
  <!--
      This route is used to trigger created() hook
      when switching between chats
  -->
</template>

<script>
  export default {
    name: "ChatReRoute",
    props: ['params'],
    created(){
      this.$router.push({
        name: 'chat',
        params: this.params
      })
    }
  }
</script>

<style scoped>

</style>