<template>
  <v-container grid-list-xl pt-4>
    <v-layout column>
      <h1 id="logo">Bidder</h1>
      <v-flex>
        <p>Bidders är ett av Sveriges ledande sida när det kommer till Auktioner där man kan sälja allt från kläder till leksaker till smycken och mycket mer.
        På Bidders kan du enkelt lägga upp oönskde föremål som vem some helst sen kan hitta och kunna buda på. På Bidders så skänks 5% av vinsten till välgörenhet.</p>
      </v-flex>
        <v-flex>
            <h2>
                Historia
            </h2>
        </v-flex>
        <v-flex>
            <p>Bidders grundades år 2010 av Johan Wiren tillsammans med 3 andra. När vi bestämnde oss för att skapa Bidders visste vi att vi kunde överträffa de andra sidorna på marknaden
             och inom 5 år så blev Bidders deklarerad som Sveriges bästa sida när det kommer till Auktioner. Vi har sedan dess bara blivit bättre och satsar allt på att hålla standarden hög och kundern nöjda. </p>
        </v-flex>
      <v-flex>
        <h3>
          Kontakt
        </h3>
      </v-flex>
      <v-flex>
        <p>Adress: Östra Kanalgatan 5</p>
        <p>Telefonummer: 040-641 63 00</p>
        <p>E-post: info@ecutbildning.se</p>
      </v-flex>
      <v-btn
              v-if="$store.state.loggedIn"
              fab
              dark
              color="teal"
              medium>
        <v-icon dark>create</v-icon>
      </v-btn>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  name: "About",
  data() {
    return {
    };
  }
};
</script>

<style scoped>
    .far {
    font-size: 70px;
    margin-right: 110px;
  }
  button {
    position: fixed;
    bottom: 70px;
    right: 20px;
  }

    #logo {
      font-family: 'Pacifico', cursive;
      font-size: 10vh;
      color: gold;
      text-shadow: 1px 1px 1px teal, 0 0 0.05em teal, 0 0 0.05em teal;
      margin-bottom: 0vh;
    }
</style>
