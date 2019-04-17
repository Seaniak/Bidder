import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    profilePicture: '',
    loggedIn: false,
    openNavDrawer: null,
    filteredItems: [],
    auctions: [],
    addUserResponseFromDb: ''
  },
  mutations: {
    filterItems(state, searchInput = '') {
      let filter = new RegExp(searchInput, "i")
      state.filteredItems = state.auctions.filter(p => p.title.match(filter) || p.body.match(filter))
    },
    openDrawer(state, value){
      state.openNavDrawer = value;
    },
    setProfilePicture(state, value) {
      state.profilePicture = value;
    },
    addPost(state, value) {
      fetch('/api/auctions', {
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
            state.auctions.push(res)
          })
          .catch(e => console.log(e))
    },
    addUserToDb(state, value) {
      fetch('/api/register', {
        method: 'POST',
        mode: 'cors',
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(value)
      })
          .then(res => {
            console.log(res);
            return res.json()
          })
          .then(res => {
            console.log(res);
            state.addUserResponseFromDb = res.message
          })
          .catch(e => console.log(e))
    },
    clearResponseFromDb(state, value = '') {
      state.addUserResponseFromDb = value;
    },
    updatePost(state, value) {
      for (let post of state.auctions)
        if (post.id === value.id) {
          let index = state.auctions.indexOf(post)
          state.auctions[index] = value
        }


      fetch('/api/auctions/' + value.id, {
        method: 'PUT',
        mode: 'cors',
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(value)
      })
          .catch(e => console.log(e))

      console.log('Updating auction: ', value.id)

    },
    deletePost(state, value) {
      for (let post of state.auctions)
        if (post.id === value.id) {
          let index = state.auctions.indexOf(post)
          state.auctions.splice(index, 1);
        }

      fetch('/api/auctions/' + value.id, {
        method: 'DELETE',
        mode: 'cors',
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(value)
      })
          .catch(e => console.log(e))

      console.log('Deleting auction: ', value.id)

    },
    logOut(state, value) {
      fetch('/logout')
          .then(res => {
            if (res.url.includes('logout')) {
              state.loggedIn = value;
            }
          })
    },
    getAuctions(state, value) {
      state.posts = value;
      console.log(state.auctions)
    },
    getUser(state, user) {
      console.log('Fetching user');

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
      console.log('Fetching auctions')
      fetch('/api/auctions')
          .then((res) => {
            return res.json();
          })
          .then((res) => {
            context.commit('getAuctions', res)
            context.commit('filterItems')
          })
          .catch(e => console.log(e))
    },
  }
})
