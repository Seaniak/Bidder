<template>
      <v-container>
         <v-flex >

         <h1>Register account</h1>
         <v-form mx-auto v-model="valid" ref="form">
            <v-text-field
                    placeholder="Enter first name"
                    label="Name"
                    v-model="name"
                    :rules="nameRules"
                    :counter="25"
                    required
            ></v-text-field>
            <v-text-field
                    placeholder="Enter surname"
                    label="Surname"
                    v-model="surname"
                    :rules="surnameRules"
                    :counter="25"
                    required
            ></v-text-field>
            <v-text-field
                    placeholder="Enter username"
                    label="Username"
                    v-model="username"
                    :rules="usernameRules"
                    :counter="20"
                    required
            ></v-text-field>
            <v-text-field
                    placeholder="Enter password"
                    label="Password"
                    v-model="password"
                    :rules="passwordRules"
                    :counter="30"
                    required
                    :type="showPassword ? 'text' : 'password'"
                    :append-icon="showPassword ? 'visibility' : 'visibility_off'"
                    @click:append="showPassword = !showPassword"
            ></v-text-field>
            <v-text-field
                    placeholder="Enter email"
                    label="E-mail"
                    v-model="email"
                    :rules="emailRules"
                    required
            ></v-text-field>
            <v-btn @click="submit" :class="{ red: !valid, green: valid }">submit</v-btn>
            <v-btn @click="clear">clear</v-btn>
         </v-form>
         </v-flex>
      </v-container>
</template>

<script>

   export default {
      name: "RegisterUser",
      data () {
         return {
            valid: false,
            name: '',
            surname: '',
            username: '',
            password: '',
            email: '',
            showPassword: false,
            responseFromDb: '',
            select: null,
            nameRules: [
               (v) => !!v || 'Name is required',
               (v) => v && v.length <= 25 || 'Name must be less than 25 characters'
            ],
            surnameRules: [
               (v) => !!v || 'Surname is required',
               (v) => v && v.length <= 25 || 'Surname must be less than 25 characters'
            ],
            usernameRules: [
               (v) => !!v || 'Username is required',
               (v) => v && v.length <= 20 || 'Username must be less than 20 characters'
            ],
            passwordRules: [
               (v) => !!v || 'Password is required',
               (v) => v && v.length >= 10 || 'Password must be longer than 10 and shorter than 30 characters'
            ],
            emailRules: [
               (v) => !!v || 'E-mail is required',
               (v) => /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(v) || 'E-mail must be valid',
               (v) => v && v.length <= 35 || 'Password must be less than 35 characters'
            ],
         }
      },
      mounted() {
         this.$store.commit('clearResponseFromDb');
      },
      methods: {
         submit () {
            if(this.$refs.form.validate()) {
               this.addUserToDb();
            }
         },
         clear () {
            this.$refs.form.reset()
         },
         addUserToDb() {
            this.$store.commit('addUserToDb', {
               name: this.name,
               surname: this.surname,
               username: this.username,
               password: this.password,
               email: this.email
            });
         }
      }
   }
</script>

<style scoped>
   h1 {
      color: black;
   }
</style>