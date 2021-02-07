<template>
  <div class="bonus-container">
    <div>
      <h1>Bonuses list</h1>
    </div>

    <ManagerFormAddBonus @updateData="fetchData" />
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
import ManagerFormAddBonus from "@/components/ManagerFormAddBonus.vue";

import { repositoryFactory } from "./../../services/repositoryFactory";
const bonusesRepository = repositoryFactory.get("bonuses");

export default {
  name: "BonusesList",
  components: { CustomArray, ManagerFormAddBonus },
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
      const { data } = await bonusesRepository
        .get()
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
.bonus-container {
  width: 100%;
  max-width: 1100px;
  margin: auto;
}
</style>