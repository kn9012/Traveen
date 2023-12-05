<script setup>
import { onMounted } from "vue";
import { storeToRefs } from "pinia";
import AfterTraveenRightListItem from "@/components/aftertraveen/item/AfterTraveenRightListItem.vue";
import { useCourseStore } from "@/stores/course";
import { detailPost } from "@/api/post";
import { useRoute } from "vue-router";

const courseStore = useCourseStore();
const { courseList } = storeToRefs(courseStore);
const { setCourseList } = courseStore;

const props = defineProps({ type: String });
const route = useRoute();
const { idx } = route.params;

onMounted(() => {
  if (props.type === "modify") {
    detailPost(
      idx,
      ({ data }) => {
        setCourseList(data.postItem);
        console.log(data);
        // console.log(courseList.value);
      },
      (error) => console.log(error)
    );
  }
});
</script>

<template>
  <div class="wrap">
    <div class="wrap-top">나의 여행 코스</div>
    <div class="wrap-content">
      <AfterTraveenRightListItem
        v-for="(trip, index) in courseList"
        :trip="trip"
        :key="trip.tripinfoIdx"
        :index="index + 1"
      />
    </div>
  </div>
</template>

<style scoped>
@import "@/assets/scss/components/aftertraveen/item/afterTraveenRightForm.scss";
</style>
