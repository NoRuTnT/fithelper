<template>
    <div class="cards-container">
        
        <div class="card-body">
            <span class="title">나의 등급</span>
            <div class="description">                
                <img src="@/assets/bronze.png" alt="Bronze" width="200" height="200">
                <span class="info-value">bronze</span>
            </div>
            
        <button class="btn-more">상세 보기</button>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
.cards-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
}
.description {
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin: 1rem;
  padding: 1rem;
}

.card-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 1rem;
}

</style>