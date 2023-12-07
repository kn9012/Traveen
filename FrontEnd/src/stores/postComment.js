import { computed, ref } from "vue";
import { defineStore } from "pinia";

export const usePostCommentStore = defineStore(
  "postComment",
  () => {
    const commentList = ref([]);
    const commentCount = computed(() => commentList.value.length);

    const addComment = (postComment) => {
      commentList.value.push(postComment);
    };

    const deleteComment = (idx) => {
      commentList.value = commentList.value.filter((item) => item.idx !== idx);
    };

    const setCommentList = (list) => {
      commentList.value = list;
    };

    // 내가 단 댓글인지 확인
    const isMyComment = (idx) => {
      return commentList.value.some((item) => item.idx == idx);
    };

    return {
      commentList,
      commentCount,
      addComment,
      deleteComment,
      setCommentList,
      isMyComment,
    };
  },
  { persist: true }
);
