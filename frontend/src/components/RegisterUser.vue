<template>
  <v-container>
    <v-flex>
      <h1>Skapa nytt konto</h1>
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
      <v-alert class="mt-3" :value="successMessage" type="success">{{ successMessage }}</v-alert>
      <v-alert class="mt-3" :value="errorMessage" type="error">{{ errorMessage }}</v-alert>
    </v-flex>
  </v-container>
</template>

<script>
import { eventBus } from "@/main";

export default {
  name: "RegisterUser",
  data() {
    return {
      errorMessage: '',
      successMessage: '',
      valid: false,
      name: "",
      surname: "",
      username: "",
      password: "",
      email: "",
      showPassword: false,
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
          /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.timeLeft(v) ||
          "E-mail måste vara korrekt ifylld",
        v => (v && v.length <= 35) || "Max 35 tecken"
      ]
    };
  },
  mounted() {
    eventBus.$on("submitRegisterClicked", () => {
      this.submit();
    });
  },
  beforeDestroy() {
    // Removes event listener on component destroy
    eventBus.$off('submitRegisterClicked')
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
          return res.text();
        })
        .then(res => {
          console.log(res);
          if(res === 'success'){
            this.errorMessage = '';
            this.successMessage = 'Nytt konto registrerat!'
            setTimeout(()=>{
              this.$router.push({ name: "login" });
            }, 3000)
          } else {
            this.errorMessage = 'Användarnamnet är upptaget'
          }
        })
        .catch(e => console.log(e));
    }
  }
};
</script>

<style scoped>
h1 {
  color: black;
}
  .error-message{
    color: red;
  }
</style>
