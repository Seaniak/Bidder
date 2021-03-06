<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-container>
    <v-carousel height="50vh" class="border">
      <v-carousel-item
              v-for="(image, i) in (updateAuction !== undefined) ? updateAuction.images : []"
              :key="i"
              :src="image"
      ></v-carousel-item>
    </v-carousel>
    <h1 v-if="updateAuction !== undefined">{{ updateAuction.title }}</h1>

    <AuctionEnded
            v-if="(updateAuction !== undefined) ? auctionEnded : null"
            :auction="updateAuction"/>

    <v-layout row wrap class="pt-2 justify-content-around">
      <v-card class="border col-5">
        <h4>Högsta Bud</h4>
        <h3 v-if="updateAuction !== undefined">{{ updateAuction.maxBid }}kr</h3>
      </v-card>
      <v-card class="border col-5">
        <h4>Avslutas</h4>
        <AuctionTimeCountDown
                v-if="updateAuction !== undefined"
                :auctionEndTime="updateAuction.endTime"
                @auctionEnded="auctionEnded = $event"/>
      </v-card>
    </v-layout>
    <v-layout row wrap class="pt-2 justify-content-around">
      <v-card
              class="pointer border col-5"
              @click="startChat(updateAuction.username)">
        <h4>Säljare</h4>
        <h3 v-if="updateAuction !== undefined" class="user-bidder"> {{ updateAuction.username }}</h3>
      </v-card>
      <v-card class="border col-5">
        <h4>Kategori</h4>
        <h3 v-if="updateAuction !== undefined">{{ updateAuction.category }}</h3>
      </v-card>
    </v-layout>
    <v-card class="description border py-1 mt-2 col-12">
      <h3>Beskrivning</h3>
      <p v-if="updateAuction !== undefined">{{ updateAuction.description }}</p>
      <p v-if="updateAuction !== undefined && updateAuction.auctionCondition">Varans skick: {{ updateAuction.auctionCondition }}</p>
    </v-card>
    <v-data-table
            :headers="headers"
            :items="(updateAuction !== undefined) ? updateAuction.bids : []"
            :hide-actions="pageControl"
            :must-sort="true"
            :no-data-text="'Inga bud är lagda!'"
            :rows-per-page-text="'Antal bud per sida'"
            :pagination.sync="pagination"
            :rows-per-page-items="pageController"
            class="border elevation-1"
    >
      <template v-slot:items="props">
        <td>{{ props.item.sum }}</td>
        <td class="text-xs-right pointer user-bidder" @click="startChat(props.item.username)">{{ props.item.username }}
        </td>
        <td class="text-xs-right">{{ getBidDateString(props.item.time) }}</td>
      </template>
      <template v-slot:pageText="props">
        Bud {{ props.pageStart }} - {{ props.pageStop }} av {{ props.itemsLength }}
      </template>
    </v-data-table>
    <SellerActiveAuctions :seller="updateAuction.username" :auctionId="updateAuction.id" />
  </v-container>
</template>

<script>
  import AuctionTimeCountDown from "../components/AuctionTimeCountDown";
  import AuctionEnded from '@/components/AuctionEnded'
  import SellerActiveAuctions from '@/components/SellerActiveAuctions'

  export default {
    components: {
      AuctionTimeCountDown,
      SellerActiveAuctions,
      AuctionEnded
    },
    data() {
      return {
        auctionEnded: false,
        pageController: [10, 20, 30, {text: "Alla", value: -1}],
        endDateString: 0,
        pagination: {
          descending: true,
          page: 1,
          rowsPerPage: 10,
          sortBy: "sum"
        },
        headers: [
          {
            text: "Bud",
            align: "left",
            sortable: true,
            value: "sum"
          },
          {text: "Användare", value: "username", sortable: true},
          {text: "Klockslag", value: "time", sortable: true}
        ]
      };
    },
    methods: {
      startChat(recipient) {
        if (this.$store.state.currentUser !== undefined && recipient !== this.$store.state.currentUser.username)
          this.$router.push({
            name: 'chat',
            params: {
              recipient: recipient
            }
          })
      },
      getBidDateString(bidTimeStamp) {
        let bidDate = new Date(bidTimeStamp);
        return bidDate.toLocaleDateString() + " " + bidDate.toLocaleTimeString();
      },
    },
    computed: {
      pageControl() {
        return !(this.updateAuction !== undefined && this.updateAuction.bids.length > 10);
      },
      // auctionEnded() {
      //   let ended = new Date(this.updateAuction.endTime) < new Date()
      //   return ended
      // },
      updateAuction() {
        return this.$store.getters.getAuction(this.$route.params.id);
      },
    },
    async created() {
      if (this.$store.getters.getAuction(this.$route.params.id) === undefined) {
        let auction = await fetch("/api/auctions/" + this.$route.params.id);
        auction = await auction.json();
        this.$store.commit("addAuction", auction);
      }
    }
  };
</script>

<style>
  .user-bidder {
    color: teal;
    font-weight: bold;
  }

  .border {
    border-radius: 4px;
  }

  .description {
    text-align: start;
  }
  .v-carousel__controls{
    border-radius: 0 0 4px 4px;
  }
</style>