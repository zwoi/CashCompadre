<script setup lang="ts">

import listitem from "../components/ListItem.vue"
import { useRoute } from 'vue-router';
import { IonPage,IonButton, IonHeader,IonItem,IonLabel, IonTitle, IonContent, IonToolbar, IonFab, IonFabButton, IonList, IonBackButton, IonButtons } from '@ionic/vue';
import { useExpenses} from "@/composables/useExpenses";
import { useCategory } from "@/composables/useCategory";
import { onMounted, ref, watchEffect } from "vue";
const route = useRoute();
console.log(route.params.id);

const categorytitle = route.params.id as string;
const { categories, category, getOneCategory, getCategories } = useCategory();
onMounted(() => {
    getOneCategory(categorytitle);
    
});

let totalAmount = 0;

watchEffect(() => {
    // Entpacke die Ref-Wrap und überprüfe, ob category und category.value.expenses vorhanden sind
    if (category && category.value && category.value.expenses) {
        totalAmount = category.value.expenses.reduce((total, expense) => {
            if (expense.amount !== undefined) {
                return total + expense.amount;
            }
            return total;
        }, 0);
    }
});
</script>

<template>
    <ion-page>
        <ion-content>
            <ion-header>
                <ion-toolbar>
                    <ion-title>{{ categorytitle }}</ion-title>
                </ion-toolbar>
                <ion-list v-if="category">
                    <ion-item v-for="expense in category.expenses" :key="expense.id">
                        <ion-label>{{ expense.note }}</ion-label>
                        <ion-label>{{ expense.amount }}</ion-label>
                    </ion-item>
                    <!-- Anzeigen des Gesamtbetrags als zusätzlicher Eintrag -->
                    <ion-item>
                        <ion-label>Total</ion-label>
                        <ion-label>{{ totalAmount }}</ion-label>
                    </ion-item>
                </ion-list>
            </ion-header>
        </ion-content>
    </ion-page>
</template>

<style scoped></style>