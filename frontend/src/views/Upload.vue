<template>
  <v-container>
    <h1>Skapa auktion</h1>
    <form>
      <v-text-field
              label="Titel"
              v-model="title"
              class="mt-4"
      ></v-text-field>
      <v-textarea
              name="Beskrivning"
              label="Beskrivning"
              v-model="description"
      ></v-textarea>
      <v-textarea
              name="Skick"
              label="Skick"
              v-model="auctionCondition"
      ></v-textarea>
      <v-textarea
              name="Budstart"
              label="Budstart"
              placeholder="0"
              v-model="startSum"
      ></v-textarea>
      <v-textarea
              name="Accepterat pris"
              label="Accepterat pris"
              placeholder="0"
              v-model="reservedSum"
      ></v-textarea>
      <v-layout wrap align-center>
        <v-flex column xs12 sm6>
          <v-select v-model="chosenCategory"
                    :items="categories"
                    label="Kategori"
          ></v-select>
        </v-flex>
      </v-layout>

      <div>
        <h4>Välj sluttid</h4>
        <Datepicker v-model="endTime" format="YYYY-MM-DD H:i:s" width="100%"/>
      </div>

      <div>
        <div id="postImage">
          <FileUpload @uploadImage="handleImage($event)"/>
        </div>
      </div>
    </form>
  </v-container>
</template>

<script>
  import FileUpload from '@/components/FileUpload';
  import {eventBus} from "@/main";
  import Datepicker from 'vuejs-datetimepicker';
  import {convertImage} from "@/utilities/ImageConverter";

  export default {
    components: {
      FileUpload,
      Datepicker
    },
    data() {
      return {
        files: [],
        thumbnail: [],
        imagePaths: [],
        thumbnail: '',
        title: '',
        description: '',
        auctionCondition: '',
        categories: ['Fordon', 'Teknik', 'Konst', 'Hus', 'Inredning'],
        chosenCategory: 'Fordon',
        endTime: null,
        startSum: null,
        reservedSum: null,
      }
    },
    created() {
      eventBus.$on('uploadAuctionClicked', () => {
        this.handleSubmit();
      });
    },
    beforeDestroy() {
      // Removes event listener on component destroy
      eventBus.$off('uploadAuctionClicked')
    },
    methods: {
      async handleSubmit() {
        // if no input, don't submit
        if (!this.title.length || !this.description.length || !this.auctionCondition.length ||
            this.reservedSum < this.startSum && this.startSum < 0 && this.endTime > Date.now()) {
          return;
        }

        this.thumbnail =  await this.uploadImages(this.thumbnail[0])

        let imagePaths = [];

        for (let file of this.files) {
          imagePaths.push(await this.uploadImages(file))
        }

        this.postAuction(imagePaths);
        this.$router.push({name: 'home'})
      },
      async uploadImages(file) {

        let fileData = new FormData();
        fileData.append('file', file)

        let response = await fetch('/api/upload', {
          method: 'POST',
          body: fileData
        })
        response = await response.text()
        console.log(response);
        return response
      },
      postAuction(imagePaths) {

        let data = {
          title: this.title,
          description: this.description,
          category: this.chosenCategory,
          auctionCondition: this.auctionCondition,
          startSum: this.startSum,
          reservedSum: this.reservedSum,
          frontEndEndTime: this.endTime,
          username: this.$store.state.currentUser.username,
          thumbnail: this.thumbnail,
          images: imagePaths
        };
        console.log(data);

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
              console.log(res)
              this.$store.commit('addAuction', res)
              this.$store.commit('filterItems')
            })
            .catch(e => console.log(e))
      },
      handleImage(imageData) {
        this.files = imageData.files
        this.thumbnail = imageData.thumbnail
      }
    }
  }
</script>

<style scoped>
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