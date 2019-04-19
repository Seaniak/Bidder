<template>
  <v-container>
    <v-flex>
      <h1>Register account</h1>
      <v-form mx-auto v-model="valid" ref="form">
        <v-text-field
          placeholder="Skriv in förnamn"
          label="Namn"
          v-model="name"
          :rules="nameRules"
          :counter="25"
          required
        ></v-text-field>
        <v-text-field
          placeholder="Skriv in efternamn"
          label="Efternamn"
          v-model="surname"
          :rules="surnameRules"
          :counter="25"
          required
        ></v-text-field>
        <v-text-field
          placeholder="Skriv in användarnamn"
          label="Användarnamn"
          v-model="username"
          :rules="usernameRules"
          :counter="20"
          required
        ></v-text-field>
        <v-text-field
          placeholder="Skriv in lösenord"
          label="Lösenord"
          v-model="password"
          :rules="passwordRules"
          :counter="30"
          required
          :type="showPassword ? 'text' : 'password'"
          :append-icon="showPassword ? 'visibility' : 'visibility_off'"
          @click:append="showPassword = !showPassword"
        ></v-text-field>
        <v-text-field
          placeholder="Skriv in e-mail"
          label="E-mail"
          v-model="email"
          :rules="emailRules"
          required
        ></v-text-field>
        <v-btn @click="clear">Rensa formulär</v-btn>
      </v-form>
    </v-flex>
  </v-container>
</template>

<script>
import { eventBus } from "@/main";

export default {
  name: "RegisterUser",
  data() {
    return {
      valid: false,
      name: "",
      surname: "",
      username: "",
      password: "",
      email: "",
      showPassword: false,
      responseFromDb: "",
      select: null,
      nameRules: [
        v => !!v || "Du måste fylla i namn",
        v => (v && v.length <= 25) || "Max 25 tecken"
      ],
      surnameRules: [
        v => !!v || "Du måste fylla i efternamn",
        v => (v && v.length <= 25) || "Max 25 tecken"
      ],
      usernameRules: [
        v => !!v || "Du måste fylla i användarnamn",
        v => (v && v.length <= 20) || "Max 20 tecken"
      ],
      passwordRules: [
        v => !!v || "Du måste fylla i ett lösenord",
        v => (v && v.length >= 10) || "Minst 10 och max 25 tecken"
      ],
      emailRules: [
        v => !!v || "Du måste fylla i e-mail",
        v =>
          /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) ||
          "E-mail måste vara korrekt ifylld",
        v => (v && v.length <= 35) || "Max 35 tecken"
      ]
    };
  },
  mounted() {
    this.$store.commit("clearResponseFromDb");
    eventBus.$on("submitRegisterClicked", () => {
      this.submit();
    });
  },
  methods: {
    submit() {
      if (this.$refs.form.validate()) {
        this.addUserToDb();
      }
    },
    clear() {
      this.$refs.form.reset();
    },
    addUserToDb() {
      let user = {
        name: this.name,
        surname: this.surname,
        username: this.username,
        password: this.password,
        email: this.email
      };

      fetch("/api/register", {
        method: "POST",
        mode: "cors",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(user)
      })
        .then(res => {
          console.log(res);
          return res.json();
        })
        .then(res => {
          console.log(res);
          this.$store.commit("addUserToDb", res.message);
        })
        .catch(e => console.log(e));
      this.$router.push({ name: "registerSuccess" });
    }
  }
};
</script>

<style scoped>
h1 {
  color: black;
}
</style>
