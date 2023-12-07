<script setup>
import { computed } from "vue";
import { deletePostComment } from "@/api/post";
import { usePostCommentStore } from "@/stores/postComment";
import { useMemberStore } from "@/stores/member";

const postCommentStore = usePostCommentStore();
const { deleteComment } = postCommentStore;

const memberStore = useMemberStore();
const userInfo = computed(() => memberStore.userInfo);

const props = defineProps({
  postComment: Object,
});

const onClickDeleteComment = () => {
  deletePostComment(
    props.postComment.idx,
    () => {
      deleteComment(props.postComment.idx);
    },
    (error) => console.log(error)
  );
};

</script>

<template>
  <div class="wrap">
    <div class="name">{{ props.postComment.userName }}</div>
    <div class="content">{{ props.postComment.content }}</div>
    <div v-if="props.postComment.userIdx == userInfo.idx">
    <img
        src="@/assets/img/icon_close.png"
        alt=""
        class="btn-delete-comment"
        @click="onClickDeleteComment"
      />
    </div>
  </div>
</template>

<style scoped>
@import "@/assets/scss/components/aftertraveen/item/afterTraveenCommentItem.scss";
</style>
