<template>
  <div ref="app" id="app" class="container">
    <Header />
    <main>
      <router-view />
    </main>
    <Footer />
  </div>
</template>

<script>
import Header from "@/components/Header.vue";
import Footer from "@/components/Footer.vue";

export default {
  name: "App",
  components: {
    Header,
    Footer,
  },
};
</script>

<style lang="scss">
@import url("https://fonts.googleapis.com/css2?family=Maven+Pro:wght@400;700&display=swap");

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

html {
  font-family: "Maven Pro", sans-serif;
  font-size: 62.5%;
}

html,
body {
  width: 100%;
  height: 100%;
}

#app {
  width: 100%;
  height: 100%;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #000;
 }

.container {
  position: relative;
  overflow-x: hidden;
  display: grid;
  grid-template-rows: auto 1fr auto;
}

main {
  margin-top: 25px;
  @media (max-width: $mobile-max-width) {
    margin-top: 75px;
  }
}
</style>
