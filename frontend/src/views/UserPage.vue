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
import { getBalance } from "@/api/users";
import { useUser } from '../composables/useUser'

const { user, getUserValues, setBalance } = useUser();

const text = ref('!');

onMounted(() => {
  getUserValues();
});

const handleClick = () => {
  // setBalance(newBalance);
// INSERT ALERT WITH FORM TO CHANGE BALANCE

  console.log('Clicked on Geplante monatliche Ausgaben');
}

</script>

<template>
  <ion-page>
    <ion-content>
      <ion-header>
        <ion-toolbar>
          <ion-title>Profile</ion-title>
        </ion-toolbar>
      </ion-header>
      <ion-grid v-if="user">
        <ion-row>
          <ion-col>
            <ion-item>
              <ion-label>Benutzer:</ion-label>
              <ion-text>{{ user.loginName }}</ion-text>
            </ion-item>
          </ion-col>
        </ion-row>
        <ion-row>
          <ion-col>
            <ion-item @click="handleClick">
              <ion-label>Geplante monatliche Ausgaben:</ion-label>
              <ion-text>{{ user.balance }}</ion-text>
            </ion-item>
          </ion-col>
        </ion-row>
        <!-- Weitere Zeilen für zusätzliche Benutzerinformationen -->
      </ion-grid>
      <p v-else>No user data available</p>
    </ion-content>
  </ion-page>
</template>
  
