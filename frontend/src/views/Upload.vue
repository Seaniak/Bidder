<template>
  <v-container>

    <h1>{{ post ? 'Edit post' : 'Upload Post' }}</h1>
    <form @submit="handleSubmit">
      <v-text-field
              label="Title"
              v-model="title"
              class="mt-4"
      ></v-text-field>
      <v-textarea
              name="Details"
              label="Details"
              v-model="info"
      ></v-textarea>
      <div>
        <div id="postImage">
          <FileUpload @uploadImage="handleImage($event)"/>
        </div>
        <img v-if="imageSrc.length" width="60%" :src="imageSrc" alt="profile picture">
      </div>
      <div id="submitBtnDiv">
        <v-btn dark fab medium color="teal" id="submitBtn" type="submit">
          <v-icon dark large>{{ post ? 'create' : 'add' }}</v-icon>
        </v-btn>
      </div>
    </form>
      <v-btn
              v-if="post"
              dark
              fab
              absolute
              small
              color="red"
              class="remove-btn darken-2"
              @click="deletePost">
        <v-icon dark large>remove</v-icon>
      </v-btn>
  </v-container>
</template>

<script>
  import FileUpload from '@/components/FileUpload'

  export default {
    components: {
      FileUpload
    },
    props: ['post'],
    data() {
      return {
        imageSrc: '',
        title: '',
        info: ''
      }
    },
    mounted() {
      if (this.post) {
        this.imageSrc = this.post.image
        this.title = this.post.title
        this.info = this.post.body
      }
    },
    methods: {
      handleSubmit(e) {
        e.preventDefault()

        if (this.post) {
          this.editPost()
          return;
        }
        // if no input, don't submit
        if (!this.imageSrc.length || !this.title.length || !this.info.length) {
          return;
        }
        this.$store.commit('addPost', {
          title: this.title,
          body: this.info,
          image: this.imageSrc
        })
        this.returnHome()
      },
      editPost() {
        console.log(this.post.id)

        let editedPost = {
          id: this.post.id,
          title: this.title,
          body: this.info,
          image: this.imageSrc
        }

        this.$store.commit('updatePost', editedPost)
        this.returnHome()
      },
      deletePost() {
        this.$store.commit('deletePost', this.post)
        this.returnHome()
      },
      handleImage(imageData) {
        this.imageSrc = imageData
      },
      returnHome(){
        this.$router.push({name: 'home'})
      }
    }
  }
</script>

<style scoped>
  #submitBtnDiv {
    position: fixed;
    display: block;
    right: 20px;
    bottom: 70px;
  }

  .remove-btn {
    bottom: 80px;
  }

  #postTitle, #postInfo, #postImage {
    text-align: start;
    width: 80vw;
    display: flex;
    flex-direction: column;
    margin: 0 auto;
    padding: 0;
  }

  #postImage > h2 {
    display: inline-block;
  }

  h2 {
    padding: 0;
  }

  input {
    width: 100%;
    font-size: 1.1em;
  }

  textarea {
    width: 100%;
    font-size: 1.4em;
  }

  img {
    position: absolute;
    margin-top: -55px;
    right: 20px;
    background-color: whitesmoke;
  }
</style>