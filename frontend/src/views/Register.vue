<template>

   <v-container>
      <v-flex v-if="!$store.state.addUserResponseFromDb">
      <RegisterUser />
      </v-flex>
      <v-flex v-else>
      </v-flex>
   </v-container>
</template>

<script>
   import RegisterUser from "../components/RegisterUser";

   export default {
      name: "Register.vue",
      components: {RegisterUser}
   }
</script>

<style scoped></style>
