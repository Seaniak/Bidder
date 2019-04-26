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
         async searchInput(value){
            if (!value) value = '#default#';
            console.log(value)
            await fetch("/api/auctions/search/" + value, {
               method: "POST",
               mode: "cors",
               headers: {
                  "Content-Type": "application/json"
               },
               body: JSON.stringify(value)
            })
                .then(res => {
                   console.log(res)
                   return res.json()
                })
                .then(res => {
                   console.log(typeof res);
                   if(!(res.length === 0)) {
                   let searchRes = [];
                   res.forEach(auction => {
                      if (searchRes.includes(auction)){
                         console.log('Auction already exists')
                      } else {
                         searchRes.push(auction);
                      }
                   });
                   this.$store.commit("filterItems", searchRes);
                   }
                })
                .catch(e => console.log(e));
         }
      }
   };
</script>

<style scoped></style>
