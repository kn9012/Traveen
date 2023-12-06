<script setup>
const props = defineProps({
  postComment: Object,
});
</script>

<template>
  <div class="wrap">
    <div class="name">{{ props.postComment.userName }}</div>
    <div class="content">{{ props.postComment.content }}</div>
  </div>
</template>

<style scoped>
@import "@/assets/scss/components/aftertraveen/item/afterTraveenCommentItem.scss";
</style>
