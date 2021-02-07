<template>
  <div class="form-container">
    <Form
      class="form"
      @submit="submit"
      :validation-schema="schema"
      v-slot="{ errors }"
    >
      <h1 class="form-title">Register</h1>
      <label class="label" for="register-username">
        username
        <span class="label-error">{{ errors.username }}</span>
      </label>
      <Field
        class="input"
        v-model="user.username"
        name="username"
        as="input"
        placeholder="username"
        id="register-username"
      />

      <label class="label" for="register-password">
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
        id="register-password"
      />

      <label class="label" for="register-confirmPassword">
        confirm password
        <span class="label-error">{{ errors.confirmPassword }}</span>
      </label>
      <Field
        class="input"
        v-model="user.confirmPassword"
        name="confirmPassword"
        as="input"
        type="password"
        placeholder="confirm password"
        id="register-confirmPassword"
      />

      <label class="label" for="register-email">
        e-mail
        <span class="label-error">{{ errors.email }}</span>
      </label>
      <Field
        class="input"
        v-model="user.email"
        name="email"
        as="input"
        type="email"
        placeholder="confirm-email"
        id="register-email"
      />

      <label class="label" for="first-name">
        first name
        <span class="label-error">{{ errors.firstName }}</span>
      </label>
      <Field
        class="input"
        v-model="user.firstName"
        name="firstName"
        as="input"
        placeholder="first-name"
        id="first-name"
      />

      <label class="label" for="last-name">
        last name
        <span class="label-error">{{ errors.lastName }}</span>
      </label>
      <Field
        class="input"
        v-model="user.lastName"
        name="lastName"
        as="input"
        placeholder="last-name"
        id="last-name"
      />

      <button class="button">REGISTER</button>
    </Form>
  </div>
</template>

<script>
import { Form, Field } from "vee-validate";
import { object, string, ref } from "yup";
import { mapActions} from 'vuex'
export default {
  name: "register",
  components: { Form, Field },
  data() {
    const schema = object().shape({
      username: string().required(),
      password: string().required(),
      confirmPassword: string()
        .required()
        .oneOf([ref("password")], "passwords do not match"),
      email: string().required().email(),
      firstName: string().required(),
      lastName: string().required()
    });
    return {
      schema,
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        email: "",
        firstName: "",
        lastName: ""
      },
    };
  },
  methods: {
    ...mapActions({
      register: 'auth/register'
    }),
    submit(user) {
      // console.log("submit: ", JSON.stringify(user));
      // console.log("submit: ", JSON.stringify(this.user));
      console.log("aaa");
      this.message = '';
      this.submitted = true;
      this.$store.dispatch('auth/register', user).then(
        data => {
          this.message = data.message;
          this.successful = true;
        },
        error => {
          this.message =
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
          this.successful = false;
        }
      );
    }
  },
};
</script>

<style scoped lang="scss">
.form-container {
  width: $width;
  height: 100%;
  margin: 0 10px;

  @media (max-width: (($width * 2) + 39px)) {
    margin-top: 50px;
  }
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
      $border-size,
      $border-radius,
      $input-gradient,
      $width,
      $height
    );
  }

  .button {
    @include create-button(
      $border-size,
      $border-radius,
      $input-gradient,
      $input-gradient-inverted,
      $width,
      $height
    );
  }
}
</style>