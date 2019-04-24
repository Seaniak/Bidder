<template>
  <v-container>
    <router-link :to="'/auction/' + auction.id">
       <v-card>

          <v-img
                  :src="auction.thumbnail ? auction.thumbnail : defaultThumbnail"
                  aspect-ratio="2.75"
          ></v-img>
          <v-card-title primary-title>
                   <div class="col-12">
                      <h3>{{ auction.title }}</h3>
                   </div>
                   <div class="col-6">
                      <h5>Nuvarande bud</h5>
                      <v-icon medium>attach_money</v-icon>
                   </div>
                   <div class="col-6">
                      <h5>{{ getDateString(auction.endTime) }}</h5>
                      <v-icon medium>timelapse</v-icon>
                   </div>
          </v-card-title>
       </v-card>
    </router-link>
  </v-container>

</template>

<script>
  export default {
    name: "AuctionCard",
    data() {
      return {}
    },
     methods: {
        getDateString(auctionTimeStamp)  {
           let bidDate = new Date(auctionTimeStamp);
           return (bidDate.toLocaleDateString() + " " + bidDate.toLocaleTimeString());
        }
     },
    computed: {
      defaultThumbnail() {
        return 'https://cdn.starwebserver.se/shops/coolcard/files/cache/trainermix_grande.jpg?_=1475359673'
      }

    },
    props: ["auction"],
  };
</script>

<style scoped>
  * {
    text-decoration: none;
    color: var(--main-font-color);
  }
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
