<template>
  <div class="form-container">
    <div v-if="editMode">
      <h2>Edit type with id: {{ type.id }}</h2>
    </div>
    <div v-else>
      <h2>Add new</h2>
    </div>

    <Form
      class="form"
      @submit="submit"
      :validation-schema="schema"
      v-slot="{ errors }"
    >
      <div class="input-wrapper">
        <label class="label" for="type-name">
          Type name
          <span class="label-error">{{ errors.name }}</span>
        </label>
        <Field
          class="input"
          v-model="type.name"
          name="name"
          as="input"
          placeholder="Type name"
          id="type-name"
        />
      </div>

      <div class="input-wrapper">
        <label class="label" for="type-description">
          Type description
          <span class="label-error">{{ errors.description }}</span>
        </label>
        <Field
          class="input"
          v-model="type.description"
          name="description"
          as="input"
          placeholder="Description"
          id="type-description"
        />
      </div>

      <div class="input-wrapper">
        <label class="label" for="type-points">
          Points
          <span class="label-error">{{ errors.points }}</span>
        </label>
        <Field
          class="input"
          v-model="type.points"
          name="points"
          as="input"
          placeholder="Points"
          id="type-points"
        />
      </div>
      <button class="button">SAVE</button>
    </Form>
    <button
      v-show="false"
      @click="assignValues"
      class="button"
      ref="elBtn"
    ></button>
  </div>
</template>

<script>
import { Form, Field } from "vee-validate";
import { object, string, number } from "yup";

import { repositoryFactory } from "@/services/repositoryFactory";
const typesRepository = repositoryFactory.get("types");

export default {
  name: "ManagerFormAddType",
  components: { Form, Field },
  props: {
    element: {
      type: Object,
      required: false
    }
  },
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
      type: {
        name: "",
        description: "",
        points: ""
      },
      errorred: false,
      elementCopy: null,
      editMode: false
    };
  },
  created() {
    if (this.element !== null) {
      this.elementCopy = this.element;

      this.editMode = true;
    }
    this.$emit("clearEditedElement");
  },
  mounted() {
    if (this.elementCopy !== null) {
      let el = this.$refs.elBtn;
      el.click();
    }
  },
  methods: {
    submit(type) {
      this.addData(this.type);
    },
    async addData(type) {
      await typesRepository
        .create(type)
        .then(() => {
          this.$emit("updateData");
          this.$emit("closeModal");
        })
        .catch(error => {
          console.log(error);
          this.errored = true;
        });
    },
    assignValues() {
      this.type = this.elementCopy;
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
    max-width: 810px;
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