<template>
  <header class="header">
    <div
      class="background"
      :class="{ 'nav-background': !bigNavigationBackground }"
    ></div>
    <Navbar />
  </header>
</template>

<script>
import Navbar from "@/components/Navbar.vue";

export default {
  name: "Header",
  components: {
    Navbar,
  },
  data() {
    return {
      bigNavigationBackground: null,
    };
  },
  watch: {
    $route(to, from) {
      if (this.$route.name === "Home") {
        this.bigNavigationBackground = true
      } else {
        this.bigNavigationBackground = false
      }
    },
  },
};
</script>

<style scoped lang="scss">
.header {
  width: 100vw;
}

.background {
  position: absolute;
  overflow: hidden;
  z-index: -100;
  left: 0;
  width: 100vw;
  height: 670px;
  border-radius: 0 0 50% 50%/0 0 100% 100%;
  background: linear-gradient(130deg, #462f88 18.56%, #4baec4 85.45%);
  transform: scaleX(3);
  transition: transform 0.2s ease-in-out;
  @media (max-width: $mobile-max-width) {
    transform: scaleX(4);
    height: 610px;
  }
}

.nav-background {
  transform: translate(0, -302px) scale(2.8, 0.1);
  @media (max-width: $mobile-max-width) {
    transform: translate(0, -275px) scale(2.8, 0.1);
  }
}
</style>
