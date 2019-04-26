<template>
  <v-container>
    <h1>Logga in</h1>
    <v-form @submit="logIn">
      <v-text-field
              class="mt-5"
              v-model="username"
              label="Användarnamn"
      ></v-text-field>
      <v-text-field
              v-model="password"
              :append-icon="showPassword ? 'visibility' : 'visibility_off'"
              @click:append="showPassword = !showPassword"
              :type="showPassword ? 'text' : 'password'"
              label="Lösenord"
      ></v-text-field>
    </v-form>
  </v-container>
</template>

<script>
  import {eventBus} from "@/main";

  export default {
    name: "login",
    data() {
      return {
        username: "",
        password: "",
        showPassword: false
      };
    },
    created() {
      eventBus.$on("nav-login-clicked", () => this.logIn());
    },
    beforeDestroy() {
      // Removes event listener on component destroy
      eventBus.$off('nav-login-clicked')
    },
    methods: {
      async logIn(e) {
        if (e !== undefined) e.preventDefault();
        this.$router.push({name: "home"});

        let user = {
          username: this.username,
          password: this.password
        };

        const transformRequest = (jsonData = {}) =>
            Object.entries(jsonData)
                .map(x => `${encodeURIComponent(x[0])}=${encodeURIComponent(x[1])}`)
                .join("&");

        let transformedCredentials = transformRequest({
          username: user.username,
          password: user.password
        })

        let loginRes = await fetch("/login", {
          method: "POST",
          body: transformedCredentials,
          headers: {
            "Content-Type": "application/x-www-form-urlencoded"
          }
        })

        if (!loginRes.url.includes("error")) {
          let user = await fetch('/api/remember-me')
          user = await user.json()
          this.$store.commit("loginUser", user);
        }
        // for automatically login on connect
      }
    }
  };
</script>

<style scoped></style>
