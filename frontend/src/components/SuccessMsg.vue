<template>
  <v-container>
    <v-card>
      <v-img
        src="https://www.vistaridgeumc.org/wp-content/uploads/sites/111/2016/12/welcome.png"
        aspect-ratio="2.75"
      ></v-img>

      <v-card-title primary-title>
        <div>
          <h3 class="headline mb-0">Bidder</h3>
          <div>{{ successCardText }}</div>
        </div>
      </v-card-title>

      <v-card-actions>
        <router-link to="/">
          <v-btn flat color="orange">Skapa auktion</v-btn>
        </router-link>
        <router-link to="/about">
          <v-btn flat color="orange">Bläddra bland auktioner</v-btn>
        </router-link>
      </v-card-actions>

      <v-alert :value="true" type="success">
        User {{ this.$store.state.addUserResponseFromDb }}
      </v-alert>
    </v-card>
  </v-container>
</template>

<script>
export default {
  name: "SuccessMsg.vue",
  data() {
    return {
      successCardText: "Välkommen till Bidder, må du buda i lorem ipsum."
    };
  }
};
</script>

<style scoped></style>
