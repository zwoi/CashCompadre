<script setup lang="ts">
import listitem from "../components/ListItem.vue"
import { useRoute } from 'vue-router';
import { IonPage, IonHeader, IonTitle, IonContent, IonToolbar, IonFab, IonFabButton, IonList, IonBackButton, IonButtons } from '@ionic/vue';
const route = useRoute();
console.log(route.params.id);
const category = route.params.id;
</script>

<template>
    <ion-page>
        <ion-content>

            <ion-header>
                <ion-toolbar>
                    <ion-buttons slot="start">
                        <ion-back-button> </ion-back-button>
                    </ion-buttons>
                    <ion-title>{{ category }}</ion-title>
                </ion-toolbar>
            </ion-header>


        </ion-content>

    </ion-page>
</template>




<style scoped></style>