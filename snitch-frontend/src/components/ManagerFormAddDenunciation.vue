<template>
  <div>
    <div class="form-container">
      <div v-if="editMode">
        <h2>Edit denunciation with id: {{ req.id }}</h2>
      </div>
      <div v-else>
        <h2>Add new</h2>
      </div>

      <form v-if="downloadComplete" class="form" @submit.prevent="submit">
        <div class="input-wrapper">
          <label class="label" for="denunciation-snitch">
            Snitch name
          </label>
          <select
            class="input"
            id="denunciation-snitch"
            v-model="selectedSnitchId"
          >
            <option v-for="user in users" :key="user.id" :value="user.id">
              {{ user.name }}
            </option>
          </select>
        </div>

        <div class="input-wrapper">
          <label class="label" for="denunciation-victim">
            Victim name
          </label>
          <select
            class="input"
            id="denunciation-victim"
            v-model="selectedVictimId"
          >
            <option v-for="user in users" :key="user.id" :value="user.id">
              {{ user.name }}
            </option>
          </select>
        </div>

        <div class="input-wrapper">
          <label class="label" for="denunciation-type">
            Type
          </label>
          <select class="input" id="denunciation-type" v-model="selectedTypeId">
            <option v-for="type in types" :key="type.id" :value="type.id">
              {{ type.name }}
            </option>
          </select>
        </div>

        <div class="input-wrapper">
          <label class="label" for="denunciation-bonuses">
            Bonuses
          </label>
          <div id="denunciation-bonuses" class="input-checkbox">
            <div v-for="bonus in bonuses" :key="bonus.id">
              <input
                type="checkbox"
                class="checkbox"
                v-model="selectedBonusIds"
                :value="bonus.id"
                :id="bonus.id"
              />
              <label class="checkbox-label" :for="bonus.id">{{
                bonus.name
              }}</label>
            </div>
          </div>
        </div>
        <p class="error-message">{{ errorMessage }}</p>
        <button class="button">SAVE</button>
      </form>

      <button
        v-show="false"
        @click="assignValues"
        class="button"
        ref="elBtn"
      ></button>
    </div>
  </div>
</template>

<script>
import { repositoryFactory } from "@/services/repositoryFactory";
const snitchRepository = repositoryFactory.get("snitch");

export default {
  name: "ManagerFormAddDenunciation",
  props: {
    element: {
      type: Object,
      required: false
    }
  },
  data() {
    return {
      req: {
        snitchId: null,
        victimId: null,
        typeId: null,
        bonusIds: []
      },
      selectedSnitchId: null,
      selectedVictimId: null,
      selectedTypeId: null,
      selectedBonusIds: [],
      types: [],
      bonuses: [],
      users: [],
      errorred: false,
      errorMessage: "",
      elementCopy: null,
      editMode: false,
      downloadComplete: false
    };
  },
  created() {
    if (this.element !== null) {
      this.elementCopy = this.element;
      this.editMode = true;
      this.assignValues();
    }
    this.getData();
    this.$emit("clearEditedElement");
  },
  methods: {
    submit(denunciation) {
      this.errorMessage = "";
      if (!this.selectedSnitchId) {
        this.errorMessage = "snitch name is required field";
      } else if (!this.selectedVictimId) {
        this.errorMessage = "victim name is required field";
      } else if (!this.selectedTypeId) {
        this.errorMessage = "type name is required field";
      } else {
        this.assignNewValues();
        this.addData(this.req);
      }
    },
    async addData(denunciation) {
      await snitchRepository
        .create(denunciation)
        .then(() => {
          this.$emit("updateData");
          this.$emit("closeModal");
        })
        .catch(error => {
          console.log(error);
          this.errored = true;
        });
    },
    async getData() {
      await Promise.all([
        snitchRepository.getTypes(),
        snitchRepository.getBonuses(),
        snitchRepository.getUsers()
      ])
        .then(values => {
          this.types = values[0].data;
          this.bonuses = values[1].data;
          this.users = values[2].data;
          this.downloadComplete = true;
        })
        .catch(error => {
          console.log(error);
          this.errored = true;
        });
    },
    assignValues() {
      this.req = this.elementCopy;
      this.selectedSnitchId = this.elementCopy.snitchId;
      this.selectedVictimId = this.elementCopy.victimId;
      this.selectedTypeId = this.elementCopy.typeId;
      this.selectedBonusIds = this.elementCopy.bonusIds;
    },
    assignNewValues() {
      this.req.snitchId = this.selectedSnitchId;
      this.req.victimId = this.selectedVictimId;
      this.req.typeId = this.selectedTypeId;
      this.req.bonusIds = this.selectedBonusIds;
    }
  }
};
</script>

<style scoped lang="scss">
.form-container {
  margin: 10px 10px;
  display: flex;
  flex-flow: column wrap;
}

.form {
  width: 100%;
  display: flex;
  flex-flow: column wrap;
  justify-content: flex-start;
  
  .label {
    @include create-label(100%);
  }

  .input {
    max-width: 735px;
    @include create-input(
      $border-size,
      $border-radius,
      $input-gradient,
      auto,
      $height
    );
    &-wrapper {
      display: flex;
      flex-flow: column nowrap;
      margin-right: 5px;
      @media (max-width: $mobile-max-width) {
        width: 90%;
        margin: auto;
      }
    }
  }
  .input-checkbox {
    @include create-input(
      $border-size,
      $border-radius,
      $input-gradient,
      auto,
      auto
    );
    text-align: left;
  }

 
  .checkbox-label {
    margin-left: 10px;
    font-size: $regular-font-size;
    line-height: 0;
  }
  .checkbox {
    margin: 10px auto 10px 15%;
    @media (max-width: $mobile-max-width) {
      margin: 10px;
    }
  }
  .checkbox-wrapper {
    font-size: $regular-font-size;
    margin-bottom: 0;
  }

  .button {
    @include create-button(
      $border-size,
      $border-radius,
      $input-gradient,
      $input-gradient-inverted,
      100%,
      $height
    );
    @media (max-width: $mobile-max-width) {
      width: 90%;
      margin: auto;
      margin-top: 45px;
    }
    margin-left: auto;
    margin-right: 0px;
    margin-top: 0;
  }
}

select {
  text-align: center;
  text-align-last: center;
}
option {
  text-align: left;
}
.error-message {
  margin-bottom: 10px;
}
</style>