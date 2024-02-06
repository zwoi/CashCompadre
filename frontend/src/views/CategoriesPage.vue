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
  IonLabel,
  IonAlert

} from "@ionic/vue";
import { useTodos } from "../composables/useTodos";
import { ref } from "vue";

import { useCategory } from '../composables/useCategory';
const text = ref('hello');
const { categories, getCategories } = useCategory();
import { add } from 'ionicons/icons';
const Categories = ref([]);

const alertButtons = ['OK'];
const alertInputs = [
  {
    placeholder: 'Name',
  },
  {
    placeholder: 'Category Name',
    attributes: {
      maxlength: 8,
    },
  },
  {
    type: 'number',
    placeholder: 'Limit',
    min: 1,
    max: 100,
  },
  {
    type: 'number',
    placeholder: 'percentage',
  },
];
</script>

<template>
  <ion-page>
    <ion-content>
      <ion-header>
        <ion-toolbar>
          <ion-title>Categories</ion-title>
        </ion-toolbar>
      </ion-header>
      <ion-button v-on:click="getCategories"></ion-button>
      <ion-list>

        <ion-item :router-link="'/tabs/categories/test'" button>
          <ion-label>Test</ion-label>
        </ion-item>
        <ion-item v-for="category in categories" :key="category.id">
          <ion-label>{{ category.title }}</ion-label>
        </ion-item>
      </ion-list>
      <ion-fab slot="fixed" vertical="bottom" horizontal="end">

        <ion-fab-button color:primary id="present-alert">
          <ion-icon :icon="add"></ion-icon>
        </ion-fab-button>
      </ion-fab>
      <ion-alert
    trigger="present-alert"
    header="Please enter your info"
    :buttons="alertButtons"
    :inputs="alertInputs"
  ></ion-alert>
    </ion-content>
  </ion-page>
</template>
<style scoped>
ion-list {}
</style>
  
