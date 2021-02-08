<template>
  <div class="customArray-container">
    <input
      type="text"
      placeholder="Start typing to search..."
      v-model="search"
      class="input"
    />
    <table class="table">
      <thead>
        <tr>
          <th scope v-for="key in columns" :key="key">
            <span class="table__column-name" @click="sortByColumn(key)">
              {{ key }}</span
            >
          </th>
          <th v-if="true">
            <span class="table__edit">-</span>
          </th>
        </tr>
      </thead>

      <tbody>
        <tr v-for="(listElement, index) in paginatedData" :key="index">
          <td v-for="column in columns" :key="column">
            {{ listElement?.[column] }}
          </td>
          <td v-if="true">
            <span @click="editEntry(listElement?.id)" class="table__edit"
              >EDIT</span
            >
          </td>
        </tr>
      </tbody>
    </table>

    <CustomArrayPagination
      :data="preparedData"
      :entriesPerPage="entriesPerPage"
      @updateData="pagData"
    />
  </div>
</template>

<script>
import CustomArrayPagination from "@/components/CustomArrayPagination.vue";
import _sortBy from "lodash/sortBy";
import _toString from "lodash/toString";

export default {
  props: {
    data: {
      type: Array,
      required: true
    },
    title: {
      type: String,
      required: false
    },
    entriesPerPage: {
      type: Number,
      required: true
    }
  },
  name: "CustomArray",
  components: { CustomArrayPagination },
  data() {
    return {
      dataLength: 0,
      columns: [],
      search: "",
      reverse: false,
      sortKey: "",
      startPage: 1,
      pageFrom: 0,
      pageTo: 0
    };
  },
  created() {
    this.columns = Object.keys(this.data[0]);
  },
  watch: {
    preparedData() {}
  },
  methods: {
    pagData(e) {
      let [to, from] = e;
      this.pageFrom = from;
      this.pageTo = to;
    },
    sortByColumn(column) {
      this.reverse = this.sortKey == column ? !this.reverse : false;
      this.sortKey = column;
    },
    sortData() {
      return this.sortKey === ""
        ? this.data
        : this.reverse
        ? _sortBy(this.data, [this.sortKey]).reverse()
        : _sortBy(this.data, [this.sortKey]);
    },
    searchData(data) {
      return data.filter(item =>
        this.columns.some(field =>
          _toString(item[field])
            .toLowerCase()
            .includes(this.search.toLowerCase())
        )
      );
    },
    paginate(data) {
      return data.slice(this.pageFrom, this.pageTo);
    },
    editEntry(id) {
      this.$emit("editEntry", id);
    }
  },
  computed: {
    preparedData() {
      return this.searchData(this.sortData());
    },
    paginatedData() {
      return this.paginate(this.preparedData);
    }
  }
};
</script>

<style  scoped lang="scss">
.customArray-container {
  width: 100%;
}

table {
  border-collapse: separate;
  border-spacing: 0 10px;
  &.table {
    margin-top: 16px;
    margin-right: 0;
    width: 100%;
    font-size: $regular-font-size;
    @media (max-width: $mobile-max-width) {
      width: 95%;
      margin: auto;
    }
  }

  .table__column-name,
  .table__edit {
    cursor: pointer;
  }
}

th {
  text-transform: capitalize;
}

td,
tr {
  border: solid 2px $solid-border-color;
  border-style: solid none;
  padding: 14px;
}

td:first-child {
  border-left-style: solid;
  border-top-left-radius: $border-radius;
  border-bottom-left-radius: $border-radius;
}

td:last-child {
  border-right-style: solid;
  border-bottom-right-radius: $border-radius;
  border-top-right-radius: $border-radius;
}

.input {
  @include create-input(
    $border-size,
    $border-radius,
    $input-gradient,
    100%,
    $height
  );
  margin-top: 16px;
}
</style>