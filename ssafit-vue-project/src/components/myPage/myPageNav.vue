<template>
    <div>
        <nav>
            <ul>
                <li><RouterLink to="/myPage">메인</RouterLink></li>
                <li><RouterLink to="/myPage/class">클래스</RouterLink></li>
                <li><RouterLink to="/myPage/chatroom">1대1채팅</RouterLink></li>
                <li><RouterLink to="/">미구현입니다.</RouterLink></li>
            </ul>
        </nav>
    </div>
</template>

<script setup>

</script>

<style scoped>
nav{
    height: 100px;
    background-color: pink;
}
ul{
    display: flex;
    justify-content: space-around;
    
}
li{
    list-style: none;
    margin: 10px;
    line-height: 80px;
}
</style>