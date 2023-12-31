<script setup>
import { ref, onMounted, watch, computed } from "vue";
import { storeToRefs } from "pinia";
import { listTrip, detailTrip, listLikeTrip } from "@/api/trip";
import TripListItem from "@/components/trip/item/TripListItem.vue";
import PageNavigation from "@/components/common/PageNavigation.vue";
import VEmptyItem from "@/components/common/VEmptyItem.vue";
import TripModal from "@/components/trip/TripModal.vue";
import { useRoute } from "vue-router";
import { useTripStore } from "@/stores/trip";
import { useMyTripStore } from "@/stores/mytrip";
import { useMemberStore } from "@/stores/member";

const tripStore = useTripStore();
const { setTrip } = tripStore;

const memberStore = useMemberStore();
const userInfo = computed(() => memberStore.userInfo);

const myTripStore = useMyTripStore();
const { setMytripLike } = myTripStore;
const { mytripLikeCount } = storeToRefs(myTripStore);

const route = useRoute();

// trip list 길이
const len = ref(0);
const show = ref(false);

const { word } = route.params;

const trips = ref([]);
const trip = ref({});

const currentPage = ref(1);
const totalPage = ref(0);
const { VITE_TRIP_LIST_SIZE } = import.meta.env;

// 아이템 클릭했을 때
const clickItem = (idx) => {
  getTrip(idx);
};

const param = ref({
  pgno: currentPage.value,
  spp: VITE_TRIP_LIST_SIZE,
  word: "",
  type: "",
  order: "",
  category: "",
});

// 모달창 닫기
const closeModal = () => {
  show.value = false;
};

onMounted(() => {
  window.scrollTo(0, 0);
  param.value.word = word;
  // 여행지 리스트 불러오기
  getTripList();
  // 사용자의 찜한 리스트 불러오기
  getMyTripLikeList();
  len.value = trips.value.length;
});

const getTripList = () => {
  listTrip(
    param.value,
    ({ data }) => {
      trips.value = data.tripinfos;
      currentPage.value = data.currentPage;
      totalPage.value = data.totalPageCount;
    },
    (error) => console.log(error)
  );
};

watch(
  () => trip.value,
  () => {
    setTrip(trip.value);
    show.value = true;
  }
);

watch(
  () => mytripLikeCount.value,
  () => {
    getTripList();
  }
);

const getTrip = (idx) => {
  detailTrip(
    idx,
    ({ data }) => {
      trip.value = data;
      setTrip(data);
    },
    (error) => console.log(error)
  );
};

const getMyTripLikeList = () => {
  userInfo.value &&
    listLikeTrip(
      userInfo.value.idx,
      ({ data }) => setMytripLike(data),
      (error) => console.log(error)
    );
};

const onPageChange = (val) => {
  currentPage.value = val;
  param.value.pgno = val;
  getTripList();
};

const searchTrip = () => {
  param.value.pgno = 1;
  getTripList();
};

const selectCategory = (val) => {
  param.value.pgno = 1;
  param.value.category = val;
  getTripList();
};

// 여행지 글 갯수 세기
watch(trips, (newValue) => {
  len.value = newValue.length;
});
</script>

<template>
  <div id="search-box">
    <select name="select-type" id="select-type" v-model="param.type">
      <option value="">전체</option>
      <option value="restaurant">식당</option>
      <option value="cafe">카페</option>
      <option value="stay">숙소</option>
      <option value="playground">장소</option>
      <option value="store">상점</option>
    </select>
    <select name="select-order" id="select-order" v-model="param.order">
      <option value="">조회수</option>
      <option value="likeCount">찜</option>
    </select>
    <input
      type="text"
      placeholder="무엇이든 검색해보세요."
      id="input-search"
      v-model="param.word"
    />
    <button id="btn-search-tripinfo" @click="searchTrip">검색</button>
  </div>
  <!-- tab box -->
  <div id="tab-box">
    <ul class="tabnav">
      <li :class="{ active: param.category === '' }" @click="selectCategory('')">전체</li>
      <li :class="{ active: param.category === 'drama' }" @click="selectCategory('drama')">
        드라마
      </li>
      <li :class="{ active: param.category === 'movie' }" @click="selectCategory('movie')">영화</li>
      <li :class="{ active: param.category === 'show' }" @click="selectCategory('show')">예능</li>
      <li :class="{ active: param.category === 'artist' }" @click="selectCategory('artist')">
        아티스트
      </li>
    </ul>
  </div>
  <div v-if="len === 0">
    <VEmptyItem text="검색 결과가 없습니다." />
  </div>
  <div v-else id="content-box">
    <TripListItem
      v-for="trip in trips"
      :key="trip.idx"
      :trip="trip"
      @click-item="clickItem(trip.idx)"
    />
  </div>
  <TripModal :show="show" @close-modal="closeModal" type="trip" :trip="trip" />
  <!-- pagination -->
  <PageNavigation
    :current-page="currentPage"
    :total-page="totalPage"
    @pageChange="onPageChange"
  ></PageNavigation>
</template>

<style scoped>
@import "@/assets/scss/components/trip/tripList.scss";
</style>
