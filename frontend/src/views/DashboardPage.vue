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
import { useTodos } from "../composables/useTodos";
import { ref } from "vue";
const text = ref('!');
const { newTodo, todos, getTodos, addTodo, finishTodo, archiveTodo } = useTodos();

</script>

<template>
  <ion-page>
  <ion-content>
    <ion-header>
        <ion-toolbar>
            <ion-title>Dashboard</ion-title>
        </ion-toolbar>
    </ion-header>
    <p>dash </p>
    </ion-content>
</ion-page>
</template>
  
