<template>
  <div>
    <div class="container">
      <div id="map"></div>
      <div class="gym-list">
        <h2>현재 선택한 매장 예약</h2>
      <ul>
        <li>장소이름 : {{gym.name}}</li>
        <li>설명 : {{ gym.description }}</li>
        <li>운영상태 : {{ gym.status }}</li>
        <li>예약비 : {{ gym.price }}</li>
        <li>예약시간
          <input type="datetime-local" v-model="gym.meetTime" />
        </li>
        <li><button @click="reserveGym">예약하기</button></li>
        
      </ul>
    </div>
    </div>
    <button @click="initMap">내위치</button>
    <button @click="displayMarker(myMarkerPosition)">즐겨찾기 마커 표시</button>
    <button @click="displayMarker([])">즐겨찾기 마커 해제</button>
    <div id="menu_wrap" class="bg_white">
      <div class="option">
        <div>
          키워드 : <input type="text" value="역삼 헬스장" id="keyword" size="15" @keyup.enter="searchPlaces">
          <button @click="searchPlaces">검색하기</button>
          <p id="result"></p>
          <h1>현재위치로부터 선택한 지점사이의 거리: {{ minLength }}m</h1>
        </div>
      </div>
      <hr>
      <ul id="placesList"></ul>
      <div id="pagination"></div>

    </div>
    
  </div>
</template>

<script setup>
import { onMounted, ref, toRaw } from 'vue';
import { useGymStore } from "@/stores/gym";
import { useAuthStore } from '@/stores/auth';
let map = null;
let ps = null; // mount 될 때 등록
let myCenter = null;
let infowindow = null;

const initMap = function () {
  myCenter = new kakao.maps.LatLng(33.450701, 126.570667); //카카오본사
  infowindow = new kakao.maps.InfoWindow({zIndex:1});
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition((position) => {
      const lat = position.coords.latitude;
      const lon = position.coords.longitude;
      myCenter = new kakao.maps.LatLng(lat, lon);
      new kakao.maps.Marker({
        map,
        position: myCenter,
      });
      map.setCenter(myCenter);
    });
  }
  const container = document.getElementById('map');
  const options = {
    center: myCenter,
    level: 3,
  }; // 지도 객체를 등록합니다.
  map = new kakao.maps.Map(container, options);
  // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
  const mapTypeControl = new kakao.maps.MapTypeControl();

  // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
  map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

  // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
  const zoomControl = new kakao.maps.ZoomControl();
  map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

  // 지도에 위치를 표시하는 변수 할당
  ps = new kakao.maps.services.Places();
};

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement('script'); // autoload=false 스크립트를 동적으로 로드하기 위해서 사용
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${import.meta.env.VITE_KAKAO_API_KEY
      }&libraries=services`; 
    script.addEventListener('load', () => {
      kakao.maps.load(initMap);
    }); //헤드태그에 추가
    document.head.appendChild(script);
  }
});

const myMarkerPosition = ref([
  [33.450701, 126.570667],
])

const markers = ref([]);

const displayMarker = function (markerPositions) {
  //마커지우기
  if (markers.value.length > 0) {
    markers.value.forEach((marker) => marker.setMap(null));
  }

  const positions = markerPositions.map(
    (position) => new kakao.maps.LatLng(...position)
  );
  if (positions.length > 0) {
    markers.value = positions.map(
      (position) =>
        new kakao.maps.Marker({
          map: toRaw(map),
          position,
        })
    );

    const bounds = positions.reduce(
      (bounds, latlng) => bounds.extend(latlng),
      new kakao.maps.LatLngBounds()
    );

    toRaw(map).setBounds(bounds);
  }
};



let currlatlng = myCenter;

const minLength = ref(0); // 검색된 위치와 현 위치와의 최단거리를 저장하는 반응형 변수


// 키워드로 장소를 검색합니다
const searchPlaces = function(){
  let keyword = document.getElementById('keyword').value;

  if (!keyword.replace(/^\s+|\s+$/g, '')) {
        alert('키워드를 입력해주세요!');
        return false;
    }
    
    // 현재 보이는 화면 좌표를 가져옴
    kakao.maps.event.addListener(map, 'center_changed', function() {

    // 지도의 중심좌표를 얻어옵니다 
    currlatlng = map.getCenter(); 
});

    // 그 좌표를 기준으로 반경 2km (2000) 근방의 헬스장을 탐색함
    const searchOption = {
      location: currlatlng,
      radius: 2000,
    }
    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
    ps.keywordSearch(keyword, placesSearchCB, searchOption); 
}

// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {

        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        displayPlaces(data);

        // 페이지 번호를 표출합니다
        displayPagination(pagination);

    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

        alert('검색 결과가 존재하지 않습니다.');
        return;

    } else if (status === kakao.maps.services.Status.ERROR) {

        alert('검색 결과 중 오류가 발생했습니다.');
        return;

    }
}

// 검색 결과 목록과 마커를 표출하는 함수입니다
function displayPlaces(places) {

    let listEl = document.getElementById('placesList'), 
    menuEl = document.getElementById('menu_wrap'),
    fragment = document.createDocumentFragment(), 
    bounds = new kakao.maps.LatLngBounds(), 
    listStr = '';
    
    // 검색 결과 목록에 추가된 항목들을 제거합니다
    removeAllChildNods(listEl);

    // 지도에 표시되고 있는 마커를 제거합니다
    removeMarker();
    
    for ( var i=0; i<places.length; i++ ) {      

        // 마커를 생성하고 지도에 표시합니다
        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
            marker = addMarker(placePosition, i), 
            itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);

        // 마커와 검색결과 항목에 mouseover 했을때
        // 해당 장소에 인포윈도우에 장소명을 표시합니다
        // mouseout 했을 때는 인포윈도우를 닫습니다
        (function(marker, title) {
            kakao.maps.event.addListener(marker, 'mouseover', function() {
                displayInfowindow(marker, title);
                /////////////////////////////
            });

            kakao.maps.event.addListener(marker, 'mouseout', function() {
                infowindow.close();
                minLength.value = 0;
            });

            itemEl.onmouseover =  function () {
                displayInfowindow(marker, title);
                ///////////////////////
                
            };

            itemEl.onmouseout =  function () {
                infowindow.close();
                minLength.value = 0;
            };
        })(marker, places[i].place_name);

        fragment.appendChild(itemEl);
    }

    // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
    listEl.appendChild(fragment);
    menuEl.scrollTop = 0;

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    map.setBounds(bounds);
    
    // 검색된 내용 list에 eventListener 추가
    updateGymList();
}

// 검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, places) {

    var el = document.createElement('li'),
    itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
                '<div class="info" style="border:2px solid gray; margin: 5px; border-radius: 8px;" >' +
                '   <h5>' + places.place_name + '</h5>';

    if (places.road_address_name) {
        itemStr += '    <span>' + places.road_address_name + '</span>' +
                    '   <span class="jibun gray">' +  places.address_name  + '</span>';
    } else {
        itemStr += '    <span>' +  places.address_name  + '</span>'; 
    }

      itemStr += '  <span class="tel">' + places.phone  + '</span>' +
                '</div>';           

    el.innerHTML = itemStr;
    el.className = 'item';

    return el;
}

// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, idx, title) {
    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
        imgOptions =  {
            spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
            spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
            marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage 
        });

    marker.setMap(map); // 지도 위에 마커를 표출합니다
    markers.value.push(marker);  // 배열에 생성된 마커를 추가합니다

    return marker;
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
  if (markers.value.length > 0) {
    markers.value.forEach((marker) => marker.setMap(null));
  }
}

// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
function displayPagination(pagination) {
    var paginationEl = document.getElementById('pagination'),
        fragment = document.createDocumentFragment(),
        i; 
    // 기존에 추가된 페이지번호를 삭제합니다
    while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild (paginationEl.lastChild);
    }

    for (i=1; i<=pagination.last; i++) {
        var el = document.createElement('a');
        el.href = "#";
        el.innerHTML = i;
        el.style.cssText  = 'margin: 10px; text-decoration: none';
        if (i===pagination.current) {
            el.className = 'on';
        } else {
            el.onclick = (function(i) {
                return function() {
                    pagination.gotoPage(i);
                }
            })(i);
        }

        fragment.appendChild(el);
    }
    paginationEl.appendChild(fragment);
}

// 아래는 최단 거리를 구할 때 사용하는 함수
// 하버사인 공식으로 현 위치와 마커된 헬스장 위치의 최단 거리를 계산합니다.
    // 1. toRadian 생성 degree -> radian
    function toRadian(x){
      return x*Math.PI/180
    }
// 2. 하버사인 공식으로 계산한 결과 반환
    function getMinLength(lat1, lng1, lat2, lng2){
      return 6371*Math.acos(Math.cos(toRadian(lat2))*Math.cos(toRadian(lat1))*Math.cos(toRadian(lng1)-toRadian(lng2))
      +Math.sin(toRadian(lat2))*Math.sin(toRadian(lat1)))
    }



// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title) {
    var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

    let posDst = marker.getPosition(); // LatLng
    minLength.value = (getMinLength(currlatlng.getLat(), currlatlng.getLng(), posDst.getLat(), posDst.getLng())*1000).toFixed(2); // 미터 단위로



    infowindow.setContent(content);
    infowindow.open(map, marker);
}

 // 검색결과 목록의 자식 Element를 제거하는 함수입니다
function removeAllChildNods(el) {   
    while (el.hasChildNodes()) {
        el.removeChild (el.lastChild);
    }
}

//////////////////////////////////////////////


const authStore = useAuthStore();
const gymStore = useGymStore();

// 선택된 매장에 대한 정보
// 프론트에 나타난 list내용을 클릭하면 DB에 저장된다.
const gym = ref({
  gymId: '',
  userId: '',
  name: '', 
  category: '',
  status: '',
  price: '',
  description: '',
  reserveTime: '',
  meetTime: ''
})

const reserveGym = async () => {
    try {
        authStore.updateUserIdFromToken(); // 사용자 ID 업데이트
        gym.value.userId = authStore.userId; // 예약자 ID 설정
        await gymStore.createGym(gym.value); // 예약 시도
        alert('저장되었습니다.');
    } catch (error) {
        if (error.response && error.response.status === 401) {
            // 401 Unauthorized 에러 처리
            router.push('/'); // 홈 페이지로 리디렉션
        } else {
            alert('예약 중 에러가 발생했습니다.'); // 기타 에러 처리
        }
    }
};
// 아래부터 db에 gym 관련 내용 추가하고 reserve하는 부분
// 새로운 검색을 했을 때마다 아래 내용을 실행해야 됨
function updateGymList(){
  let gymList = document.getElementsByClassName('info');
  console.log(gymList);
  for(let i=0 ; i<gymList.length ; i++){
    gymList[i].addEventListener('click', function(){
      gym.value.name = gymList[i].children[0].innerText;
      gym.value.description = gymList[i].children[1].innerText;
      // data
      gym.value.category = '전신';
      gym.value.status = '영업중';
      gym.value.price = 300000;
    })
  }

}



</script>

<style scoped>
.container {
  display: flex;
  justify-content: space-between;
  align-items: start;
  margin: 0 auto;
  padding: 20px;
}
#map {
  width: 500px;
  height: 400px;
  flex: 1; 
}
.gym-list {
  flex: 1;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
}
.gym-list h2 {
  margin-bottom: 20px;
  color: #333;
}

.gym-list ul {
  list-style-type: none;
  padding: 0;
}

.gym-list li {
  margin-bottom: 10px;
  line-height: 1.6;
}

button {
  padding: 5px 10px;
  background-color: #8e8e8e;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin: 0 5px; /* 양쪽 마진 추가 */
}

button:hover {
  background-color: #494949;
}
#placesList {
    list-style: none; /* 기본 리스트 스타일 제거 */
    padding: 0;
    margin: 20px 0;
}

#placesList li {
    padding: 10px;
    border-bottom: 1px solid #9b9b9b; /* 항목 사이의 연한 구분선 */
}
#pagination {
    text-align: center; /* 페이지네이션 버튼을 중앙 정렬 */
    margin: 20px 0;
}

#pagination  {
    display: inline-block;
    margin: 0 5px; /* 버튼 사이의 간격 */
    padding: 5px 10px;
    border: 1px solid #ddd; /* 테두리 */
    background-color: #f9f9f9; /* 배경색 */
    color: #333; /* 글자색 */
    text-decoration: none; /* 밑줄 제거 */
    border-radius: 5px; /* 모서리 둥글게 */
}

#pagination a:hover {
    background-color: #e9e9e9; /* 마우스 오버 시 배경색 변경 */
}


</style>
