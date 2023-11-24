<template>
    <div class="cards-container">
        
        <div class="card-body">
            <span class="title">참여중인 수업</span>
            <div class="description" v-for="classItem in store.classList" :key="classItem.classId  ">                
              <div class="class-card">
                    <h3>213{{classItem.name}}</h3>
                    <p>{{ classItem.category }}</p>                    
                </div>  

            </div>         
        
        </div>
    </div>
</template>

<script setup>
import { onMounted } from "vue";
import { useClassStore} from '@/stores/class';
import { useRouter } from 'vue-router';
import { useAuthStore } from '@/stores/auth';

const authStore = useAuthStore();
const router = useRouter();
const store = useClassStore();

authStore.updateUserIdFromToken();
onMounted(() => {    
  // authStore.updateUserIdFromToken();
  store.usergetClassList(authStore.userId)
  console.log(store.value.classList);
})

const goToClassDetail = (classId) => {
    router.push({ name: 'ClassNameDetailView', params: { classId } });
};

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