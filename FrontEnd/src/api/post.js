import { localAxios } from "@/util/http-commons";

const local = localAxios();

// 여행 후기 등록
const registPost = (param, success, fail) => {
  local.post(`/post/regist`, JSON.stringify(param)).then(success).catch(fail);
};

// 여행 후기 글 목록
const listPost = (param, success, fail) => {
  local.get(`/post/list`, { params: param }).then(success).catch(fail);
};

// 여행 후기 글 상세 조회
const detailPost = (idx, success, fail) => {
  local.get(`/post/view/${idx}`).then(success).catch(fail);
};

// 여행 후기 글 수정
const modifyPost = (param, success, fail) => {
  local.put(`/post/modify`, JSON.stringify(param)).then(success).catch(fail);
};

// 여행 후기 글 삭제
const deletePost = (idx, success, fail) => {
  local.delete(`/post/delete/${idx}`).then(success).catch(fail);
};

// 여행 후기 글에 속한 아이템 목록
const listPostItem = (postIdx, success, fail) => {
  local.get(`/post/list/${postIdx}`).then(success).catch(fail);
};

// 여행 후기 댓글 목록 (게시글별)
const listPostComment = (postIdx, success, fail) => {
  local.get(`/post/postComment/list/post/${postIdx}`).then(success).catch(fail);
};

// 여행 후기 댓글 목록 (사용자별)
const listPostCommentByUseridx = (userIdx, success, fail) => {
  local.get(`/post/postComment/list/user/${userIdx}`).then(success).catch(fail);
};

// 여행 후기 댓글 등록
const registPostComment = (postComment, success, fail) => {
  local
    .post(`/post/postComment/regist`, JSON.stringify(postComment))
    .then(success)
    .catch(fail);
};

// 여행 후기 댓글 삭제
const deletePostComment = (postIdx, success, fail) => {
  local.delete(`/post/postComment/delete/${postIdx}`).then(success).catch(fail);
};

// post idx로 여행 후기 댓글 가져오기
const viewPostComment = (postIdx, success, fail) => {
  local.get(`/post/postComment/view/${postIdx}`).then(success).catch(fail);
};

export {
  registPost,
  listPost,
  detailPost,
  modifyPost,
  deletePost,
  listPostItem,
  listPostComment,
  listPostCommentByUseridx,
  registPostComment,
  deletePostComment,
  viewPostComment,
};
