<template>
  <v-container grid-list-xl pt-4>
    <v-layout column>
      <h1>About me</h1>
      <v-flex xs10 mt-3 ml-auto>
        <img :src="profilePic" alt="profile picture" />
      </v-flex>
      <v-flex>
        <h2>Title</h2>
      </v-flex>
      <v-flex>
        <p>
          lorem bla bla lorem bla bla lorem bla bla lorem bla bla lorem bla bla
          lorem bla bla lorem bla bla lorem bla bla lorem bla bla
        </p>
      </v-flex>
      <v-flex>
        <h3>
          Links and stuff
        </h3>
      </v-flex>
      <v-btn v-if="$store.state.loggedIn" fab dark color="teal" medium>
        <v-icon dark>create</v-icon>
      </v-btn>
    </v-layout>
  </v-container>
</template>

<script>
import profilePic from "../assets/logo.png";

export default {
  name: "About",
  data() {
    return {
      profilePic: profilePic
    };
  }
};
</script>

<style scoped>
img {
  background-color: whitesmoke;
  border-radius: 5px;
}
button {
  position: fixed;
  bottom: 70px;
  right: 20px;
}
</style>
