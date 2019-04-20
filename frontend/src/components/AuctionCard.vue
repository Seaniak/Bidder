<template>
   <div @click="seeAuctionDetails">
            <!-- auction.imagePaths -->
            <img class="img img-fluid" :src="`https://shopcdn.textalk.se/shop/35510/art10/h3474/132433474-origpic-a14e5f.jpg?max-width=720&max-height=720&quality=85`">

                     <h4 >{{ auction.title }}</h4>
                     <h4>Nuvarande bud</h4>
                     <h4>{{ auction.createTime }}</h4>
   </div>

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
