<template>
  <div class="denunciation-container">
    <div>
      <h1>Denunciation list</h1>
    </div>

    <Modal v-if="showModal" @close="showModal = !showModal" :title="title">

      <ManagerFormAddDenunciation
        @updateData="fetchData"
        @closeModal="showModal = !showModal"
        @clearEditedElement="clearEditedElement"
        :element="singleElement"
      />
    </Modal>
    <button
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
          :title="title"
          :entriesPerPage="20"
          @editEntry="edit"
        />
      </div>
    </section>
  </div>
</template>

<script>
import CustomArray from "@/components/CustomArray.vue";
import ManagerFormAddDenunciation from "@/components/ManagerFormAddDenunciation.vue";
import Modal from "@/components/Modal.vue";

import { repositoryFactory } from "@/services/repositoryFactory";
const snitchRepository = repositoryFactory.get("snitch");

export default {
  name: "DenunciationList",
  components: { CustomArray, Modal, ManagerFormAddDenunciation },
  data() {
    return {
      title: "Denunciation",
      res: null,
      errored: false,
      loading: true,
      showModal: false,
      singleElement: null
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    async fetchData() {


      await snitchRepository
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
    async fetchDataById(e) {
   

      await snitchRepository
        .getById(e)
        .then(response => {
          this.singleElement = response.data;
        })
        .catch(error => {
          console.log(error);
          this.errored = true;
        });
    },
    async edit(e) {
      await this.fetchDataById(e);
      console.log("asd", this.singleElement);
      this.showModal = true;
    },
    clearEditedElement() {
      this.singleElement = null;
    }
  }
};
</script>

<style  scoped lang="scss">
.denunciation-container {
  width: 100%;
  max-width: 1100px;
  margin: auto;
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