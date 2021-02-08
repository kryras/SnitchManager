<template>
  <div>
    <nav v-if="entriesPerPage < dataLength">
      <ul class="pagination">
        <li class="page-item">
          <button
            type="button"
            class="page-link-arrow"
            v-if="page != 1"
            @click="changePage(page - 1)"
          >
            <img
              src="@/assets/wideheaded_downwards_arrow.png"
              alt="WIDE-HEADED ARROW"
              class="arrow left"
            />
          </button>
        </li>

        <li v-if="page === 1" class="page-item">
          <button
            type="button"
            class="page-link"
            v-for="pageNumber in pages.slice(page - 1, page + 2)"
            :key="pageNumber"
            @click="changePage(pageNumber)"
          >
            {{ pageNumber }}
          </button>
        </li>
        <li
          v-else-if="page > 1 && page < pages[pages.length - 1]"
          class="page-item"
        >
          <button
            type="button"
            class="page-link"
            v-for="pageNumber in pages.slice(page - 2, page + 1)"
            :key="pageNumber"
            @click="changePage(pageNumber)"
          >
            {{ pageNumber }}
          </button>
        </li>
        <li
          v-else-if="page === pages[pages.length - 1] && pages.length === 2"
          class="page-item"
        >
          <button
            type="button"
            class="page-link"
            v-for="pageNumber in pages.slice(page - 2, page)"
            :key="pageNumber"
            @click="changePage(pageNumber)"
          >
            {{ pageNumber }}
          </button>
        </li>
        <li v-else-if="page === pages[pages.length - 1]" class="page-item">
          <button
            type="button"
            class="page-link"
            v-for="pageNumber in pages.slice(page - 3, page)"
            :key="pageNumber"
            @click="changePage(pageNumber)"
          >
            {{ pageNumber }}
          </button>
        </li>

        <li class="page-item">
          <button
            type="button"
            class="page-link-arrow right"
            @click="changePage(page + 1)"
            v-if="page < pages.length"
          >
            <img
              src="@/assets/wideheaded_downwards_arrow.png"
              alt="WIDE-HEADED ARROW"
              class="arrow right"
            />
          </button>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
export default {
  props: {
    data: {
      type: Array,
      required: true
    },
    entriesPerPage: {
      type: Number,
      required: true
    }
  },
  name: "CustomArrayPagination",
  data() {
    return {
      dataLength: 0,
      page: 1,
      pages: []
    };
  },
  created() {
    this.dataLength = this.data.length;
    this.setPages();
    this.paginate(this.data);
  },
  watch: {
    data() {
      this.dataLength = this.data.length;
      this.page = 1;
      this.setPages();
      this.paginate();
    }
  },
  methods: {
    changePage(pageNumber) {
      this.page = pageNumber;
      this.paginate(this.data);
    },
    setPages() {
      this.pages = [];
      let numberOfPages = Math.ceil(this.dataLength / this.entriesPerPage);
      for (let index = 1; index <= numberOfPages; index++) {
        this.pages.push(index);
      }
    },
    paginate(data) {
      let page = this.page;
      let perPage = this.entriesPerPage;
      let from = page * perPage - perPage;
      let to = page * perPage;
      this.$emit("updateData", [to, from]);
    }
  }
};
</script>

<style scoped lang="scss">
.pagination {
  display: flex;
  flex-flow: row nowrap;
  justify-content: center;
  margin: 16px auto;
  ul,
  li {
    list-style-type: none;
  }
}

button {
  font-size: $regular-font-size;
  font-family: "Maven Pro", sans-serif;
  width: 40px;
  height: 40px;
  background: none;
  border-radius: $border-radius;
  border: 1px solid $solid-border-color;
  margin: 8px;
}
.page-link-arrow {
  background: $input-gradient;
  border: none;
}
.arrow {
  cursor: pointer;
  display: block;
  width: 20px;
  height: 25px;
  margin: auto;
  @media (max-width: 370px) {
    margin: 95px auto 0;
  }
  &.left {
    transform: rotate(90deg);
  }
  &.right {
    transform: rotate(-90deg);
  }
}
</style>