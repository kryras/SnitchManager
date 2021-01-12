<template>
  <div class="home__container">
    <div class="hero">
      <h1 class="hero__title">SnitchManager</h1>
      <h2 class="hero__subtitle">
        Tell on your colleagues and gain extra points
      </h2>
      <router-link class="hero__button" :to="{ name: 'LoginRegister' }">
        BECOME A SNITCH TODAY!
      </router-link>
      <img
        @click="scrollTo"
        src="@/assets/wideheaded_downwards_arrow.png"
        alt="WIDE-HEADED ARROW"
        class="hero__arrow"
      />
    </div>
    <div class="steps">
      <h3 class="steps__title">How it works</h3>
      <div class="steps__step">
        <div class="step__number step__number-first">1</div>
        <div class="step__description step__description-first">
          Tell on your colleagues to your boss
        </div>
      </div>
      <div class="steps__step">
        <div class="step__number step__number-second">2</div>
        <div class="step__description step__description-second">
          Wait for boss approval
        </div>
      </div>
      <div class="steps__step">
        <div class="step__number step__number-third">3</div>
        <div class="step__description step__description-third">Get points!</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home",
  components: {},
  methods: {
    scrollTo() {
      const element = document.querySelector(".steps");
      element.scrollIntoView({ behavior: "smooth" });
    },
  },
};
</script>

<style scoped lang="scss">
@import url("https://fonts.googleapis.com/css2?family=Fira+Mono:wght@500&display=swap");

.hero {
  color: #fff;
  width: 100vw;
  display: inline-block;

  &__title {
    font-size: 10.5rem;
    font-weight: 400;
    margin: 160px auto 0;

    @media (max-width: $mobile-max-width) {
      font-size: 5rem;
    }
  }

  &__subtitle {
    font-size: 1.8rem;
    font-weight: 400;
    font-weight: 400;
    margin: 20px auto;
  }

  &__button {
    $border-size: 5px;
    position: relative;
    text-decoration: none;
    color: #4972a8;
    display: inline-block;
    font-size: 1.8rem;
    font-weight: 700;
    margin: 30px auto 0;
    padding: (21px - 0) (30px - 0);
    background-color: #fff;
    border-radius: $border-radius;
    transition: background-color 0.2s ease-in-out;
    &::before {
      content: "";
      border-radius: $border-radius;
      background-image: $input-gradient;
      width: 100%;
      height: 100%;
      top: 0;
      left: 0;
      position: absolute;
      z-index: -2;
    }
    &::after {
      content: "";
      border-radius: $border-radius;
      background-color: #fff;
      width: calc(100% - #{$border-size * 2});
      height: calc(100% - #{$border-size * 2});
      top: $border-size;
      left: $border-size;
      position: absolute;
      z-index: -1;
    }

    &:focus,
    &:hover {
      background-color: transparent;
    }
  }

  &__arrow {
    cursor: pointer;
    display: block;
    width: 30px;
    height: 35px;
    margin: 115px auto 0;
    opacity: 0.3;
    transform: scale(1);
    animation: pulse ease-in-out 1s infinite alternate;
    @media (max-width: 370px) {
      margin: 95px auto 0;
    }
  }

  @keyframes pulse {
    0% {
      transform: scale(1);
    }
    100% {
      transform: scale(0.8);
    }
  }
}
.steps {
  max-width: $content-max-width;
  margin: 70px auto;
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  justify-content: space-around;

  &__title {
    font-size: 5rem;
    font-weight: 400;
    width: 100%;
  }

  &__step {
    margin: 70px auto 0 auto;
    justify-content: center;
    flex-direction: column;
    width: 262px;
    position: relative;
  }

  .step {
    &__number {
      font-family: "Fira Mono", "Courier New", Courier, monospace;
      font-size: 15rem;
      font-weight: 500;
      &-first {
        color: #472f88;
        &::after {
          @include create-circle(69px, rgba(71, 47, 136, 0.75), 10px, -20px);
        }
        &::before {
          @include create-circle(39px, rgba(71, 47, 136, 0.25), 30px, 190px);
        }
      }

      &-second {
        color: #496da5;
        &::after {
          @include create-circle(69px, rgba(73, 109, 165, 0.75), 80px, 190px);
        }
        &::before {
          @include create-circle(39px, rgba(73, 109, 165, 0.25), 136px, 0px);
        }
      }
      &-third {
        color: #4b9cbc;
        &::after {
          @include create-circle(69px, rgba(75, 156, 188, 0.75), 10px, 190px);
        }
        &::before {
          @include create-circle(39px, rgba(75, 156, 188, 0.25), 93px, 0px);
        }
      }
    }

    &__description {
      font-size: 2.4rem;
      &-first {
        &::before {
          @include create-circle(26px, rgba(71, 47, 136, 0.5), 152px, 62px);
        }
      }
      &-second {
        &::before {
          @include create-circle(26px, rgba(73, 109, 165, 0.5), 20px, 32px);
        }
      }
      &-third {
        &::before {
          @include create-circle(26px, rgba(75, 156, 188, 0.5), 155px, 195px);
        }
      }
    }
  }
}
</style>