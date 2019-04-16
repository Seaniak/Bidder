<template>
  <v-text-field
          dark
          id="nav-search"
          label="Search for post"
          v-model="searchInput"
  ></v-text-field>
</template>

<script>
  export default {
    name: "HomeNav",
    data(){
      return {
        searchInput: ''
      }
    },
    watch: {
      searchInput(value){
        this.$store.commit('filterItems', value);
      }
    }
  }
</script>

<style scoped>

</style>