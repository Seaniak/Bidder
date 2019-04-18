<template>
  <v-container column>
    <h1>Skapa auktion</h1>
    <form @submit="handleSubmit">
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
              v-model="itemCondition"
      ></v-textarea>
      <v-textarea
              name="Budstart"
              label="Budstart"
              placeholder="0"
              v-model="startingBid"
      ></v-textarea>
      <v-textarea
              name="Accepterat pris"
              label="Accepterat pris"
              placeholder="0"
              v-model="acceptedBid"
      ></v-textarea>
      <v-layout wrap align-center>
        <v-flex column xs12 sm6 >
          <v-select
                  :items="category"
                  ref="selectedCategory"
                  label="Kategori"
          ></v-select>
        </v-flex>
      </v-layout>
      <div>
        <div id="postImage">
        <h4>Lägg till bild</h4>
          <FileUpload @uploadImage="handleImage($event)"/>
        </div>
        <img v-if="imageData" width="60%" :src="imageData" alt="profile picture">
      </div>

      <div id="submitBtnDiv">
      <h4>Lägg upp auktion</h4>
        <v-btn dark fab medium color="teal" id="submitBtn" type="submit">
          <v-icon dark large>add</v-icon>
        </v-btn>
      </div>
    </form>
  </v-container>
</template>

<script>
  import FileUpload from '@/components/FileUpload';
  import { eventBus } from "@/main";

  export default {
    components: {
      FileUpload
    },
    data() {
      return {
        imageData: null,
        title: '',
        description: '',
        itemCondition: '',
        category: ['Fordon', 'Teknik', 'Konst', 'Hus', 'Inredning'],
        startingTime: new Date(),
        endingTime: new Date(),
        startingBid: null,
        acceptedBid: null
      }
    },
    mounted() {
        eventBus.$on('uploadAuctionClicked', () => {
          this.submit();
        });
    },
    methods: {
      handleSubmit(e) {
        e.preventDefault();

        // if no input, don't submit
        if (!this.title.length || !this.description.length || !this.itemCondition.length ||
                this.category != null || this.startingBid < 0 || this.acceptedBid < this.startingBid) {
          return;
        }

        let data = new FormData();
        data.append('title', this.title);
        data.append('description', this.description);
        data.append('auction_condition', this.itemCondition);
        data.append('category', this.$refs.selectedCategory.innerText);
        data.append('start-sum', this.startingBid);
        data.append('reserved-sum', this.acceptedBid);
        data.append('create-time', this.startingTime);
        data.append('end-time', this.endingTime);
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
            .catch(e => console.log(e));

        this.$router.push({name: 'home'})
      },
      handleImage(imageData) {
        this.imageData = imageData
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