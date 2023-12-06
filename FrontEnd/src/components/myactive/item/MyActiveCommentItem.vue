<script setup>
import { useRouter } from "vue-router";

const router = useRouter();

const props = defineProps({
  postComment: Object,
});

const goAfterTraveenDetailPage = () => {
  router.push({ name: "aftertraveen-detail", params: { idx: props.postComment.postIdx } });
};
</script>

<template>
  <li @click="goAfterTraveenDetailPage">
    <span class="title">{{ props.postComment.content }}</span>
    <div class="right">
      <span class="date">{{ props.postComment.createDate }}</span>
    </div>
  </li>
</template>

<style scoped>
@import "@/assets/scss/components/myactive/item/myActiveCommentItem.scss";
</style>
