<template>
   <v-text-field
           dark
           id="nav-search"
           label="Sök efter auktioner"
           v-model="searchInput"
   ></v-text-field>
</template>

<script>
   export default {
      name: "HomeNav",
      data() {
         return {
            searchInput: ""
         };
      },
      watch: {
         async searchInput(value) {
            await fetch("/api/auctions/search/" + value, {
               method: "POST",
               mode: "cors",
               headers: {
                  "Content-Type": "application/json"
               },
               body: JSON.stringify(value)
            })
                .then(res => {
                   return res.json()
                })
                .then(res => {
                   console.log(res);
                   let queryCollector = [];
                   res.forEach(auction => (queryCollector.push(auction.title + ' ' + auction.description + ' '
                       + auction.auctionCondition + ' ' + auction.username + ' ' + auction.category + ' ')));
                   let query = '';
                   query.concat(...queryCollector);
                   console.log('Query is: ' + queryCollector);
                   this.$store.commit("filterItems", query);
                })
                .catch(e => console.log(e));
         }
      }
   };
</script>

<style scoped></style>
