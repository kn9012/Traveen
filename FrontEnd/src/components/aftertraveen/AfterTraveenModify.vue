<script setup>
import { ref, computed } from "vue";
import { storeToRefs } from "pinia";
import AfterTraveenLeftForm from "@/components/aftertraveen/item/AfterTraveenLeftForm.vue";
import AfterTraveenRightFormVue from "@/components/aftertraveen/item/AfterTraveenRightForm.vue";
import { useRoute, useRouter } from "vue-router";
import { modifyPost } from "@/api/post";
import { useCourseStore } from "@/stores/course";
import { useMemberStore } from "@/stores/member";

const route = useRoute();
const router = useRouter();
const { idx } = route.params;

const memberStore = useMemberStore();
const courseStore = useCourseStore();
const { currentCourse, courseList } = storeToRefs(courseStore);

const { setCourse, setCourseList } = courseStore;


// 여행 후기 조회 페이지로 이동
const goAftertraveenDetail = () => {
  router.push({ name: "aftertraveen-detail", params: { idx } });
};

const userInfo = computed(() => memberStore.userInfo);


const postParam = ref({
  post: {
    idx: idx,
    title: currentCourse.value.title,
    userIdx: userInfo.value.idx,
    courseIdx: currentCourse.value.idx,
    content: currentCourse.value.content,
  },
  courseIdx: currentCourse.value.idx,
  list: courseList,
});

const modifyAftertraveen = () => {
    if (postParam.value.post.title === "") {
    alert("제목을 입력해주세요.");
  } else if (postParam.value.post.content === "") {
    alert("여행 후기 요약을 입력해주세요.");
  } else {
    modifyPost(
      postParam.value,
      () => {
        alert("게시글이 수정되었습니다.");
        setCourse(postParam.value.post);
        setCourseList(postParam.value.list);
        
        router.push({ name: "aftertraveen-detail", params: { idx: idx } });
      },
      (error) => console.log(error)
    );
  }
};

const onInputTitle = (val) => (postParam.value.post.title = val);
const onInputContent = (val) => (postParam.value.post.content = val);

</script>

<template>
  <article>
    <h2>여행 후기 수정</h2>
    <h5>나의 여행 코스가 마음에 들었다면 후기를 통해 다른 사람들에게 공유해볼까요?</h5>
    <div class="container">
      <AfterTraveenLeftForm type="modify" @on-input-title="onInputTitle" @on-input-content="onInputContent" />
      <AfterTraveenRightFormVue type="modify"/>
    </div>
    <div class="btn-container">
      <button @click="goAftertraveenDetail">취소</button>
      <button @click="modifyAftertraveen">수정하기</button>
    </div>
  </article>
</template>

<style scoped>
@import "@/assets/scss/components/aftertraveen/afterTraveenModify.scss";
</style>
