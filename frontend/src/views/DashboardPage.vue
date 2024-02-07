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
  IonFab,
  IonFabButton,
  IonIcon,
} from "@ionic/vue";

import { ref } from "vue";
import { add } from 'ionicons/icons';
const text = ref('!');
const alertButtons = ['OK','Ich bin ein HS'];
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
            <ion-title>Dashboard</ion-title>
        </ion-toolbar>
    </ion-header>
    <ion-fab slot="fixed" vertical="bottom" horizontal="end"> 
    <ion-fab-button color:primary id="present-alert">
          <ion-icon :icon="add"></ion-icon>
        </ion-fab-button>
      </ion-fab>
      <ion-alert trigger="present-alert" header="Please enter your info" :buttons="alertButtons"
        :inputs="alertInputs"></ion-alert>
    </ion-content>
  
</ion-page>
</template>
  
