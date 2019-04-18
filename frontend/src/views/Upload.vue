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
          <FileUpload @uploadImage="handleImage($event)" />
        </div>
        <img v-if="imageData" width="60%" :src="imageData" alt="profile picture">
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
import FileUpload from "@/components/FileUpload";

  export default {
    components: {
      FileUpload
    },
    data() {
      return {
        imageData: null,
        title: '',
        description: ''
      }
    },
    mounted() {
    },
    editPost() {
      console.log(this.post.id);

        // if no input, don't submit
        if (!this.title.length || !this.description.length) {
          return;
        }

        let data = new FormData();
        data.append('title', this.title);
        data.append('description', this.description);
        data.append('files', this.imageData);

        fetch('/api/auctions', {
          method: 'POST',
          mode: 'cors',
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
            })
            .catch(e => console.log(e))

        this.$router.push({name: 'home'})
      },
      handleImage(imageData) {
        this.imageData = imageData
      }
    }
  }
};
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

#postTitle,
#postInfo,
#postImage {
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
