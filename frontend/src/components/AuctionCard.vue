<template>
    <v-item-group>
      <v-container grid-list-md>
        <v-layout>
          <v-flex
                  xs12
                  md12
          >
            <v-item>
              <v-card @click="seeAuctionDetails"
                      slot-scope="{ active, toggle }"
                      class="d-flex"
                      light
                      height="200"
              >
                <v-img
                  :src="`https://shopcdn.textalk.se/shop/35510/art10/h3474/132433474-origpic-a14e5f.jpg?max-width=720&max-height=720&quality=85`"
                >
                </v-img>
                <div id="auction-text">
                <h4>{{ auction.title }}</h4>
                <h4>Nuvarande bud</h4>
                <h4>{{ auction.createTime }}</h4>
                </div>
              </v-card>
            </v-item>
          </v-flex>
        </v-layout>
      </v-container>
    </v-item-group>
<!--  <div id="auction" @click="seeAuctionDetails">
    <div id="auction-text">
      <h2>{{ auction.title }}</h2>
    </div>
    <figure>
      <img :src="auction.imageData" alt="thumbnail" />
    </figure>
  </div>-->

</template>

<script>
export default {
  name: "AuctionCard",
  data() {
    return {
      pictureExists: '' //this.auction.imagePaths
    }
  },
  props: ["auction"],
  methods: {
    seeAuctionDetails() {
      this.$router.push({
        name: "auctionDetails",
        params: { auction: this.auction }
      });
    }
  }
};
</script>

<style scoped>
  .no_bg {
    background: coral;
  }

  .bg {
    background: yellow;
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
