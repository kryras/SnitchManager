<template>
  <div
    class="backdrop"
    @click.self="closeModal"
    tabindex="0"
    @keyup.escape="closeModal"
  >
    <div class="modal">
      <h1>{{ title }}</h1>
      <slot></slot>
      <button @click="closeModal" @keyup.escape="closeModal" class="close">
        CLOSE
      </button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    title: {
      type: String,
      required: false
    }
  },
  methods: {
    closeModal() {
      this.$emit("close");
    },
    saveInput() {
      console.log("modal input save");
      this.$emit("close");
    }
  }
};
</script>

<style scoped lang="scss">
.modal {
  min-width: min(900px, calc(70%));
  max-width: 1100px;
  max-height: 70%;
  padding: 20px;
  margin: 20px;
  background: white;
  border-radius: $border-radius;
  overflow-y: auto;
  z-index: 1000;
}
.backdrop {
  top: 0;
  left: 0;
  position: fixed;
  background: rgba(0, 0, 0, 0.5);
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-shrink: 0;
}

.modal {
  button {
    @include create-button(
      $border-size,
      $border-radius,
      $input-gradient,
      $input-gradient-inverted,
      $width-list-input,
      $height
    );
    &.close {
      border: solid 1px $solid-border-color;
      background: white;
      color: black;
      margin-top: 59px;
      &:hover,
      &:focus {
        color: white;
      }
    }
  }
}
</style>