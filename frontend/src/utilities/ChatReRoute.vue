<template>
  
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