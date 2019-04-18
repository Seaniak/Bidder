<template>
   <v-container column>
      <h1>Skapa auktion</h1>
      <form ref="form">
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
            <v-flex column xs12 sm6>
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
               <FileUpload @uploadImage="handleImage()"/>
            </div>
            <img width="60%" :src="imageData" alt="auction thumbnail">
         </div>
      </form>
   </v-container>
</template>

<script>
   import FileUpload from '@/components/FileUpload';
   import {eventBus} from "@/main";

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
            startingTime: new Date(Date.now()).getTime(),
            endingTime: new Date(Date.now()).getTime(),
            startingBid: 0,
            acceptedBid: 0,
            userId: this.$store.state.loggedInUserId
         }
      },
      mounted() {
         eventBus.$on('uploadAuctionClicked', () => {
            console.log('Before handle submit');
            this.handleSubmit();
         });
      },
      methods: {
         handleSubmit() {
            //e.preventDefault();
            console.log('Inside handle submit');
            /*!this.title.length || !this.description.length || !this.itemCondition.length ||
                   this.category != null || this.startingBid < 0 || this.acceptedBid < this.startingBid*/
            if (false) {
               console.log('In the handlesubmit if statement');
               return;
            }

            let auctionData = {
            title: this.title,
            description: this.description,
            auction_condition: this.itemCondition,
            category: this.category[0],
            start_sum: this.startingBid,
            reserved_sum: this.acceptedBid,
            create_time: this.startingTime,
            end_time: this.endingTime,
            user_id: this.userId
            };
            console.log(auctionData);

            fetch('/api/auctions', {
               method: 'POST',
               mode: 'cors',
               headers: {
                  "Content-Type": "application/json"
               },
               body: JSON.stringify(auctionData)
            })
                .then(res => {
                   return res.json()
                })
                .then(res => {
                   this.$store.commit('addAuction', res)
                })
                .catch(e => console.log(e));

            this.$router.push({name: 'home'})
         }
      }
      /*         handleImage(imageData) {
                  this.imageData = imageData
               }*/
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