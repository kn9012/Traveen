<script setup>
import { ref, computed } from "vue";
import { useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";
import VKakaoMap from "@/components/common/VKakaoMap.vue";
import { useTripStore } from "@/stores/trip";
import { useCourseStore } from "@/stores/course";
import { likeTrip, deleteLikeTrip } from "@/api/trip";
import { useMyTripStore } from "../../stores/mytrip";

const memberStore = useMemberStore();
const userInfo = computed(() => memberStore.userInfo);

const tripStore = useTripStore();

const courseStore = useCourseStore();
const { addCourse, setCourseList, setCourse } = courseStore;

const myTripStore = useMyTripStore();
const { isMytripLike, addMytripLike, deleteMytripLike } = myTripStore;

const router = useRouter();

const props = defineProps({
  show: Boolean,
  type: String,
  trip: Object,
});

const likeTripParam = ref({
  tripinfoIdx: "",
  userIdx: "",
});

const emit = defineEmits(["closeModal"]);
const onClickCloseModal = () => {
  emit("closeModal");
};

// 코스짜는 페이지로 이동
const goWriteCourse = () => {
  if (userInfo.value) {
    router.replace({ name: "course" });
    if (props.type === "trip") setCourse({}); // 새로운 코스 생성할때는 선택되었던 코스 초기화
    setCourseList([]); // 코스리스트 초기화
    onAddCourse();
  } else {
    alert("로그인 후 이용해주세요.");
    router.push({ name: "login" });
  }
};

// 코스에 추가하기
const onAddCourse = () => {
  addCourse(tripStore.selectTrip);
  onClickCloseModal();
};

// 여행지 찜하기
const clickLike = () => {
  if (!userInfo.value || userInfo.value === "") {
    alert("로그인 후 이용해주세요.");
    router.push({ name: "login" });
  } else {
    likeTripParam.value.tripinfoIdx = props.trip.idx;
    likeTripParam.value.userIdx = userInfo.value.idx;
    likeTrip(
      likeTripParam.value,
      () => {
        addMytripLike(props.trip);
      },
      (error) => console.log(error)
    );
  }
};

// 여행지 찜 취소
const cancelLike = () => {
  deleteLikeTrip(
    props.trip.idx,
    () => {
      deleteMytripLike(props.trip.idx);
    },
    (error) => console.log(error)
  );
};
</script>

<template>
  <div class="modalContainer" v-if="show">
    <div class="modalBody" @click.stop>
      <img
        src="@/assets/img/icon_close.png"
        alt=""
        class="btn-close-modal"
        @click="onClickCloseModal"
      />
      <div class="tripinfo-modal-container">
        <div class="left">
          <div class="title-box">
            <div class="detail-title">{{ tripStore.selectTrip.title }}</div>
            <div class="detail-type">{{ tripStore.selectTrip.categoryName }}</div>
          </div>
          <div class="place-name-box">
            <div class="detail-place-name">{{ tripStore.selectTrip.placeName }}</div>
            <div class="detail-place-like-hit">
              <div class="hit">
                <img src="@/assets/img/icon_view.png" alt="" />{{ tripStore.selectTrip.viewCount }}
              </div>
              <div class="like">
                <img src="@/assets/img/icon_heart.png" alt="" />{{ tripStore.selectTrip.likeCount }}
              </div>
            </div>
          </div>
          <div class="detail-place-category">{{ tripStore.selectTrip.type }}</div>
          <div class="detail-desc">
            {{ tripStore.selectTrip.desc }}
          </div>
          <div class="detail-info-box">
            <div class="detail-info">
              <span>운영시간</span>
              <span class="detail-info-right">{{ tripStore.selectTrip.operTime }}</span>
            </div>
            <div class="detail-info">
              <span>휴식시간</span>
              <span class="detail-info-right">{{ tripStore.selectTrip.restTime }}</span>
            </div>
            <div class="detail-info">
              <span>휴무일 안내</span>
              <span class="detail-info-right">{{ tripStore.selectTrip.restdateContent }}</span>
            </div>
            <div class="detail-info">
              <span>전화번호</span>
              <span class="detail-info-right">{{ tripStore.selectTrip.tel }}</span>
            </div>
            <div class="detail-info">
              <span>주소</span>
              <span class="detail-info-right">{{ tripStore.selectTrip.address }}</span>
            </div>
          </div>
        </div>
        <div v-if="props.trip" class="right">
          <VKakaoMap />
          <div class="button-wrap">
            <button v-if="props.type === 'trip'" @click="goWriteCourse">여행코스 만들기</button>
            <button v-else @click="onAddCourse">추가하기</button>
            <div v-if="!isMytripLike(tripStore.selectTrip.idx)">
              <button @click="clickLike">
                찜하기
                <img src="@/assets/img/icon_empty_heart.png" alt="" />
              </button>
            </div>
            <div v-else>
              <button @click="cancelLike">
                찜 취소
                <img src="@/assets/img/icon_heart.png" alt="" />
              </button>
            </div>
          </div>
          <!-- 준비 중인 댓글 서비스 -->
          <!-- <div class="comment-title">Comments</div>
          <div class="comment-box">
            <div class="comments">
              <div class="userName">예핑고</div>
              <div class="comment-content">드라마 정주행 다시 하고 싶어졌어요 ㅎㅎ</div>
            </div>
            <div class="comments">
              <div class="userName">박핑가핑가</div>
              <div class="comment-content">
                드라마 정주행 다시 하고 싶어졌어요 ㅎㅎ드라마 정주행 다시 하고 싶어졌어요 ㅎㅎ드라마
                정주행 다시 하고 싶어졌어요 ㅎㅎ드라마 정주행 다시 하고 싶어졌어요 ㅎㅎ
              </div>
            </div>
            <div class="comments">
              <div class="userName">박핑가핑가</div>
              <div class="comment-content">
                드라마 정주행 다시 하고 싶어졌어요 ㅎㅎ드라마 정주행 다시 하고 싶어졌어요 ㅎㅎ드라마
                정주행 다시 하고 싶어졌어요 ㅎㅎ드라마 정주행 다시 하고 싶어졌어요 ㅎㅎ
              </div>
            </div>
          </div>
          <form action="" id="form-detail-comment">
            <input type="text" placeholder="댓글 달기.." id="input-detail-comment" />
            <button type="button" id="btn-comment-trip">등록</button>
          </form> -->
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
@import "@/assets/scss/components/trip/tripModal.scss";
</style>
