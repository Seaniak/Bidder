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
      if (!value) value = "-default-";
      this.getAuctions(value);
    }
  },
  created() {
    this.getAuctions("-default-");
  },
  methods: {
    getAuctions(search) {
      fetch("/api/auctions/search/" + search, {
        method: "POST",
        mode: "cors",
        headers: {
          "Content-Type": "application/json"
        },
        body: search
      })
        .then(res => {
          return res.json();
        })
        .then(res => {
          let searchRes = res.filter(a => new Date(a.endTime) > Date.now());
          this.$store.commit("filterItems", searchRes);
        })
        .catch(e => console.log(e));
    }
  }
};
</script>
<style>
  .primary--text {
    color: lightgray !important;
    caret-color: lightgray !important;
  }
  .v-input--is-label-active {
    color: lightgray !important;
    caret-color: lightgray !important;
  }

  .v-input--is-label-focused {
    color: lightgray !important;
    caret-color: lightgray !important;
  }

  .v-input--is-dirty {
    color: lightgray !important;
    caret-color: lightgray !important;
  }

  .v-text-field{
    color: lightgray !important;
    caret-color: lightgray !important;
  }
</style>
<style scoped>
  .v-text-field{
    color: lightgray !important;
    caret-color: lightgray !important;
  }
  #nav-search{
    max-width: 90% !important;
  }
  .v-input--is-label-active {
    color: lightgray !important;
    caret-color: lightgray !important;
  }

  .v-input--is-label-focused {
    color: lightgray !important;
    caret-color: lightgray !important;
  }

  .v-input--is-dirty {
    color: lightgray !important;
    caret-color: lightgray !important;
  }
</style>
