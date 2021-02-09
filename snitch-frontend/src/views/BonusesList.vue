<template>
  <div class="bonus-container">
    <div>
      <h1>Bonuses list</h1>
    </div>

    <Modal v-if="showModal" @close="showModal = !showModal" :title="title">
      <ManagerFormAddBonus
        @updateData="fetchData"
        @closeModal="showModal = !showModal"
        @clearEditedElement="clearEditedElement"
        :element="singleElement"
      />
    </Modal>
    <button
      v-if="role === roleAdmin || role === roleModerator"
      @click="showModal = !showModal"
      class="button"
      :class="{ visible: showModal }"
    >
      ADD NEW
    </button>
    <section v-if="errored">
      <h2>
        We're sorry, we're not able to retrieve this information at the moment,
        please try back later
      </h2>
    </section>

    <section v-else>
      <div v-if="loading"><h2>Loading...</h2></div>

      <div v-else>
        <CustomArray
          v-if="res !== null && res.length > 0"
          :data="res"
          :entriesPerPage="20"
          :role="role"
          @editEntry="edit"
        />
      </div>
    </section>
  </div>
</template>

<script>
import CustomArray from "@/components/CustomArray.vue";
import ManagerFormAddBonus from "@/components/ManagerFormAddBonus.vue";
import Modal from "@/components/Modal.vue";

import { Role } from "@/helpers/role.js";

import { repositoryFactory } from "@/services/repositoryFactory";
const bonusesRepository = repositoryFactory.get("bonuses");

export default {
  name: "BonusesList",
  components: { CustomArray, ManagerFormAddBonus, Modal },
  data() {
    return {
      title: "Bonus",
      showModal: false,
      res: null,
      errored: false,
      loading: true,
      singleElement: null,
      user: null,
      role: null
    };
  },
  created() {
    this.fetchData();
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
    async fetchData() {
      await bonusesRepository
        .get()
        .then(response => {
          this.res = response.data;
        })
        .catch(error => {
          console.log(error);
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
    async fetchDataById(id) {
      await bonusesRepository
        .getById(id)
        .then(response => {
          this.singleElement = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
    async edit(e) {
      await this.fetchDataById(e);
      this.showModal = true;
    },
    clearEditedElement() {
      this.singleElement = null;
    }
  }
};
</script>

<style scoped lang="scss">
.bonus-container {
  max-width: 1100px;
  margin: auto;
  display: flex;
  flex-flow: column wrap;
  justify-content: center;
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
}

.visible {
  visibility: hidden;
}
</style>