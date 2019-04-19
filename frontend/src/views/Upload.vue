<template>
   <v-container>
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
            <h4>Välj starttid</h4>
            <Datepicker v-model="createTime" format="YYYY-MM-DD H:i:s" width="100%"/>
         </div>
         <div>
            <h4>Välj sluttid</h4>
            <Datepicker v-model="endTime" format="YYYY-MM-DD H:i:s" width="100%"/>
         </div>

         <div>
            <div id="postImage">
               <FileUpload @uploadImage="handleImage($event)"/>
            </div>
            <div v-if="previewImages[0]">
               <img v-for="image of previewImages" width="60%" :src="image" :key="image + 1" alt="profile picture">
            </div>
         </div>
      </form>
   </v-container>
</template>

<script>
   import FileUpload from '@/components/FileUpload';
   import {eventBus} from "@/main";
   import Datepicker from 'vuejs-datetimepicker';

   export default {
      components: {
         FileUpload,
         Datepicker
      },
      data() {
         return {
            previewImages: [],
            files: [],
            title: '',
            description: '',
            auctionCondition: '',
            categories: ['Fordon', 'Teknik', 'Konst', 'Hus', 'Inredning'],
            chosenCategory: '',
            createTime: null,
            endTime: null,
            startSum: null,
            reservedSum: null,
         }
      },
      computed: {},
      mounted() {
         eventBus.$on('uploadAuctionClicked', () => {
            console.log('Before handle submit');
            this.handleSubmit();
         });
      },
      methods: {
         async handleSubmit(e) {
            if (e !== undefined)
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
               category: this.chosenCategory,
               auctionCondition: this.auctionCondition,
               startSum: this.startSum,
               reservedSum: this.reservedSum,
               create_time: this.createTime,
               end_time: this.endTime,
               imagePaths: imagePaths
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