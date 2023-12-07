<script setup>
import { ref, onMounted, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import { storeToRefs } from "pinia";
import KakaoMapLine from "@/components/common/VKakaoMapLine.vue";
import AfterTraveenDetailListItem from "@/components/aftertraveen/item/AfterTraveenDetailListItem.vue";
import AfterTraveenCommentItem from "@/components/aftertraveen/item/AfterTraveenCommentItem.vue";
import { detailPost, deletePost, registPostComment, listPostComment } from "@/api/post";
import { listCourseItem } from "@/api/course";
import { listFollowing, deleteFollowing, followUser } from "@/api/friend";
import { useMemberStore } from "@/stores/member";
import { useFriendStore } from "@/stores/friend";
import { useCourseStore } from "@/stores/course";
import { usePostCommentStore } from "@/stores/postComment";

const route = useRoute();
const router = useRouter();
const post = ref({});
const postComments = ref([]);

const { idx } = route.params;

const items = ref([]);

const memberStore = useMemberStore();
const userInfo = computed(() => memberStore.userInfo);

const friendStore = useFriendStore();
const { setFollowingList } = friendStore;
const { addFollowing, isMyFollowing, deleteMyFollowing } = friendStore;

const courseStore = useCourseStore();
const { setCourse } = courseStore;

const postCommentStore = usePostCommentStore();
const { addComment, setCommentList } = postCommentStore;
const { commentList } = storeToRefs(postCommentStore);

const followUserParam = ref({
  fromIdx: userInfo.value.idx,
  toIdx: "",
});

onMounted(() => {
  // 여행 후기 글 불러오기
  getPost();

  listAftertraveenComment();

  listFollowing(
    userInfo.value.idx,
    ({ data }) => {
      setFollowingList(data);
    },
    (error) => console.log(error)
  );
});

const getPost = () => {
  detailPost(
    idx,
    ({ data }) => {
      post.value = data.post;
      items.value = data.postItem;
    },
    (error) => console.log(error)
  );
};

// 여행 후기 글 삭제
const onPostDelete = () => {
  deletePost(
    idx,
    () => {
      alert("게시글이 삭제되었습니다.");
      post.value.flag = 0;
      router.push({ name: "aftertraveen" });
    },
    (error) => console.log(error)
  );
};

const onClickFollow = () => {
  followUserParam.value.toIdx = post.value.userIdx;
  if (followUserParam.value.toIdx !== userInfo.value.idx) {
    followUser(
      followUserParam.value,
      () => {
        addFollowing({ idx: post.value.userIdx });
        alert("팔로우가 완료되었습니다.");
      },
      (error) => console.log(error)
    );
  }
};

const onDeleteFollow = (idx) => {
  deleteFollowing(
    idx,
    () => {
      deleteMyFollowing(idx);
      alert("팔로우가 취소되었습니다.");
    },
    (error) => console.log(error)
  );
};

// 여행 후기 수정 페이지로 이동
const goAftertraveenModify = () => {
  listCourseItem(
    post.value.courseIdx,
    ({ data }) => {
      setCourse({
        idx: data.courseIdx,
        title: data.courseTitle,
        startDate: data.startDate,
        endDate: data.endDate,
      });
    },
    (error) => console.log(error)
    );
    router.push({ name: "aftertraveen-modify", params: { idx } });
};

const goAftertraveenListpage = () => {
  window.scrollTo(0, 0);
  router.push({ name: "aftertraveen-list" });
};

const postComment = ref({
  idx: "",
  userIdx: userInfo.value.idx,
  userName: "",
  postIdx: idx,
  content: "",
});

const writeAftertraveenComment = () => {
  registPostComment(
    postComment.value,
    () => {
      addComment(postComment.value);
      postComment.value.content = "";
      console.log(commentList);
    },
    (error) => console.log(error)
  );
};

const listAftertraveenComment = () => {
  listPostComment(
    idx,
    ({ data }) => {
      setCommentList(data);
      postComments.value = data;
      console.log(commentList);
    },
    (error) => console.log(error)
  );
};
</script>

<template>
  <div class="info-wrap">
    <div class="title-wrap">
      <div class="title">{{ post.title }}</div>
      <div v-if="post.userIdx === userInfo.idx" class="button-wrap">
        <button @click="onPostDelete">삭제</button>
        <button @click="goAftertraveenModify">수정</button>
      </div>
      <div v-else class="button-wrap"></div>
    </div>

    <div class="profile-wrap">
      <img src="@/assets/img/img_profile.png" alt="" />
      <div class="name">{{ post.userName }}</div>
      <div v-if="post.userIdx !== userInfo.idx && !isMyFollowing(post.userIdx)">
        <button @click="onClickFollow">팔로우</button>
      </div>
      <div v-if="isMyFollowing(post.userIdx)">
        <button @click="onDeleteFollow(post.userIdx)">팔로우 취소</button>
      </div>
    </div>

    <div class="line"></div>

    <div class="mid-wrap">
      <div class="left">
        <div class="left-item">
          <div class="left-text">여행일</div>
          <div class="left-content" id="date">{{ post.startDate }} ~ {{ post.endDate }}</div>
        </div>
        <div class="left-item">
          <div class="left-text">여행 후기 요약</div>
          <div class="left-content">
            {{ post.content }}
          </div>
        </div>
      </div>
      <div class="right">
        <KakaoMapLine :height="`${160}px`" />
      </div>
    </div>

    <div class="line"></div>

    <div class="content-wrap">
      <AfterTraveenDetailListItem v-for="trip in items" :key="trip.idx" :trip="trip" />
    </div>
  </div>

  <div class="comments-wrap">
    <div class="comment-title">Comments</div>
    <div class="comment-content">
      <AfterTraveenCommentItem v-for="postComment in commentList" :key="postComment.idx" :postComment="postComment"/>
    </div>
    <div class="input-wrap">
      <input type="text" placeholder="댓글 달기.." v-model="postComment.content"/>
      <button @click="writeAftertraveenComment">등록</button>
    </div>
  </div>

  <div class="button-wrap">
    <button id="btn-go-list" @click="goAftertraveenListpage">목록으로</button>
  </div>
</template>

<style scoped>
@import "@/assets/scss/components/aftertraveen/afterTraveenDetail.scss";
</style>
