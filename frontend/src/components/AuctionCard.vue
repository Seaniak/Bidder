<template>
  <v-container>
    <router-link :to="'/auction/' + auction.id">
      <!-- auction.imagePaths -->
      <v-img :src="auction.thumbnail ? auction.thumbnail : defaultThumbnail">
        <div class="row">
          <div class="column">
            <h4 class="xs12">{{ auction.title }}</h4>
            <h4>Nuvarande bud</h4>
            <h4>{{ auction.createTime }}</h4>
          </div>
        </div>
      </v-img>
    </router-link>
  </v-container>

</template>

<script>
  export default {
    name: "AuctionCard",
    data() {
      return {}
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
