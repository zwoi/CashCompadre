<script setup lang="ts">
import listitem from "../components/ListItem.vue"
import { useRoute } from 'vue-router';
import { IonPage, IonHeader, IonTitle, IonContent, IonToolbar, IonFab, IonFabButton, IonList } from '@ionic/vue';
const route = useRoute();
console.log(route.params.id);
const Category = route.params.id;
</script>

<template>
    <ion-page>
        <ion-content>

            <ion-header>
                <ion-toolbar>
                    <ion-title>{{ Category }}</ion-title>
                </ion-toolbar>
            </ion-header>
            
            <ion-list>
                <listitem title="Test" link="/categories/food"></listitem>
            </ion-list>
        </ion-content>

    </ion-page>
    <h1></h1>
</template>




<style scoped></style>