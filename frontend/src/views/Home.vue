<template>
  <div class="home">
    <v-container>
      <v-layout>
        <v-flex xs12 sm6 md6 class="ml-auto mr-auto">
          <HomeHeader @searchInput="searchInput = $event"/>
          <transition-group
                  name="animate-route"
                  mode="out-in"
                  enter-active-class="animated fadeIn"
                  leave-active-class="animated fadeOut">
            <h3 v-if="$store.state.filteredItems.length === 0" key="no-posts">No posts found</h3>
            <Post v-else v-for="(post, index) in $store.state.filteredItems"
                  :key="index"
                  :post="post"
            />
          </transition-group>
          <v-btn
                  id="upArrow"
                  dark
                  fab
                  color="teal"
                  medium
                  @click="$vuetify.goTo(0)">
            <v-icon dark large>keyboard_arrow_up</v-icon>
          </v-btn>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
  // @ is an alias to /src
  import Post from "@/components/Post.vue";
  import HomeHeader from "@/components/HomeHeader.vue";

  export default {
    name: "home",
    data() {
      return {
        searchInput: ''
      }
    },
       components: {
      Post,
      HomeHeader
    }
  };
</script>

<style scoped>
#upArrow{
  position: fixed;
  bottom: 70px;
  right: 20px;
}
  h3{
    animation-duration: 100ms;
  }

</style>