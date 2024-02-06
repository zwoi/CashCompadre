<script setup lang="ts">

import { useRoute } from 'vue-router';
import { IonPage, IonHeader, IonTitle, IonContent, IonToolbar } from '@ionic/vue';
const route = useRoute();
console.log(route.params.id);
const Category="123"
</script>

<template>
    <ion-page>
  <ion-content>
    <ion-header>
        <ion-toolbar>
            <ion-title>{{ Category }}</ion-title>
        </ion-toolbar>
    </ion-header>
    </ion-content>
    <p>cat</p>
</ion-page>
    <h1></h1>
</template>




<style scoped></style>