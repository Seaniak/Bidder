<template>
   <v-app>
      <v-container>
         <h1></h1>
         <v-form mx-auto v-model="valid" ref="form">
            <v-text-field
                    placeholder="Enter username"
                    label="Username"
                    v-model="username"
                    :rules="nameRules"
                    :counter="10"
                    required
            ></v-text-field>
            <v-text-field
                    placeholder="Enter password"
                    label="Password"
                    v-model="password"
                    :rules="passwordRules"
                    :counter="10"
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
            <v-checkbox
                    label="Do you agree?"
                    v-model="checkbox"
                    :rules="[(v) => !!v || 'You must agree to continue!']"
                    required
            ></v-checkbox>

            <v-btn @click="submit" :class="{ red: !valid, green: valid }">submit</v-btn>
            <v-btn @click="clear">clear</v-btn>
         </v-form>
      </v-container>
   </v-app>
</template>

<script>
   export default {
      name: "RegisterUser",
      data () {
         return {
            valid: false,
            username: '',
            password: '',
            showPassword: false,
            nameRules: [
               (v) => !!v || 'Name is required',
               (v) => v && v.length <= 10 || 'Name must be less than 10 characters'
            ],
            passwordRules: [
               (v) => !!v || 'Password is required',
               (v) => v && v.length <= 10 || 'Password must be less than 10 characters'
            ],
            email: '',
            emailRules: [
               (v) => !!v || 'E-mail is required',
               (v) => /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(v) || 'E-mail must be valid'
            ],
            select: null,
            checkbox: true
         }
      },
      methods: {
         submit () {
            if(this.$refs.form.validate()) {
               this.$store.state.users
            }
         },
         clear () {
            this.$refs.form.reset()
         }
      }
   }
</script>

<style scoped>
   h1 {
      color: black;
   }
</style>