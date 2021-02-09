<template>
  <div>
    <div class="form-container">
      <div>
        <h2>Edit role for user with ID: {{ idCopy }}</h2>
      </div>

      <form v-if="downloadComplete" class="form" @submit.prevent="submit">
        <div class="input-wrapper">
          <label class="label" for="role">
            User role
          </label>
          <select class="input" id="role" v-model="selectedId">
            <option v-for="role in res" :key="role.id" :value="role.id">
              {{ role.id }} - {{ role.name }}
            </option>
          </select>
        </div>
        <p class="error-message">{{ errorMessage }}</p>
        <button class="button">SAVE</button>
      </form>
      <button
        v-show="false"
        @click="submit"
        class="button"
        ref="elBtn"
      ></button>
    </div>
  </div>
</template>

<script>
import { repositoryFactory } from "@/services/repositoryFactory";
const userRepository = repositoryFactory.get("user");

export default {
  name: "AdminFormManageRole",
  props: {
    id: {
      type: Number
    }
  },
  data() {
    return {
      req: {
        id: null
      },
      res: null,
      selectedId: null,
      errorred: false,
      errorMessage: "",
      idCopy: null,
      downloadComplete: false
    };
  },
  created() {
    this.idCopy = this.id;
    this.getData();
    this.$emit("clearEditedElement");
  },
  methods: {
    submit() {
      this.errorMessage = "";
      if (!this.selectedId) {
        this.errorMessage = "role is required";
      } else {
        this.addData(this.idCopy, this.selectedId);
      }
    },
    async addData(id, role) {
      await userRepository
        .create(id, role)
        .then(() => {
          this.$emit("updateData");
          this.$emit("closeModal");
        })
        .catch(error => {
          console.log(error);
          this.errored = true;
        });
    },
    async getData() {
      await userRepository
        .getRoles()
        .then(response => {
          this.res = response.data;
          this.downloadComplete = true;
        })
        .catch(error => {
          console.log(error);
          this.errored = true;
        });
    }
  }
};
</script>

<style scoped lang="scss">
.form-container {
  margin: 10px 10px;
  display: flex;
  flex-flow: column wrap;
}

.form {
  width: 100%;
  display: flex;
  flex-flow: column wrap;
  justify-content: flex-start;

  .label {
    @include create-label(100%);
  }

  .input {
    @include create-input(
      $border-size,
      $border-radius,
      $input-gradient,
      auto,
      $height
    );
    &-wrapper {
      display: flex;
      flex-flow: column nowrap;
      margin-right: 5px;
      @media (max-width: $mobile-max-width) {
        width: 90%;
        margin: auto;
      }
    }
  }
  .input-checkbox {
    @include create-input(
      $border-size,
      $border-radius,
      $input-gradient,
      auto,
      auto
    );
    text-align: left;
  }
  .checkbox-label {
    margin-left: 10px;
    font-size: $regular-font-size;
    line-height: 0;
  }
  .checkbox {
    margin: 10px auto 10px 30%;
    @media (max-width: $mobile-max-width) {
      margin: 10px;
    }
  }
  .checkbox-wrapper {
    font-size: $regular-font-size;
    margin-bottom: 0;
  }

  .button {
    @include create-button(
      $border-size,
      $border-radius,
      $input-gradient,
      $input-gradient-inverted,
      100%,
      $height
    );
    @media (max-width: $mobile-max-width) {
      width: 90%;
      margin: auto;
      margin-top: 59px;
    }
    margin-left: auto;
    margin-right: 0px;
  }
}

select {
  text-align: center;
  text-align-last: center;
}
option {
  text-align: left;
}
.error-message {
  margin-bottom: 10px;
}
</style>