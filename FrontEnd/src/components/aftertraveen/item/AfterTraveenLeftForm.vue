<script setup>
import { ref, onMounted } from "vue";
import VKakaoMapLine from "@/components/common/VKakaoMapLine.vue";
import { storeToRefs } from "pinia";
import { useCourseStore } from "@/stores/course";
import { detailPost } from "@/api/post";
import { useRoute } from "vue-router";

const courseStore = useCourseStore();
const { currentCourse } = storeToRefs(courseStore);

const props = defineProps({ type: String });

const emit = defineEmits(["onInputTitle", "onInputContent"]);
const route = useRoute();
const { idx } = route.params;

const title = ref("");
const content = ref("");

const onInputTitle = () => {
  emit("onInputTitle", title.value);
};

const onInputContent = () => {
  emit("onInputContent", content.value);
};

onMounted(() => {
  props.type === "modify" &&
    detailPost(
      idx,
      ({ data }) => {
        title.value = data.post.title;
        content.value = data.post.content;
      },
      (error) => console.log(error)
    );
});
</script>

<template>
  <div class="wrap">
    <div class="wrap-top">기본 정보</div>
    <div class="wrap-content">
      <label>글 제목</label>
      <input type="text" placeholder="제목을 입력해주세요." @input="onInputTitle" v-model="title" />
      <label>여행코스</label>
      <div class="course-title">{{ currentCourse.title }}</div>
      <label>여행일</label>
      <div class="date">{{ currentCourse.startDate }} ~ {{ currentCourse.endDate }}</div>
      <label>여행 후기 요약</label>
      <textarea
        name=""
        id=""
        cols="30"
        rows="10"
        placeholder="여행 후기를 요약해주세요."
        @input="onInputContent"
        v-model="content"
      ></textarea>
      <label>지도</label>
      <VKakaoMapLine :height="`${220}px`" />
    </div>
  </div>
</template>

<style scoped>
@import "@/assets/scss/components/aftertraveen/item/afterTraveenLeftForm.scss";
</style>
