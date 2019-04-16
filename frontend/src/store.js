import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    profilePicture: '',
    loggedIn: false,
    filteredItems: [],
    posts: []
  },
  mutations: {
    filterItems(state, searchInput = '') {
      let filter = new RegExp(searchInput, "i")
      state.filteredItems = state.posts.filter(p => p.title.match(filter) || p.body.match(filter))
    },
    setProfilePicture(state, value) {
      state.profilePicture = value;
    },
    addPost(state, value) {
      fetch('/api/posts', {
        method: 'POST',
        mode: 'cors',
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(value)
      })
          .then(res => {
            return res.json()
          })
          .then(res => {
            state.posts.push(res)
          })
          .catch(e => console.log(e))
    },
    updatePost(state, value) {
      for (let post of state.posts)
        if (post.id === value.id) {
          let index = state.posts.indexOf(post)
          state.posts[index] = value
        }

      fetch('/api/posts/' + value.id, {
        method: 'PUT',
        mode: 'cors',
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(value)
      })
          .catch(e => console.log(e))

      console.log('Updating post: ', value.id)

    },
    deletePost(state, value) {
      for (let post of state.posts)
        if (post.id === value.id) {
          let index = state.posts.indexOf(post)
          state.posts.splice(index, 1);
        }

      fetch('/api/posts/' + value.id, {
        method: 'DELETE',
        mode: 'cors',
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(value)
      })
          .catch(e => console.log(e))

      console.log('Deleting post: ', value.id)

    },
    logOut(state, value) {
      fetch('/logout')
          .then(res => {
            if (res.url.includes('logout')) {
              state.loggedIn = value
            }
          })
    },
    getPosts(state, value) {
      state.posts = value
      console.log(state.posts)
    },
    getUser(state, user) {
      console.log('Fetching user')

      const transformRequest = (jsonData = {}) =>
          Object.entries(jsonData)
              .map(x => `${encodeURIComponent(x[0])}=${encodeURIComponent(x[1])}`)
              .join('&');

      fetch('/login', {
        method: "POST",
        body: transformRequest({username: user.username, password: user.password}),
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      })
          .then(function (response) {
            console.log(response);

            let successfulLogin = !response.url.includes("error");
            console.log("the login result is: ", successfulLogin);

            state.loggedIn = successfulLogin;
          });

    }
  },
  actions: {
    getPosts(context) {
      console.log('Fetching posts')
      fetch('/api/posts')
          .then((res) => {
            return res.json();
          })
          .then((res) => {
            context.commit('getPosts', res)
            context.commit('filterItems')
          })
          .catch(e => console.log(e))
    },
  }
})
