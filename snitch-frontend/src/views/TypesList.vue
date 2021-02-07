<template>
  <div class="type-container">
    <div>
      <h1>Types list</h1>
    </div>

    <ManagerFormAddType @updateData="fetchData" />
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
        />
      </div>
    </section>
  </div>
</template>

<script>
import CustomArray from "@/components/CustomArray.vue";
import ManagerFormAddType from "@/components/ManagerFormAddType.vue";

import { repositoryFactory } from "@/services/repositoryFactory";
const typesRepository = repositoryFactory.get("types");

export default {
  name: "TypesList",
  components: { CustomArray, ManagerFormAddType },
  data() {
    return {
      title: "",
      res: null,
      errored: false,
      loading: true
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      await typesRepository
        .get()
        .then((response) => {
          this.res = response.data;
        })
        .catch(error => {
          console.log(error);
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    }
  }
};
</script>

<style scoped lang="scss">
.type-container {
  width: 100%;
  max-width: 1100px;
  margin: auto;
}
</style>