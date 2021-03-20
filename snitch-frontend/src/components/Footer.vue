<template>
  <footer class="footer-container">
    <h2 class="footer__title">Snitch Manager</h2>
    <nav>
      <router-link to="/">home</router-link>
      <router-link :to="{ name: 'Ranking' }">ranking</router-link>
      <router-link
        v-if="role === roleAdmin || role === roleModerator || role === roleUser"
        :to="{ name: 'DenunciationList' }"
        >list</router-link
      >
      <router-link :to="{ name: 'TypesList' }">types</router-link>
      <router-link :to="{ name: 'BonusesList' }">bonuses</router-link>
      <router-link :to="{ name: 'About' }">about</router-link>
      <router-link
        v-if="role === roleAdmin"
        style="text-decoration:underline;"
        :to="{ name: 'AdminManageRoles' }"
      >
        manage roles
      </router-link>
      <router-link v-if="user == null" :to="{ name: 'LoginRegister' }">
        login/register
      </router-link>
      <a v-if="user != null" style="cursor: pointer;" @click="logout">logout</a>
    </nav>
    <p class="footer__copyright">Copyright &copy; 2021 RSS</p>
  </footer>
</template>

<script>
import { Role } from "@/helpers/role.js";

export default {
  name: "Footer",
  data() {
    return {
      user: null,
      role: null
    };
  },
  created() {
    this.user = this.$store.state.auth.user;
    if (this.user) {
      this.role = this.user?.userRoles.toString();
    }
    this.$store.watch(
      state => {
        return this.$store.state.auth.user;
      },
      (newValue, oldValue) => {
        this.user = newValue;
        if (this.user) {
          this.role = this.user?.userRoles.toString();
        } else {
          this.role = null;
        }
      },
      {
        deep: true
      }
    );
  },
  computed: {
    roleAdmin() {
      return Role.Admin;
    },
    roleModerator() {
      return Role.Moderator;
    },
    roleUser() {
      return Role.User;
    }
  },
  methods: {
    logout() {
      this.$store.dispatch("auth/logout").then(
        () => {
          console.log("wylogowano");
          this.$router.push({ name: "LoginRegister" });
        },
        error => {
          this.loading = false;
          this.message =
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
        }
      );
    }
  }
};
</script>

<style scoped lang="scss">
.footer-container {
  z-index: 1;
  position: relative;
  overflow: hidden;
  height: 280px;
  margin-top: 100px;
  display: flex;
  flex-flow: column wrap;
  justify-content: center;
  align-items: center;
  &::after {
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
}

nav {
  list-style-type: none;
  display: flex;
  flex-flow: row wrap;
  justify-content: center;
  a {
    padding: $nav-element-padding;
    color: #fff;
    font-size: $nav-font-size;
    text-decoration: none;
  }

  a:hover {
    text-shadow: 0px 0px 3px rgb(75, 174, 195);
  }
}

.footer {
  &__title {
    font-size: 5rem;
    font-weight: 400;
    color: #fff;
    margin-bottom: 60px;
  }
  &__copyright {
    color: #fff;
    position: absolute;
    bottom: 5px;
  }
}

@media all and (max-width: $mobile-max-width) {
  .footer-container {
    height: 350px;
  }
}
</style>



