<template>
  <v-container>
    <h1>Skapa auktion</h1>
    <form>
      <v-text-field
              label="Titel"
              v-model="title"
              class="upload-input mt-4"></v-text-field>
      <v-textarea
              class="upload-input"
              name="Beskrivning"
              label="Beskrivning"
              v-model="description"
      ></v-textarea>
      <v-textarea
              class="upload-input"
              name="Skick"
              label="Skick"
              v-model="auctionCondition"
      ></v-textarea>
      <v-textarea
              class="upload-input"
              name="Budstart"
              label="Budstart"
              placeholder="0"
              v-model="startSum"
      ></v-textarea>
      <v-textarea
              class="upload-input"
              name="Accepterat pris"
              label="Accepterat pris"
              placeholder="0"
              v-model="reservedSum"
      ></v-textarea>
      <v-layout wrap align-center>
        <v-flex column xs12 sm6>
          <v-select
                  v-model="chosenCategory"
                  :items="categories"
                  label="Kategori"
          ></v-select>
        </v-flex>
      </v-layout>

      <div>
        <h3>Välj sluttid</h3>
        <Datetime
                class="theme-teal"
                type="datetime"
                v-model="endTime"
                input-class="date-input"
                value-zone="Europe/Stockholm"
                zone="Europe/Stockholm"
                format="yyyy-MM-dd HH:mm"
                :phrases="{ok: 'Fortsätt', cancel: 'Avbryt'}"
                :min-datetime="minDatetime"
                :max-datetime="maxDatetime"
                auto
        ></Datetime>
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
  import FileUpload from "@/components/FileUpload";
  import {eventBus} from "@/main";
  import {convertImage} from "@/utilities/ImageConverter";
  import {Datetime} from 'vue-datetime'
  import 'vue-datetime/dist/vue-datetime.css'
  import {DateTime} from "luxon";


  export default {
    components: {
      FileUpload,
      Datetime
    },
    data() {
      return {
        files: [],
        thumbnail: [],
        imagePaths: [],
        thumbnail: "",
        title: "",
        description: "",
        auctionCondition: "",
        categories: ["Fordon", "Teknik", "Konst", "Hus", "Inredning"],
        chosenCategory: "Fordon",
        endTime: DateTime.fromJSDate(new Date()).plus({weeks: 1}).toISO(),
        startSum: null,
        reservedSum: null,
        minDatetime: '' + DateTime.fromJSDate(new Date()),
        maxDatetime: DateTime.fromJSDate(new Date()).plus({months: 1}).toISO()
      };
    },
    created() {
      eventBus.$on("uploadAuctionClicked", () => {
        this.handleSubmit();
      });
    },
    beforeDestroy() {
      // Removes event listener on component destroy
      eventBus.$off("uploadAuctionClicked");
    },
    methods: {
      async handleSubmit() {
        // if no input, don't submit
        if (
            !this.title.length ||
            !this.description.length ||
            !this.auctionCondition.length ||
            (this.reservedSum < this.startSum &&
                this.startSum < 0 &&
                this.endTime > Date.now())
        ) {
          return;
        }

        this.thumbnail = await this.uploadImages(this.thumbnail[0]);

        let imagePaths = [];

        for (let file of this.files) {
          imagePaths.push(await this.uploadImages(file));
        }

        this.postAuction(imagePaths);
        this.$router.push({name: "home"});
      },
      async uploadImages(file) {
        let fileData = new FormData();
        fileData.append("file", file);

        let response = await fetch("/api/upload", {
          method: "POST",
          body: fileData
        });
        response = await response.text();
        return response;
      },
      postAuction(imagePaths) {

        let data = {
          title: this.title,
          description: this.description,
          category: this.chosenCategory,
          auctionCondition: this.auctionCondition,
          startSum: this.startSum,
          reservedSum: this.reservedSum,
          frontEndEndTime: DateTime.fromJSDate(new Date(this.endTime)).toFormat("yyyy-MM-dd HH:mm:ss"),
          username: this.$store.state.currentUser.username,
          thumbnail: this.thumbnail,
          images: imagePaths
        };

        fetch("/api/auctions", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(data)
        })
            // .then(res => {
            //   return res.json();
            // })
            // .then(res => {
            //   this.$store.commit("addAuction", res);
            //   this.$store.commit("filterItems");
            // })
            // .catch(e => console.log(e));
      },
      handleImage(imageData) {
        this.files = imageData.files;
        this.thumbnail = imageData.thumbnail;
      }
    }
  };
</script>

<style>
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

  .upload-input {
    width: 100%;
    font-size: 1.4em;
  }

  .date-input{
    padding: 5px 0;
    font-size: 1.3em;
    text-align: center;
  }
  .vdatetime-popup{
    border-radius: 10px;
  }
  .vdatetime-popup__header{
    border-radius: 10px 10px 0 0;
  }
  .theme-teal .vdatetime-popup__header,
  .theme-teal .vdatetime-calendar__month__day--selected > span > span,
  .theme-teal .vdatetime-calendar__month__day--selected:hover > span > span {
    background: teal;
  }

  .theme-teal .vdatetime-year-picker__item--selected,
  .theme-teal .vdatetime-time-picker__item--selected,
  .theme-teal .vdatetime-popup__actions__button {
    color: teal;
  }
</style>
