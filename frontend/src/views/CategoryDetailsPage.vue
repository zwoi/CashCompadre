<script setup lang="ts">
import listitem from "../components/ListItem.vue"
import { useRoute } from 'vue-router';
import { IonPage, IonHeader,IonItem,IonLabel, IonTitle, IonContent, IonToolbar, IonFab, IonFabButton, IonList, IonBackButton, IonButtons } from '@ionic/vue';
import { useExpenses } from "@/composables/useExpenses";
import { useCategory } from "@/composables/useCategory";
import { onMounted } from "vue";
const route = useRoute();
console.log(route.params.id);
const {expenses} = useExpenses();
const categorytitle = route.params.id as string;
const { categories, category, getOneCategory, getCategories } = useCategory();
onMounted(() => {
    getOneCategory(categorytitle);
    
});
</script>

<template>
    <ion-page>
        <ion-content>

            <ion-header>
                <ion-toolbar>
                    <ion-buttons slot="start">
                        <ion-back-button> </ion-back-button>
                    </ion-buttons>
                    <ion-title>{{ categorytitle }}</ion-title>
                </ion-toolbar>
                <ion-list v-if="category">
                    <ion-item v-for="expense in category.expenses" :key="category.id" ion-item>
                        <ion-label>{{ expense.note }}</ion-label>
                    </ion-item>
                </ion-list>
            </ion-header>


        </ion-content>

    </ion-page>
</template>




<style scoped></style>