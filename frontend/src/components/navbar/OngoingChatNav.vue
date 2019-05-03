<template>
  <v-text-field
      dark
    id="nav-search"
    label="Sök användare"
    v-model="searchInput"
  ></v-text-field>
</template>

<script>
  import {eventBus} from "@/main";

  export default {
  name: "HomeNav",
  data() {
    return {
      searchInput: ""
    };
  },
  watch: {
    async searchInput(value) {
      eventBus.$emit('search-chat-recipient', value)
    }
  },
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
