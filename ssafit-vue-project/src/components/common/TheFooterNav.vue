<template>
    <div>
        <footer>
            <ul>
                <li><a href="">이용약관</a></li>
                <li><a href="">개인정보처리방침</a></li>
                <li><a href="">운영정책</a></li>
                <li><a href="">고객센터</a></li>
                <li><a href="">공지사항</a></li>
            </ul>
            <p>Copyright &copy; SSAFY. All rights reserved.</p>
        </footer>
    </div>
</template>

<script setup>

</script>

<style scoped>
footer {
    height: 100px;
    background-color: rgb(78, 78, 78);
}
ul{
    display: flex;
    justify-content: center;
}
li{
    list-style: none;
}
a{
    text-decoration: none;
    margin: 10px;
    color: white;
}
p{
    text-align: center;
    color: white;
}
</style>