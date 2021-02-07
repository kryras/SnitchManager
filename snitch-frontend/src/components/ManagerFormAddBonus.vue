<template>
  <div class="form-container">
    <button @click="showFormAction" class="button">
      {{ formButtonText }}
    </button>
    <Form
      v-if="!isFormActive"
      class="form"
      @submit="submit"
      :validation-schema="schema"
      v-slot="{ errors }"
    >
      <div class="input-wrapper">
        <label class="label" for="bonus-name">
          Bonus name
          <span class="label-error">{{ errors.name }}</span>
        </label>
        <Field
          class="input"
          v-model="bonus.name"
          name="name"
          as="input"
          placeholder="Bonus name"
          id="bonus-name"
        />
      </div>

      <div class="input-wrapper">
        <label class="label" for="bonus-description">
          Bonus description
          <span class="label-error">{{ errors.description }}</span>
        </label>
        <Field
          class="input"
          v-model="bonus.description"
          name="description"
          as="input"
          placeholder="Description"
          id="bonus-description"
        />
      </div>

      <div class="input-wrapper">
        <label class="label" for="bonus-points">
          Points
          <span class="label-error">{{ errors.points }}</span>
        </label>
        <Field
          class="input"
          v-model="bonus.points"
          name="points"
          as="input"
          placeholder="Points"
          id="bonus-points"
        />
      </div>
      <button class="button">ADD</button>
    </Form>
  </div>
</template>

<script>
import { Form, Field } from "vee-validate";
import { object, string, number } from "yup";

import { repositoryFactory } from "@/services/repositoryFactory";
const bonusesRepository = repositoryFactory.get("bonuses");

export default {
  name: "ManagerFormAddBonus",
  components: { Form, Field },
  data() {
    const schema = object().shape({
      name: string().required(),
      description: string(),
      points: number()
        .typeError("points is a required field")
        .required()
        .positive("must be a positive number")
        .integer()
    });
    return {
      schema,
      bonus: {
        name: "",
        description: "",
        points: ""
      },
      isFormActive: false,
      formButtonText: "",
      errorred: false
    };
  },
  created() {
    this.showFormAction();
  },
  methods: {
    submit(bonus) {
      this.addData(this.bonus);
    },
    async addData(bonus) {
      await bonusesRepository
        .create(bonus)
        .then(() => {
          this.$emit("updateData");
          this.clearInputs();
        })
        .catch(error => {
          console.log(error);
          this.errored = true;
        });
    },
    clearInputs() {
      this.bonus.name = "";
      this.bonus.description = "";
      this.bonus.points = "";
    },
    showFormAction() {
      if (!this.isFormActive) {
        this.formButtonText = "ADD NEW";
      } else {
        this.formButtonText = "HIDE FORM";
      }
      this.isFormActive = !this.isFormActive;
      this.clearInputs();
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
  flex-flow: row wrap;
  justify-content: flex-start;

  .label {
    @include create-label($width-list-input);
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
      justify-content: center;
      margin-right: 5px;
      @media (max-width: $mobile-max-width) {
        width: 90%;
        margin: auto;
      }
    }
  }

  .button {
    @include create-button(
      $border-size,
      $border-radius,
      $input-gradient,
      $input-gradient-inverted,
      $width-list-input,
      $height
    );
    margin-left: auto;
    margin-right: 0px;
  }
}
.button {
  @include create-button(
    $border-size,
    $border-radius,
    $input-gradient,
    $input-gradient-inverted,
    $width-list-input,
    $height
  );
  margin-top: 10px;
  margin-left: auto;
  margin-right: 0px;
}
</style>