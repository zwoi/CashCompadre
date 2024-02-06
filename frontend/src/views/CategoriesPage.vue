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
  IonFabButton

} from "@ionic/vue";
import { useTodos } from "../composables/useTodos";
import { ref } from "vue";
const text = ref('hello');
const { newTodo, todos, getTodos, addTodo, finishTodo, archiveTodo } = useTodos();
import {add} from 'ionicons/icons'; 
</script>

<template>
 <ion-page>
  <ion-content>
    <ion-header>
        <ion-toolbar>
            <ion-title>Categories</ion-title>
        </ion-toolbar>
    </ion-header>
    </ion-content>
</ion-page>
</template>
  
