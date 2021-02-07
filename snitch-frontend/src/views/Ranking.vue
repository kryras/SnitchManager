<template>
  <div class="">
    <h1>Ranking</h1>
  </div>

  <section v-if="errored">
    <h2>
      We're sorry, we're not able to retrieve this information at the moment,
      please try back later
    </h2>
  </section>

  <section v-else>
    <div v-if="loading"><h2>Loading...</h2></div>

    <div v-else>
      <CustomArray v-if="res !== null && res.length > 0" :data="res" :title="title" :entriesPerPage="20" />
    </div>
  </section>
</template>

<script>
import CustomArray from "@/components/CustomArray.vue";

import { repositoryFactory } from "./../../services/repositoryFactory";
const userRepository = repositoryFactory.get("user");

export default {
  name: "Ranking",
  components: { CustomArray },
  data() {
    return {
      title: "",
      res: null,
      errored: false,
      loading: true,
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      const { data } = await userRepository
        .getRanking()
        .catch(error => {
          console.log(error);
          this.errored = true;
        })
        .finally(() => (this.loading = false));
      this.res = data;
    }
  }
};
</script>

<style scoped lang="scss">
</style>