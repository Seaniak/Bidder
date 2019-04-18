<template>
  <v-container>
    <h1>Skapa auktion</h1>
    <form @submit="handleSubmit">
      <v-text-field
              label="Title"
              v-model="title"
              class="mt-4"
      ></v-text-field>
      <v-textarea
              name="Details"
              label="Details"
              v-model="description"
      ></v-textarea>
      <div>
        <div id="postImage">
          <FileUpload @uploadImage="handleImage($event)"/>
        </div>
        <div v-if="previewImages[0]">
          <img v-for="image of previewImages" width="60%" :src="image" :key="image + 1" alt="profile picture">
        </div>
      </div>
      <div id="submitBtnDiv">
        <v-btn dark fab medium color="teal" id="submitBtn" type="submit">
          <v-icon dark large>add</v-icon>
        </v-btn>
      </div>
    </form>
  </v-container>
</template>

<script>
  import FileUpload from '@/components/FileUpload'

  export default {
    components: {
      FileUpload
    },
    data() {
      return {
        previewImages: [],
        files: [],
        title: '',
        description: ''
      }
    },
    computed: {},
    methods: {
      async handleSubmit(e) {
        e.preventDefault()

        // if no input, don't submit
        if (!this.title.length || !this.description.length) {
          return;
        }

        let imagePaths = [];

        // send all images to server
        for (let file of this.files) {
          let fileData = new FormData();
          fileData.append('file', file)

          let response = await fetch('/api/upload', {
            method: 'POST',
            body: fileData
          })

          response = await response.text();

          console.log(response)
          imagePaths.push(response)

          // when all image paths has been received from
          // the server, a call to post is made
          if (imagePaths.length === this.files.length) {
            this.postAuction(imagePaths)
            this.$router.push({name: 'home'})
          }
        }
      },
      postAuction(imagePaths) {
        // let data = new FormData();
        // data.append('title', this.title);
        // data.append('description', this.description);
        // data.append('imagePaths', imagePaths);

        let data = {
          title: this.title,
          description: this.description,
          category: 'default',
          imagePaths: imagePaths
        }

        fetch('/api/auctions', {
          method: 'POST',
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(data)
        })
            .then(res => {
              return res.json()
            })
            .then(res => {
              this.$store.commit('addAuction', res)
              this.$store.commit('filterItems')
            })
            .catch(e => console.log(e))
      },
      handleImage(imageData) {
        this.previewImages = imageData.previewImages
        this.files = imageData.files
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