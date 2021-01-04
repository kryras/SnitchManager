<template>
  <footer class="footer">
    <h1>Footer</h1>
  </footer>
</template>

<script>
export default {
  name: "Footer",
};
</script>

<style scoped lang="scss">
.footer {
  position: relative;
  overflow: hidden;
  height: 100px;
}

.footer::after {
  content: "";
  position: absolute;
  z-index: -1;
  left: 0;
  bottom: 0;
  height: 100%;
  width: 100vw;
  border-radius: 50% 50% 0 0/100% 100% 0 0;
  background: linear-gradient(330deg, #462f88 18.56%, #4baec4 85.45%);
  transform: scaleX(2.5);
}
</style>
