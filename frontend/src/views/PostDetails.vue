<template>
  <v-container>
    <figure>
      <img width="100%" :src="post.imageData" alt="poster">
    </figure>
    <h1>{{post.title}}</h1>
    <p>{{post.body}}</p>
    <v-btn
            v-if="$store.state.loggedIn"
            fab
            dark
            color="teal"
            fixed
            medium
            @click="updatePost">
      <v-icon dark>create</v-icon>
    </v-btn>
  </v-container>
</template>

<script>
  export default {
    name: "PostDetails",
    props: ['post'],
    methods: {
      updatePost() {
        this.$router.push({
          name: 'upload',
          params: {post: this.post}
        })
      }
    }
  }
</script>

<style scoped>
  button {
    position: fixed;
    bottom: 70px;
    right: 20px;
  }
</style>