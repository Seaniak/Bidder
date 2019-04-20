<template>
   <v-container>
         <div @click="seeAuctionDetails">
            <!-- auction.imagePaths -->
            <v-img :src="`https://shopcdn.textalk.se/shop/35510/art10/h3474/132433474-origpic-a14e5f.jpg?max-width=720&max-height=720&quality=85`">
               <div class="row">
                  <div class="column">
                     <h4 class="xs12">{{ auction.title }}</h4>
                     <h4>Nuvarande bud</h4>
                     <h4>{{ auction.createTime }}</h4>
                  </div>
               </div>
            </v-img>
         </div>
   </v-container>

</template>

<script>
   export default {
      name: "AuctionCard",
      data() {
         return {
         }
      },
      props: ["auction"],
      methods: {
         seeAuctionDetails() {
            this.$router.push({
               name: "auctionDetails",
               params: {auction: this.auction}
            });
         }
      }
   };
</script>

<style scoped>
   #auction {
      display: flex;
      text-decoration: none;
      color: var(--main-font-color);
      width: 100%;
      animation-duration: 200ms;
      border-bottom: solid 1px #7c7e70;
   }

   #auction-text {
      display: flex;
      flex-direction: row;
      padding-left: 3%;
      text-align: start;
      flex: 4;
   }

   figure {
      flex: 2;
   }

   img {
      width: 100%;
   }
</style>
