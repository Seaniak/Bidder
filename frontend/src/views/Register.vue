<template>

   <v-container>
      <v-flex v-if="!$store.state.addUserResponseFromDb">

      <RegisterUser />
      </v-flex>
      <v-flex v-else>
         <SuccessMsg />
      </v-flex>
   </v-container>
</template>

<script>
   import RegisterUser from "../components/RegisterUser";
   import SuccessMsg from "../components/SuccessMsg";

   export default {
      name: "Register.vue",
      components: {SuccessMsg, RegisterUser}
   }
</script>

<style scoped>

</style>