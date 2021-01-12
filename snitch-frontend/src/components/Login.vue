<template>
  <div class="form-container">
    <Form
      class="form"
      @submit="submit"
      :validation-schema="schema"
      v-slot="{ errors }"
    >
      <h1 class="form-title">Login</h1>
      <label class="label" for="login-username">
        username
        <span class="label-error">{{ errors.username }}</span>
      </label>
      <Field
        class="input"
        v-model="user.username"
        name="username"
        as="input"
        placeholder="username"
        id="login-username"
      />

      <label class="label" for="login-password">
        password
        <span class="label-error">{{ errors.password }}</span>
      </label>
      <Field
        class="input"
        v-model="user.password"
        name="password"
        as="input"
        type="password"
        placeholder="password"
        id="login-password"
      />
      <button class="button">LOGIN</button>
    </Form>
  </div>
</template>

<script>
import { Form, Field } from "vee-validate";
import { object, string } from "yup";
export default {
  name: "Login",
  components: { Form, Field },
  data() {
    const schema = object().shape({
      username: string().required(),
      password: string().required(),
    });
    return {
      schema,
      user: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    submit(user) {
      // console.log("submit: ", JSON.stringify(user));
      console.log("submit: ", JSON.stringify(this.user));
    },
  },
};
</script>

<style scoped lang="scss">
.form-container {
  width: $width;
  height: 100%;
  margin: 0 10px;
}

.form {
  display: flex;
  flex-flow: column nowrap;
  justify-content: center;

  .label {
    @include create-label($width);
  }

  .input {
    @include create-input(
      $width,
      $height,
      $border-size,
      $border-radius,
      $input-gradient
    );
  }

  .button {
    @include create-button(
      $width,
      $height,
      $border-size,
      $border-radius,
      $input-gradient,
      $input-gradient-inverted
    );
  }
}
</style>