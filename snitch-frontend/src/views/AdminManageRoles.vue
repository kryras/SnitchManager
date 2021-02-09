<template>
  <div class="admin-container">
    <div>
      <h1>{{ title }}</h1>
    </div>

    <Modal v-if="showModal" @close="showModal = !showModal" :title="title">
      <AdminFormManageRole
        @updateData="fetchData"
        @closeModal="showModal = !showModal"
        @clearEditedElement="clearEditedElement"
        :id="singleElement"
      />
    </Modal>

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
          :role="'ROLE_ADMIN'"
          @editEntry="edit"
        />
      </div>
    </section>
  </div>
</template>

<script>
import CustomArray from "@/components/CustomArray.vue";
import Modal from "@/components/Modal.vue";
import AdminFormManageRole from "@/components/AdminFormManageRole.vue";

import { repositoryFactory } from "@/services/repositoryFactory";
const userRepository = repositoryFactory.get("user");

export default {
  name: "AdminManageRole",
  components: { CustomArray, AdminFormManageRole, Modal },
  data() {
    return {
      title: "Admin - Manage roles",
      showModal: false,
      res: null,
      errored: false,
      loading: true,
      singleElement: null
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      await userRepository
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
    async edit(e) {
      this.showModal = true;
      this.singleElement = e;
    },
    clearEditedElement() {
      this.singleElement = null;
    }
  }
};
</script>

<style scoped lang="scss">
.admin-container {
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