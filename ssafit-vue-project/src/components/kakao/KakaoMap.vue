<template>
    <div>
        <div id="map"></div>
        <button @click="initMap">내위치</button>
        <button @click="changeSize(0)">Hide</button>
        <button @click="changeSize(400)">show</button>
        <button @click="displayMarker(markerPositions1)">marker set 1</button>
        <button @click="displayMarker(markerPositions2)">marker set 2</button>
        <button @click="displayMarker([])">marker set 3 (empty)</button>
        <button @click="displayInfoWindow">infowindow</button>
    </div>
</template>

<script setup >
import { ref, onMounted } from 'vue';
const map = ref();
const markers = ref([]);
const infowindow = ref();
const markerPositions1 = [
    [33.452278, 126.567803],
    [33.452671, 126.574792],
    [33.451744, 126.572441],
];

const markerPositions2 = [
    [37.499590490909185, 127.0263723554437],
    [37.499427948430814, 127.02794423197847],
    [37.498553760499505, 127.02882598822454],
    [37.497625593121384, 127.02935713582038],
    [37.49629291770947, 127.02587362608637],
    [37.49754540521486, 127.02546694890695],
    [37.49646391248451, 127.02675574250912],
];

const initMap = function () {
    let myCenter = new kakao.maps.LatLng(33.450701, 126.570667);
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition((position) => {
            const lat = position.coords.latitude;
            const lon = position.coords.longitude;
            myCenter = new kakao.maps.LatLng(lat, lon);
            new kakao.maps.Marker({
                map: map.value,
                position: myCenter,
            });
            map.value.setCenter(myCenter);
        });
    }

    const container = document.getElementById('map');
    const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3,
    };
    map.value = new kakao.maps.Map(container, options);
    var mapTypeControl = new kakao.maps.MapTypeControl();
    map.value.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
    var zoomControl = new kakao.maps.ZoomControl();
    map.value.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
};





const getBounds = function () {
    let center = map.getCenter();
    let bounds = map.getBounds();
    let sw = bounds.getSouthWest();
    let ne = bounds.getNorthEast();
    let swLat = sw.getLat();
    let swLng = sw.getLng();
    let neLat = ne.getLat();
    let neLng = ne.getLng();
    let centerLat = center.getLat();
    let centerLng = center.getLng();
    let position = [swLat, swLng, neLat, neLng, centerLat, centerLng];
    return position;
};
const getData = function () {

    let position = getBounds();
    fetch('/api/user/hospital/map?position=' + position)
        .then(response => response.json())
        .then(data => {

            for (let i = 0; i < data.length; i++) {
                // 지도에 마커를 생성하고 표시한다.
                let marker = new kakao.maps.Marker({
                    position: new kakao.maps.LatLng(data[i].latitude, data[i].longitude), // 마커의 좌표
                    title: data.address_name,
                    map: map // 마커를 표시할 지도 객체
                });
                // 인포윈도우를 생성하고 지도에 표시합니다.
                let infowindow = new kakao.maps.InfoWindow({
                    content: "<div class='d-flex flex-column m-3'><h6 class='text'>"
                        + data[i].place_name + "</h6>" +
                        "<label class='text'>주소 : "
                        + data[i].address_name + "</label>" +
                        "<div className='d-flex'>" +
                        "<a class='btn btn-sm btn-primary' href='" + data[i].place_url + "'>" +
                        "자세히</a>" +
                        "<a class='btn btn-sm btn-secondary ms-1' href=tel:'" + data[i].phone + "'>" +
                        "전화</a>" +
                        "</div></div>",
                    removable: true
                });

                // 마커에 이벤트를 등록하는 함수 만들고 즉시 호출하여 클로저를 만듭니다
                // 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
                (function (marker, infowindow) {
                    // 마커에 mouseover 이벤트를 등록하고 마우스 오버 시 인포윈도우를 표시합니다
                    kakao.maps.event.addListener(marker, 'click', function () {
                        infowindow.open(map, marker);
                    });

                    // 마커에 mouseout 이벤트를 등록하고 마우스 아웃 시 인포윈도우를 닫습니다
                    /*
                    kakao.maps.event.addListener(marker, 'mouseout', function() {
                      infowindow.close();
                    });
                     */
                })(marker, infowindow);

                // 생성된 마커를 마커 저장하는 변수에 넣음
                markers.push(marker);
            }
            clusterer.addMarkers(markers);
        });
}



// Deletes all markers in the array by removing references to them.
const deleteMarkers = function () {
    for (let idx = 0; idx < markers.length; idx++) {
        markers[idx].setMap(null);
    }
    markers = [];
}

const deleteClusterMarkers = function () {
    for (let sidx = 0; sidx < markers.length; sidx++) {
        clusterer.removeMarker(markers[sidx]);
    }
    clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 9 // 클러스터 할 최소 지도 레벨
    });
}



const changeSize = (size) => {
    const container = document.getElementById('map');
    container.style.width = `${size}px`;
    container.style.height = `${size}px`;
    map.value.relayout();
};

const displayMarker = (markerPositions) => {
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
                    map: map.value,
                    position,
                })
        );

        const bounds = positions.reduce(
            (bounds, latlng) => bounds.extend(latlng),
            new kakao.maps.LatLngBounds()
        );

        map.value.setBounds(bounds);
    }
};

const displayInfoWindow = () => {
    if (infowindow.value && infowindow.value.getMap()) {
        map.value.setCenter(infowindow.value.getPosition());
        return;
    }

    const iwContent = '<div style="padding:5px;">Hello World!</div>';
    const iwPosition = new kakao.maps.LatLng(33.450701, 126.570667);
    const iwRemoveable = true;

    infowindow.value = new kakao.maps.InfoWindow({
        map: map.value,
        position: iwPosition,
        content: iwContent,
        removable: iwRemoveable,
    });

    map.value.setCenter(iwPosition);
};

onMounted(() => {
    if (window.kakao && window.kakao.maps) {
        initMap();
        displayMarker(markerPositions1);
        displayInfoWindow();
        getData();
    } else {
        const script = document.createElement('script');
        script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${import.meta.env.VITE_KAKAO_API_KEY
            }`;
        script.addEventListener('load', () => {
            kakao.maps.load(() => {
                initMap();
                displayMarker(markerPositions1);
                displayInfoWindow();
                getData();
            });
        });
        document.head.appendChild(script);
    }
});
</script>


<style scoped>
#map {
    width: 500px;
    height: 400px;
}
</style>