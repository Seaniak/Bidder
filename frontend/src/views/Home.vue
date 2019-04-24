<template>
   <div >
      <transition-group class="container row"
              name="animate-route"
              mode="out-in"
              enter-active-class="animated fadeIn"
              leave-active-class="animated fadeOut"
      >
         <h1 class="col-12 mb-3" key="keyNeededToPreventError">Bidder</h1>
         <h3
                 v-if="$store.state.filteredItems.length === 0"
                 key="no-auctions"
         >
            Inga auktioner hittades
         </h3>
         <AuctionCard class="col-12 col-md-4"
                      v-else
                      v-for="(auction, index) in $store.state.filteredItems"
                      :key="index + 0"
                      :auction="auction"
         />
      </transition-group>
   </div>
</template>

<script>
   // @ is an alias to /src
   import AuctionCard from "@/components/AuctionCard.vue";

   export default {
      name: "home",
      components: {
         AuctionCard
      }
   };
</script>

<style scoped>
   h3 {
      animation-duration: 100ms;
   }
</style>
