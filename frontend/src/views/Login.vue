<template>
  <v-container>
    <h1>Log in</h1>
    <v-form @submit="logIn">
      <v-text-field
              class="mt-5"
              v-model="username"
              label="Username"
      ></v-text-field>
      <v-text-field
              v-model="password"
              :append-icon="showPassword ? 'visibility' : 'visibility_off'"
              @click:append="showPassword = !showPassword"
              :type="showPassword ? 'text' : 'password'"
              label="Password"
      ></v-text-field>
      <v-btn
              class="mt-5"
              type="submit"
      >
        Log in
      </v-btn>
    </v-form>
  </v-container>
</template>

<script>
  export default {
    name: "login",
    data() {
      return {
        username: '',
        password: '',
        showPassword: false
      }
    },
    methods: {
      logIn(e) {
        e.preventDefault()
        this.$router.push({name: 'home'})

        let user = {
          username: this.username,
          password: this.password
        }

        this.$store.commit('getUser', user)
      }
    }
  }
</script>

<style scoped>

</style>