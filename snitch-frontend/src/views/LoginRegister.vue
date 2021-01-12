<template>
  <div class="container">
    <Login />
    <Register />
  </div>
</template>

<script>
import Login from "@/components/Login.vue";
import Register from "@/components/Register.vue";

export default {
  name: "LoginRegister",
  components: { Login, Register },
};
</script>

<style scoped lang="scss">

.container {
  display: flex;
  flex-flow: row wrap;
  justify-content: space-evenly;
  align-items: flex-start;
  margin-top: 50px;
  margin-bottom: 50px;
  position: relative;
  overflow: hidden;
  &::after {
    content: "";
    position: absolute;
    width: 4px;
    height: 100%;
    top: 14px;
    left: calc(50% - 2px);
    background: $solid-border-color;
    @media (max-width: (($width * 2) + 40px)) {
      width: 0;
    }
  }
}
</style>