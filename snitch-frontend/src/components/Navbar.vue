<template>
  <div class="navigation__container">
    <nav class="logo  ">
      <img src="@/assets/logo.png" alt="LOGO" class="logo" />
    </nav>
    <nav>
      <input type="checkbox" id="drop" />
      <label for="drop" class="toggle">
        <div class="first-bar"></div>
        <div class="second-bar"></div>
        <div class="third-bar"></div>
      </label>
      <ul class="menu">
        <li><router-link to="/">home</router-link></li>
        <li><router-link :to="{ name: 'Ranking' }">ranking</router-link></li>
        <li>
          <label for="drop-1" class="toggle">denunciation</label>
          <label for="drop-1" class="toggle-desktop">denunciation</label>
          <input type="checkbox" id="drop-1" />
          <ul>
            <li
              v-if="
                role === roleAdmin ||
                  role === roleModerator ||
                  role === roleUser
              "
            >
              <router-link :to="{ name: 'DenunciationList' }">list</router-link>
            </li>
            <li>
              <router-link :to="{ name: 'TypesList' }">types</router-link>
            </li>
            <li>
              <router-link :to="{ name: 'BonusesList' }">bonuses</router-link>
            </li>
          </ul>
        </li>
        <li><router-link :to="{ name: 'About' }">about</router-link></li>
        <li v-if="role === roleAdmin">
          <router-link
            style="text-decoration:underline;"
            :to="{ name: 'AdminManageRoles' }"
          >
            manage roles
          </router-link>
        </li>

        <li>
          <router-link v-if="user == null" :to="{ name: 'LoginRegister' }">
            login/register
          </router-link>
        </li>
        <li>
          <a v-if="user != null" style="cursor: pointer;" @click="logout"
            >logout</a
          >
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
import { Role } from "@/helpers/role.js";

export default {
  name: "Navbar",
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
.navigation__container {
  position: relative;
  z-index: 100;
  display: flex;
  flex-flow: row nowrap;
  justify-content: space-between;
  align-content: center;
  margin: auto;
  min-width: $content-min-width;
  max-width: $content-max-width;

  a.router-link-exact-active {
    text-shadow: 0px 0px 4px rgb(75, 174, 195);
  }
}

.toggle,
[id^="drop"] {
  display: none;
}

.language {
  &-desktop {
    display: block;
    ul {
      list-style: none;
    }
  }
  &-mobile {
    display: none;
  }
}

.lang {
  padding: $nav-element-padding;
  color: #000;
  font-size: $nav-font-size;
  cursor: pointer;
}

nav {
  a,
  .toggle-desktop {
    display: block;
    padding: $nav-element-padding;
    color: #fff;
    font-size: $nav-font-size;
    text-decoration: none;
  }

  a:hover,
  .lang:hover {
    text-shadow: 0px 0px 3px rgb(75, 174, 195);
  }

  ul {
    li {
      display: block;
      float: left;
    }

    ul {
      display: none;
      position: absolute;
      border: solid 1px $solid-border-color;
      border-radius: $border-radius;
      background-color: #fff;
      overflow: auto;
    }
  }
}

nav ul li:hover > ul {
  display: inherit;
}

nav ul ul li {
  min-width: 150px;
  float: none;
  display: list-item;
  position: relative;

  a {
    color: #000;
  }
}

li > label:after {
  content: " ▾";
}

.logo {
  display: block;
  width: 20px;
  height: 20px;
  margin: auto auto auto 10px;
}

@media all and (max-width: $mobile-max-width) {
  .logo {
    @media (max-width: $mobile-max-width) {
      display: none;
    }
  }

  nav {
    margin: 0;
    position: fixed;

    a {
      color: #000;
    }
  }

  .toggle + a,
  .menu {
    display: none;
    background-color: #fff;
  }

  .toggle {
    box-sizing: border-box;
    width: 100vw;
    display: block;
    padding: $nav-element-padding;
    color: #fff;
    font-size: $nav-font-size;
    text-decoration: none;
    &-desktop {
      display: none !important;
    }
  }

  #drop:checked ~ label.toggle {
    background-color: #fff;
    color: #000;
    border-bottom: 2px solid $solid-border-color;
  }
  li > label.toggle {
    color: #000;
  }

  .toggle:hover {
    text-shadow: 0px 0px 3px rgb(75, 174, 195);
  }

  [id^="drop"]:checked ~ ul {
    display: block;
  }

  nav {
    ul {
      max-height: 80vh;
      position: fixed;
      overflow: auto;
      border-bottom: 2px solid $solid-border-color;
      border-radius: 0 0 $border-radius $border-radius;

      ul {
        float: none;
        position: static;
      }

      li {
        display: block;
        width: 100vw;
      }
    }

    label {
      cursor: pointer;
    }

    div {
      border-radius: 3px;
      margin: 2px auto;
      width: 30px;
      height: 4px;
      background-color: #fff;
      border: 1px solid $solid-border-color;
      box-shadow: 0px 0px 3px rgb(75, 174, 195);
      transition: transform 0.2s ease-in-out;
    }
  }

  nav ul ul li:hover > ul,
  nav ul li:hover > ul {
    display: none;
  }

  nav ul ul li {
    display: block;
    width: 100%;
  }

  #drop:checked ~ label > div {
    background-color: #000;
    border: none;
    box-shadow: none;

    &.first-bar {
      transform: translate(0, 6px) rotate(225deg) scaleY(0.8);
      transform-origin: center;
    }
    &.second-bar {
      transform: scale(0);
    }
    &.third-bar {
      transform: translate(0, -6px) rotate(-225deg) scaleY(0.8);
      transform-origin: center;
    }
  }
}
</style>
