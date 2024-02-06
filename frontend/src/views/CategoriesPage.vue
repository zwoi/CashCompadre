<script setup lang="ts">
import {
  IonPage,
  IonHeader,
  IonToolbar,
  IonTitle,
  IonContent,
  IonCol,
  IonRow,
  IonGrid,
  IonItem,
  IonList,
  IonButton,
  IonInput,
  IonIcon,
  IonFab,
  IonFabButton,
  IonLabel

} from "@ionic/vue";
import { useTodos } from "../composables/useTodos";
import { ref } from "vue";
import { useCategory } from '../composables/useCategory';
const text = ref('hello');
const { categories, getCategories } = useCategory();
import {add} from 'ionicons/icons'; 
const Categories = ref([]);
</script>

<template>
 <ion-page>
  <ion-content>
    <ion-header>
        <ion-toolbar>
            <ion-title>Categories</ion-title>
        </ion-toolbar>
    </ion-header>
    <ion-list>
      
      <ion-item :router-link="'/tabs/categories/test'" button >
        <ion-label>Test</ion-label>
      </ion-item>
      <ion-item v-for="category in categories" :key="category.id">
        <ion-label>{{ category.title }}</ion-label>
      </ion-item>
    </ion-list> 
    </ion-content>
</ion-page>
</template>
  
