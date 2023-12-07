<script setup>
import { ref, computed, onMounted, watch } from "vue";
import VEmptyItem from "@/components/common/VEmptyItem.vue";
import MyActiveCommentItemVue from "@/components/myactive/item/MyActiveCommentItem.vue";
import { useMemberStore } from "@/stores/member";
import { listPostCommentByUseridx } from "@/api/post";

const memberStore = useMemberStore();
const userInfo = computed(() => memberStore.userInfo);

const len = ref(0);
const postComments = ref([]);

onMounted(() => {
  // 내가 쓴 여행 후기 댓글 불러오기
  getPostCommentList();
});

const getPostCommentList = () => {
  listPostCommentByUseridx(
    userInfo.value.idx,
    ({ data }) => {
      postComments.value = data;
    },
    (error) => console.log(error)
  );
};

// 글 갯수 세기
watch(postComments, (newValue) => {
  len.value = newValue.length;
});
</script>

<template>
  <div class="subtitle">내가 쓴 댓글</div>
  <div class="container">
    <ul v-if="len === 0">
      <VEmptyItem text="내가 쓴 댓글이 없습니다." />
    </ul>
    <ul v-else>
      <MyActiveCommentItemVue v-for="postComment in postComments" :key="postComment.idx" :postComment="postComment" />
    </ul>
  </div>
</template>

<style scoped>
@import "@/assets/scss/components/myactive/myActiveComment.scss";
</style>
