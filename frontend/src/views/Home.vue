<template>
   <div class="container row">
         <h1 class="col-12 mb-3">Bidder</h1>
            <h3
                    v-if="$store.state.filteredItems.length === 0"
                    key="no-auctions"
            >
               Inga auktioner hittades
            </h3>
            <Auction class="col-xs-6 col-md-6"
                     v-else
                     v-for="(auction, index) in $store.state.filteredItems"
                     :key="index + 0"
                     :auction="auction"
            />
      </div>
</template>
<!--
<transition-group
                  name="animate-route"
                  mode="out-in"
                  enter-active-class="animated fadeIn"
                  leave-active-class="animated fadeOut"
>
</transition-group>
-->


<script>
   // @ is an alias to /src
   import Auction from "@/components/AuctionCard.vue";

   export default {
      name: "home",
      components: {
         Auction
      }
   };
</script>

<style scoped>
   h3 {
      animation-duration: 100ms;
   }
</style>
