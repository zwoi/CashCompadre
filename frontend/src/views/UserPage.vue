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
} from "@ionic/vue";

import { onMounted, ref } from "vue";
import { getUser } from "@/api/users";
import { useUser } from '../composables/useUser'

const { user, getUserValues } = useUser();


const text = ref('!');

onMounted(() => {
  getUserValues();
  });


</script>

<template>
  <ion-page>
    <ion-content>
      <ion-header>
        <ion-toolbar>
          <ion-title>Profile</ion-title>
        </ion-toolbar>
      </ion-header>
      <div v-if="user">

      </div>
      <p v-if="user">Was geht {{ user.loginName }}</p>
      <p v-else>No user data available</p>
    </ion-content>
  </ion-page>
</template>
  
